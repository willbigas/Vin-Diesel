package br.com.mercado.control;

import br.com.mercado.exceptions.BuscaCepException;
import br.com.mercado.interfaces.BuscaCepEventos;
import br.com.mercado.interfaces.BuscaCepEventosImpl;
import br.com.mercado.uteis.Enderecos;
import br.com.mercado.dao.EnderecoDao;
import br.com.mercado.dao.FornecedorDao;
import br.com.mercado.model.Endereco;
import br.com.mercado.model.Fornecedor;
import br.com.mercado.model.tablemodel.FornecedorTableModel;
import br.com.mercado.uteis.Mensagem;
import br.com.mercado.uteis.Texto;
import br.com.mercado.uteis.Validacao;
import br.com.mercado.view.TelaFornecedorGerenciar;
import br.com.mercado.view.TelaPrincipal;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Will
 */
public class TelaFornecedorGerenciarControl {

    TelaFornecedorGerenciar telaFornecedorGerenciar;
    Fornecedor fornecedor;
    FornecedorDao fornecedorDao;
    FornecedorTableModel fornecedorTableModel;
    EnderecoDao enderecoDao;
    Integer linhaSelecionada;

    public TelaFornecedorGerenciarControl() {
        fornecedorDao = new FornecedorDao();
        enderecoDao = new EnderecoDao();
        fornecedorTableModel = new FornecedorTableModel();
    }

    public void carregarEstadosNaComboBox() {
        telaFornecedorGerenciar.getCbEstado().setModel(new DefaultComboBoxModel<>(Enderecos.ESTADOS_BRASILEIROS));

    }

    public void chamarTelaFornecedorGerenciar() {
        if (telaFornecedorGerenciar == null) { // se tiver nulo chama janela normalmente
            telaFornecedorGerenciar = new TelaFornecedorGerenciar(this);
            TelaPrincipal.desktopPane.add(telaFornecedorGerenciar);
            telaFornecedorGerenciar.setVisible(true);
        } else {//se ele estiver criado
            if (telaFornecedorGerenciar.isVisible()) {
                telaFornecedorGerenciar.pack();//Redimensiona ao Quadro Original
            } else {
                TelaPrincipal.desktopPane.add(telaFornecedorGerenciar);
                telaFornecedorGerenciar.setVisible(true);
            }
        }
        telaFornecedorGerenciar.getTblFornecedor().setModel(fornecedorTableModel);
        carregarEstadosNaComboBox();
        fornecedorTableModel.limpar();
        fornecedorTableModel.adicionar(fornecedorDao.pesquisar());
    }

    private void cadastrarFornecedor() {
        fornecedor = new Fornecedor();
        fornecedor.setNome(telaFornecedorGerenciar.getTfNome().getText());
        fornecedor.setTelefone(telaFornecedorGerenciar.getTfTelefone().getText());

        Endereco endereco = new Endereco();
        endereco.setBairro(telaFornecedorGerenciar.getTfBairro().getText());

        try {
            endereco.setCep(Integer.valueOf(telaFornecedorGerenciar.getTfCep().getText()));

        } catch (NumberFormatException numberFormatException) {
            Mensagem.info(Texto.ERRO_COVERTER_CAMPO_CEP);
            fornecedor = null;
            endereco = null;
            return;
        }

        endereco.setCidade(telaFornecedorGerenciar.getTfCidade().getText());
        endereco.setComplemento(telaFornecedorGerenciar.getTfComplemento().getText());
        endereco.setEstado((String) telaFornecedorGerenciar.getCbEstado().getSelectedItem());
        endereco.setNumero(telaFornecedorGerenciar.getTfNumero().getText());
        endereco.setRua(telaFornecedorGerenciar.getTfRua().getText());
        Integer idEndereco = enderecoDao.inserir(endereco);
        endereco.setId(idEndereco);

        fornecedor.setEndereco(endereco);

        if (telaFornecedorGerenciar.getCheckAtivo().isSelected()) {
            fornecedor.setAtivo(true);
        } else {
            fornecedor.setAtivo(false);
        }

        if (Validacao.validaEntidade(fornecedor) != null) {
            Mensagem.info(Validacao.validaEntidade(fornecedor));
            fornecedor = null;
            endereco = null;
            return;
        }

        Integer idInserido = fornecedorDao.inserir(fornecedor);
        if (idInserido != 0) {
            fornecedor.setId(idInserido);
            fornecedorTableModel.adicionar(fornecedor);
            limparCampos();
            Mensagem.info(Texto.SUCESSO_CADASTRAR);
        } else {
            Mensagem.info(Texto.ERRO_CADASTRAR);
        }
        fornecedor = null;
    }

    private void alterarFornecedor() {
        fornecedor = fornecedorTableModel.pegaObjeto(telaFornecedorGerenciar.getTblFornecedor().getSelectedRow());
        fornecedor.setNome(telaFornecedorGerenciar.getTfNome().getText());
        fornecedor.setTelefone(telaFornecedorGerenciar.getTfTelefone().getText());

        Endereco endereco = new Endereco();
        endereco.setBairro(telaFornecedorGerenciar.getTfBairro().getText());
        endereco.setCep(Integer.valueOf(telaFornecedorGerenciar.getTfCep().getText()));
        endereco.setCidade(telaFornecedorGerenciar.getTfCidade().getText());
        endereco.setComplemento(telaFornecedorGerenciar.getTfComplemento().getText());
        endereco.setEstado((String) telaFornecedorGerenciar.getCbEstado().getSelectedItem());
        endereco.setNumero(telaFornecedorGerenciar.getTfNumero().getText());
        endereco.setRua(telaFornecedorGerenciar.getTfRua().getText());
        boolean enderecoAlterado = enderecoDao.alterar(endereco);
        if (!enderecoAlterado) {
            Mensagem.erro(Texto.ERRO_EDITAR);
            endereco = null;
            fornecedor = null;
            return;
        }

        if (Validacao.validaEntidade(fornecedor) != null) {
            Mensagem.info(Validacao.validaEntidade(fornecedor));
            fornecedor = null;
            endereco = null;
            return;
        }

        boolean alterado = fornecedorDao.alterar(fornecedor);
        linhaSelecionada = telaFornecedorGerenciar.getTblFornecedor().getSelectedRow();
        if (alterado) {
            fornecedorTableModel.atualizar(linhaSelecionada, fornecedor);
            Mensagem.info(Texto.SUCESSO_EDITAR);
            limparCampos();
        } else {
            Mensagem.erro(Texto.ERRO_EDITAR);
        }
        fornecedor = null;
    }

    public void buscarCepAction() {
        BuscaCepEventos buscaCepEvents = new BuscaCepEventosImpl();
        BuscaCepControl buscadorDeCep = new BuscaCepControl();
        try {
            buscadorDeCep.buscar(telaFornecedorGerenciar.getTfCep().getText());
            Endereco endereco = new Endereco();
            endereco.setEstado(buscadorDeCep.getUf());
            endereco.setBairro(buscadorDeCep.getBairro());
            endereco.setCidade(buscadorDeCep.getCidade());
            endereco.setRua(buscadorDeCep.getLogradouro());
            endereco.setComplemento(buscadorDeCep.getComplemento());

            // mostra na tela o cep pesquisado
            telaFornecedorGerenciar.getTfBairro().setText(endereco.getBairro());
            telaFornecedorGerenciar.getTfCidade().setText(endereco.getCidade());
            telaFornecedorGerenciar.getTfComplemento().setText(endereco.getComplemento());
            telaFornecedorGerenciar.getCbEstado().getModel().setSelectedItem(endereco.getEstado());
            telaFornecedorGerenciar.getTfRua().setText(endereco.getRua());
            telaFornecedorGerenciar.getTfCep().setText(telaFornecedorGerenciar.getTfCep().getText());
        } catch (BuscaCepException buscaCepException) {
            System.out.println(buscaCepException.getMessage());
            buscaCepException.printStackTrace();
        } catch (NumberFormatException numberFormatException) {
            System.out.println(numberFormatException.getMessage());
            numberFormatException.printStackTrace();
        }
    }

    public void gravarFornecedorAction() {
        if (fornecedor == null) {
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
            fornecedor = fornecedorTableModel.pegaObjeto(telaFornecedorGerenciar.getTblFornecedor().getSelectedRow());
            boolean deletado = fornecedorDao.desativar(fornecedor.getId());
            if (deletado) {
                fornecedorTableModel.remover(telaFornecedorGerenciar.getTblFornecedor().getSelectedRow());
                telaFornecedorGerenciar.getTblFornecedor().clearSelection();
                Mensagem.info(Texto.SUCESSO_DESATIVAR);
            } else {
                Mensagem.erro(Texto.ERRO_DESATIVAR);
            }
        }
        fornecedor = null;
    }

    public void pesquisarFornecedorAction() {
        List<Fornecedor> fornecedoresPesquisados = fornecedorDao.pesquisar(telaFornecedorGerenciar.getTfPesquisar().getText());
        if (fornecedoresPesquisados == null) {
            fornecedorTableModel.limpar();
            fornecedoresPesquisados = fornecedorDao.pesquisar();
        } else {
            fornecedorTableModel.limpar();
            fornecedorTableModel.adicionar(fornecedoresPesquisados);
        }
    }

    public void carregarFornecedorAction() {
        fornecedor = fornecedorTableModel.pegaObjeto(telaFornecedorGerenciar.getTblFornecedor().getSelectedRow());
        telaFornecedorGerenciar.getTfNome().setText(fornecedor.getNome());
        telaFornecedorGerenciar.getTfTelefone().setText(fornecedor.getTelefone());

        telaFornecedorGerenciar.getTfBairro().setText(fornecedor.getEndereco().getBairro());
        telaFornecedorGerenciar.getTfCidade().setText(fornecedor.getEndereco().getCidade());
        telaFornecedorGerenciar.getTfComplemento().setText(fornecedor.getEndereco().getComplemento());
        telaFornecedorGerenciar.getCbEstado().getModel().setSelectedItem(fornecedor.getEndereco().getEstado());
        telaFornecedorGerenciar.getTfNumero().setText(fornecedor.getEndereco().getNumero());
        telaFornecedorGerenciar.getTfRua().setText(fornecedor.getEndereco().getRua());
        telaFornecedorGerenciar.getTfCep().setText(String.valueOf(fornecedor.getEndereco().getCep()));

        if (fornecedor.getAtivo() == true) {
            telaFornecedorGerenciar.getCheckAtivo().setSelected(true);
        } else {
            telaFornecedorGerenciar.getCheckAtivo().setSelected(false);
        }

    }

    private void limparCampos() {
        telaFornecedorGerenciar.getTfNome().setText("");
        telaFornecedorGerenciar.getTfBairro().setText("");
        telaFornecedorGerenciar.getTfCep().setText("");
        telaFornecedorGerenciar.getTfCidade().setText("");
        telaFornecedorGerenciar.getTfComplemento().setText("");
        telaFornecedorGerenciar.getCbEstado().setSelectedIndex(0);
        telaFornecedorGerenciar.getTfNumero().setText("");
        telaFornecedorGerenciar.getTfTelefone().setText("");
        telaFornecedorGerenciar.getTfPesquisar().setText("");
        telaFornecedorGerenciar.getTfRua().setText("");
        telaFornecedorGerenciar.getCheckAtivo().setSelected(false);
        telaFornecedorGerenciar.getTfNome().requestFocus();
    }
}
