package pl.garciapl.trafficcity.mongodb.interfaces;

import pl.garciapl.trafficcity.mongodb.model.Log;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lciesluk
 * Date: 12.12.14
 * Time: 10:21
 * To change this template use File | Settings | File Templates.
 */
public interface ILog {
    void saveLog(String message);

    List<Log> readLogs();
}
