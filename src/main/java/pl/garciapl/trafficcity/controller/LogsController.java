package pl.garciapl.trafficcity.controller;

import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.garciapl.trafficcity.mongodb.interfaces.ILog;
import pl.garciapl.trafficcity.mongodb.model.Log;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lciesluk
 * Date: 12.12.14
 * Time: 10:19
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class LogsController {

    private ILog log;

    @RequestMapping("/logsapi")
    public
    @ResponseBody
    String getLogs() {
        List<Log> logs = log.readLogs();
        if (logs.size() == 0) {
            return "No content";
        }
        return new GsonBuilder().serializeNulls().create().toJson(logs);
    }

    @Autowired
    public void setLog(ILog log) {
        this.log = log;
    }
}
