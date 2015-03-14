package pl.garciapl.trafficcity;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.garciapl.trafficcity.exception.NoUserFoundException;
import pl.garciapl.trafficcity.exception.UserExistsException;
import pl.garciapl.trafficcity.mongodb.interfaces.IUser;
import pl.garciapl.trafficcity.mongodb.model.User;
import pl.garciapl.trafficcity.mongodb.request.SingleMarker;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: lciesluk
 * Date: 11.12.14
 * Time: 12:57
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:bihapi-context-test.xml", "classpath*:bihapi-ctx.xml", "classpath*:bihapi-mongodb-ctx.xml"})
public class UserRepositoryTest {

    private IUser user;
    private static final Logger logger = Logger.getLogger(UserRepositoryTest.class.getName());

    //    @Test
    public void addUserMarkers() {
        String login = "48660475164";
        List<SingleMarker> list = new ArrayList<>();
        list.add(new SingleMarker(20.997190, 52.233219, "Car"));
        list.add(new SingleMarker(21.000237, 52.228251, "Car"));
        list.add(new SingleMarker(21.011481, 52.231458, "Car"));
        logger.info("userMarkers size : " + list.size());

        try {
            if (list.size() > 0) {
                user.addUserMarkers(login, list);
            }
        } catch (NoUserFoundException e) {
            logger.info(e.getMessage());
        }
    }

    //    @Test
    public void getUserMarkers() {
        String login = "48660475164";
//        String login = "48500600700";
        try {
            List<SingleMarker> userMarkers = user.getUserMarkers(login);
            logger.info("UserMakers count : " + userMarkers.size());
            for (SingleMarker singleMarker : userMarkers) {
                logger.info(singleMarker.toString());
            }
        } catch (NoUserFoundException e) {
            logger.info(e.getMessage());
        }
    }

    //    @Test
    public void deleteUserMarkers() {
        String login = "48660475164";
//        String login = "48500600700";
        try {
            int i = user.clearUserMarkers(login);
            logger.info("User markers was deleted : " + i);
        } catch (NoUserFoundException e) {
            logger.info(e.getMessage());
        }
    }

    //        @Test
    public void addNewUser() {
        String login = "48660475164";
        User newuser = new User(login);
        try {
            user.addUser(newuser);
        } catch (UserExistsException e) {
            logger.info(e.getMessage());
        }
    }

    //    @Test
    public void getUserByLogin() {
        String login = "48660475164";
//        String login = "48660475165";
        try {
            User userByLogin = user.getUserByLogin(login);
            logger.info(userByLogin.toString());
        } catch (NoUserFoundException e) {
            logger.info("No user found with login " + login);
        }
    }

    @Autowired
    public void setUser(final IUser user) {
        this.user = user;
    }
}
