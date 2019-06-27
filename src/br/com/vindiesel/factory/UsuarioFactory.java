package br.com.vindiesel.factory;

import br.com.vindiesel.model.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author William
 */
public class UsuarioFactory {

    private static List<Usuario> usuarios = new ArrayList<>();

    public static java.util.Collection geraColecao() {
        return usuarios;
    }

    public static void populaOjetosNoRelatório(List<Usuario> usuariosList) {
        usuarios = usuariosList;
    }
}
