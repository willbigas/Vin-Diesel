package br.com.vindiesel.control;

import br.com.vindiesel.dao.DistanciaDao;
import br.com.vindiesel.dao.LocalizacaoDao;
import br.com.vindiesel.model.Distancia;
import br.com.vindiesel.model.Location;

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
        primeiraLocalizacao = LocalizacaoDao.getLocalizacao(primeiroCep);
        segundaLocalizacao = LocalizacaoDao.getLocalizacao(segundoCep);
        distancia = DistanciaDao.getDistancia(primeiraLocalizacao, segundaLocalizacao);
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
        primeiraLocalizacao = LocalizacaoDao.getLocalizacao(primeiroCep);
        segundaLocalizacao = LocalizacaoDao.getLocalizacao(segundoCep);
        distancia = DistanciaDao.getDistancia(primeiraLocalizacao, segundaLocalizacao);
        return distancia.getRoutes().get(0).getLegs().get(0).getDistance().getValue();
    }

}
