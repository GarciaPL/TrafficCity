package pl.garciapl.trafficcity.mongodb.interfaces;


import pl.garciapl.trafficcity.exception.NoUserFoundException;
import pl.garciapl.trafficcity.exception.UserExistsException;
import pl.garciapl.trafficcity.mongodb.model.Marker;
import pl.garciapl.trafficcity.mongodb.model.User;
import pl.garciapl.trafficcity.mongodb.request.SingleMarker;

import java.util.List;

/**
 * Created by lukasz on 08.12.14.
 */
public interface IUser {
    List<User> getAllUsers();

    void addUser(User user) throws UserExistsException;

    void addMarker(Marker singleMarker);

    User getUserByLogin(String login) throws NoUserFoundException;

    void addUserMarkers(String login, List<SingleMarker> userMarkers) throws NoUserFoundException;

    int clearUserMarkers(String login) throws NoUserFoundException;

    List<SingleMarker> getUserMarkers(String login) throws NoUserFoundException;

    boolean checkUserExists(String login);
}
