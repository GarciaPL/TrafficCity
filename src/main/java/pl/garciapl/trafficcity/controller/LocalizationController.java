package pl.garciapl.trafficcity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.garciapl.trafficcity.api.CredentialsApi;
import pl.garciapl.trafficcity.api.LocalizationApi;
import pl.garciapl.trafficcity.dao.localization.LocalizationRequest;
import pl.garciapl.trafficcity.dao.localization.LocalizationResponse;
import pl.garciapl.trafficcity.dao.localization.LocalizationResponseFailure;
import pl.garciapl.trafficcity.dao.localization.Tolerance;

/**
 * Created by lukasz on 30.11.14.
 */
@Controller
@RequestMapping("/localizationapi")
public class LocalizationController {

    private LocalizationApi localizationApi;
    private CredentialsApi credentials;

    @RequestMapping(value = "/getlocalization", method = RequestMethod.GET)
    public
    @ResponseBody
    String getLocalization() {

        Object localization = localizationApi.getLocalization(new LocalizationRequest().setAcceptableAccuracy("30")
                .setAddress(credentials.getLogin()).setRequestedAccuracy("25")
                .setTolerance(Tolerance.LOW_DELAY));

//        setMaximumAge(new MaximumAge().setMetric(Metric.DAY).setUnits(5))
//                .setResponseTime(new ResponseTime().setMetric(Metric.HOUR).setUnits(6)))

        if (localization != null) {
            if (localization instanceof LocalizationResponse) {
                LocalizationResponse localizationResponse = (LocalizationResponse) localization;
                return localizationResponse.toString();
            } else if (localization instanceof LocalizationResponseFailure) {
                LocalizationResponseFailure localizationResponseFailure = (LocalizationResponseFailure) localization;
                return localizationResponseFailure.toString();
            } else {
                return "No content";
            }
        } else {
            return "No content";
        }
    }

    @Autowired
    public void setLocalizationApi(@Qualifier("localizationapi") final LocalizationApi localizationApi) {
        this.localizationApi = localizationApi;
    }

    @Autowired
    public void setCredentials(@Qualifier("credentials") final CredentialsApi credentials) {
        this.credentials = credentials;
    }
}
