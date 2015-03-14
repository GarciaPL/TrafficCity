package pl.garciapl.trafficcity.utils;

import org.apache.commons.codec.binary.Base64;

/**
 * Created with IntelliJ IDEA.
 * User: lciesluk
 * Date: 11.12.14
 * Time: 16:53
 * To change this template use File | Settings | File Templates.
 */
public class Base64Generator {

    public static String generateBase64(String login, String password) {
        if (login != null && password != null) {
            String auth = login + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
            return "Basic " + new String(encodedAuth);
        } else {
            return null;
        }
    }

    public static String generateBase64Image(byte[] array) {
        if (array.length != 0) {
            return Base64.encodeBase64String(array);
        } else {
            return null;
        }
    }
}
