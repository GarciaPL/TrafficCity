package pl.garciapl.trafficcity.api;

/**
 * Created with IntelliJ IDEA.
 * User: lciesluk
 * Date: 12.12.14
 * Time: 09:29
 * To change this template use File | Settings | File Templates.
 */
public interface Streets4MPIApi {

    void generateS4MPIFiles(String fileOsm, String projectName);

    void generateHeadMaps(String projectName);
}
