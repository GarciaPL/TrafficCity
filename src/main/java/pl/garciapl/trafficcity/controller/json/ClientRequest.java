package pl.garciapl.trafficcity.controller.json;

/**
 * Created by lukasz on 20.12.14.
 */
public class ClientRequest {

    private String phoneNumber;

    public ClientRequest() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "ClientRequest{" +
                "phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
