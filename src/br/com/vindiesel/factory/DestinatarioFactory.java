package br.com.vindiesel.factory;

import br.com.vindiesel.model.Destinatario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author William
 */
public class DestinatarioFactory {

    private static List<Destinatario> destinatarios = new ArrayList<>();

    public static java.util.Collection geraColecao() {
        return destinatarios;
    }

    public static void populaOjetosNoRelatório(List<Destinatario> destinatariosList) {
        destinatarios = destinatariosList;
    }

}
