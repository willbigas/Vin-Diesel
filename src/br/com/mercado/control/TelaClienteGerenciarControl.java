package br.com.mercado.control;

import br.com.mercado.exceptions.BuscaCepException;
import br.com.mercado.interfaces.BuscaCepEventos;
import br.com.mercado.interfaces.BuscaCepEventosImpl;
import br.com.mercado.dao.ClienteDao;
import br.com.mercado.dao.EnderecoDao;
import br.com.mercado.model.Cliente;
import br.com.mercado.model.Endereco;
import br.com.mercado.model.tablemodel.ClienteTableModel;
import br.com.mercado.uteis.Enderecos;
import br.com.mercado.uteis.Mensagem;
import br.com.mercado.uteis.Texto;
import br.com.mercado.uteis.UtilTable;
import br.com.mercado.uteis.Validacao;
import br.com.mercado.view.TelaClienteGerenciar;
import br.com.mercado.view.TelaPrincipal;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Will
 */
public class TelaClienteGerenciarControl {
    
    private TelaClienteGerenciar telaClienteGerenciar;
    private Cliente cliente;
    private Endereco enderecoCliente;
    private ClienteDao clienteDao;
    private ClienteTableModel clienteTableModel;
    private EnderecoDao enderecoDao;
    private Integer linhaSelecionada;
    
    public TelaClienteGerenciarControl() {
        clienteDao = new ClienteDao();
        enderecoDao = new EnderecoDao();
        clienteTableModel = new ClienteTableModel();
    }
    
    public void chamarTelaClienteGerenciar() {
        if (telaClienteGerenciar == null) {
            telaClienteGerenciar = new TelaClienteGerenciar(this);
            TelaPrincipal.desktopPane.add(telaClienteGerenciar);
            telaClienteGerenciar.setVisible(true);
        } else {
            if (telaClienteGerenciar.isVisible()) {
                telaClienteGerenciar.pack();
            } else {
                TelaPrincipal.desktopPane.add(telaClienteGerenciar);
                telaClienteGerenciar.setVisible(true);
            }
        }
        telaClienteGerenciar.getTblCliente().setModel(clienteTableModel);
        redimensionarTela();
        carregarEstadosNaComboBox();
        clienteTableModel.limpar();
        clienteTableModel.adicionar(clienteDao.pesquisar());
    }
    
    private void redimensionarTela() {
        UtilTable.centralizarCabecalho(telaClienteGerenciar.getTblCliente());
        UtilTable.redimensionar(telaClienteGerenciar.getTblCliente(), 0, 50);
        UtilTable.redimensionar(telaClienteGerenciar.getTblCliente(), 1, 355);
        UtilTable.redimensionar(telaClienteGerenciar.getTblCliente(), 2, 100);
        UtilTable.redimensionar(telaClienteGerenciar.getTblCliente(), 3, 225);
        UtilTable.redimensionar(telaClienteGerenciar.getTblCliente(), 4, 150);
        UtilTable.redimensionar(telaClienteGerenciar.getTblCliente(), 5, 50);
    }
    
    private void carregarEstadosNaComboBox() {
        telaClienteGerenciar.getCbEstado().setModel(new DefaultComboBoxModel<>(Enderecos.ESTADOS_BRASILEIROS));
    }
    
    private void cadastrarCliente() {
        cliente = new Cliente();
        cliente.setNome(telaClienteGerenciar.getTfNome().getText());
        cliente.setEmail(telaClienteGerenciar.getTfEmail().getText());
        cliente.setTelefone(telaClienteGerenciar.getTfTelefone().getText());
        
        if (telaClienteGerenciar.getCheckAtivo().isSelected()) {
            cliente.setAtivo(true);
        }
        if (!telaClienteGerenciar.getCheckAtivo().isSelected()) {
            cliente.setAtivo(false);
        }
        
        if (Validacao.validaEntidade(cliente) != null) {
            Mensagem.info(Validacao.validaEntidade(cliente));
            cliente = null;
            return;
        }
        
        enderecoCliente = new Endereco();
        enderecoCliente.setBairro(telaClienteGerenciar.getTfBairro().getText());
        enderecoCliente.setCep(Integer.valueOf(telaClienteGerenciar.getTfCep().getText()));
        enderecoCliente.setCidade(telaClienteGerenciar.getTfCidade().getText());
        enderecoCliente.setComplemento(telaClienteGerenciar.getTfComplemento().getText());
        enderecoCliente.setEstado((String) telaClienteGerenciar.getCbEstado().getSelectedItem());
        enderecoCliente.setNumero(telaClienteGerenciar.getTfNumero().getText());
        enderecoCliente.setRua(telaClienteGerenciar.getTfRua().getText());
        
        if (Validacao.validaEntidade(enderecoCliente) != null) {
            Mensagem.info(Validacao.validaEntidade(enderecoCliente));
            enderecoCliente = null;
            return;
        }
        
        Integer idEndereco = enderecoDao.inserir(enderecoCliente);
        
        enderecoCliente.setId(idEndereco);
        cliente.setEndereco(enderecoCliente);
        Integer idInserido = clienteDao.inserir(cliente);
        if (idInserido != 0) {
            cliente.setId(idInserido);
            clienteTableModel.adicionar(cliente);
            limparCampos();
            Mensagem.info(Texto.SUCESSO_CADASTRAR);
        } else {
            Mensagem.info(Texto.ERRO_CADASTRAR);
        }
        cliente = null;
        
    }
    
    private void alterarCliente() {
        
        cliente.setNome(telaClienteGerenciar.getTfNome().getText());
        cliente.setEmail(telaClienteGerenciar.getTfEmail().getText());
        cliente.setTelefone(telaClienteGerenciar.getTfTelefone().getText());
        if (telaClienteGerenciar.getCheckAtivo().isSelected()) {
            cliente.setAtivo(true);
        }
        if (!telaClienteGerenciar.getCheckAtivo().isSelected()) {
            cliente.setAtivo(false);
        }
        
        if (Validacao.validaEntidade(cliente) != null) {
            Mensagem.info(Validacao.validaEntidade(cliente));
            cliente = null;
            return;
        }
        
        enderecoCliente = new Endereco();
        enderecoCliente.setBairro(telaClienteGerenciar.getTfBairro().getText());
        enderecoCliente.setCep(Integer.valueOf(telaClienteGerenciar.getTfCep().getText()));
        enderecoCliente.setCidade(telaClienteGerenciar.getTfCidade().getText());
        enderecoCliente.setComplemento(telaClienteGerenciar.getTfComplemento().getText());
        enderecoCliente.setEstado((String) telaClienteGerenciar.getCbEstado().getSelectedItem());
        enderecoCliente.setNumero(telaClienteGerenciar.getTfNumero().getText());
        enderecoCliente.setRua(telaClienteGerenciar.getTfRua().getText());
        Integer idEndereco = enderecoDao.inserir(enderecoCliente);
        enderecoCliente.setId(idEndereco);
        cliente.setEndereco(enderecoCliente);
        boolean alterado = clienteDao.alterar(cliente);
        linhaSelecionada = telaClienteGerenciar.getTblCliente().getSelectedRow();
        if (alterado) {
            clienteTableModel.atualizar(linhaSelecionada, cliente);
            Mensagem.info(Texto.SUCESSO_EDITAR);
            limparCampos();
        } else {
            Mensagem.erro(Texto.ERRO_EDITAR);
        }
        cliente = null;
    }
    
    public void gravarClienteAction() {
        if (cliente == null) {
            cadastrarCliente();
        } else {
            alterarCliente();
        }
    }
    
    public void desativarClienteAction() {
        int retorno = Mensagem.confirmacao(Texto.PERGUNTA_DESATIVAR);
        if (retorno == JOptionPane.NO_OPTION) {
            return;
        }
        if (retorno == JOptionPane.CLOSED_OPTION) {
            return;
        }
        cliente = clienteTableModel.pegaObjeto(telaClienteGerenciar.getTblCliente().getSelectedRow());
        boolean deletado = clienteDao.desativar(cliente);
        if (deletado) {
            clienteTableModel.remover(telaClienteGerenciar.getTblCliente().getSelectedRow());
            telaClienteGerenciar.getTblCliente().clearSelection();
            Mensagem.info(Texto.SUCESSO_DESATIVAR);
        } else {
            Mensagem.erro(Texto.ERRO_DESATIVAR);
        }
        cliente = null;
    }
    
    public void pesquisarClienteAction() {
        List<Cliente> fornecedoresPesquisados = clienteDao.pesquisar(telaClienteGerenciar.getTfPesquisa().getText());
        if (fornecedoresPesquisados == null) {
            clienteTableModel.limpar();
            fornecedoresPesquisados = clienteDao.pesquisar();
        } else {
            clienteTableModel.limpar();
            clienteTableModel.adicionar(fornecedoresPesquisados);
        }
    }
    
    public void carregarClienteAction() {
        cliente = clienteTableModel.pegaObjeto(telaClienteGerenciar.getTblCliente().getSelectedRow());
        telaClienteGerenciar.getTfNome().setText(cliente.getNome());
        telaClienteGerenciar.getTfTelefone().setText(cliente.getTelefone());
        telaClienteGerenciar.getTfEmail().setText(cliente.getEmail());
        telaClienteGerenciar.getTfBairro().setText(cliente.getEndereco().getBairro());
        telaClienteGerenciar.getTfCidade().setText(cliente.getEndereco().getCidade());
        telaClienteGerenciar.getTfComplemento().setText(cliente.getEndereco().getComplemento());
        telaClienteGerenciar.getCbEstado().getModel().setSelectedItem(cliente.getEndereco().getEstado());
        telaClienteGerenciar.getTfNumero().setText(cliente.getEndereco().getNumero());
        telaClienteGerenciar.getTfRua().setText(cliente.getEndereco().getRua());
        telaClienteGerenciar.getTfCep().setText(String.valueOf(cliente.getEndereco().getCep()));
        if (cliente.getAtivo() == true) {
            telaClienteGerenciar.getCheckAtivo().setSelected(true);
        } else {
            telaClienteGerenciar.getCheckAtivo().setSelected(false);
        }
    }
    
    public void buscarCepAction() {
        BuscaCepEventos buscaCepEvents = new BuscaCepEventosImpl();
        BuscaCepControl buscadorDeCep = new BuscaCepControl();
        try {
            buscadorDeCep.buscar(telaClienteGerenciar.getTfCep().getText());
            Endereco endereco = new Endereco();
            endereco.setEstado(buscadorDeCep.getUf());
            endereco.setBairro(buscadorDeCep.getBairro());
            endereco.setCidade(buscadorDeCep.getCidade());
            endereco.setRua(buscadorDeCep.getLogradouro());
            endereco.setComplemento(buscadorDeCep.getComplemento());
            System.out.println("Endereco encontrado" + endereco);

            // mostra na tela o cep pesquisado
            telaClienteGerenciar.getTfBairro().setText(endereco.getBairro());
            telaClienteGerenciar.getTfCidade().setText(endereco.getCidade());
            telaClienteGerenciar.getTfComplemento().setText(endereco.getComplemento());
            telaClienteGerenciar.getCbEstado().getModel().setSelectedItem(endereco.getEstado());
            telaClienteGerenciar.getTfRua().setText(endereco.getRua());
            telaClienteGerenciar.getTfCep().setText(telaClienteGerenciar.getTfCep().getText());
        } catch (BuscaCepException buscaCepException) {
            System.out.println(buscaCepException.getMessage());
            buscaCepException.printStackTrace();
        } catch (NumberFormatException numberFormatException) {
            System.out.println(numberFormatException.getMessage());
            numberFormatException.printStackTrace();
        }
    }
    
    private boolean validarCampos() {
        if (telaClienteGerenciar.getTfNome().getText().isEmpty()
                || telaClienteGerenciar.getTfBairro().getText().isEmpty()
                || telaClienteGerenciar.getTfCep().getText().isEmpty()
                || telaClienteGerenciar.getTfCidade().getText().isEmpty()
                || telaClienteGerenciar.getTfNumero().getText().isEmpty()
                || telaClienteGerenciar.getTfNumero().getText().isEmpty()
                || telaClienteGerenciar.getTfTelefone().getText().isEmpty()
                || telaClienteGerenciar.getTfRua().getText().isEmpty()) {
            telaClienteGerenciar.getTfNome().requestFocus();
            return true;
        }
        return false;
    }
    
    private void limparCampos() {
        telaClienteGerenciar.getTfNome().setText("");
        telaClienteGerenciar.getTfBairro().setText("");
        telaClienteGerenciar.getTfEmail().setText("");
        telaClienteGerenciar.getTfCep().setText("");
        telaClienteGerenciar.getTfCidade().setText("");
        telaClienteGerenciar.getTfComplemento().setText("");
        telaClienteGerenciar.getCbEstado().setSelectedIndex(0);
        telaClienteGerenciar.getTfNumero().setText("");
        telaClienteGerenciar.getTfTelefone().setText("");
        telaClienteGerenciar.getTfPesquisa().setText("");
        telaClienteGerenciar.getTfRua().setText("");
        telaClienteGerenciar.getCheckAtivo().setSelected(false);
        telaClienteGerenciar.getTfNome().requestFocus();
    }
    
}
