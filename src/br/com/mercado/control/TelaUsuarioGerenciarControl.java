package br.com.mercado.control;

import br.com.mercado.exceptions.BuscaCepException;
import br.com.mercado.interfaces.BuscaCepEventos;
import br.com.mercado.interfaces.BuscaCepEventosImpl;
import br.com.mercado.dao.EnderecoDao;
import br.com.mercado.dao.UsuarioDao;
import br.com.mercado.dao.TipoUsuarioDao;
import br.com.mercado.model.Endereco;
import br.com.mercado.model.Usuario;
import br.com.mercado.model.TipoUsuario;
import br.com.mercado.model.tablemodel.UsuarioTableModel;
import br.com.mercado.uteis.Enderecos;
import br.com.mercado.uteis.Mensagem;
import br.com.mercado.uteis.Texto;
import br.com.mercado.uteis.Validacao;
import br.com.mercado.view.TelaPrincipal;
import br.com.mercado.view.TelaUsuarioGerenciar;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author William
 */
public class TelaUsuarioGerenciarControl {

    TelaUsuarioGerenciar telaUsuarioGerenciar;
    List<TipoUsuario> listTiposUsuarios;
    TipoUsuarioDao tipoUsuarioDao;
    UsuarioDao funcionarioDao;
    UsuarioTableModel tableModelFuncionarios;
    Usuario usuario;
    EnderecoDao enderecoDao;
    Integer linhaSelecionada;
    Endereco endereco;

    public TelaUsuarioGerenciarControl() {
        tipoUsuarioDao = new TipoUsuarioDao();
        funcionarioDao = new UsuarioDao();
        enderecoDao = new EnderecoDao();
        tableModelFuncionarios = new UsuarioTableModel();
    }

    public void carregarEstadosNaComboBox() {
        telaUsuarioGerenciar.getCbEstado().setModel(new DefaultComboBoxModel<>(Enderecos.ESTADOS_BRASILEIROS));
    }

    public void chamarTelaFuncionarioGerenciar() {
        if (telaUsuarioGerenciar == null) { // se tiver nulo chama janela normalmente
            telaUsuarioGerenciar = new TelaUsuarioGerenciar(this);
            TelaPrincipal.desktopPane.add(telaUsuarioGerenciar);
            telaUsuarioGerenciar.setVisible(true);
        } else {//se ele estiver criado
            if (telaUsuarioGerenciar.isVisible()) {
                telaUsuarioGerenciar.pack();//Redimensiona ao Quadro Original
            } else {
                TelaPrincipal.desktopPane.add(telaUsuarioGerenciar);
                telaUsuarioGerenciar.setVisible(true);
            }
        }
        carregarTiposUsuariosNaCombo();
        carregarEstadosNaComboBox();
        telaUsuarioGerenciar.getTblFuncionario().setModel(tableModelFuncionarios);
        tableModelFuncionarios.adicionar(funcionarioDao.pesquisar());
    }

    private void carregarTiposUsuariosNaCombo() {
        listTiposUsuarios = tipoUsuarioDao.pesquisar();
        DefaultComboBoxModel<TipoUsuario> model = new DefaultComboBoxModel(listTiposUsuarios.toArray());
        telaUsuarioGerenciar.getCbTipoUsuario().setModel(model);
    }

    private void inserirFuncionario() {
        usuario = new Usuario();
        criarFuncionario();

        if (Validacao.validaEntidade(usuario) != null) {
            Mensagem.info(Validacao.validaEntidade(usuario));
            usuario = null;
            endereco = null;
            return;
        }
        Integer idInserido = funcionarioDao.inserir(usuario);
        if (idInserido != 0) {
            usuario.setId(idInserido);
            tableModelFuncionarios.adicionar(usuario);
            limparCampos();
            Mensagem.info(Texto.SUCESSO_CADASTRAR);
        } else {
            Mensagem.info(Texto.ERRO_CADASTRAR);
        }
        usuario = null;
    }

    private void alterarFuncionario() {
        criarFuncionario();
        if (Validacao.validaEntidade(usuario) != null) {
            Mensagem.info(Validacao.validaEntidade(usuario));
            usuario = null;
            return;
        }
        boolean alterado = funcionarioDao.alterar(usuario);
        linhaSelecionada = telaUsuarioGerenciar.getTblFuncionario().getSelectedRow();
        if (alterado) {
            tableModelFuncionarios.atualizar(linhaSelecionada, usuario);
            Mensagem.info(Texto.SUCESSO_EDITAR);
            limparCampos();
        } else {
            Mensagem.erro(Texto.ERRO_EDITAR);
        }
        usuario = null;
        endereco = null;
    }

    private void criarFuncionario() {
        usuario.setNome(telaUsuarioGerenciar.getTfNome().getText());
        usuario.setTelefone(telaUsuarioGerenciar.getTfTelefone().getText());
        usuario.setEmail(telaUsuarioGerenciar.getTfEmail().getText());
        usuario.setPis(Integer.valueOf(telaUsuarioGerenciar.getTfPis().getText()));
        usuario.setSalario(Double.valueOf(telaUsuarioGerenciar.getTfSalario().getText()));
        usuario.setSenha(telaUsuarioGerenciar.getTfSenha().getText());
        usuario.setTipoUsuario((TipoUsuario) telaUsuarioGerenciar.getCbTipoUsuario().getSelectedItem());

        // modifica os atributos baseado no que o usuario modificar.
        endereco.setBairro(telaUsuarioGerenciar.getTfBairro().getText());
        endereco.setCep(Integer.valueOf(telaUsuarioGerenciar.getTfCep().getText()));
        endereco.setCidade(telaUsuarioGerenciar.getTfCidade().getText());
        endereco.setComplemento(telaUsuarioGerenciar.getTfComplemento().getText());
        endereco.setEstado((String) telaUsuarioGerenciar.getCbEstado().getSelectedItem());
        endereco.setNumero(telaUsuarioGerenciar.getTfNumero().getText());
        endereco.setRua(telaUsuarioGerenciar.getTfRua().getText());
        Integer idEndereco = enderecoDao.inserir(endereco);
        endereco.setId(idEndereco);
        usuario.setEndereco(endereco);

        if (telaUsuarioGerenciar.getCheckAtivo().isSelected()) {
            usuario.setAtivo(true);
        } else {
            usuario.setAtivo(false);
        }
    }

    public void buscaCepEMostraNaTela() {
        BuscaCepEventos buscaCepEvents = new BuscaCepEventosImpl();
        BuscaCepControl buscadorDeCep = new BuscaCepControl();
        try {
            buscadorDeCep.buscar(telaUsuarioGerenciar.getTfCep().getText());
            Endereco endereco = new Endereco();
            endereco.setEstado(buscadorDeCep.getUf());
            endereco.setBairro(buscadorDeCep.getBairro());
            endereco.setCidade(buscadorDeCep.getCidade());
            endereco.setRua(buscadorDeCep.getLogradouro());
            endereco.setComplemento(buscadorDeCep.getComplemento());
            System.out.println("Endereco encontrado" + endereco);

            // mostra na tela o cep pesquisado
            telaUsuarioGerenciar.getTfBairro().setText(endereco.getBairro());
            telaUsuarioGerenciar.getTfCidade().setText(endereco.getCidade());
            telaUsuarioGerenciar.getTfComplemento().setText(endereco.getComplemento());
            telaUsuarioGerenciar.getCbEstado().getModel().setSelectedItem(endereco.getEstado());
            telaUsuarioGerenciar.getTfRua().setText(endereco.getRua());
            telaUsuarioGerenciar.getTfCep().setText(telaUsuarioGerenciar.getTfCep().getText());
        } catch (BuscaCepException buscaCepException) {
            System.out.println(buscaCepException.getMessage());
            buscaCepException.printStackTrace();
        } catch (NumberFormatException numberFormatException) {
            System.out.println(numberFormatException.getMessage());
            numberFormatException.printStackTrace();
        }
    }

    public void gravarFuncionarioAction() {
        if (usuario == null) {
            inserirFuncionario();
        } else {
            alterarFuncionario();
        }
    }

    public void desativarFuncionarioAction() {
        int retorno = Mensagem.confirmacao(Texto.PERGUNTA_DESATIVAR);

        if (retorno == JOptionPane.NO_OPTION) {
            return;
        }
        if (retorno == JOptionPane.YES_OPTION) {
            usuario = tableModelFuncionarios.pegaObjeto(telaUsuarioGerenciar.getTblFuncionario().getSelectedRow());
            boolean deletado = funcionarioDao.desativar(usuario.getId());
            if (deletado) {
                tableModelFuncionarios.remover(telaUsuarioGerenciar.getTblFuncionario().getSelectedRow());
                telaUsuarioGerenciar.getTblFuncionario().clearSelection();
                Mensagem.info(Texto.SUCESSO_DESATIVAR);
            } else {
                Mensagem.erro(Texto.ERRO_DESATIVAR);
            }
        }
        usuario = null;
    }

    public void carregarFuncionariosAction() {
        usuario = tableModelFuncionarios.pegaObjeto(telaUsuarioGerenciar.getTblFuncionario().getSelectedRow());
        telaUsuarioGerenciar.getTfNome().setText(usuario.getNome());
        telaUsuarioGerenciar.getTfTelefone().setText(usuario.getTelefone());
        telaUsuarioGerenciar.getTfEmail().setText(usuario.getEmail());
        telaUsuarioGerenciar.getTfPis().setText(String.valueOf(usuario.getPis()));
        telaUsuarioGerenciar.getTfSalario().setText(String.valueOf(usuario.getSalario()));
        telaUsuarioGerenciar.getTfSenha().setText(usuario.getSenha());

        telaUsuarioGerenciar.getTfBairro().setText(usuario.getEndereco().getBairro());
        telaUsuarioGerenciar.getTfCidade().setText(usuario.getEndereco().getCidade());
        telaUsuarioGerenciar.getTfComplemento().setText(usuario.getEndereco().getComplemento());
        telaUsuarioGerenciar.getCbEstado().getModel().setSelectedItem(usuario.getEndereco().getEstado());
        telaUsuarioGerenciar.getTfNumero().setText(usuario.getEndereco().getNumero());
        telaUsuarioGerenciar.getTfRua().setText(usuario.getEndereco().getRua());
        telaUsuarioGerenciar.getTfCep().setText(String.valueOf(usuario.getEndereco().getCep()));

        if (usuario.getAtivo() == true) {
            telaUsuarioGerenciar.getCheckAtivo().setSelected(true);
        } else {
            telaUsuarioGerenciar.getCheckAtivo().setSelected(false);
        }

    }

    private void limparCampos() {
        telaUsuarioGerenciar.getTfNome().setText("");
        telaUsuarioGerenciar.getTfTelefone().setText("");
        telaUsuarioGerenciar.getTfEmail().setText("");
        telaUsuarioGerenciar.getTfPis().setText("");
        telaUsuarioGerenciar.getTfSalario().setText("");
        telaUsuarioGerenciar.getTfSenha().setText("");
        telaUsuarioGerenciar.getTfPesquisar().setText("");
        telaUsuarioGerenciar.getTfCidade().setText("");
        telaUsuarioGerenciar.getTfBairro().setText("");
        telaUsuarioGerenciar.getTfComplemento().setText("");
        telaUsuarioGerenciar.getTfRua().setText("");
        telaUsuarioGerenciar.getTfCep().setText("");
        telaUsuarioGerenciar.getTfNumero().setText("");
        telaUsuarioGerenciar.getCheckAtivo().setSelected(false);
        telaUsuarioGerenciar.getCbTipoUsuario().setSelectedItem(0);
        telaUsuarioGerenciar.getCbEstado().setSelectedItem(0);
        telaUsuarioGerenciar.getTfNome().requestFocus();
    }
}
