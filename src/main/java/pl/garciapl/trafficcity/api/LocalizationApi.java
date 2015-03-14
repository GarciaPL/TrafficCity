package pl.garciapl.trafficcity.api;


import pl.garciapl.trafficcity.dao.localization.LocalizationRequest;

/**
 * Created by lukasz on 30.11.14.
 */
public interface LocalizationApi {
    <T> T getLocalization(LocalizationRequest localizationRequest);
}
