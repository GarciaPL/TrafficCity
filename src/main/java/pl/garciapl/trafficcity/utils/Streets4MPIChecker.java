package pl.garciapl.trafficcity.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: lciesluk
 * Date: 12.12.14
 * Time: 11:19
 * To change this template use File | Settings | File Templates.
 */
public class Streets4MPIChecker {

    private static Logger logger = Logger.getLogger(Streets4MPIChecker.class.getName());

    public static boolean checkStreets4MPIProjectExist(String path, String projectName) {
        File projectFiles = new File(path + (path.endsWith("/") ? "" : "/") + "osm" + "/" + projectName);
        if (projectFiles.exists()) {
            if (projectFiles.isDirectory()) {
                File[] files = projectFiles.listFiles();
                if (files != null) {
                    if (files.length == 0) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public static List<String> getStreet4MPIProjects(String path) {
        List<String> filesDirectories = new ArrayList<>();
        File projectFiles = new File(path + (path.endsWith("/") ? "" : "/"));
        File[] files = projectFiles.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory() && file.list().length != 0) {
                    filesDirectories.add(file.getName());
                }
            }
        }
        return filesDirectories;
    }

    public static List<String> getHeatMaps(String path, String projectName) {
        List<String> filesDirectories = new ArrayList<>();
        File projectFiles = new File(path + (path.endsWith("/") ? "" : "/") + "images" + "/" + projectName);
        File[] files = projectFiles.listFiles();
        if (files != null) {
            for (File file : files) {
                if (!file.isDirectory()) {
                    if (FilenameUtils.getExtension(file.getName()).equals("png")) {
                        try {
                            byte[] bytes = IOUtils.toByteArray(FileUtils.openInputStream(file));
                            filesDirectories.add(Base64Generator.generateBase64Image(bytes));
                        } catch (IOException e) {
                            logger.info(e.getMessage());
                        }
                    }
                }
            }
        }
        return filesDirectories;
    }
}
