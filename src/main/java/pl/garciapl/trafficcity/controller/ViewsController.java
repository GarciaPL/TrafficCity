package pl.garciapl.trafficcity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import pl.garciapl.trafficcity.api.Streets4MPIApi;
import pl.garciapl.trafficcity.controller.json.ClientLoginJSON;
import pl.garciapl.trafficcity.controller.json.ClientRegisterJSON;
import pl.garciapl.trafficcity.controller.json.ClientRequest;
import pl.garciapl.trafficcity.controller.json.ResultResponse;
import pl.garciapl.trafficcity.dao.config.ConfigResponse;
import pl.garciapl.trafficcity.exception.NoUserFoundException;
import pl.garciapl.trafficcity.exception.UserExistsException;
import pl.garciapl.trafficcity.mongodb.interfaces.ILayer;
import pl.garciapl.trafficcity.mongodb.interfaces.ILog;
import pl.garciapl.trafficcity.mongodb.interfaces.IUser;
import pl.garciapl.trafficcity.mongodb.model.Log;
import pl.garciapl.trafficcity.mongodb.model.User;
import pl.garciapl.trafficcity.mongodb.request.SingleMarker;
import pl.garciapl.trafficcity.utils.Streets4MPIChecker;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: lciesluk
 * Date: 18.12.14
 * Time: 14:20
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ViewsController {

    private ILayer layer;
    private IUser user;
    private ILog log;
    private Streets4MPIApi streets4MPIApi;
    private MongoTemplate mongoTemplate;

    @Resource(name = "properties")
    private Properties properties;

    private final String USER_SESSION = "user_login";

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexView(ModelMap model) {

        try {
            mongoTemplate.getCollectionNames();
        } catch (Exception e) {
            model.addAttribute("mongo_host", properties.getProperty("mongo_host"));
            model.addAttribute("mongo_port", properties.getProperty("mongo_port"));
            return "db_error";
        }

        try {
            Path streets4mpi_path = Paths.get(properties.getProperty("streets4mpi_path"));
            if (Files.notExists(streets4mpi_path) || !Files.isDirectory(streets4mpi_path) || !Files.isReadable(streets4mpi_path) ||
                    !Files.isWritable(streets4mpi_path)) {
                model.addAttribute("streets4mpi_path", properties.getProperty("streets4mpi_path"));
                return "streets_error";
            }
        } catch (Exception e) {
            model.addAttribute("streets4mpi_path", properties.getProperty("streets4mpi_path"));
            return "streets_error";
        }

        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginViewGet(ModelMap model) {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public
    @ResponseBody
    ClientLoginJSON loginViewPost(@ModelAttribute(value = "client") ClientRequest client,
                                  SessionStatus status, HttpSession session, BindingResult result) {

        ClientLoginJSON clientLogin = new ClientLoginJSON();
        try {
            User userByLogin = user.getUserByLogin(client.getPhoneNumber());
            clientLogin.setResult(ResultResponse.SUCCESS.getResult());
            session.setAttribute(USER_SESSION, userByLogin.getLogin());
        } catch (NoUserFoundException e) {
            clientLogin.setResult(ResultResponse.FAIL.getResult());
            clientLogin.setMessage("User does not exist in system. Please Sign up");
        }

        return clientLogin;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerViewGet(ModelMap model) {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public
    @ResponseBody
    ClientRegisterJSON registerViewPost(@ModelAttribute(value = "client") ClientRequest client,
                                        SessionStatus status, HttpSession session, BindingResult result) {

        ClientRegisterJSON clientRegisterJSON = new ClientRegisterJSON();
        if (user.checkUserExists(client.getPhoneNumber())) {
            clientRegisterJSON.setResult("FAIL");
            clientRegisterJSON.setMessage("Login is already taken");
        } else {
            try {
                user.addUser(new User(client.getPhoneNumber()));
            } catch (UserExistsException e) {
                clientRegisterJSON.setResult("FAIL");
                clientRegisterJSON.setMessage("Login is already taken");
                return clientRegisterJSON;
            }
            clientRegisterJSON.setResult("SUCCESS");
            session.setAttribute(USER_SESSION, client.getPhoneNumber());
        }

        return clientRegisterJSON;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboardViewGet(ModelMap model, SessionStatus status, HttpSession session) {

        if (!checkLogin(session)) {
            return "login";
        }

        return "dashboard";
    }

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public String projectsView(ModelMap model, SessionStatus status, HttpSession session) {

        if (!checkLogin(session)) {
            return "login";
        }

        List<String> street4MPIProjects = layer.getStreet4MPIProjects();
        model.addAttribute("heatmapprojects", street4MPIProjects);

        return "projects";
    }

    @RequestMapping(value = "/generate/gallery/{project}", method = RequestMethod.GET)
    public String generateGalleryViewGet(@PathVariable String project, ModelMap model,
                                         SessionStatus status, HttpSession session) {

        if (!checkLogin(session)) {
            return "login";
        }

        if (project != null) {
            streets4MPIApi.generateHeadMaps(project);
        }

        return "dashboard";
    }

    @RequestMapping(value = "/gallery/{project}", method = RequestMethod.GET)
    public String galleryView(@PathVariable String project, ModelMap model,
                              SessionStatus status, HttpSession session) {

        if (!checkLogin(session)) {
            return "login";
        }

        List<String> streets4mpi_path = Streets4MPIChecker.getHeatMaps(properties.getProperty("streets4mpi_path"), project);

        model.addAttribute("project", project);
        model.addAttribute("heatmaps", streets4mpi_path);

        return "gallery";
    }

    @RequestMapping(value = "/user_markers", method = RequestMethod.GET)
    public String userMarkersViewGet(Model model, SessionStatus status, HttpSession session) {

        if (!checkLogin(session)) {
            return "login";
        }

        try {
            List<SingleMarker> userMarkers = user.getUserMarkers((String) session.getAttribute(USER_SESSION));
            model.addAttribute("markersList", userMarkers);

            Double centerLongitude = (userMarkers.size() > 0) ? 0.00 : 52.229676;
            Double centerLatitude = (userMarkers.size() > 0) ? 0.00 : 21.012229;
            for (SingleMarker singleMarker : userMarkers) {
                centerLongitude += singleMarker.getLongitude();
                centerLatitude += singleMarker.getLatitude();
            }
            if (userMarkers.size() > 0) {
                centerLongitude /= (double) userMarkers.size();
                centerLatitude /= (double) userMarkers.size();
            }

            model.addAttribute("centerLongitude", centerLongitude);
            model.addAttribute("centerLatitude", centerLatitude);

        } catch (NoUserFoundException e) {
            return "dashboard";
        }

        return "user_markers";
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String settingsViewGet(ModelMap model) {

        List<ConfigResponse> configList = new ArrayList<>();
        Iterator<String> iterator = properties.stringPropertyNames().iterator();
        while (iterator.hasNext()) {
            String property = iterator.next();
            configList.add(new ConfigResponse(property, properties.getProperty(property)));
        }

        model.addAttribute("settings", configList);

        return "settings";
    }

    @RequestMapping(value = "/logs", method = RequestMethod.GET)
    public String logViewGet(ModelMap model) {

        List<Log> logs = log.readLogs();
        model.addAttribute("logs", logs);

        return "logs";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String errorViewGet(ModelMap model) {

        return "404";
    }

    private boolean checkLogin(HttpSession session) {
        String user_login = (String) session.getAttribute(USER_SESSION);
        if (user_login == null) {
            return false;
        } else {
            return true;
        }
    }

    @Autowired
    public void setLog(ILog log) {
        this.log = log;
    }

    @Autowired
    public void setLayer(ILayer layer) {
        this.layer = layer;
    }

    @Autowired
    public void setUser(IUser user) {
        this.user = user;
    }

    @Autowired
    public void setStreets4MPIApi(@Qualifier("streets4mpiapi") Streets4MPIApi streets4MPIApi) {
        this.streets4MPIApi = streets4MPIApi;
    }

    @Autowired
    public void setMongoTemplate(@Qualifier("mongoTemplate") MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}
