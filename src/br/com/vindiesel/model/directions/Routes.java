/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.model.directions;

import java.util.List;

/**
 *
 * @author William
 */
public class Routes {
    
    private List<Legs> legs;

    public List<Legs> getLegs() {
        return legs;
    }

    public void setLegs(List<Legs> legs) {
        this.legs = legs;
    }

    @Override
    public String toString() {
        return "Routes{" + "legs=" + legs + '}';
    }
    
}
