/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.garciapl.trafficcity.dao.localization;

/**
 * @author lciesluk
 */
public class LocalizationRequest {

    private String acceptableAccuracy;
    private String address;
    private String requestedAccuracy;
    private Tolerance tolerance;
    private MaximumAge maximumAge;
    private ResponseTime responseTime;

    public LocalizationRequest() {
    }

    public LocalizationRequest(String acceptableAccuracy, String address, String requestedAccuracy, Tolerance tolerance, MaximumAge maximumAge, ResponseTime responseTime) {
        this.acceptableAccuracy = acceptableAccuracy;
        this.address = "tel:" + address;
        this.requestedAccuracy = requestedAccuracy;
        this.tolerance = tolerance;
        this.maximumAge = maximumAge;
        this.responseTime = responseTime;
    }

    public String getAcceptableAccuracy() {
        return acceptableAccuracy;
    }

    public LocalizationRequest setAcceptableAccuracy(String acceptableAccuracy) {
        this.acceptableAccuracy = acceptableAccuracy;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public LocalizationRequest setAddress(String address) {
        this.address = "tel:" + address;
        return this;
    }

    public String getRequestedAccuracy() {
        return requestedAccuracy;
    }

    public LocalizationRequest setRequestedAccuracy(String requestedAccuracy) {
        this.requestedAccuracy = requestedAccuracy;
        return this;
    }

    public Tolerance getTolerance() {
        return tolerance;
    }

    public LocalizationRequest setTolerance(Tolerance tolerance) {
        this.tolerance = tolerance;
        return this;
    }

    public MaximumAge getMaximumAge() {
        return maximumAge;
    }

    public LocalizationRequest setMaximumAge(MaximumAge maximumAge) {
        this.maximumAge = maximumAge;
        return this;
    }

    public ResponseTime getResponseTime() {
        return responseTime;
    }

    public LocalizationRequest setResponseTime(ResponseTime responseTime) {
        this.responseTime = responseTime;
        return this;
    }

    @Override
    public String toString() {
        return "LocalizationRequest{" +
                "acceptableAccuracy=" + acceptableAccuracy +
                ", address='" + address + '\'' +
                ", requestedAccuracy=" + requestedAccuracy +
                ", tolerance=" + tolerance +
                ", maximumAge=" + maximumAge +
                ", responseTime=" + responseTime +
                '}';
    }
}
