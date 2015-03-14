package pl.garciapl.trafficcity.dao.ussd;

/**
 * Created by lukasz on 29.11.14.
 */
public class USSDResponseFailure {

    private String status;
    private String description;

    public USSDResponseFailure() {
    }

    public USSDResponseFailure(String status, String description) {
        this.status = status;
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "USSDResponseFailure{" +
                "status='" + status + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
