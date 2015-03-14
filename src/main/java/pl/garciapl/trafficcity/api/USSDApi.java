package pl.garciapl.trafficcity.api;


import pl.garciapl.trafficcity.dao.ussd.USSDRequest;

/**
 * Created by lukasz on 30.11.14.
 */
public interface USSDApi {
    <T> T sendUSSD(USSDRequest ussdRequest);
}
