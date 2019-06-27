package br.com.vindiesel.factory;

import br.com.vindiesel.model.Encomenda;
import br.com.vindiesel.model.Entrega;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author William
 */
public class EntregaFactory {

    private static List<Entrega> entregas = new ArrayList<>();

    public static java.util.Collection geraColecao() {
        return entregas;
    }

    public static void populaOjetosNoRelatório(List<Entrega> entregasList) {
        entregas = entregasList;
    }
}
