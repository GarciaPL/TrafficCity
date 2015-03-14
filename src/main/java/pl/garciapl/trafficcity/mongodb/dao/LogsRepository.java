package pl.garciapl.trafficcity.mongodb.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import pl.garciapl.trafficcity.mongodb.interfaces.ILog;
import pl.garciapl.trafficcity.mongodb.model.Log;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: lciesluk
 * Date: 12.12.14
 * Time: 10:22
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class LogsRepository implements ILog {

    static final Logger logger = Logger.getLogger(LogsRepository.class.getName());

    private MongoTemplate mongoTemplate;

    @Override
    public void saveLog(String message) {
        mongoTemplate.save(new Log(message, new Date().toString()));
    }

    @Override
    public List<Log> readLogs() {
        return mongoTemplate.findAll(Log.class);
    }

    @Autowired
    public void setMongoTemplate(@Qualifier("mongoTemplate") MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}
