package pl.garciapl.trafficcity;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by lukasz on 14.03.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:bihapi-context-test.xml", "classpath*:bihapi-ctx.xml", "classpath*:bihapi-mongodb-ctx.xml"})
public class PropertiesChangerTest {

    @Resource(name = "properties")
    private Properties properties;

    private static final Logger logger = Logger.getLogger(LogsRepositoryTest.class.getName());

    @Test
    public void changePropertiesTest() throws ConfigurationException {
        PropertiesConfiguration configuration = new PropertiesConfiguration("config.properties");
        if (configuration != null) {
            Object bihapi_login = configuration.getProperty("bihapi_login");
            logger.info(bihapi_login.toString());
            configuration.setProperty("bihapi_login", "48507842716");
            configuration.save();
        } else {
            logger.warning("Cannot find config.properties");
        }
    }
}
