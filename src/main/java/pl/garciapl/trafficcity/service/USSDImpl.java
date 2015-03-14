package pl.garciapl.trafficcity.service;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.garciapl.trafficcity.api.CredentialsApi;
import pl.garciapl.trafficcity.api.USSDApi;
import pl.garciapl.trafficcity.dao.ussd.USSDRequest;
import pl.garciapl.trafficcity.dao.ussd.USSDResponse;
import pl.garciapl.trafficcity.dao.ussd.USSDResponseFailure;
import pl.garciapl.trafficcity.exception.USSDErrorHandler;
import pl.garciapl.trafficcity.mongodb.interfaces.ILog;
import pl.garciapl.trafficcity.service.base.BihapiGeneric;
import pl.garciapl.trafficcity.utils.Base64Generator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lukasz on 29.11.14.
 */
public class USSDImpl extends BihapiGeneric implements USSDApi {

    private RestTemplate restTemplate;
    private CredentialsApi credentials;
    private Gson gson;
    private String url;
    private ILog log;

    public USSDImpl() {
    }

    public USSDImpl(RestTemplate restTemplate, CredentialsApi credentials, Gson gson, String url, ILog log) {
        this.restTemplate = restTemplate;
        this.credentials = credentials;
        this.gson = gson;
        this.url = url;
        this.log = log;
    }

    @Override
    public <T> T sendUSSD(USSDRequest ussdRequest) {

        restTemplate.setErrorHandler(new USSDErrorHandler());

        enableSSL();

        String authHeader = Base64Generator.generateBase64(credentials.getLogin(), credentials.getPassword());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Authorization", authHeader);

        Map<String, String> variables = new HashMap<String, String>(2);
        variables.put("TO", ussdRequest.getTo());
        variables.put("MSG", ussdRequest.getMsg());

        ResponseEntity<String> exchange = null;
        try {
            exchange = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>(headers), String.class, variables);

            String body = exchange.getBody();
            if (exchange.getStatusCode() == HttpStatus.OK) {
                log.saveLog("Send USSD Success : " + body);
                return (T) gson.fromJson(body, USSDResponse.class);
            } else {
                String substring = StringUtils.substring(body, StringUtils.indexOf(body, "{", 1), StringUtils.lastIndexOf(body, "}", body.length() - 1));
                log.saveLog("Send USSD Failure : " + substring);
                return (T) gson.fromJson(substring, USSDResponseFailure.class);
            }
        } catch (RestClientException e) {
            log.saveLog("Send USSD Failure : " + e.getMessage());
            return null;
        }
    }
}
