package pl.garciapl.trafficcity.dao.transport;


/**
 * Created by lukasz on 06.12.14.
 */
public class Zoom {

    private int zoom;

    public Zoom(int zoom) {
        this.zoom = zoom;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    @Override
    public String toString() {
        return "Zoom{" +
                "zoom=" + zoom +
                '}';
    }
}
