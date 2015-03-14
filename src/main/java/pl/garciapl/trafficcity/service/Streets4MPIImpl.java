package pl.garciapl.trafficcity.service;

import pl.garciapl.trafficcity.api.Streets4MPIApi;
import pl.garciapl.trafficcity.mongodb.interfaces.ILayer;
import pl.garciapl.trafficcity.mongodb.interfaces.ILog;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: lciesluk
 * Date: 12.12.14
 * Time: 10:03
 * To change this template use File | Settings | File Templates.
 */
public class Streets4MPIImpl implements Streets4MPIApi {

    private String streets4mpi_path;
    private ILog log;
    private ILayer layer;

    public Streets4MPIImpl(String streets4mpi_path, ILog log, ILayer layer) {
        this.streets4mpi_path = streets4mpi_path;
        this.log = log;
        this.layer = layer;
    }

    @Override
    public void generateS4MPIFiles(String fileOsm, String projectName) {
        try {

            String streets4mpi = this.streets4mpi_path;
            if (!streets4mpi.endsWith("/")) {
                streets4mpi += "/";
            }

            Process p = Runtime.getRuntime().exec("python " + this.streets4mpi_path + "streets4mpi.py " +
                    "--home-dir " + streets4mpi +
                    " --osm " + fileOsm + " --project " + projectName);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                log.saveLog("Generating Street4MPI files : " + line);
            }

            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line2 = null;
            while ((line2 = error.readLine()) != null) {
                System.out.println(line2);
                log.saveLog("Exception during generating Street4MPI files : " + line2);
            }
        } catch (Exception e) {
            log.saveLog("Exception during generating Street4MPI files : " + e.getMessage());
        }
    }

    @Override
    public void generateHeadMaps(String projectName) {
        try {

            String streets4mpi = this.streets4mpi_path;
            if (!streets4mpi.endsWith("/")) {
                streets4mpi += "/";
            }

            Process p = Runtime.getRuntime().exec("python " + this.streets4mpi_path + "visualization.py " +
                    "--home-dir " + streets4mpi + " --project " + projectName + "");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                log.saveLog("Generating HeatMaps : " + line);
            }

            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line2 = null;
            while ((line2 = error.readLine()) != null) {
                System.out.println(line2);
                log.saveLog("Exception during making HeatMaps : " + line2);
            }

        } catch (Exception e) {
            log.saveLog("Exception during making HeatMaps : " + e.getMessage());
        }
    }
}
