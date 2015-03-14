package pl.garciapl.trafficcity;

import com.mongodb.gridfs.GridFSDBFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.garciapl.trafficcity.mongodb.interfaces.ILayer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: lciesluk
 * Date: 11.12.14
 * Time: 16:08
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:bihapi-context-test.xml", "classpath*:bihapi-ctx.xml", "classpath*:bihapi-mongodb-ctx.xml"})
public class LayerRepositoryTest {

    private ILayer layer;
    private static final Logger logger = Logger.getLogger(LayerRepositoryTest.class.getName());

    //    @Test
    public void addLayerFile() {
        File file = new File("/home/lciesluk/Pulpit/test.png");
        layer.saveLayer(file);
    }

    //    @Test
    public void addLayerByte() {
        File file = new File("/home/lciesluk/Pulpit/test.png");
        String projectName = "Polczynska";
        if (file.exists()) {
            Path path = Paths.get(file.getAbsolutePath());
            try {
                byte[] bytes = Files.readAllBytes(path);
                layer.saveLayerStream(projectName, bytes);
            } catch (IOException e) {
                logger.info(e.getMessage());
            }
        } else {
            logger.info("File does not exist");
        }
    }

    @Test
    public void addHeatMap() {
        File file = new File("/home/lciesluk/Pulpit/test2.png");
        String projectName = "Polczynska";
        if (file.exists()) {
            Path path = Paths.get(file.getAbsolutePath());
            try {
                byte[] bytes = Files.readAllBytes(path);
                layer.saveHeatStream(projectName, bytes);
            } catch (IOException e) {
                logger.info(e.getMessage());
            }
        } else {
            logger.info("File does not exist");
        }
    }

    //    @Test
    public void getLayer() {
        String filename = "test2.png";
//        String filename = "test2.png";
        GridFSDBFile layerRead = layer.getLayer(filename);
        if (layerRead != null) {
            logger.info("Filename : " + layerRead.getFilename());
            logger.info("UploadDate : " + layerRead.getUploadDate().toString());
            logger.info("Id : " + layerRead.getId());

//        InputStream inputStream = layerRead.getInputStream();
        } else {
            logger.info("Cannot find image with name " + filename);
        }
    }

    //    @Test
    public void getLayers() {
        String projectName = "test2";
        layer.getLayers(projectName);
    }

    //    @Test
    public void getHeatMaps() {
        String projectName = "Polczynska";
        List<GridFSDBFile> heatMaps = layer.getHeatMaps(projectName);
        for (GridFSDBFile gridFSDBFile : heatMaps) {
            logger.info("File : " + gridFSDBFile.getFilename());
        }
    }

    //    @Test
    public void getHeatMapProjects() {
        List<String> heatMapProjects = layer.getHeatMapProjects();
        for (String name : heatMapProjects) {
            logger.info("Project : " + name);
        }
    }

    //    @Test
    public void getHeatMapsBase64() {
        String projectName = "Polczynska";
        List<String> heatMapsBase64 = layer.getHeatMapsBase64(projectName);
        for (String base64 : heatMapsBase64) {
            logger.info("Base64 : " + base64);
        }
    }

    @Autowired
    public void setLayer(final ILayer layer) {
        this.layer = layer;
    }
}
