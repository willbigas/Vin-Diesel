package br.com.vindiesel.factory;

import br.com.vindiesel.model.Encomenda;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author William
 */
public class EncomendaFactory {

    private static List<Encomenda> encomendas = new ArrayList<>();

    public static java.util.Collection geraColecao() {
        return encomendas;
    }

    public static void populaOjetosNoRelatório(List<Encomenda> encomendasList) {
        encomendas = encomendasList;
    }
}
