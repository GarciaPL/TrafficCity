package pl.garciapl.trafficcity;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.garciapl.trafficcity.mongodb.interfaces.ILog;
import pl.garciapl.trafficcity.mongodb.model.Log;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: lciesluk
 * Date: 12.12.14
 * Time: 10:33
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:bihapi-context-test.xml", "classpath*:bihapi-ctx.xml", "classpath*:bihapi-mongodb-ctx.xml"})
public class LogsRepositoryTest {

    private ILog log;
    private static final Logger logger = Logger.getLogger(LogsRepositoryTest.class.getName());

    //    @Test
    public void testReadLogs() {
        List<Log> logs = log.readLogs();
        logger.info("Logs size : " + logs.size());
        for (Log log : logs) {
            logger.info("Log : " + log.toString());
        }
    }

    //    @Test
    public void testSaveLog() {
        log.saveLog("Test error message");
    }

    @Autowired
    public void setLog(ILog log) {
        this.log = log;
    }
}
