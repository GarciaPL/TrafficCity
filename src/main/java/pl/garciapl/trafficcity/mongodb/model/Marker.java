package pl.garciapl.trafficcity.mongodb.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by lukasz on 09.12.14.
 */
@Document(collection = "markers")
public class Marker {

    @Id
    private String id;
    private String login;
    private Double longitude;
    private Double latitude;
    private String transportType;
    private String userId;

    public Marker(String login, Double longitude, Double latitude, String transportType) {
        this.id = new ObjectId().toString();
        this.login = login;
        this.longitude = longitude;
        this.latitude = latitude;
        this.transportType = transportType;
        this.userId = new ObjectId().toString();
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

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Marker{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", transportType='" + transportType + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
