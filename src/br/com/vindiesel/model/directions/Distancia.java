package br.com.vindiesel.model.directions;

import java.util.List;

/**
 *
 * @author William
 */
public class Distancia {

    private List<Routes> routes;

    public List<Routes> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Routes> routes) {
        this.routes = routes;
    }

    @Override
    public String toString() {
        return "Distancia{" + "routes=" + routes + '}';
    }

}
