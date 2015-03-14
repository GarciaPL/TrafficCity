package pl.garciapl.trafficcity.controller.json;

/**
 * Created with IntelliJ IDEA.
 * User: lciesluk
 * Date: 19.12.14
 * Time: 13:06
 * To change this template use File | Settings | File Templates.
 */
public class UploadFileJSON {

    private String result;
    private String message;

    public UploadFileJSON() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UploadFileJSON{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
