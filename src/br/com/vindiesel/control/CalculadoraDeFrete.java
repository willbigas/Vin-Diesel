package br.com.vindiesel.control;

import br.com.vindiesel.dao.DaoDirectionsAPI;
import br.com.vindiesel.dao.DaoGeocodingAPI;
import br.com.vindiesel.model.directions.Distancia;
import br.com.vindiesel.model.geocoding.Location;

/**
 *
 * @author William
 */
public class CalculadoraDeFrete {

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
        
        buscaALocalizacaoDoPrimeiroCep(primeiroCep);
        
        buscaALocalizacaoDoSegundoCep(segundoCep);
        
        distancia = DaoDirectionsAPI.getDistancia(primeiraLocalizacao, segundaLocalizacao);

        if (distancia == null) {
            return null; // deu erro vai pro metodo manual
        }
        String distanciaEncontrada = distancia.getRoutes().get(0).getLegs().get(0).getDistance().getText();
        String[] campos = distanciaEncontrada.split(" ");
        return campos[0];
    }

    private void buscaALocalizacaoDoSegundoCep(String segundoCep) {
        segundaLocalizacao = DaoGeocodingAPI.getLocalizacao(segundoCep); // Tenta pela Primeira vez o Segundo cep
        
        segundaLocalizacao =  TentaPelaSegundaVez(segundoCep , segundaLocalizacao); // Tenta pela Segunda vez o Segundo cep
    }

    private void buscaALocalizacaoDoPrimeiroCep(String primeiroCep) {
        primeiraLocalizacao = DaoGeocodingAPI.getLocalizacao(primeiroCep);  // Tenta pela primeira vez o Primeiro cep
        
        primeiraLocalizacao = TentaPelaSegundaVez(primeiroCep , primeiraLocalizacao);  // Tenta pela segunda vez o Primeiro cep
    }

    private Location TentaPelaSegundaVez(String cep , Location localizacao) {
        if (localizacao == null) {
            String cepGenerico = "";
            String campos[] = cep.split("");
            for (int i = 0; i < campos.length; i++) {
                if (i == 6) {
                    break;
                }
                cepGenerico += campos[i];

            }
            cepGenerico += "00";
            System.out.println("Tentativa de Genérico iniciada no no CEP com o Cep :" + cepGenerico);
            localizacao = DaoGeocodingAPI.getLocalizacao(cepGenerico);
            return localizacao;
            // se null não encontrou  o cep na segunda tentativa

        }
        return localizacao;
    }

     
}
