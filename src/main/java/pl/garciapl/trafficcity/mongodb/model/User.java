package pl.garciapl.trafficcity.mongodb.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by lukasz on 08.12.14.
 */
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String login;
    private String markersId;

    public User(String login) {
        this.id = new ObjectId().toString();
        this.login = login;
        this.markersId = new ObjectId().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMarkersId() {
        return markersId;
    }

    public void setMarkersId(String markersId) {
        this.markersId = markersId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", markersId='" + markersId + '\'' +
                '}';
    }
}
