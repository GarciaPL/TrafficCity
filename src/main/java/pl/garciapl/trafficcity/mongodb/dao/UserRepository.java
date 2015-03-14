package pl.garciapl.trafficcity.mongodb.dao;

import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;
import pl.garciapl.trafficcity.api.SMSApi;
import pl.garciapl.trafficcity.dao.sms.SMSRequest;
import pl.garciapl.trafficcity.exception.NoUserFoundException;
import pl.garciapl.trafficcity.exception.UserExistsException;
import pl.garciapl.trafficcity.mongodb.interfaces.IUser;
import pl.garciapl.trafficcity.mongodb.model.Marker;
import pl.garciapl.trafficcity.mongodb.model.User;
import pl.garciapl.trafficcity.mongodb.request.SingleMarker;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by lukasz on 08.12.14.
 */
@Repository
public class UserRepository implements IUser {

    private static final Logger logger = Logger.getLogger(UserRepository.class.getName());
    private MongoTemplate mongoTemplate;
    private SMSApi smsApi;

    @Override
    public List<User> getAllUsers() {
        List<User> users = mongoTemplate.findAll(User.class);
        logger.info("Total users count : " + users.size());
        return users;
    }

    @Override
    public void addUser(User user) throws UserExistsException {

        if (checkUserExists(user.getLogin())) {
            throw new UserExistsException("User with login " + user.getLogin() + " exists");
        } else {
            mongoTemplate.insert(user);
            logger.info("New user was created");
        }
    }

    @Override
    public void addMarker(Marker marker) {
        mongoTemplate.insert(marker);
    }

    @Override
    public User getUserByLogin(String login) throws NoUserFoundException {

        BasicQuery query = new BasicQuery("{ login : '" + login + "' }");
//        List<User> users = mongoTemplate.find(query, User.class); //findOne
        User one = mongoTemplate.findOne(query, User.class);
//        if (users.size() > 0) {
//            return users.get(0);
        if (one != null) {
            return one;
        } else {
            throw new NoUserFoundException("No user found with login " + login);
        }
    }

    @Override
    public void addUserMarkers(String login, List<SingleMarker> userMarkers) throws NoUserFoundException {

        User userByLogin = getUserByLogin(login);

        for (SingleMarker singleMarker : userMarkers) {
            Marker marker = new Marker(login, singleMarker.getLongitude(), singleMarker.getLatitude(), singleMarker.getTransportType());
            marker.setUserId(userByLogin.getId());
            addMarker(marker);
        }

        smsApi.sendSMS(new SMSRequest(login, "Your markers was successfully saved in TrafficCity", "TrafficCity"));
    }

    @Override
    public int clearUserMarkers(String login) throws NoUserFoundException {
        User userByLogin = getUserByLogin(login);
        BasicQuery query = new BasicQuery("{ login : '" + userByLogin.getLogin() + "' }");
        WriteResult remove = mongoTemplate.remove(query, Marker.class);
        return remove.getN();
    }

    @Override
    public List<SingleMarker> getUserMarkers(String login) throws NoUserFoundException {
        User userByLogin = getUserByLogin(login);
        BasicQuery query = new BasicQuery("{ login : '" + userByLogin.getLogin() + "' }");
        List<Marker> markers = mongoTemplate.find(query, Marker.class);
        List<SingleMarker> singleMarkers = new ArrayList<>(markers.size());
        for (int i = 0; i < markers.size(); i++) {
            Marker marker = markers.get(i);
            singleMarkers.add(new SingleMarker(marker.getLongitude(), marker.getLatitude(), marker.getTransportType()));
        }
        return singleMarkers;
    }

    @Override
    public boolean checkUserExists(String login) {
        BasicQuery query = new BasicQuery("{ login : '" + login + "' }");
        User user = mongoTemplate.findOne(query, User.class);
        if (user == null) {
            return false;
        } else {
            return true;
        }
    }

    @Autowired
    public void setSmsApi(@Qualifier("smsapi") SMSApi smsApi) {
        this.smsApi = smsApi;
    }

    @Autowired
    public void setMongoTemplate(@Qualifier("mongoTemplate") MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

}
