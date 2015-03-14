package pl.garciapl.trafficcity.api;


import pl.garciapl.trafficcity.dao.transport.TransportRequest;

/**
 * Created by lukasz on 06.12.14.
 */
public interface TransportApi {
    void getMap(TransportRequest transportRequest);
}
