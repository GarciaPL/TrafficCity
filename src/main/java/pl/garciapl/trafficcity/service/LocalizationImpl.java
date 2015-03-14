package pl.garciapl.trafficcity.service;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.garciapl.trafficcity.api.CredentialsApi;
import pl.garciapl.trafficcity.api.LocalizationApi;
import pl.garciapl.trafficcity.dao.localization.LocalizationRequest;
import pl.garciapl.trafficcity.dao.localization.LocalizationResponse;
import pl.garciapl.trafficcity.dao.localization.LocalizationResponseFailure;
import pl.garciapl.trafficcity.exception.LocalizationErrorHandler;
import pl.garciapl.trafficcity.mongodb.interfaces.ILog;
import pl.garciapl.trafficcity.service.base.BihapiGeneric;
import pl.garciapl.trafficcity.utils.Base64Generator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lukasz on 29.11.14.
 */
public class LocalizationImpl extends BihapiGeneric implements LocalizationApi {

    private RestTemplate restTemplate;
    private CredentialsApi credentials;
    private Gson gson;
    private String url;
    private ILog log;

    public LocalizationImpl() {
    }

    public LocalizationImpl(RestTemplate restTemplate, CredentialsApi credentials, Gson gson, String url, ILog log) {
        this.restTemplate = restTemplate;
        this.credentials = credentials;
        this.gson = gson;
        this.url = url;
        this.log = log;
    }

    @Override
    public <T> T getLocalization(LocalizationRequest localizationRequest) {

        restTemplate.setErrorHandler(new LocalizationErrorHandler());

        enableSSL();

        String authHeader = Base64Generator.generateBase64(credentials.getLogin(), credentials.getPassword());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Authorization", authHeader);

        String localizationJson = gson.toJson(localizationRequest);

        Map<String, String> variables = new HashMap<String, String>(1);
        variables.put("QUERY", localizationJson);

        ResponseEntity<String> exchange;
        try {
            exchange = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>(headers), String.class, variables);

            String body = exchange.getBody();
            if (exchange.getStatusCode() == HttpStatus.OK) {
                log.saveLog("Get Localization Success : " + body);
                return (T) gson.fromJson(body, LocalizationResponse.class);
            } else {
                String substring = StringUtils.substring(body, StringUtils.indexOf(body, "{", 1), StringUtils.lastIndexOf(body, "}", body.length() - 1));
                log.saveLog("Get Localization Failure : " + substring);
                return (T) gson.fromJson(substring, LocalizationResponseFailure.class);
            }
        } catch (RestClientException e) {
            log.saveLog("Get Localization Failure : " + e.getMessage());
            return null;
        }
    }
}
