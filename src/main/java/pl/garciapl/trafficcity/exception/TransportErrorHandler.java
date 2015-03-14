package pl.garciapl.trafficcity.exception;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by lukasz on 09.12.14.
 */
public class TransportErrorHandler implements ResponseErrorHandler {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return true;
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
    }
}
