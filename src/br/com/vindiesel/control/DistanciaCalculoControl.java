package br.com.vindiesel.control;

import br.com.vindiesel.dao.DaoDirectionsAPI;
import br.com.vindiesel.dao.DaoGeocodingAPI;
import br.com.vindiesel.model.directions.Distancia;
import br.com.vindiesel.model.geocoding.Location;

/**
 *
 * @author William
 */
public class DistanciaCalculoControl {

    Location primeiraLocalizacao;
    Location segundaLocalizacao;
    Distancia distancia;

    /**
     * Calcula Distancia entre 2 ceps usando 2 apis do google Atenção com a key
     * do Google Cloud platform
     *
     * @param primeiroCep
     * @param segundoCep
     * @return - Retorna a Distancia dos 2 ceps aproxima em kms, usando a rota
     * mais curta
     */
    public String calculaDistanciaEmKm(String primeiroCep, String segundoCep) {
        primeiraLocalizacao = DaoGeocodingAPI.getLocalizacao(primeiroCep);
        segundaLocalizacao = DaoGeocodingAPI.getLocalizacao(segundoCep);
        distancia = DaoDirectionsAPI.getDistancia(primeiraLocalizacao, segundaLocalizacao);
        String distanciaEncontrada = distancia.getRoutes().get(0).getLegs().get(0).getDistance().getText();
        String[] campos = distanciaEncontrada.split(" ");
        
        return campos[0];
    }

    /**
     * Calcula Distancia entre 2 ceps usando 2 apis do google *Atenção com a key
     * do Google Cloud platform*
     *
     * @param primeiroCep
     * @param segundoCep
     * @return - Retorna a Distancia dos 2 ceps aproxima em metros, usando a rota
     * mais curta
     */
    public Integer calculaDistanciaEmMetros(String primeiroCep, String segundoCep) {
        primeiraLocalizacao = DaoGeocodingAPI.getLocalizacao(primeiroCep);
        segundaLocalizacao = DaoGeocodingAPI.getLocalizacao(segundoCep);
        distancia = DaoDirectionsAPI.getDistancia(primeiraLocalizacao, segundaLocalizacao);
        return distancia.getRoutes().get(0).getLegs().get(0).getDistance().getValue();
    }

}
