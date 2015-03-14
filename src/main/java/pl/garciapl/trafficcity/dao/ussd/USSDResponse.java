package pl.garciapl.trafficcity.dao.ussd;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by lukasz on 30.11.14.
 */
@XmlRootElement()
public class USSDResponse {

    @XmlElement
    private String result;
    @XmlElement
    private String id;
    @XmlElement
    private String deliveryStatus;

    public USSDResponse() {
    }

    public USSDResponse(String result, String id, String deliveryStatus) {
        this.result = result;
        this.id = id;
        this.deliveryStatus = deliveryStatus;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public String toString() {
        return "USSDResponse{" +
                "result='" + result + '\'' +
                ", id='" + id + '\'' +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                '}';
    }
}


