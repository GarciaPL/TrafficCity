package pl.garciapl.trafficcity.dao.sms;

/**
 * Created by lukasz on 06.12.14.
 */
public class SMSRequest {

    private String to;
    private String from;
    private String msg;

    public SMSRequest(String to, String msg, String from) {
        this.to = to;
        this.msg = msg;
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public SMSRequest setTo(String to) {
        this.to = to;
        return this;
    }

    public String getFrom() {
        return from;
    }

    public SMSRequest setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public SMSRequest setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public String toString() {
        return "SMSRequest{" +
                "to='" + to + '\'' +
                ", from='" + from + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
