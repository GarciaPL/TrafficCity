package pl.garciapl.trafficcity;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.garciapl.trafficcity.api.Streets4MPIApi;

/**
 * Created with IntelliJ IDEA.
 * User: lciesluk
 * Date: 12.12.14
 * Time: 11:10
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:bihapi-context-test.xml", "classpath*:bihapi-ctx.xml", "classpath*:bihapi-mongodb-ctx.xml"})
public class Streets4MPITest {

    private Streets4MPIApi mpiApi;

    //    @Test
    public void testGenerateFiles() {

        String fileOsm = "Lukasza.osm";
        String projectName = "uwaga";
//        String projectName = "test";
//        String projectName = "Ostrobramska";

        mpiApi.generateS4MPIFiles(fileOsm, projectName);

    }

    //    @Test
    public void testVisualizeFiles() {

        String projectName = "uwaga";
//        String projectName = "test";
//        String projectName = "Ostrobramska";

        //od razu insert do bazy danych
        mpiApi.generateHeadMaps(projectName);

    }

    @Autowired
    public void setMpiApi(@Qualifier("streets4mpiapi") Streets4MPIApi mpiApi) {
        this.mpiApi = mpiApi;
    }
}
