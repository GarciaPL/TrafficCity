package pl.garciapl.trafficcity.mongodb.request;

import java.util.List;

/**
 * Created by lukasz on 10.12.14.
 */
public class UserMarkers {

    private List<SingleMarker> markers;

    public UserMarkers(List<SingleMarker> markers) {
        this.markers = markers;
    }

    public List<SingleMarker> getMarkers() {
        return markers;
    }

    public void setMarkers(List<SingleMarker> markers) {
        this.markers = markers;
    }

    @Override
    public String toString() {
        return "UserMarkers{" +
                "markers=" + markers +
                '}';
    }
}
