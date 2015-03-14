/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.garciapl.trafficcity.dao.localization;

/**
 * @author lciesluk
 */
public class ResponseTime {

    private Metric metric;
    private String units;

    public ResponseTime() {
    }

    public ResponseTime(Metric metric, String units) {
        this.metric = metric;
        this.units = units;
    }

    public Metric getMetric() {
        return metric;
    }

    public ResponseTime setMetric(Metric metric) {
        this.metric = metric;
        return this;
    }

    public String getUnits() {
        return units;
    }

    public ResponseTime setUnits(String units) {
        this.units = units;
        return this;
    }

}
