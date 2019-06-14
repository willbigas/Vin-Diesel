package br.com.vindiesel.control;

import br.com.vindiesel.dao.UsuarioDao;
import br.com.vindiesel.model.Usuario;
import br.com.vindiesel.uteis.Mensagem;
import br.com.vindiesel.uteis.Texto;
import br.com.vindiesel.view.TelaLogin;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author William
 */
public class TelaLoginControl {

    TelaLogin telaLogin;
    TelaPrincipalControl telaPrincipalControl;
    UsuarioDao usuarioDao;
    Usuario usuario;
    public Integer tipoUsuarioLogado;

    public static final int ADMINISTRADOR = 1;
    public static final int FUNCIONARIO = 2;

    public TelaLoginControl() {
        usuarioDao = new UsuarioDao();
    }

    public void chamarTelaLoginAction() {
        telaLogin = new TelaLogin(this);
        telaLogin.setLocationRelativeTo(null);
        telaLogin.setVisible(true);
    }

    private void chamarTelaPrincipal() {
        telaPrincipalControl = new TelaPrincipalControl();
        telaPrincipalControl.chamarTelaPrincipal();
    }

    public void acessarTelaPrincipalAction() {
        usuario = usuarioDao.pesquisarLogin(telaLogin.getTfLogin().getText());
        if (usuario == null) {
            Mensagem.info(Texto.ERRO_USUARIO);
            return;
        }
        /**
         * Get password retorna Char[] String então deve se instanciar uma nova
         * string com os caracteres.
         */
        if (!usuario.getSenha().equals(new String(telaLogin.getTfSenha().getPassword()))) {
            Mensagem.atencao(Texto.SENHA_USUARIO);
            return;
        }

        if (usuario.getTipoUsuario().getTipoPermissao() == ADMINISTRADOR) {
            tipoUsuarioLogado = ADMINISTRADOR;
            chamarTelaPrincipal();

        }

        if (usuario.getTipoUsuario().getTipoPermissao() == FUNCIONARIO) {
            tipoUsuarioLogado = FUNCIONARIO;
            chamarTelaPrincipal();

        }
        telaLogin.dispose();
    }

    public void abrirLink(String endereco) {
        try {
            Desktop desktop = null;
            //Primeiro verificamos se é possível a integração com o desktop
            if (!Desktop.isDesktopSupported()) {
                throw new IllegalStateException("Erro ao acessar sua area de Trabalho , Contate o administrador do sistema.");
            }
            desktop = Desktop.getDesktop();
            //Agora vemos se é possível disparar o browser default.
            if (!desktop.isSupported(Desktop.Action.BROWSE)) {
                throw new IllegalStateException("Navegador Padrão não encontrado!");
            }
            URI uri = new URI(endereco);
            desktop.browse(uri);
            //Dispara o browser default, que pode ser o Explorer, Firefox ou outro.
        } catch (IllegalStateException illegalStateException) {
        } catch (URISyntaxException uRISyntaxException) {
        } catch (IOException iOException) {
        }
    }

}
