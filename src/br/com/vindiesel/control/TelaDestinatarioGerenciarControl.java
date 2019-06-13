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
    private Endereco enderecoCliente;
    private DestinatarioDao destinatarioDao;
    private DestinatarioTableModel destinatarioTableModel;
    private EnderecoDao enderecoDao;
    private Integer linhaSelecionada;
    
    public TelaDestinatarioGerenciarControl() {
        destinatarioDao = new DestinatarioDao();
        enderecoDao = new EnderecoDao();
        destinatarioTableModel = new DestinatarioTableModel();
    }
    
    public void chamarTelaClienteGerenciar() {
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
        telaDestinatarioGerenciar.getTblCliente().setModel(destinatarioTableModel);
        redimensionarTela();
        carregarEstadosNaComboBox();
        destinatarioTableModel.limpar();
        destinatarioTableModel.adicionar(destinatarioDao.pesquisar());
    }
    
    private void redimensionarTela() {
        UtilTable.centralizarCabecalho(telaDestinatarioGerenciar.getTblCliente());
        UtilTable.redimensionar(telaDestinatarioGerenciar.getTblCliente(), 0, 50);
        UtilTable.redimensionar(telaDestinatarioGerenciar.getTblCliente(), 1, 355);
        UtilTable.redimensionar(telaDestinatarioGerenciar.getTblCliente(), 2, 100);
        UtilTable.redimensionar(telaDestinatarioGerenciar.getTblCliente(), 3, 225);
        UtilTable.redimensionar(telaDestinatarioGerenciar.getTblCliente(), 4, 150);
        UtilTable.redimensionar(telaDestinatarioGerenciar.getTblCliente(), 5, 50);
    }
    
    private void carregarEstadosNaComboBox() {
        telaDestinatarioGerenciar.getCbEstado().setModel(new DefaultComboBoxModel<>(EnderecoSigla.ESTADOS_BRASILEIROS));
    }
    
    private void cadastrarDestinatario() {
        destinatario = new Destinatario();
        destinatario.setNome(telaDestinatarioGerenciar.getTfNome().getText());
        
        
        if (Validacao.validaEntidade(destinatario) != null) {
            Mensagem.info(Validacao.validaEntidade(destinatario));
            destinatario = null;
            return;
        }
        
        enderecoCliente = new Endereco();
        enderecoCliente.setBairro(telaDestinatarioGerenciar.getTfBairro().getText());
        enderecoCliente.setCep(Integer.valueOf(telaDestinatarioGerenciar.getTfCep().getText()));
        enderecoCliente.setCidade(telaDestinatarioGerenciar.getTfCidade().getText());
        enderecoCliente.setComplemento(telaDestinatarioGerenciar.getTfComplemento().getText());
        enderecoCliente.setEstado((String) telaDestinatarioGerenciar.getCbEstado().getSelectedItem());
        enderecoCliente.setNumero(telaDestinatarioGerenciar.getTfNumero().getText());
        enderecoCliente.setRua(telaDestinatarioGerenciar.getTfRua().getText());
        
        if (Validacao.validaEntidade(enderecoCliente) != null) {
            Mensagem.info(Validacao.validaEntidade(enderecoCliente));
            enderecoCliente = null;
            return;
        }
        
        Integer idEndereco = enderecoDao.inserir(enderecoCliente);
        
        enderecoCliente.setId(idEndereco);
        destinatario.setEndereco(enderecoCliente);
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
    
    private void alterarCliente() {
        destinatario.setNome(telaDestinatarioGerenciar.getTfNome().getText());
        
        
        
        if (Validacao.validaEntidade(destinatario) != null) {
            Mensagem.info(Validacao.validaEntidade(destinatario));
            destinatario = null;
            return;
        }
        
        enderecoCliente = new Endereco();
        enderecoCliente.setBairro(telaDestinatarioGerenciar.getTfBairro().getText());
        enderecoCliente.setCep(Integer.valueOf(telaDestinatarioGerenciar.getTfCep().getText()));
        enderecoCliente.setCidade(telaDestinatarioGerenciar.getTfCidade().getText());
        enderecoCliente.setComplemento(telaDestinatarioGerenciar.getTfComplemento().getText());
        enderecoCliente.setEstado((String) telaDestinatarioGerenciar.getCbEstado().getSelectedItem());
        enderecoCliente.setNumero(telaDestinatarioGerenciar.getTfNumero().getText());
        enderecoCliente.setRua(telaDestinatarioGerenciar.getTfRua().getText());
        Integer idEndereco = enderecoDao.inserir(enderecoCliente);
        enderecoCliente.setId(idEndereco);
        destinatario.setEndereco(enderecoCliente);
        boolean alterado = destinatarioDao.alterar(destinatario);
        linhaSelecionada = telaDestinatarioGerenciar.getTblCliente().getSelectedRow();
        if (alterado) {
            destinatarioTableModel.atualizar(linhaSelecionada, destinatario);
            Mensagem.info(Texto.SUCESSO_EDITAR);
            limparCampos();
        } else {
            Mensagem.erro(Texto.ERRO_EDITAR);
        }
        destinatario = null;
    }
    
    public void gravarClienteAction() {
        if (destinatario == null) {
            cadastrarDestinatario();
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
        destinatario = destinatarioTableModel.pegaObjeto(telaDestinatarioGerenciar.getTblCliente().getSelectedRow());
        boolean deletado = destinatarioDao.desativar(destinatario);
        if (deletado) {
            destinatarioTableModel.remover(telaDestinatarioGerenciar.getTblCliente().getSelectedRow());
            telaDestinatarioGerenciar.getTblCliente().clearSelection();
            Mensagem.info(Texto.SUCESSO_DESATIVAR);
        } else {
            Mensagem.erro(Texto.ERRO_DESATIVAR);
        }
        destinatario = null;
    }
    
    public void pesquisarClienteAction() {
        List<Destinatario> fornecedoresPesquisados = destinatarioDao.pesquisar(telaDestinatarioGerenciar.getTfPesquisa().getText());
        if (fornecedoresPesquisados == null) {
            destinatarioTableModel.limpar();
            fornecedoresPesquisados = destinatarioDao.pesquisar();
        } else {
            destinatarioTableModel.limpar();
            destinatarioTableModel.adicionar(fornecedoresPesquisados);
        }
    }
    
    public void carregarClienteAction() {
        destinatario = destinatarioTableModel.pegaObjeto(telaDestinatarioGerenciar.getTblCliente().getSelectedRow());
        telaDestinatarioGerenciar.getTfNome().setText(destinatario.getNome());
        
        
        telaDestinatarioGerenciar.getTfBairro().setText(destinatario.getEndereco().getBairro());
        telaDestinatarioGerenciar.getTfCidade().setText(destinatario.getEndereco().getCidade());
        telaDestinatarioGerenciar.getTfComplemento().setText(destinatario.getEndereco().getComplemento());
        telaDestinatarioGerenciar.getCbEstado().getModel().setSelectedItem(destinatario.getEndereco().getEstado());
        telaDestinatarioGerenciar.getTfNumero().setText(destinatario.getEndereco().getNumero());
        telaDestinatarioGerenciar.getTfRua().setText(destinatario.getEndereco().getRua());
        telaDestinatarioGerenciar.getTfCep().setText(String.valueOf(destinatario.getEndereco().getCep()));
    }
    
    public void buscarCepAction() {
        BuscaCepEventos buscaCepEvents = new BuscaCepEventosImpl();
        BuscaCepControl buscadorDeCep = new BuscaCepControl();
        try {
            buscadorDeCep.buscar(telaDestinatarioGerenciar.getTfCep().getText());
            Endereco endereco = new Endereco();
            endereco.setEstado(buscadorDeCep.getUf());
            endereco.setBairro(buscadorDeCep.getBairro());
            endereco.setCidade(buscadorDeCep.getCidade());
            endereco.setRua(buscadorDeCep.getLogradouro());
            endereco.setComplemento(buscadorDeCep.getComplemento());
            System.out.println("Endereco encontrado" + endereco);

            // mostra na tela o cep pesquisado
            telaDestinatarioGerenciar.getTfBairro().setText(endereco.getBairro());
            telaDestinatarioGerenciar.getTfCidade().setText(endereco.getCidade());
            telaDestinatarioGerenciar.getTfComplemento().setText(endereco.getComplemento());
            telaDestinatarioGerenciar.getCbEstado().getModel().setSelectedItem(endereco.getEstado());
            telaDestinatarioGerenciar.getTfRua().setText(endereco.getRua());
            telaDestinatarioGerenciar.getTfCep().setText(telaDestinatarioGerenciar.getTfCep().getText());
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
