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
public enum Tolerance {
    @SerializedName("NoDelay")
    NO_DELAY("NoDelay"),

    @SerializedName("LowDelay")
    LOW_DELAY("LowDelay"),

    @SerializedName("DelayTolerant")
    DELAY_TOLERANT("DelayTolerant");

    private String tolerance;

    private Tolerance(String tolerance) {
        this.tolerance = tolerance;
    }

    public String getTolerance() {
        return tolerance;
    }

}
