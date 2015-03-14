package pl.garciapl.trafficcity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/home")
public class SimpleController {

    @RequestMapping("/simple")
    public
    @ResponseBody
    String simple() {
        return "Hello TrafficCity!";
    }

}
