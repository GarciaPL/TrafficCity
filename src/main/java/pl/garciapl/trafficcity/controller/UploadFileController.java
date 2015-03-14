package pl.garciapl.trafficcity.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.garciapl.trafficcity.api.Streets4MPIApi;
import pl.garciapl.trafficcity.mongodb.interfaces.ILog;
import pl.garciapl.trafficcity.utils.Streets4MPIChecker;

import javax.annotation.Resource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: lciesluk
 * Date: 12.12.14
 * Time: 10:09
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class UploadFileController {

    @Resource(name = "properties")
    private Properties osmPath;

    private Streets4MPIApi streets4MPIApi;
    private ILog log;

    @RequestMapping(value = "/uploadfile", method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        return "upload_osm";
    }

    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    public String uploadFileHandler(Model model, @RequestParam("file") final MultipartFile file, @RequestParam("name") final String name) {

        if (name == null || name.length() == 0 || file == null) {
            model.addAttribute("uploadMessage", "File or name are not set");
            log.saveLog("File or name are not set while uploading");
            return "upload_osm";
        }

        if (!file.isEmpty()) {
            try {
                String extension = FilenameUtils.getExtension(file.getOriginalFilename());
                if (!extension.equals("osm")) {
                    model.addAttribute("uploadMessage", "File " + name + " does not have osm extension");
                    log.saveLog("File " + name + " does not have osm extension");
                    return "upload_osm";
                }

                byte[] bytes = file.getBytes();
                boolean checkStreets = Streets4MPIChecker.checkStreets4MPIProjectExist(osmPath.getProperty("streets4mpi_path") +
                        (osmPath.getProperty("streets4mpi_path").endsWith("/") ? "" : "/") + "osm", name);

                if (checkStreets) {
                    File dir = new File(osmPath.getProperty("streets4mpi_path") +
                            (osmPath.getProperty("streets4mpi_path").endsWith("/") ? "" : "/")
                            + "osm" + "/" + name);

                    dir.mkdir();

                    if (dir.exists()) {
                        File serverFile = new File(osmPath.getProperty("streets4mpi_path") +
                                (osmPath.getProperty("streets4mpi_path").endsWith("/") ? "" : "/")
                                + "osm/"
                                + name + "/"
                                + file.getOriginalFilename());

                        BufferedOutputStream stream = new BufferedOutputStream(
                                new FileOutputStream(serverFile));
                        stream.write(bytes);
                        stream.close();

                        final String fileNameWithOutExt = FilenameUtils.removeExtension(file.getOriginalFilename());
                        Thread thread = new Thread() {
                            public void run() {
                                streets4MPIApi.generateS4MPIFiles(fileNameWithOutExt, name);
                            }
                        };
                        thread.start();

                        model.addAttribute("uploadMessage", "File " + file.getOriginalFilename() + " was successfully saved in project named " + name +
                                ". Now system is generating Streets4MPI files");
                        log.saveLog("File " + file.getOriginalFilename() + " was successfully saved in project named " + name +
                                ". Now system is generating Streets4MPI files");
                        return "upload_osm";
                    } else {
                        model.addAttribute("uploadMessage", "Directory " + dir.toString() + " does not exists on server");
                        log.saveLog("Directory " + dir.toString() + " does not exists on server");
                        return "upload_osm";
                    }
                } else {
                    model.addAttribute("uploadMessage", "Directory " + osmPath.getProperty("streets4mpi_path") +
                            (osmPath.getProperty("streets4mpi_path").endsWith("/") ? "" : "/") + "osm/"
                            + " does not exists on server or files already existed in project directory called " + name);
                    log.saveLog("Directory " + osmPath.getProperty("streets4mpi_path") +
                            (osmPath.getProperty("streets4mpi_path").endsWith("/") ? "" : "/") + "osm/"
                            + " does not exists on server or files already existed in project directory called " + name);
                    return "upload_osm";
                }
            } catch (Exception e) {
                model.addAttribute("uploadMessage", "UploadFile Exception : " + e.getMessage());
                log.saveLog("UploadFile Exception : " + e.getMessage());
                return "upload_osm";
            }
        } else {
            model.addAttribute("uploadMessage", "File " + file.getOriginalFilename() + " is empty");
            log.saveLog("File " + file.getOriginalFilename() + " is empty");
            return "upload_osm";
        }
    }

    @Autowired
    public void setLog(ILog log) {
        this.log = log;
    }

    @Autowired
    public void setStreets4MPIApi(@Qualifier("streets4mpiapi") Streets4MPIApi streets4MPIApi) {
        this.streets4MPIApi = streets4MPIApi;
    }
}
