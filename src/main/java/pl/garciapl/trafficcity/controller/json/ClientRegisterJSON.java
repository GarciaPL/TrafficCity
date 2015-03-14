package pl.garciapl.trafficcity.controller.json;

/**
 * Created with IntelliJ IDEA.
 * User: lciesluk
 * Date: 19.12.14
 * Time: 11:23
 * To change this template use File | Settings | File Templates.
 */
public class ClientRegisterJSON {

    private String login;
    private String telephone;
    private String result;
    private String message;

    public ClientRegisterJSON() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
        return "ClientRegisterJSON{" +
                "login='" + login + '\'' +
                ", telephone='" + telephone + '\'' +
                ", result='" + result + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
