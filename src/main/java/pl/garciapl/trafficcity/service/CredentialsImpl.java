package pl.garciapl.trafficcity.service;


import pl.garciapl.trafficcity.api.CredentialsApi;

/**
 * Created by lukasz on 29.11.14.
 */
public class CredentialsImpl implements CredentialsApi {

    private String login;
    private String password;

    public CredentialsImpl(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String getLogin() {
        return this.login;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
