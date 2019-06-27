package br.com.vindiesel.control.report;

import br.com.vindiesel.dao.UsuarioDao;
import br.com.vindiesel.model.Usuario;
import br.com.vindiesel.uteis.Relatorio;
import br.com.vindiesel.view.TelaPrincipal;
import br.com.vindiesel.view.TelaUsuarioGerenciarRelatorio;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author William
 */
public class TelaUsuarioGerenciarRelatorioControl {

    TelaUsuarioGerenciarRelatorio telaUsuarioGerenciarRelatorio;
    UsuarioDao usuarioDao;
    Usuario usuario;
    List<Usuario> listUsuarios;

    public TelaUsuarioGerenciarRelatorioControl() {
        usuarioDao = new UsuarioDao();
    }

    public void chamarTelaUsuarioGerenciarRelatorio() {

        if (telaUsuarioGerenciarRelatorio == null) {
            telaUsuarioGerenciarRelatorio = new TelaUsuarioGerenciarRelatorio(this);
            TelaPrincipal.desktopPane.add(telaUsuarioGerenciarRelatorio);
            telaUsuarioGerenciarRelatorio.setVisible(true);
        } else {
            if (telaUsuarioGerenciarRelatorio.isVisible()) {
                telaUsuarioGerenciarRelatorio.pack();
            } else {
                TelaPrincipal.desktopPane.add(telaUsuarioGerenciarRelatorio);
                telaUsuarioGerenciarRelatorio.setVisible(true);
            }
        }
    }

    public void acionarRelatorioAction() {
        listUsuarios = usuarioDao.pesquisar("");
        chamarRelatorioDestinatario(listUsuarios);

    }

    private void chamarRelatorioDestinatario(List<Usuario> usuarios) {
        InputStream jasperFile = getClass().getResourceAsStream("/reports/usuariosList.jasper");
        List<Object> objects = (List<Object>) (Object) usuarios;
        Relatorio.chamarRelatorio(jasperFile, null, objects);
    }

}
