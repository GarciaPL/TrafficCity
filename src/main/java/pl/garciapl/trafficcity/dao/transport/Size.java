package pl.garciapl.trafficcity.dao.transport;

/**
 * Created by lukasz on 06.12.14.
 */
public class Size {

    private int sizex;
    private int sizey;

    public Size(int sizex, int sizey) {
        this.sizex = sizex;
        this.sizey = sizey;
    }

    public int getSizex() {
        return sizex;
    }

    public Size setSizex(int sizex) {
        this.sizex = sizex;
        return this;
    }

    public int getSizey() {
        return sizey;
    }

    public Size setSizey(int sizey) {
        this.sizey = sizey;
        return this;
    }

    @Override
    public String toString() {
        return "Size{" +
                "sizex=" + sizex +
                ", sizey=" + sizey +
                '}';
    }
}
