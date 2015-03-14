/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.garciapl.trafficcity.dao.localization;

import com.google.gson.annotations.SerializedName;

/**
 * @author lciesluk
 */
public enum Metric {

    @SerializedName("Milisecond")
    MILISECOND("Milisecond"),

    @SerializedName("Second")
    SECOND("Second"),

    @SerializedName("Minute")
    MINUTE("Minute"),

    @SerializedName("Hour")
    HOUR("Hour"),

    @SerializedName("Day")
    DAY("Day"),

    @SerializedName("Week")
    WEEK("Week"),

    @SerializedName("Month")
    MONTH("Month"),

    @SerializedName("Year")
    YEAR("Year");

    private String metric;

    private Metric(String metric) {
        this.metric = metric;
    }

    public String getMetric() {
        return metric;
    }
}
