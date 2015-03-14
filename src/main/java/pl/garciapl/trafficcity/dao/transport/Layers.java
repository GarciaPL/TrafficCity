package pl.garciapl.trafficcity.dao.transport;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lukasz on 06.12.14.
 */
public enum Layers {
    @SerializedName("WMS/ENOM_Ulice")
    STREETS("WMS/ENOM_Ulice"),

    @SerializedName("WMS/Komunikacja_DworceKolejowe")
    RAIL_STATION("WMS/Komunikacja_DworceKolejowe"),

    @SerializedName("WMS/Komunikacja_Metro_wejscia")
    METRO_ENTRANCES("WMS/Komunikacja_Metro_wejscia"),

    @SerializedName("WMS/Komunikacja_Parkingi_P+R")
    PARK_AND_RIDE("WMS/Komunikacja_Parkingi_P+R"),

    @SerializedName("WMS/Komunikacja_PrzystankiKM(PKP)")
    RAILWAY_STOP_PKP("WMS/Komunikacja_PrzystankiKM(PKP)"),

    @SerializedName("WMS/Komunikacja_PrzystankiSKM")
    RAILWAY_STOP_SKM("WMS/Komunikacja_PrzystankiSKM"),

    @SerializedName("WMS/Komunikacja_PrzystankiWKD")
    RAILWAY_STOP_WKD("WMS/Komunikacja_PrzystankiWKD"),

    @SerializedName("WMS/Komunikacja_PrzystankiZTM")
    PUBLIC_TRANSPORT_STOP_ZTM("WMS/Komunikacja_PrzystankiZTM"),

    @SerializedName("WMS/Komunikacja_StacjeMetro")
    METRO_STATIONS("WMS/Komunikacja_StacjeMetro"),

    @SerializedName("WMS/Komunikacja_Strefa_płatnego_parkowania")
    PAID_PARKING_ZONES("WMS/Komunikacja_Strefa_płatnego_parkowania"),

    @SerializedName("WMS/Komunikacja_Tramwaje")
    TRAMS("WMS/Komunikacja_Tramwaje"),

    @SerializedName("WMS/Komunikacja_koleje_glowne")
    MAIN_RAILWAYS("WMS/Komunikacja_koleje_glowne"),

    @SerializedName("WMS/Komunikacja_msi_granica_1_strefy_taxi")
    TAXI_FIRST_ZONE("WMS/Komunikacja_msi_granica_1_strefy_taxi");

    private String layer;

    private Layers(String layer) {
        this.layer = layer;
    }

    public String getLayer() {
        return layer;
    }
}
