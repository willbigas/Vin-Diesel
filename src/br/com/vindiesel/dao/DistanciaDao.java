/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.dao;

import br.com.vindiesel.model.Distancia;
import br.com.vindiesel.model.Localizacao;
import br.com.vindiesel.model.Location;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author agostinho.junior
 */
public class DistanciaDao {

    private static String URL = "https://maps.googleapis.com/maps/api/directions/json?origin=";
    private static String MIDDLE = "&destination=";
    private static String SUFIX = "&key=AIzaSyASECb7nFXRCzaTET55r_Nwe3pY-C6y7xM";

    private static String URL_FULL = "https://maps.googleapis.com/maps/api/directions/json?origin=-27.6259902,-48.66934&destination=-27.6344743,-48.64885959999999&key=AIzaSyASECb7nFXRCzaTET55r_Nwe3pY-C6y7xM";

    public static Distancia getDistancia(Location primeiroLocal, Location segundoLocal) {

        String primeiraDirecao = primeiroLocal.getLat().toString() + "," + primeiroLocal.getLng();
        String segundaDirecao = segundoLocal.getLat().toString() + "," + segundoLocal.getLng();

        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        HttpGet requisicaoGet = new HttpGet(URL + primeiraDirecao + MIDDLE + segundaDirecao + SUFIX);
//        HttpGet requisicaoGet = new HttpGet();
//        requisicaoGet.addHeader("content-type", "application/json");
//        requisicaoGet.addHeader("Accept", "application/json");
        HttpResponse resposta;
        Distancia distanciaCalculada;
        Gson gson = new Gson();

        try {
            resposta = closeableHttpClient.execute(requisicaoGet);
            HttpEntity entidade = resposta.getEntity();
            String conteudo = EntityUtils.toString(entidade);

            distanciaCalculada = gson.fromJson(conteudo, Distancia.class);
            closeableHttpClient.close();
            return distanciaCalculada;

        } catch (Exception e) {
            distanciaCalculada = null;
        }

        return null;
    }

    public static void main(String[] args) {
        Location localizacao1 = LocalizacaoDao.getLocalizacao("88133810");
        Location localizacao2 = LocalizacaoDao.getLocalizacao("88130400");
        System.out.println("Primeiro Cep: 88133810");
        System.out.println("Segundo Cep: 88130400");
        Distancia distancia = getDistancia(localizacao1, localizacao2);
        System.out.println("Distancia em KM: " + distancia.getRoutes().get(0).getLegs().get(0).getDistance().getText());
        System.out.println("Distancia em Metros: " + distancia.getRoutes().get(0).getLegs().get(0).getDistance().getValue());

    }

}
