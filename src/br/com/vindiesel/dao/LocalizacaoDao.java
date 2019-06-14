/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.dao;

import br.com.vindiesel.model.Localizacao;
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
public class LocalizacaoDao {

    private static String URL = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private static String SUFIX = "&key=";
    private static String key = "AIzaSyCO3jDaXJT4K4FCTV2pGfWndcEAjA11DG4";

    private static String URL_FULL = "https://maps.googleapis.com/maps/api/geocode/json?address=88136000&key=AIzaSyCO3jDaXJT4K4FCTV2pGfWndcEAjA11DG4";

    public static Localizacao getLocalizacao() {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        String cep = "88133810";
//        HttpGet requisicaoGet = new HttpGet(URL + cep + SUFIX + key);
        HttpGet requisicaoGet = new HttpGet(URL_FULL);
//        requisicaoGet.addHeader("content-type", "application/json");
//        requisicaoGet.addHeader("Accept", "application/json");
        HttpResponse resposta;
        Localizacao localizacao;
        Gson gson = new Gson();

        try {
            resposta = closeableHttpClient.execute(requisicaoGet);
            HttpEntity entidade = resposta.getEntity();
            String conteudo = EntityUtils.toString(entidade);

            localizacao = gson.fromJson(conteudo, Localizacao.class);
            System.out.println(localizacao);

        } catch (Exception e) {
            localizacao = null;
        }

        return localizacao;
    }

    public static void main(String[] args) {
        getLocalizacao();

    }

}
