package pl.garciapl.trafficcity.dao.config;

/**
 * Created with IntelliJ IDEA.
 * User: lciesluk
 * Date: 12.12.14
 * Time: 09:11
 * To change this template use File | Settings | File Templates.
 */
public class ConfigResponse {

    private String propertyName;
    private String propertyValue;

    public ConfigResponse() {
    }

    public ConfigResponse(String propertyName, String propertyValue) {
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    @Override
    public String toString() {
        return "ConfigResponse{" +
                "propertyName='" + propertyName + '\'' +
                ", propertyValue='" + propertyValue + '\'' +
                '}';
    }
}
