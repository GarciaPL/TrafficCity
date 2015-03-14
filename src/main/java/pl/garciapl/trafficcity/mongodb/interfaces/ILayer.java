package pl.garciapl.trafficcity.mongodb.interfaces;

import com.mongodb.gridfs.GridFSDBFile;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lciesluk
 * Date: 11.12.14
 * Time: 12:22
 * To change this template use File | Settings | File Templates.
 */
public interface ILayer {
    void saveLayer(File file);

    void saveLayerStream(String projectName, byte[] data);

    GridFSDBFile getLayer(String fileName);

    List<GridFSDBFile> getLayers(String projectName);

    void saveHeatMap(File file);

    void saveHeatStream(String projectName, byte[] data);

    List<GridFSDBFile> getHeatMaps(String projectName);

    List<String> getHeatMapsBase64(String projectName);

    List<String> getHeatMapProjects();

    List<String> getStreet4MPIProjects();
}
