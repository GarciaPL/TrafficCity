package pl.garciapl.trafficcity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.garciapl.trafficcity.api.CredentialsApi;
import pl.garciapl.trafficcity.api.USSDApi;
import pl.garciapl.trafficcity.dao.ussd.USSDRequest;
import pl.garciapl.trafficcity.dao.ussd.USSDResponse;
import pl.garciapl.trafficcity.dao.ussd.USSDResponseFailure;


/**
 * Created by lukasz on 30.11.14.
 */
@Controller
@RequestMapping("/ussdapi")
public class USSDController {

    private USSDApi ussdApi;
    private CredentialsApi credentials;
    private String msg = "TEST";

    @RequestMapping(value = "/sendussd", method = RequestMethod.GET)
    public
    @ResponseBody
    String sendUSSD() {
        Object ussdapi = ussdApi.sendUSSD(new USSDRequest(credentials.getLogin(), msg));
        if (ussdapi != null) {
            if (ussdapi instanceof USSDResponse) {
                USSDResponse ussdResponse = (USSDResponse) ussdapi;
                return ussdResponse.toString();
            } else if (ussdapi instanceof USSDResponseFailure) {
                USSDResponseFailure ussdResponseFailure = (USSDResponseFailure) ussdapi;
                return ussdResponseFailure.toString();
            } else {
                return "No content";
            }
        } else {
            return "No content";
        }

    }

    @Autowired
    public void setUssdApi(@Qualifier("ussdapi") final USSDApi ussdApi) {
        this.ussdApi = ussdApi;
    }

    @Autowired
    public void setCredentials(@Qualifier("credentials") final CredentialsApi credentials) {
        this.credentials = credentials;
    }
}
