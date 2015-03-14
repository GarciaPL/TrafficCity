package pl.garciapl.trafficcity.dao.transport;

//import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lukasz on 06.12.14.
 */
public enum Format {
    @SerializedName("png")
    PNG("png"),

    @SerializedName("jpeg")
    JPEG("jpeg");

    private String format;

    private Format(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
