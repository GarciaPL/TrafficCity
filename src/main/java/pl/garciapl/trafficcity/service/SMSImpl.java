package pl.garciapl.trafficcity.service;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.garciapl.trafficcity.api.CredentialsApi;
import pl.garciapl.trafficcity.api.SMSApi;
import pl.garciapl.trafficcity.dao.sms.SMSRequest;
import pl.garciapl.trafficcity.dao.sms.SMSResponse;
import pl.garciapl.trafficcity.dao.sms.SMSResponseFailure;
import pl.garciapl.trafficcity.exception.SMSErrorHandler;
import pl.garciapl.trafficcity.mongodb.interfaces.ILog;
import pl.garciapl.trafficcity.service.base.BihapiGeneric;
import pl.garciapl.trafficcity.utils.Base64Generator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lukasz on 29.11.14.
 */
public class SMSImpl extends BihapiGeneric implements SMSApi {

    private RestTemplate restTemplate;
    private CredentialsApi credentials;
    private Gson gson;
    private String url;
    private ILog log;

    public SMSImpl(RestTemplate restTemplate, CredentialsApi credentials, Gson gson, String url, ILog log) {
        this.restTemplate = restTemplate;
        this.credentials = credentials;
        this.gson = gson;
        this.url = url;
        this.log = log;
    }

    @Override
    public <T> T sendSMS(SMSRequest smsRequest) {

        restTemplate.setErrorHandler(new SMSErrorHandler());

        enableSSL();

        String authHeader = Base64Generator.generateBase64(credentials.getLogin(), credentials.getPassword());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Authorization", authHeader);

        Map<String, String> variables = new HashMap<String, String>(3);
        variables.put("TO", smsRequest.getTo());
        variables.put("FROM", smsRequest.getFrom());
        variables.put("MSG", smsRequest.getMsg());

        ResponseEntity<String> exchange;
        try {
            exchange = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>(headers), String.class, variables);

            String body = exchange.getBody();
            if (exchange.getStatusCode() == HttpStatus.OK) {
                log.saveLog("Send SMS Success : " + body);
                return (T) gson.fromJson(body, SMSResponse.class);
            } else {
                String substring = StringUtils.substring(body, StringUtils.indexOf(body, "{", 1), StringUtils.lastIndexOf(body, "}", body.length() - 1));
                log.saveLog("Send SMS Failure : " + substring);
                return (T) gson.fromJson(substring, SMSResponseFailure.class);
            }
        } catch (RestClientException e) {
            log.saveLog("Send SMS Failure : " + e.getMessage());
            return null;
        }
    }

}
