package br.com.vindiesel.control;

import br.com.vindiesel.dao.TipoUsuarioDao;
import br.com.vindiesel.model.TipoPermissao;
import br.com.vindiesel.model.TipoUsuario;
import br.com.vindiesel.model.tablemodel.TipoUsuarioTableModel;
import br.com.vindiesel.uteis.Mensagem;
import br.com.vindiesel.uteis.Texto;
import br.com.vindiesel.uteis.Validacao;
import br.com.vindiesel.view.TelaPrincipal;
import br.com.vindiesel.view.TelaTipoUsuarioGerenciar;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Will
 */
public class TelaTipoUsuarioGerenciarControl {

    TipoUsuarioDao tipoUsuarioDao;
    TipoUsuarioTableModel tipoUsuarioTableModel;
    List<TipoPermissao> listTipoPermissao;
    TelaTipoUsuarioGerenciar telaTipoUsuarioGerenciar;
    TipoUsuario tipoUsuario;
    TipoPermissao tipoPermissaoSelecionado;
    Integer linhaSelecionada;

    private static final int ADMIN = 1;
    private static final int FUNCIONARIO = 2;

    public TelaTipoUsuarioGerenciarControl() {
        tipoUsuarioDao = new TipoUsuarioDao();
        tipoUsuarioTableModel = new TipoUsuarioTableModel();
    }

    public void chamarTelaTipoUsuarioGerenciar() {
        if (telaTipoUsuarioGerenciar == null) { // se tiver nulo chama janela normalmente
            telaTipoUsuarioGerenciar = new TelaTipoUsuarioGerenciar(this);
            TelaPrincipal.desktopPane.add(telaTipoUsuarioGerenciar);
            telaTipoUsuarioGerenciar.setVisible(true);
        } else {//se ele estiver criado
            if (telaTipoUsuarioGerenciar.isVisible()) {
                telaTipoUsuarioGerenciar.pack();//Redimensiona ao Quadro Original
            } else {
                TelaPrincipal.desktopPane.add(telaTipoUsuarioGerenciar);
                telaTipoUsuarioGerenciar.setVisible(true);
            }
        }
        criarListaDePermisssoesDeUsuarios();
        carregarComboBoxDeTipoPermissao();
        telaTipoUsuarioGerenciar.getTblTipoUsuario().setModel(tipoUsuarioTableModel);
        atualizarTabelaTipoUsuario();
    }

    private void atualizarTabelaTipoUsuario() {
        tipoUsuarioTableModel.adicionar(tipoUsuarioDao.pesquisar());
    }

    private void criarListaDePermisssoesDeUsuarios() {
        listTipoPermissao = new ArrayList<>();
        TipoPermissao administrador = new TipoPermissao();
        administrador.setId(ADMIN);
        administrador.setNome("Administrador");
        listTipoPermissao.add(administrador);
        TipoPermissao caixa = new TipoPermissao();
        caixa.setId(FUNCIONARIO);
        caixa.setNome("Funcionario");
        listTipoPermissao.add(caixa);
    }

    private void carregarComboBoxDeTipoPermissao() {
        DefaultComboBoxModel<TipoPermissao> model = new DefaultComboBoxModel(listTipoPermissao.toArray());
        telaTipoUsuarioGerenciar.getCbPermissao().setModel(model);
    }

    private void inserirTipoUsuario() {
        tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome(telaTipoUsuarioGerenciar.getTfNome().getText());
        if (telaTipoUsuarioGerenciar.getCheckAtivo().isSelected()) {
            tipoUsuario.setAtivo(true);
        } else {
            tipoUsuario.setAtivo(false);
        }
        tipoPermissaoSelecionado = (TipoPermissao) telaTipoUsuarioGerenciar.getCbPermissao().getSelectedItem();
        tipoUsuario.setTipoPermissao(tipoPermissaoSelecionado.getId());

        if (Validacao.validaEntidade(tipoUsuario) != null) {
            Mensagem.info(Validacao.validaEntidade(tipoUsuario));
            tipoUsuario = null;
            return;
        }

        int idInserido = tipoUsuarioDao.inserir(tipoUsuario);
        if (idInserido == 0) {
            Mensagem.erro(Texto.ERRO_CADASTRAR);
            return;
        }
        if (idInserido != 0) {
            tipoUsuario.setId(idInserido);
            tipoUsuarioTableModel.adicionar(tipoUsuario);
            limparCamposTipoUsuario();
            Mensagem.info(Texto.SUCESSO_CADASTRAR);
        }
        tipoUsuario = null;
    }

    private void alterarTipoUsuario() {
        tipoUsuario = tipoUsuarioTableModel.pegaObjeto(telaTipoUsuarioGerenciar.getTblTipoUsuario().getSelectedRow());
        tipoUsuario.setNome(telaTipoUsuarioGerenciar.getTfNome().getText());
        if (telaTipoUsuarioGerenciar.getCheckAtivo().isSelected()) {
            tipoUsuario.setAtivo(true);
        } else {
            tipoUsuario.setAtivo(false);
        }
        tipoPermissaoSelecionado = (TipoPermissao) telaTipoUsuarioGerenciar.getCbPermissao().getSelectedItem();
        tipoUsuario.setTipoPermissao(tipoPermissaoSelecionado.getId());

        if (Validacao.validaEntidade(tipoUsuario) != null) {
            Mensagem.info(Validacao.validaEntidade(tipoUsuario));
            tipoUsuario = null;
            return;
        }

        boolean alterado = tipoUsuarioDao.alterar(tipoUsuario);
        linhaSelecionada = telaTipoUsuarioGerenciar.getTblTipoUsuario().getSelectedRow();
        if (alterado == false) {
            Mensagem.erro(Texto.ERRO_EDITAR);
            return;
        }
        if (alterado == true) {
            tipoUsuarioTableModel.atualizar(linhaSelecionada, tipoUsuario);
            limparCamposTipoUsuario();
            Mensagem.info(Texto.SUCESSO_EDITAR);
        }
        tipoUsuario = null;
    }

    public void desativarTipoUsuarioAction() {
        int retorno = Mensagem.confirmacao(Texto.PERGUNTA_DESATIVAR);

        if (retorno == JOptionPane.NO_OPTION) {
            return;
        }
        if (retorno == JOptionPane.YES_OPTION) {
            tipoUsuario = tipoUsuarioTableModel.pegaObjeto(telaTipoUsuarioGerenciar.getTblTipoUsuario().getSelectedRow());
            boolean deletado = tipoUsuarioDao.desativar(tipoUsuario);
            if (deletado) {
                tipoUsuarioTableModel.remover(telaTipoUsuarioGerenciar.getTblTipoUsuario().getSelectedRow());
                telaTipoUsuarioGerenciar.getTblTipoUsuario().clearSelection();
                Mensagem.info(Texto.SUCESSO_DESATIVAR);
            } else {
                Mensagem.erro(Texto.ERRO_DESATIVAR);
            }
        }
        tipoUsuario = null;
    }

    public void carregarTipoUsuarioAction() {
        tipoUsuario = tipoUsuarioTableModel.pegaObjeto(telaTipoUsuarioGerenciar.getTblTipoUsuario().getSelectedRow());
        telaTipoUsuarioGerenciar.getTfNome().setText(tipoUsuario.getNome());
        if (tipoUsuario.getTipoPermissao() == ADMIN) {
            telaTipoUsuarioGerenciar.getCbPermissao().getModel().setSelectedItem(listTipoPermissao.get(ADMIN - 1));
        }
        if (tipoUsuario.getTipoPermissao() == FUNCIONARIO) {
            telaTipoUsuarioGerenciar.getCbPermissao().getModel().setSelectedItem(listTipoPermissao.get(FUNCIONARIO - 1));
        }
        if (tipoUsuario.getAtivo() == true) {
            telaTipoUsuarioGerenciar.getCheckAtivo().setSelected(true);
        }
        if (tipoUsuario.getAtivo() == false) {
            telaTipoUsuarioGerenciar.getCheckAtivo().setSelected(false);
        }
    }

    public void gravarTipoUsuarioAction() {
        if (tipoUsuario == null) {
            inserirTipoUsuario();
        } else {
            alterarTipoUsuario();
        }
    }

    private void limparCamposTipoUsuario() {
        telaTipoUsuarioGerenciar.getTfNome().setText("");
        telaTipoUsuarioGerenciar.getCheckAtivo().setSelected(false);
        telaTipoUsuarioGerenciar.getCbPermissao().getModel().setSelectedItem(listTipoPermissao.get(ADMIN - 1));
        telaTipoUsuarioGerenciar.getTblTipoUsuario().clearSelection();
        telaTipoUsuarioGerenciar.getTfNome().requestFocus();

    }

}
