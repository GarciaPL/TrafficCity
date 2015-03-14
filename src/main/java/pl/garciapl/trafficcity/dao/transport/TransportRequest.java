package pl.garciapl.trafficcity.dao.transport;

import java.util.List;

/**
 * Created by lukasz on 06.12.14.
 */
public class TransportRequest {

    private Coordinates coordinates;
    private Zoom zoom;
    private Size size;
    private String format;
    private List<Layers> layers;

    public TransportRequest(Coordinates coordinates, Zoom zoom, Size size, String format, List<Layers> layers) {
        this.coordinates = coordinates;
        this.zoom = zoom;
        this.size = size;
        this.format = format;
        this.layers = layers;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public TransportRequest setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public Zoom getZoom() {
        return zoom;
    }

    public TransportRequest setZoom(Zoom zoom) {
        this.zoom = zoom;
        return this;
    }

    public Size getSize() {
        return size;
    }

    public TransportRequest setSize(Size size) {
        this.size = size;
        return this;
    }

    public String getFormat() {
        return format;
    }

    public TransportRequest setFormat(Format format) {
        this.format = format.getFormat();
        return this;
    }

    public List<Layers> getLayers() {
        return layers;
    }

    public String getLayersAll() {
        String all_layers = "";
        for (int i = 0; i < this.layers.size(); i++) {
            all_layers += this.layers.get(i).getLayer();
            if (i + 1 < this.layers.size()) {
                all_layers += ",";
            }
        }
        return all_layers;
    }

    public TransportRequest setLayers(List<Layers> layers) {
        this.layers = layers;
        return this;
    }

    @Override
    public String toString() {
        return "TransportRequest{" +
                "coordinates=" + coordinates +
                ", zoom=" + zoom +
                ", size=" + size +
                ", format='" + format + '\'' +
                ", layers=" + layers +
                '}';
    }
}
