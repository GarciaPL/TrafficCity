package pl.garciapl.trafficcity.controller.json;

/**
 * Created with IntelliJ IDEA.
 * User: lciesluk
 * Date: 19.12.14
 * Time: 10:51
 * To change this template use File | Settings | File Templates.
 */
public class PropertyJSON {

    private String result;
    private Object message;

    public PropertyJSON() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PropertyJSON{" +
                "result='" + result + '\'' +
                ", message=" + message +
                '}';
    }
}
