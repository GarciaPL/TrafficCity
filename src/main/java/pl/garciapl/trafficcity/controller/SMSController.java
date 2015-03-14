package pl.garciapl.trafficcity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.garciapl.trafficcity.api.CredentialsApi;
import pl.garciapl.trafficcity.api.SMSApi;
import pl.garciapl.trafficcity.dao.sms.SMSRequest;
import pl.garciapl.trafficcity.dao.sms.SMSResponse;
import pl.garciapl.trafficcity.dao.sms.SMSResponseFailure;

/**
 * Created by lukasz on 29.11.14.
 */
@Controller
@RequestMapping("/smsapi")
public class SMSController {

    private SMSApi smsApi;
    private CredentialsApi credentials;

    private String from = "TrafficCity";
    private String msg = "TEST";

    @RequestMapping(value = "/sendsms", method = RequestMethod.GET)
    public
    @ResponseBody
    String sendSMS() {
        Object smsapi = smsApi.sendSMS(new SMSRequest(credentials.getLogin(), msg, from));
        if (smsapi != null) {
            if (smsapi instanceof SMSResponse) {
                SMSResponse smsResponse = (SMSResponse) smsapi;
                return smsResponse.toString();
            } else if (smsapi instanceof SMSResponseFailure) {
                SMSResponseFailure smsResponseFailure = (SMSResponseFailure) smsapi;
                return smsResponseFailure.toString();
            } else {
                return "No content";
            }
        } else {
            return "No content";
        }
    }

    @Autowired
    public void setSmsApi(@Qualifier("smsapi") final SMSApi smsApi) {
        this.smsApi = smsApi;
    }

    @Autowired
    public void setCredentials(@Qualifier("credentials") final CredentialsApi credentials) {
        this.credentials = credentials;
    }
}
