package br.com.vindiesel.dao;

import br.com.vindiesel.model.geocoding.Localizacao;
import br.com.vindiesel.model.geocoding.Location;
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
public class DaoGeocodingAPI {

    private static String URL = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private static String SUFIX = "&key=AIzaSyASECb7nFXRCzaTET55r_Nwe3pY-C6y7xM";

    private static String URL_FULL = "https://maps.googleapis.com/maps/api/geocode/json?address=88133810&key=AIzaSyASECb7nFXRCzaTET55r_Nwe3pY-C6y7xM";

    public static Location getLocalizacao(String cep) {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        HttpGet requisicaoGet = new HttpGet(URL + cep + SUFIX);
        
        HttpResponse resposta;
        Localizacao localizacao;
        Gson gson = new Gson();

        try {
            resposta = closeableHttpClient.execute(requisicaoGet);
            HttpEntity entidade = resposta.getEntity();
            String conteudo = EntityUtils.toString(entidade);

            localizacao = gson.fromJson(conteudo, Localizacao.class);
            closeableHttpClient.close();
            return localizacao.getResults().get(0).getGeometry().getLocation();

        } catch (Exception e) {
            localizacao = null;
        }

        return null;
    }

}
