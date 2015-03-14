package pl.garciapl.trafficcity.controller;

import com.google.gson.Gson;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.garciapl.trafficcity.controller.json.PropertyJSON;
import pl.garciapl.trafficcity.dao.config.ConfigResponse;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: lciesluk
 * Date: 12.12.14
 * Time: 09:01
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/configapi")
public class ConfigController {

    @Resource(name = "properties")
    private Properties properties;
    private String propertyFile = "config.properties";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public
    @ResponseBody
    String showConfig() {
        List<ConfigResponse> configResponseList = new ArrayList<>();
        Iterator<String> iterator = properties.stringPropertyNames().iterator();
        while (iterator.hasNext()) {
            String property = iterator.next();
            configResponseList.add(new ConfigResponse(property, properties.getProperty(property)));
        }

        return new Gson().toJson(configResponseList);
    }

    @RequestMapping(value = "/changeprop", method = RequestMethod.POST)
    public
    @ResponseBody
    PropertyJSON changeConfig(@RequestParam("propName") String propName, @RequestParam("propValue") String propValue) {
        PropertyJSON propertyJSON = new PropertyJSON();

        try {
            PropertiesConfiguration configuration = new PropertiesConfiguration("config.properties");
            if (configuration != null) {
                Object property = configuration.getProperty(propName);
                if (property == null) {
                    propertyJSON.setResult("FAILURE");
                    propertyJSON.setMessage("Error during setting property value");
                } else {
//                    configuration.setProperty(propName, propValue);
//                    configuration.save();
                    properties.setProperty(propName, propValue);
                    propertyJSON.setResult("FAILURE");
//                    propertyJSON.setMessage("Property " + propName + " saved successfully");
                    propertyJSON.setMessage("Saving properties not supported");
                }
            } else {
                propertyJSON.setResult("FAILURE");
                propertyJSON.setMessage("Cannot find property file " + propertyFile);
            }
        } catch (Exception e) {
            propertyJSON.setResult("FAILURE");
            propertyJSON.setMessage("Error during setting property value : " + e.getMessage());
        }

        return propertyJSON;
    }
}
