package br.com.vindiesel.control;

import br.com.vindiesel.exceptions.BuscaCepException;
import br.com.vindiesel.interfaces.BuscaCepEventos;
import br.com.vindiesel.interfaces.BuscaCepEventosImpl;
import br.com.vindiesel.dao.DestinatarioDao;
import br.com.vindiesel.dao.EnderecoDao;
import br.com.vindiesel.model.Destinatario;
import br.com.vindiesel.model.Endereco;
import br.com.vindiesel.model.tablemodel.DestinatarioTableModel;
import br.com.vindiesel.model.EnderecoSigla;
import br.com.vindiesel.uteis.Mensagem;
import br.com.vindiesel.uteis.Texto;
import br.com.vindiesel.uteis.UtilTable;
import br.com.vindiesel.uteis.Validacao;
import br.com.vindiesel.view.TelaDestinatarioGerenciar;
import br.com.vindiesel.view.TelaPrincipal;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Will
 */
public class TelaDestinatarioGerenciarControl {

    private TelaDestinatarioGerenciar telaDestinatarioGerenciar;
    private Destinatario destinatario;
    private Endereco endereco;
    private DestinatarioDao destinatarioDao;
    private DestinatarioTableModel destinatarioTableModel;
    private EnderecoDao enderecoDao;
    private Integer linhaSelecionada;

    public TelaDestinatarioGerenciarControl() {
        destinatarioDao = new DestinatarioDao();
        enderecoDao = new EnderecoDao();
        destinatarioTableModel = new DestinatarioTableModel();
    }

    public void chamarTelaDestinatarioGerenciar() {
        if (telaDestinatarioGerenciar == null) {
            telaDestinatarioGerenciar = new TelaDestinatarioGerenciar(this);
            TelaPrincipal.desktopPane.add(telaDestinatarioGerenciar);
            telaDestinatarioGerenciar.setVisible(true);
        } else {
            if (telaDestinatarioGerenciar.isVisible()) {
                telaDestinatarioGerenciar.pack();
            } else {
                TelaPrincipal.desktopPane.add(telaDestinatarioGerenciar);
                telaDestinatarioGerenciar.setVisible(true);
            }
        }
        telaDestinatarioGerenciar.getTblDestinatario().setModel(destinatarioTableModel);
        carregarEstadosNaComboBox();
        destinatarioTableModel.limpar();
        destinatarioTableModel.adicionar(destinatarioDao.pesquisar());
        redimensionarTela();
    }

    private void redimensionarTela() {
        UtilTable.centralizarCabecalho(telaDestinatarioGerenciar.getTblDestinatario());
        UtilTable.redimensionar(telaDestinatarioGerenciar.getTblDestinatario(), 0, 50);
        UtilTable.redimensionar(telaDestinatarioGerenciar.getTblDestinatario(), 1, 350);
        UtilTable.redimensionar(telaDestinatarioGerenciar.getTblDestinatario(), 2, 133);
        UtilTable.redimensionar(telaDestinatarioGerenciar.getTblDestinatario(), 3, 160);
    }

    private void carregarEstadosNaComboBox() {
        telaDestinatarioGerenciar.getCbEstado().setModel(new DefaultComboBoxModel<>(EnderecoSigla.ESTADOS_BRASILEIROS));
    }

    private void cadastrarDestinatario() {
        destinatario = new Destinatario();
        destinatario.setNome(telaDestinatarioGerenciar.getTfNome().getText());
        destinatario.setCodigoPessoa(telaDestinatarioGerenciar.getTfCodigoPessoa().getText());

        endereco = new Endereco();
        endereco.setBairro(telaDestinatarioGerenciar.getTfBairro().getText());
        endereco.setCep(Integer.valueOf(telaDestinatarioGerenciar.getTfCep().getText()));
        endereco.setCidade(telaDestinatarioGerenciar.getTfCidade().getText());
        endereco.setComplemento(telaDestinatarioGerenciar.getTfComplemento().getText());
        endereco.setEstado((String) telaDestinatarioGerenciar.getCbEstado().getSelectedItem());
        endereco.setNumero(telaDestinatarioGerenciar.getTfNumero().getText());
        endereco.setRua(telaDestinatarioGerenciar.getTfRua().getText());

        if (Validacao.validaEntidade(destinatario) != null) {
            Mensagem.info(Validacao.validaEntidade(destinatario));
            destinatario = null;
            endereco = null;
            return;
        }

        Integer idEndereco = enderecoDao.inserir(endereco);

        endereco.setId(idEndereco);
        destinatario.setEndereco(endereco);
        Integer idInserido = destinatarioDao.inserir(destinatario);
        if (idInserido != 0) {
            destinatario.setId(idInserido);
            destinatarioTableModel.adicionar(destinatario);
            limparCampos();
            Mensagem.info(Texto.SUCESSO_CADASTRAR);
        } else {
            Mensagem.info(Texto.ERRO_CADASTRAR);
        }
        destinatario = null;

    }

    private void alterarDestinatario() {
        destinatario.setNome(telaDestinatarioGerenciar.getTfNome().getText());
        destinatario.setNome(telaDestinatarioGerenciar.getTfNome().getText());
        destinatario.setCodigoPessoa(telaDestinatarioGerenciar.getTfCodigoPessoa().getText());

        endereco = destinatario.getEndereco();
        endereco.setBairro(telaDestinatarioGerenciar.getTfBairro().getText());
        endereco.setCep(Integer.valueOf(telaDestinatarioGerenciar.getTfCep().getText()));
        endereco.setCidade(telaDestinatarioGerenciar.getTfCidade().getText());
        endereco.setComplemento(telaDestinatarioGerenciar.getTfComplemento().getText());
        endereco.setEstado((String) telaDestinatarioGerenciar.getCbEstado().getSelectedItem());
        endereco.setNumero(telaDestinatarioGerenciar.getTfNumero().getText());
        endereco.setRua(telaDestinatarioGerenciar.getTfRua().getText());

        if (Validacao.validaEntidade(destinatario) != null) {
            Mensagem.info(Validacao.validaEntidade(destinatario));
            return;
        }

        Boolean enderecoAlterado = enderecoDao.alterar(endereco);

        destinatario.setEndereco(endereco);
        boolean alterado = destinatarioDao.alterar(destinatario);
        linhaSelecionada = telaDestinatarioGerenciar.getTblDestinatario().getSelectedRow();
        if (alterado) {
            destinatarioTableModel.atualizar(linhaSelecionada, destinatario);
            Mensagem.info(Texto.SUCESSO_EDITAR);
            limparCampos();
        } else {
            Mensagem.erro(Texto.ERRO_EDITAR);
        }
        destinatario = null;
    }

    public void gravarDestinatarioAction() {
        if (destinatario == null) {
            cadastrarDestinatario();
            telaDestinatarioGerenciar.getTpDestinatario().setSelectedIndex(0); // seleciona o tabbed pane
        } else {
            alterarDestinatario();
            telaDestinatarioGerenciar.getTpDestinatario().setEnabledAt(0, true); // disabilita o tabbed pane
            telaDestinatarioGerenciar.getTpDestinatario().setSelectedIndex(0); // seleciona o tabbed pane
        }
    }

    public void desativarDestinatarioAction() {
        int retorno = Mensagem.confirmacao(Texto.PERGUNTA_DESATIVAR);
        if (retorno == JOptionPane.NO_OPTION) {
            return;
        }
        if (retorno == JOptionPane.CLOSED_OPTION) {
            return;
        }
        destinatario = destinatarioTableModel.pegaObjeto(telaDestinatarioGerenciar.getTblDestinatario().getSelectedRow());
        boolean deletado = destinatarioDao.deletar(destinatario);
        if (deletado) {
            destinatarioTableModel.remover(telaDestinatarioGerenciar.getTblDestinatario().getSelectedRow());
            telaDestinatarioGerenciar.getTblDestinatario().clearSelection();
            Mensagem.info(Texto.SUCESSO_DELETAR);
        } else {
            Mensagem.erro(Texto.ERRO_DELETAR);
        }
        destinatario = null;
    }

    public void pesquisarDestinatarioAction() {
        List<Destinatario> destinatariosPesquisados = destinatarioDao.pesquisar(telaDestinatarioGerenciar.getTfPesquisa().getText());
        if (destinatariosPesquisados == null) {
            destinatarioTableModel.limpar();
            destinatariosPesquisados = destinatarioDao.pesquisar();
        } else {
            destinatarioTableModel.limpar();
            destinatarioTableModel.adicionar(destinatariosPesquisados);
        }
    }

    public void carregarDestinatarioAction() {
        destinatario = destinatarioTableModel.pegaObjeto(telaDestinatarioGerenciar.getTblDestinatario().getSelectedRow());
        telaDestinatarioGerenciar.getTfNome().setText(destinatario.getNome());
        telaDestinatarioGerenciar.getTfCodigoPessoa().setText(destinatario.getCodigoPessoa());

        telaDestinatarioGerenciar.getTfBairro().setText(destinatario.getEndereco().getBairro());
        telaDestinatarioGerenciar.getTfCidade().setText(destinatario.getEndereco().getCidade());
        telaDestinatarioGerenciar.getTfComplemento().setText(destinatario.getEndereco().getComplemento());
        telaDestinatarioGerenciar.getCbEstado().getModel().setSelectedItem(destinatario.getEndereco().getEstado());
        telaDestinatarioGerenciar.getTfNumero().setText(destinatario.getEndereco().getNumero());
        telaDestinatarioGerenciar.getTfRua().setText(destinatario.getEndereco().getRua());
        telaDestinatarioGerenciar.getTfCep().setText(String.valueOf(destinatario.getEndereco().getCep()));

        telaDestinatarioGerenciar.getTpDestinatario().setEnabledAt(0, false); // disabilita o tabbed pane
        telaDestinatarioGerenciar.getTpDestinatario().setSelectedIndex(1); // seleciona o tabbed pane
        telaDestinatarioGerenciar.getTfNome().requestFocus();
    }

    public void buscarCepAction() {
        BuscaCepEventos buscaCepEvents = new BuscaCepEventosImpl();
        BuscadorDeCepControl buscadorDeCep = new BuscadorDeCepControl();
        try {
            buscadorDeCep.buscar(telaDestinatarioGerenciar.getTfCep().getText());
            Endereco endereco = new Endereco();
            endereco.setEstado(buscadorDeCep.getUf());
            endereco.setBairro(buscadorDeCep.getBairro());
            endereco.setCidade(buscadorDeCep.getCidade());
            endereco.setRua(buscadorDeCep.getLogradouro());
            endereco.setComplemento(buscadorDeCep.getComplemento());

            // mostra na tela o cep pesquisado
            telaDestinatarioGerenciar.getTfBairro().setText(endereco.getBairro());
            telaDestinatarioGerenciar.getTfCidade().setText(endereco.getCidade());
            telaDestinatarioGerenciar.getTfComplemento().setText(endereco.getComplemento());
            telaDestinatarioGerenciar.getCbEstado().getModel().setSelectedItem(endereco.getEstado());
            telaDestinatarioGerenciar.getTfRua().setText(endereco.getRua());
            telaDestinatarioGerenciar.getTfCep().setText(telaDestinatarioGerenciar.getTfCep().getText());
            telaDestinatarioGerenciar.getTfNumero().requestFocus();
        } catch (BuscaCepException buscaCepException) {
            System.out.println(buscaCepException.getMessage());
            buscaCepException.printStackTrace();
        } catch (NumberFormatException numberFormatException) {
            System.out.println(numberFormatException.getMessage());
            numberFormatException.printStackTrace();
        }
    }

    private boolean validarCampos() {
        if (telaDestinatarioGerenciar.getTfNome().getText().isEmpty()
                || telaDestinatarioGerenciar.getTfBairro().getText().isEmpty()
                || telaDestinatarioGerenciar.getTfCep().getText().isEmpty()
                || telaDestinatarioGerenciar.getTfCidade().getText().isEmpty()
                || telaDestinatarioGerenciar.getTfNumero().getText().isEmpty()
                || telaDestinatarioGerenciar.getTfNumero().getText().isEmpty()
                || telaDestinatarioGerenciar.getTfRua().getText().isEmpty()) {
            telaDestinatarioGerenciar.getTfNome().requestFocus();
            return true;
        }
        return false;
    }

    private void limparCampos() {
        telaDestinatarioGerenciar.getTfNome().setText("");
        telaDestinatarioGerenciar.getTfBairro().setText("");
        telaDestinatarioGerenciar.getTfEmail().setText("");
        telaDestinatarioGerenciar.getTfCep().setText("");
        telaDestinatarioGerenciar.getTfCidade().setText("");
        telaDestinatarioGerenciar.getTfComplemento().setText("");
        telaDestinatarioGerenciar.getCbEstado().setSelectedIndex(0);
        telaDestinatarioGerenciar.getTfNumero().setText("");
        telaDestinatarioGerenciar.getTfPesquisa().setText("");
        telaDestinatarioGerenciar.getTfRua().setText("");
        telaDestinatarioGerenciar.getTfNome().requestFocus();
    }

}
