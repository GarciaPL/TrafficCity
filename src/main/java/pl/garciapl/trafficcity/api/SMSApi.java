package pl.garciapl.trafficcity.api;


import pl.garciapl.trafficcity.dao.sms.SMSRequest;

/**
 * Created by lukasz on 29.11.14.
 */
public interface SMSApi {
    <T> T sendSMS(SMSRequest smsRequest);
}
