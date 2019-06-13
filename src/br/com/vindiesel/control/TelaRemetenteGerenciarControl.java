package br.com.vindiesel.control;

import br.com.vindiesel.exceptions.BuscaCepException;
import br.com.vindiesel.interfaces.BuscaCepEventos;
import br.com.vindiesel.interfaces.BuscaCepEventosImpl;
import br.com.vindiesel.model.EnderecoSigla;
import br.com.vindiesel.dao.EnderecoDao;
import br.com.vindiesel.dao.RemetenteDao;
import br.com.vindiesel.model.Endereco;
import br.com.vindiesel.model.Remetente;
import br.com.vindiesel.model.tablemodel.RemetenteTableModel;
import br.com.vindiesel.uteis.Mensagem;
import br.com.vindiesel.uteis.Texto;
import br.com.vindiesel.uteis.Validacao;
import br.com.vindiesel.view.TelaRemetenteGerenciar;
import br.com.vindiesel.view.TelaPrincipal;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Will
 */
public class TelaRemetenteGerenciarControl {

    TelaRemetenteGerenciar telaRemetenteGerenciar;
    Remetente remetente;
    RemetenteDao remetenteDao;
    RemetenteTableModel remetenteTableModel;
    EnderecoDao enderecoDao;
    Integer linhaSelecionada;

    public TelaRemetenteGerenciarControl() {
        remetenteDao = new RemetenteDao();
        enderecoDao = new EnderecoDao();
        remetenteTableModel = new RemetenteTableModel();
    }

    public void carregarEstadosNaComboBox() {
        telaRemetenteGerenciar.getCbEstado().setModel(new DefaultComboBoxModel<>(EnderecoSigla.ESTADOS_BRASILEIROS));

    }

    public void chamarTelaFornecedorGerenciar() {
        if (telaRemetenteGerenciar == null) { // se tiver nulo chama janela normalmente
            telaRemetenteGerenciar = new TelaRemetenteGerenciar(this);
            TelaPrincipal.desktopPane.add(telaRemetenteGerenciar);
            telaRemetenteGerenciar.setVisible(true);
        } else {//se ele estiver criado
            if (telaRemetenteGerenciar.isVisible()) {
                telaRemetenteGerenciar.pack();//Redimensiona ao Quadro Original
            } else {
                TelaPrincipal.desktopPane.add(telaRemetenteGerenciar);
                telaRemetenteGerenciar.setVisible(true);
            }
        }
        telaRemetenteGerenciar.getTblFornecedor().setModel(remetenteTableModel);
        carregarEstadosNaComboBox();
        remetenteTableModel.limpar();
        remetenteTableModel.adicionar(remetenteDao.pesquisar());
    }

    private void cadastrarFornecedor() {
        remetente = new Remetente();
        remetente.setNome(telaRemetenteGerenciar.getTfNome().getText());
        remetente.setTelefone(telaRemetenteGerenciar.getTfTelefone().getText());

        Endereco endereco = new Endereco();
        endereco.setBairro(telaRemetenteGerenciar.getTfBairro().getText());

        try {
            endereco.setCep(Integer.valueOf(telaRemetenteGerenciar.getTfCep().getText()));

        } catch (NumberFormatException numberFormatException) {
            Mensagem.info(Texto.ERRO_COVERTER_CAMPO_CEP);
            remetente = null;
            endereco = null;
            return;
        }

        endereco.setCidade(telaRemetenteGerenciar.getTfCidade().getText());
        endereco.setComplemento(telaRemetenteGerenciar.getTfComplemento().getText());
        endereco.setEstado((String) telaRemetenteGerenciar.getCbEstado().getSelectedItem());
        endereco.setNumero(telaRemetenteGerenciar.getTfNumero().getText());
        endereco.setRua(telaRemetenteGerenciar.getTfRua().getText());
        Integer idEndereco = enderecoDao.inserir(endereco);
        endereco.setId(idEndereco);

        remetente.setEndereco(endereco);


        if (Validacao.validaEntidade(remetente) != null) {
            Mensagem.info(Validacao.validaEntidade(remetente));
            remetente = null;
            endereco = null;
            return;
        }

        Integer idInserido = remetenteDao.inserir(remetente);
        if (idInserido != 0) {
            remetente.setId(idInserido);
            remetenteTableModel.adicionar(remetente);
            limparCampos();
            Mensagem.info(Texto.SUCESSO_CADASTRAR);
        } else {
            Mensagem.info(Texto.ERRO_CADASTRAR);
        }
        remetente = null;
    }

    private void alterarFornecedor() {
        remetente = remetenteTableModel.pegaObjeto(telaRemetenteGerenciar.getTblFornecedor().getSelectedRow());
        remetente.setNome(telaRemetenteGerenciar.getTfNome().getText());
        remetente.setTelefone(telaRemetenteGerenciar.getTfTelefone().getText());

        Endereco endereco = new Endereco();
        endereco.setBairro(telaRemetenteGerenciar.getTfBairro().getText());
        endereco.setCep(Integer.valueOf(telaRemetenteGerenciar.getTfCep().getText()));
        endereco.setCidade(telaRemetenteGerenciar.getTfCidade().getText());
        endereco.setComplemento(telaRemetenteGerenciar.getTfComplemento().getText());
        endereco.setEstado((String) telaRemetenteGerenciar.getCbEstado().getSelectedItem());
        endereco.setNumero(telaRemetenteGerenciar.getTfNumero().getText());
        endereco.setRua(telaRemetenteGerenciar.getTfRua().getText());
        boolean enderecoAlterado = enderecoDao.alterar(endereco);
        if (!enderecoAlterado) {
            Mensagem.erro(Texto.ERRO_EDITAR);
            endereco = null;
            remetente = null;
            return;
        }

        if (Validacao.validaEntidade(remetente) != null) {
            Mensagem.info(Validacao.validaEntidade(remetente));
            remetente = null;
            endereco = null;
            return;
        }

        boolean alterado = remetenteDao.alterar(remetente);
        linhaSelecionada = telaRemetenteGerenciar.getTblFornecedor().getSelectedRow();
        if (alterado) {
            remetenteTableModel.atualizar(linhaSelecionada, remetente);
            Mensagem.info(Texto.SUCESSO_EDITAR);
            limparCampos();
        } else {
            Mensagem.erro(Texto.ERRO_EDITAR);
        }
        remetente = null;
    }

    public void buscarCepAction() {
        BuscaCepEventos buscaCepEvents = new BuscaCepEventosImpl();
        BuscaCepControl buscadorDeCep = new BuscaCepControl();
        try {
            buscadorDeCep.buscar(telaRemetenteGerenciar.getTfCep().getText());
            Endereco endereco = new Endereco();
            endereco.setEstado(buscadorDeCep.getUf());
            endereco.setBairro(buscadorDeCep.getBairro());
            endereco.setCidade(buscadorDeCep.getCidade());
            endereco.setRua(buscadorDeCep.getLogradouro());
            endereco.setComplemento(buscadorDeCep.getComplemento());

            // mostra na tela o cep pesquisado
            telaRemetenteGerenciar.getTfBairro().setText(endereco.getBairro());
            telaRemetenteGerenciar.getTfCidade().setText(endereco.getCidade());
            telaRemetenteGerenciar.getTfComplemento().setText(endereco.getComplemento());
            telaRemetenteGerenciar.getCbEstado().getModel().setSelectedItem(endereco.getEstado());
            telaRemetenteGerenciar.getTfRua().setText(endereco.getRua());
            telaRemetenteGerenciar.getTfCep().setText(telaRemetenteGerenciar.getTfCep().getText());
        } catch (BuscaCepException buscaCepException) {
            System.out.println(buscaCepException.getMessage());
            buscaCepException.printStackTrace();
        } catch (NumberFormatException numberFormatException) {
            System.out.println(numberFormatException.getMessage());
            numberFormatException.printStackTrace();
        }
    }

    public void gravarFornecedorAction() {
        if (remetente == null) {
            cadastrarFornecedor();
        } else {
            alterarFornecedor();
        }
    }

    public void desativarFornecedorAction() {
        int retorno = Mensagem.confirmacao(Texto.PERGUNTA_DESATIVAR);

        if (retorno == JOptionPane.NO_OPTION) {
            return;
        }
        if (retorno == JOptionPane.YES_OPTION) {
            remetente = remetenteTableModel.pegaObjeto(telaRemetenteGerenciar.getTblFornecedor().getSelectedRow());
            boolean deletado = remetenteDao.desativar(remetente.getId());
            if (deletado) {
                remetenteTableModel.remover(telaRemetenteGerenciar.getTblFornecedor().getSelectedRow());
                telaRemetenteGerenciar.getTblFornecedor().clearSelection();
                Mensagem.info(Texto.SUCESSO_DESATIVAR);
            } else {
                Mensagem.erro(Texto.ERRO_DESATIVAR);
            }
        }
        remetente = null;
    }

    public void pesquisarFornecedorAction() {
        List<Remetente> fornecedoresPesquisados = remetenteDao.pesquisar(telaRemetenteGerenciar.getTfPesquisar().getText());
        if (fornecedoresPesquisados == null) {
            remetenteTableModel.limpar();
            fornecedoresPesquisados = remetenteDao.pesquisar();
        } else {
            remetenteTableModel.limpar();
            remetenteTableModel.adicionar(fornecedoresPesquisados);
        }
    }

    public void carregarFornecedorAction() {
        remetente = remetenteTableModel.pegaObjeto(telaRemetenteGerenciar.getTblFornecedor().getSelectedRow());
        telaRemetenteGerenciar.getTfNome().setText(remetente.getNome());
        telaRemetenteGerenciar.getTfTelefone().setText(remetente.getTelefone());

        telaRemetenteGerenciar.getTfBairro().setText(remetente.getEndereco().getBairro());
        telaRemetenteGerenciar.getTfCidade().setText(remetente.getEndereco().getCidade());
        telaRemetenteGerenciar.getTfComplemento().setText(remetente.getEndereco().getComplemento());
        telaRemetenteGerenciar.getCbEstado().getModel().setSelectedItem(remetente.getEndereco().getEstado());
        telaRemetenteGerenciar.getTfNumero().setText(remetente.getEndereco().getNumero());
        telaRemetenteGerenciar.getTfRua().setText(remetente.getEndereco().getRua());
        telaRemetenteGerenciar.getTfCep().setText(String.valueOf(remetente.getEndereco().getCep()));


    }

    private void limparCampos() {
        telaRemetenteGerenciar.getTfNome().setText("");
        telaRemetenteGerenciar.getTfBairro().setText("");
        telaRemetenteGerenciar.getTfCep().setText("");
        telaRemetenteGerenciar.getTfCidade().setText("");
        telaRemetenteGerenciar.getTfComplemento().setText("");
        telaRemetenteGerenciar.getCbEstado().setSelectedIndex(0);
        telaRemetenteGerenciar.getTfNumero().setText("");
        telaRemetenteGerenciar.getTfTelefone().setText("");
        telaRemetenteGerenciar.getTfPesquisar().setText("");
        telaRemetenteGerenciar.getTfRua().setText("");
        telaRemetenteGerenciar.getCheckAtivo().setSelected(false);
        telaRemetenteGerenciar.getTfNome().requestFocus();
    }
}
