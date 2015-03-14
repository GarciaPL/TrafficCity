package pl.garciapl.trafficcity.dao.localization;

/**
 * Created by lukasz on 17.12.14.
 */
public class LocalizationResponseFailure {

    private String message;
    private String type;

    public LocalizationResponseFailure() {
    }

    public LocalizationResponseFailure(String message, String type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LocalizationResponseFailure{" +
                "message='" + message + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
