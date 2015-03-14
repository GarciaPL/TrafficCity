package pl.garciapl.trafficcity.exception;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by lukasz on 30.11.14.
 */
public class USSDErrorHandler implements ResponseErrorHandler {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {

        if (clientHttpResponse.getRawStatusCode() != 200) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {

    }
}
