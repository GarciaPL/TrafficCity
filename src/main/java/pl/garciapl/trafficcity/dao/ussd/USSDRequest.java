package pl.garciapl.trafficcity.dao.ussd;

/**
 * Created by lukasz on 06.12.14.
 */
public class USSDRequest {

    private String to;
    private String msg;

    public USSDRequest(String to, String msg) {
        this.to = to;
        this.msg = msg;
    }

    public String getTo() {
        return to;
    }

    public USSDRequest setTo(String to) {
        this.to = to;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public USSDRequest setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public String toString() {
        return "USSDRequest{" +
                "to='" + to + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
