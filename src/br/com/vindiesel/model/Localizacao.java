/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author agostinho.junior
 */
public class Localizacao {

    private List<Results> results;

    public Localizacao() {
        this.results =  new ArrayList<>();
    }
    

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Localizacao{" + "results=" + results + '}';
    }

}
