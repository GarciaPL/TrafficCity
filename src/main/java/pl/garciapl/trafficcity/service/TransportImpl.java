package pl.garciapl.trafficcity.service;

import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.garciapl.trafficcity.api.TransportApi;
import pl.garciapl.trafficcity.dao.transport.TransportRequest;
import pl.garciapl.trafficcity.exception.TransportErrorHandler;
import pl.garciapl.trafficcity.mongodb.interfaces.ILayer;
import pl.garciapl.trafficcity.mongodb.interfaces.ILog;
import pl.garciapl.trafficcity.service.base.BihapiGeneric;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lukasz on 06.12.14.
 */
public class TransportImpl extends BihapiGeneric implements TransportApi {

    private RestTemplate restTemplate;
    private String url;
    private ILayer layer;
    private ILog log;

    public TransportImpl(RestTemplate restTemplate, String url, ILog log) {
        this.restTemplate = restTemplate;
        this.url = url;
        this.log = log;
    }

    @Override
    public void getMap(TransportRequest transportRequest) {

        restTemplate.setErrorHandler(new TransportErrorHandler());

        enableSSL();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        Map<String, String> variables = new HashMap<String, String>(6);
        variables.put("LONGITUDE", Double.toString(transportRequest.getCoordinates().getLongitude()));
        variables.put("LATITUDE", Double.toString(transportRequest.getCoordinates().getLatitude()));
        variables.put("ZOOM", Integer.toString(transportRequest.getZoom().getZoom()));
        variables.put("SIZEX", Integer.toString(transportRequest.getSize().getSizex()));
        variables.put("SIZEY", Integer.toString(transportRequest.getSize().getSizey()));
        variables.put("FORMAT", transportRequest.getFormat());
        variables.put("LAYERS", transportRequest.getLayersAll());

        try {
            ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, String.class, variables);

            if (exchange.getStatusCode().value() == HttpStatus.SC_MOVED_TEMPORARILY) {
                if (exchange.getHeaders().getLocation() != null) {
                    String imageUrl = exchange.getHeaders().getLocation().toURL().toString();
                    log.saveLog("Transport POI Redirected URL : " + imageUrl);

                    List<MediaType> acceptableMediaTypes = new ArrayList<>();
                    acceptableMediaTypes.add(MediaType.IMAGE_PNG);
                    HttpHeaders headersRedirect = new HttpHeaders();
                    headers.setAccept(acceptableMediaTypes);

                    ResponseEntity<byte[]> exchangeRedirect = new RestTemplate().exchange(imageUrl, HttpMethod.GET, new HttpEntity<byte[]>(headersRedirect), byte[].class);
                    if (exchangeRedirect.getBody() != null) {
                        byte[] image = exchangeRedirect.getBody();
                        if (image != null) {
                            layer.saveLayerStream("BIHAPI", image);
                            log.saveLog("Saved image under projectName : " + "BIHAPI");
                        } else {
                            log.saveLog("Server does not return transport POI map");
                        }
                    } else {
                        log.saveLog("Server does not return transport POI map");
                    }
                } else {
                    log.saveLog("Server does not redirect request");
                }
            }

        } catch (RestClientException e) {
            log.saveLog("Get Transport POI Map Failure : " + e.getMessage());
        } catch (MalformedURLException e) {
            log.saveLog("Get Transport POI Map Failure : " + e.getMessage());
        }
    }

    @Autowired
    public void setLayer(ILayer layer) {
        this.layer = layer;
    }
}
