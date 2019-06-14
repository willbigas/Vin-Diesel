package br.com.vindiesel.model;

/**
 *
 * @author William
 */
public class Legs {
    
    private Distance distance;

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Legs{" + "distance=" + distance + '}';
    }
    
    
}
