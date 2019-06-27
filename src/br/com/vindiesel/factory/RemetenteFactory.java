package br.com.vindiesel.factory;

import br.com.vindiesel.model.Remetente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author William
 */
public class RemetenteFactory {

    private static List<Remetente> remetentes = new ArrayList<>();

    public static java.util.Collection geraColecao() {
        return remetentes;
    }

    public static void populaOjetosNoRelatório(List<Remetente> remetentesList) {
        remetentes = remetentesList;
    }
}
