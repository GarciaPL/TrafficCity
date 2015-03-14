package pl.garciapl.trafficcity.mongodb.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import pl.garciapl.trafficcity.mongodb.interfaces.ILayer;
import pl.garciapl.trafficcity.utils.Base64Generator;
import pl.garciapl.trafficcity.utils.Streets4MPIChecker;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: lciesluk
 * Date: 11.12.14
 * Time: 12:23
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class LayerRepository implements ILayer {

    private static final Logger logger = Logger.getLogger(LayerRepository.class.getName());

    private MongoTemplate mongoTemplate;
    private static String LAYER_BUCKET = "layer";
    private static String HEAT_BUCKET = "heat";

    @Resource(name = "properties")
    private Properties properties;

    @Override
    public void saveLayer(File file) {

        try {
            if (file.exists()) {
                GridFS gfsPhoto = new GridFS(mongoTemplate.getDb(), LAYER_BUCKET);
                GridFSInputFile gfsFile = gfsPhoto.createFile(file);
                gfsFile.setFilename(file.getName());
                gfsFile.save();
            } else {
                logger.info("File " + file.getName() + " does not exist");
            }
        } catch (IOException e) {
            logger.info("saveLayer " + e.getMessage());
        }
    }

    @Override
    public void saveLayerStream(String projectName, byte[] data) {

        try {
            GridFS gfsPhoto = new GridFS(mongoTemplate.getDb(), LAYER_BUCKET);
            GridFSInputFile file = gfsPhoto.createFile(data);
            file.setFilename(projectName + "_" + new Date().getTime());
            file.save();
        } catch (Exception e) {
            logger.info("saveLayerStream : " + e.getMessage());
        }
    }

    @Override
    public GridFSDBFile getLayer(String fileName) {

        try {
            GridFS gfsPhoto = new GridFS(mongoTemplate.getDb(), LAYER_BUCKET);
            return gfsPhoto.findOne(fileName);
        } catch (Exception e) {
            logger.info("getLayer " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<GridFSDBFile> getLayers(String projectName) { //projectName_*
        try {
            GridFS gfsPhoto = new GridFS(mongoTemplate.getDb(), LAYER_BUCKET);
            BasicDBObject fields = new BasicDBObject();
            Pattern projectPattern = Pattern.compile(projectName + "_", Pattern.CASE_INSENSITIVE);
            fields.put("filename", projectPattern);
            return gfsPhoto.find(fields);
        } catch (Exception e) {
            logger.info("getLayers " + e.getMessage());
            return null;
        }
    }

    @Override
    public void saveHeatMap(File file) {
        try {
            if (file.exists()) {
                GridFS gfsPhoto = new GridFS(mongoTemplate.getDb(), HEAT_BUCKET);
                GridFSInputFile gfsFile = gfsPhoto.createFile(file);
                gfsFile.setFilename(file.getName());
                gfsFile.save();
            } else {
                logger.info("File " + file.getName() + " does not exist");
            }
        } catch (IOException e) {
            logger.info("saveHeatMap " + e.getMessage());
        }
    }

    @Override
    public void saveHeatStream(String projectName, byte[] data) {
        try {
            GridFS gfsPhoto = new GridFS(mongoTemplate.getDb(), HEAT_BUCKET);
            GridFSInputFile file = gfsPhoto.createFile(data);
            file.setFilename(projectName + "_" + new Date().getTime());
            file.save();
        } catch (Exception e) {
            logger.info("saveHeatStream : " + e.getMessage());
        }
    }

    @Override
    public List<GridFSDBFile> getHeatMaps(String projectName) {
        try {
            GridFS gfsPhoto = new GridFS(mongoTemplate.getDb(), HEAT_BUCKET);
            BasicDBObject fields = new BasicDBObject();
            Pattern projectPattern = Pattern.compile(projectName + "_" + ".", Pattern.CASE_INSENSITIVE);
            fields.put("filename", projectPattern);
            return gfsPhoto.find(fields);
        } catch (Exception e) {
            logger.info("getHeatMaps " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<String> getHeatMapsBase64(String projectName) {
        List<String> arrays = new ArrayList<>();
        try {
            GridFS gfsPhoto = new GridFS(mongoTemplate.getDb(), HEAT_BUCKET);
            BasicDBObject fields = new BasicDBObject();
            Pattern projectPattern = Pattern.compile(projectName + "_" + ".", Pattern.CASE_INSENSITIVE);
            fields.put("filename", projectPattern);
            List<GridFSDBFile> gridFSDBFiles = gfsPhoto.find(fields);

            for (GridFSDBFile gridFSDBFile : gridFSDBFiles) {
                if (gridFSDBFile.getInputStream() != null) {
                    byte[] bytes = IOUtils.toByteArray(gridFSDBFile.getInputStream());
                    arrays.add(Base64Generator.generateBase64Image(bytes));
                }
            }
            return arrays;
        } catch (Exception e) {
            logger.info("getHeatMapsBytes " + e.getMessage());
            return arrays;
        }
    }

    @Override
    public List<String> getHeatMapProjects() {
        List<String> projects = new ArrayList<>();
        try {
            GridFS gfsPhoto = new GridFS(mongoTemplate.getDb(), HEAT_BUCKET);
            BasicDBObject fields = new BasicDBObject();
            Pattern projectPattern = Pattern.compile(".", Pattern.CASE_INSENSITIVE);
            fields.put("filename", projectPattern);
            List<GridFSDBFile> gridFSDBFiles = gfsPhoto.find(fields);
            for (GridFSDBFile gridFSDBFile : gridFSDBFiles) {
                if (gridFSDBFile.getFilename() != null) {
                    if (!projects.contains(gridFSDBFile.getFilename().split("_")[0])) {
                        projects.add(gridFSDBFile.getFilename().split("_")[0]);
                    }
                }
            }
            return projects;
        } catch (Exception e) {
            logger.info("getProjects " + e.getMessage());
            return projects;
        }
    }

    @Override
    public List<String> getStreet4MPIProjects() {
        List<String> projects = new ArrayList<>();
        try {
            String street4mpi = properties.getProperty("streets4mpi_path");
            if (!street4mpi.endsWith("/")) {
                street4mpi += "/";
            }
            street4mpi += "osm/";
            return Streets4MPIChecker.getStreet4MPIProjects(street4mpi);
        } catch (Exception e) {
            return projects;
        }
    }

    @Autowired
    public void setMongoTemplate(@Qualifier("mongoTemplate") MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}
