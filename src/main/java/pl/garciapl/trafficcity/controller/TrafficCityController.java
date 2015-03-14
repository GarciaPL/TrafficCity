package pl.garciapl.trafficcity.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.garciapl.trafficcity.exception.NoUserFoundException;
import pl.garciapl.trafficcity.exception.UserExistsException;
import pl.garciapl.trafficcity.mongodb.interfaces.IUser;
import pl.garciapl.trafficcity.mongodb.model.User;
import pl.garciapl.trafficcity.mongodb.request.SingleMarker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukasz on 30.11.14.
 */
@Controller
@RequestMapping("/mongoapi")
public class TrafficCityController {

    private IUser user;
    private Gson gson = new GsonBuilder().serializeNulls().create();

    @RequestMapping(value = "/getusermarkers/{login}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    String getUserMarkers(@PathVariable String login) {
        try {
            List<SingleMarker> userMarkers = user.getUserMarkers(login);
            return gson.toJson(userMarkers);
        } catch (NoUserFoundException e) {
            return gson.toJson(e.getMessage());
        }
    }

    @RequestMapping(value = "/clearusermarkers/{login}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    String clearUserMarkers(@PathVariable String login) {
        try {
            int i = user.clearUserMarkers(login);
            return gson.toJson("User markers (" + i + ") was successfully deleted");
        } catch (NoUserFoundException e) {
            return gson.toJson(e.getMessage());
        }
    }

    @RequestMapping(value = "/addusermarker/{login}", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    String addUserMarker(@PathVariable String login, @RequestBody String marker) {
        SingleMarker singleMarker = gson.fromJson(marker, SingleMarker.class);
        if (singleMarker.getTransportType() != null && singleMarker.getLongitude() != null
                && singleMarker.getLatitude() != null) {

            List<SingleMarker> markerList = new ArrayList<>();
            markerList.add(singleMarker);
            try {
                user.addUserMarkers(login, markerList);
                return gson.toJson("Marker was successfully added to " + login);
            } catch (NoUserFoundException e) {
                return gson.toJson(e.getMessage());
            }
        } else {
            return gson.toJson("Wrong RequestBody");
        }
    }

    @RequestMapping(value = "/addusermarkers/{login}", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    String addUserMarkers(@PathVariable String login, @RequestBody String markers) {
        List<SingleMarker> markerList;
        try {
            markerList = gson.fromJson(markers, new TypeToken<List<SingleMarker>>() {
            }.getType());
            if (markerList.size() > 0) {
                user.addUserMarkers(login, markerList);
                return gson.toJson("Markers were successfully added to " + login);
            } else {
                return gson.toJson("Empty markers list");
            }
        } catch (NoUserFoundException e) {
            return gson.toJson(e.getMessage());
        } catch (Exception e) {
            return gson.toJson("Exception during adding markers : " + e.getMessage());
        }
    }

    @RequestMapping(value = "/getuserbylogin/{login}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    String getUserByLogin(@PathVariable String login) {
        try {
            return gson.toJson(user.getUserByLogin(login), User.class);
        } catch (NoUserFoundException e) {
            return gson.toJson(e.getMessage());
        }
    }

    @RequestMapping(value = "/getallusers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    String getAllUsers() {
        List<User> allUsers = user.getAllUsers();
        return gson.toJson(allUsers);
    }

    @RequestMapping(value = "/adduser/{login}", method = RequestMethod.GET)
    public
    @ResponseBody
    String addUser(@PathVariable String login) {
        try {
            user.addUser(new User(login));
            return gson.toJson("New user with login " + login + " was created");
        } catch (UserExistsException e) {
            return gson.toJson(e.getMessage());
        }
    }

    @Autowired
    public void setUser(IUser user) {
        this.user = user;
    }
}
