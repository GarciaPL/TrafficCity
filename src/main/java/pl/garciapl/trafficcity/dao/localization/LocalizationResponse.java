package pl.garciapl.trafficcity.dao.localization;

import java.util.Calendar;

/**
 * Created by lukasz on 30.11.14.
 */
public class LocalizationResponse {

    private int accuracy;
    private float latitude;
    private float longitude;
    private Calendar timestamp;
    private float altitude;

    public LocalizationResponse() {
    }

    public LocalizationResponse(int accuracy, float latitude, float longitude, Calendar timestamp, float altitude) {
        this.accuracy = accuracy;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
        this.altitude = altitude;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public Calendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }

    public float getAltitude() {
        return altitude;
    }

    public void setAltitude(float altitude) {
        this.altitude = altitude;
    }

    @Override
    public String toString() {
        return "LocalizationResponse{" +
                "accuracy=" + accuracy +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", timestamp=" + timestamp +
                ", altitude=" + altitude +
                '}';
    }
}
