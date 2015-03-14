package pl.garciapl.trafficcity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.garciapl.trafficcity.api.TransportApi;
import pl.garciapl.trafficcity.dao.transport.*;

import java.util.ArrayList;

/**
 * Created by lukasz on 06.12.14.
 */
@Controller
@RequestMapping("/transportapi")
public class TransportController {

    private TransportApi transportApi;

    @RequestMapping(value = "/getmap", method = RequestMethod.GET)
    public
    @ResponseBody
    String getMap() {

        ArrayList<Layers> layerses = new ArrayList<>();
        layerses.add(Layers.RAIL_STATION);
        layerses.add(Layers.PUBLIC_TRANSPORT_STOP_ZTM);
        layerses.add(Layers.METRO_STATIONS);

        transportApi.getMap(new TransportRequest(new Coordinates(52.229572, 21.011481),
                new Zoom(15),
                new Size(400, 500),
                Format.PNG.getFormat(),
                layerses));

        return "";
    }

    @Autowired
    public void setTransportApi(@Qualifier("transportapi") final TransportApi transportApi) {
        this.transportApi = transportApi;
    }

}
