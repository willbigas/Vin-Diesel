package br.com.vindiesel.control;

import br.com.vindiesel.exceptions.BuscaCepException;
import br.com.vindiesel.interfaces.BuscaCepEventos;
import br.com.vindiesel.interfaces.BuscaCepEventosImpl;
import br.com.vindiesel.dao.EnderecoDao;
import br.com.vindiesel.dao.UsuarioDao;
import br.com.vindiesel.dao.TipoUsuarioDao;
import br.com.vindiesel.model.Endereco;
import br.com.vindiesel.model.Usuario;
import br.com.vindiesel.model.TipoUsuario;
import br.com.vindiesel.model.tablemodel.UsuarioTableModel;
import br.com.vindiesel.model.EnderecoSigla;
import br.com.vindiesel.uteis.Mensagem;
import br.com.vindiesel.uteis.Texto;
import br.com.vindiesel.uteis.UtilDate;
import br.com.vindiesel.uteis.UtilTable;
import br.com.vindiesel.uteis.Validacao;
import br.com.vindiesel.view.TelaPrincipal;
import br.com.vindiesel.view.TelaUsuarioGerenciar;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author William
 */
public class TelaUsuarioGerenciarControl {

    TelaUsuarioGerenciar telaUsuarioGerenciar;
    List<TipoUsuario> listTipoUsuarios;
    TipoUsuarioDao tipoUsuarioDao;
    UsuarioDao usuarioDao;
    UsuarioTableModel usuarioTableModel;
    Usuario usuario;
    EnderecoDao enderecoDao;
    Integer linhaSelecionada;
    Endereco endereco;

    public TelaUsuarioGerenciarControl() {
        tipoUsuarioDao = new TipoUsuarioDao();
        usuarioDao = new UsuarioDao();
        enderecoDao = new EnderecoDao();
        usuarioTableModel = new UsuarioTableModel();
    }

    public void carregarEstadosNaComboBox() {
        telaUsuarioGerenciar.getCbEstado().setModel(new DefaultComboBoxModel<>(EnderecoSigla.ESTADOS_BRASILEIROS));
    }

    public void chamarTelaUsuarioGerenciar() {
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
        telaUsuarioGerenciar.getTblUsuario().setModel(usuarioTableModel);
        usuarioTableModel.limpar();
        usuarioTableModel.adicionar(usuarioDao.pesquisar());
        redimensionarTela();
    }

    private void redimensionarTela() {
        UtilTable.centralizarCabecalho(telaUsuarioGerenciar.getTblUsuario());
        UtilTable.redimensionar(telaUsuarioGerenciar.getTblUsuario(), 0, 110);
        UtilTable.redimensionar(telaUsuarioGerenciar.getTblUsuario(), 1, 350);
        UtilTable.redimensionar(telaUsuarioGerenciar.getTblUsuario(), 2, 185);
        UtilTable.redimensionar(telaUsuarioGerenciar.getTblUsuario(), 3, 102);
        UtilTable.redimensionar(telaUsuarioGerenciar.getTblUsuario(), 4, 102);
    }

    private void carregarTiposUsuariosNaCombo() {
        listTipoUsuarios = tipoUsuarioDao.pesquisar();
        DefaultComboBoxModel<TipoUsuario> model = new DefaultComboBoxModel(listTipoUsuarios.toArray());
        telaUsuarioGerenciar.getCbTipoUsuario().setModel(model);
    }

    private void inserirUsuario() {
        usuario = new Usuario();

        usuario.setNome(telaUsuarioGerenciar.getTfNome().getText());
        usuario.setDataNascimento(UtilDate.dataLocal(telaUsuarioGerenciar.getTfDataNascimento().getText()));
        usuario.setTelefone(telaUsuarioGerenciar.getTftelefone().getText());
        usuario.setEmail(telaUsuarioGerenciar.getTfEmail().getText());
        usuario.setCpf(telaUsuarioGerenciar.getTfCpf().getText());
        try {

            usuario.setPis(Integer.valueOf(telaUsuarioGerenciar.getTfPis().getText()));
            usuario.setSalario(Double.valueOf(telaUsuarioGerenciar.getTfSalario().getText()));
        } catch (NumberFormatException numberFormatException) {
            Mensagem.info(Texto.ERRO_COVERTER_CAMPO_PIS_SALARIO);
            usuario = null;
            return;
        }
        usuario.setSenha(telaUsuarioGerenciar.getTfSenha().getText());
        usuario.setTipoUsuario((TipoUsuario) telaUsuarioGerenciar.getCbTipoUsuario().getSelectedItem());

        // modifica os atributos baseado no que o usuario modificar.
        endereco = new Endereco();
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

        if (Validacao.validaEntidade(usuario) != null) {
            Mensagem.info(Validacao.validaEntidade(usuario));
            usuario = null;
            endereco = null;
            return;
        }
        Integer idInserido = usuarioDao.inserir(usuario);
        if (idInserido != 0) {
            usuario.setId(idInserido);
            usuarioTableModel.adicionar(usuario);
            limparCamposAction();
            Mensagem.info(Texto.SUCESSO_CADASTRAR);
        } else {
            Mensagem.info(Texto.ERRO_CADASTRAR);
        }
        usuario = null;
        endereco = null;
    }

    private void alterarUsuario() {
        usuario = usuarioTableModel.pegaObjeto(telaUsuarioGerenciar.getTblUsuario().getSelectedRow());
        usuario.setNome(telaUsuarioGerenciar.getTfNome().getText());

        usuario.setDataNascimento(UtilDate.dataLocal(telaUsuarioGerenciar.getTfDataNascimento().getText()));
        usuario.setTelefone(telaUsuarioGerenciar.getTftelefone().getText());
        usuario.setEmail(telaUsuarioGerenciar.getTfEmail().getText());
        usuario.setCpf(telaUsuarioGerenciar.getTfCpf().getText());

        try {

            usuario.setPis(Integer.valueOf(telaUsuarioGerenciar.getTfPis().getText()));
            usuario.setSalario(Double.valueOf(telaUsuarioGerenciar.getTfSalario().getText()));
        } catch (NumberFormatException numberFormatException) {
            Mensagem.info(Texto.ERRO_COVERTER_CAMPO_PIS_SALARIO);
            return;
        }

        usuario.setSenha(telaUsuarioGerenciar.getTfSenha().getText());
        usuario.setTipoUsuario((TipoUsuario) telaUsuarioGerenciar.getCbTipoUsuario().getSelectedItem());

        endereco = usuario.getEndereco();
        endereco.setBairro(telaUsuarioGerenciar.getTfBairro().getText());
        endereco.setCep(Integer.valueOf(telaUsuarioGerenciar.getTfCep().getText()));
        endereco.setCidade(telaUsuarioGerenciar.getTfCidade().getText());
        endereco.setComplemento(telaUsuarioGerenciar.getTfComplemento().getText());
        endereco.setEstado((String) telaUsuarioGerenciar.getCbEstado().getSelectedItem());
        endereco.setNumero(telaUsuarioGerenciar.getTfNumero().getText());
        endereco.setRua(telaUsuarioGerenciar.getTfRua().getText());

        boolean enderecoAlterado = enderecoDao.alterar(endereco);

        if (!enderecoAlterado) {
            Mensagem.erro(Texto.ERRO_EDITAR);
            return;
        }

        if (telaUsuarioGerenciar.getCheckAtivo().isSelected()) {
            usuario.setAtivo(true);
        } else {
            usuario.setAtivo(false);
        }

        if (Validacao.validaEntidade(usuario) != null) {
            Mensagem.info(Validacao.validaEntidade(usuario));
            return;
        }
        usuario.setEndereco(endereco);
        boolean alterado = usuarioDao.alterar(usuario);
        linhaSelecionada = telaUsuarioGerenciar.getTblUsuario().getSelectedRow();
        if (alterado) {
            usuarioTableModel.atualizar(linhaSelecionada, usuario);
            Mensagem.info(Texto.SUCESSO_EDITAR);
            limparCamposAction();
        } else {
            Mensagem.erro(Texto.ERRO_EDITAR);
        }
        usuario = null;
        endereco = null;
    }

    public void buscarCepAction() {
        BuscaCepEventos buscaCepEvents = new BuscaCepEventosImpl();
        BuscadorDeCepControl buscadorDeCep = new BuscadorDeCepControl();
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
            telaUsuarioGerenciar.getTfNumero().requestFocus();

        } catch (BuscaCepException buscaCepException) {
            System.out.println(buscaCepException.getMessage());
            buscaCepException.printStackTrace();
        } catch (NumberFormatException numberFormatException) {
            System.out.println(numberFormatException.getMessage());
            numberFormatException.printStackTrace();
        }
    }

    public void gravarUsuarioAction() {
        if (usuario == null) {
            inserirUsuario();
        } else {
            alterarUsuario();
        }
    }

    public void desativarUsuarioAction() {
        int retorno = Mensagem.confirmacao(Texto.PERGUNTA_DESATIVAR);

        if (retorno == JOptionPane.NO_OPTION) {
            return;
        }
        if (retorno == JOptionPane.YES_OPTION) {
            usuario = usuarioTableModel.pegaObjeto(telaUsuarioGerenciar.getTblUsuario().getSelectedRow());
            boolean deletado = usuarioDao.desativar(usuario.getId());
            if (deletado) {
                usuarioTableModel.remover(telaUsuarioGerenciar.getTblUsuario().getSelectedRow());
                telaUsuarioGerenciar.getTblUsuario().clearSelection();
                Mensagem.info(Texto.SUCESSO_DESATIVAR);
            } else {
                Mensagem.erro(Texto.ERRO_DESATIVAR);
            }
        }
        usuario = null;
    }

    public void carregarUsuarioAction() {
        usuario = usuarioTableModel.pegaObjeto(telaUsuarioGerenciar.getTblUsuario().getSelectedRow());
        telaUsuarioGerenciar.getTfNome().setText(usuario.getNome());
        telaUsuarioGerenciar.getTfDataNascimento().setText(UtilDate.dataLocal(usuario.getDataNascimento()));
        System.out.println("Cpf do Usuário :" + usuario.getCpf());
        telaUsuarioGerenciar.getTfCpf().setText(usuario.getCpf());
        telaUsuarioGerenciar.getTftelefone().setText(usuario.getTelefone());
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
        telaUsuarioGerenciar.getTpGerenciarUsuario().setSelectedIndex(1);
    }

    public void pesquisarUsuarioAction() {
        List<Usuario> encomendasPesquisadas = usuarioDao.pesquisar(telaUsuarioGerenciar.getTfPesquisar().getText());
        if (encomendasPesquisadas == null) {
            usuarioTableModel.limpar();
            encomendasPesquisadas = usuarioDao.pesquisar();
        } else {
            usuarioTableModel.limpar();
            usuarioTableModel.adicionar(encomendasPesquisadas);
        }

    }

    public void limparCamposAction() {
        telaUsuarioGerenciar.getTfNome().setText("");
        telaUsuarioGerenciar.getTfCpf().setText("");
        telaUsuarioGerenciar.getTfDataNascimento().setText("");
        telaUsuarioGerenciar.getTftelefone().setText("");
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
        telaUsuarioGerenciar.getCbTipoUsuario().getModel().setSelectedItem(tipoUsuarioDao.pesquisar(1));
        telaUsuarioGerenciar.getCbEstado().getModel().setSelectedItem("AC");
        telaUsuarioGerenciar.getTfNome().requestFocus();
    }
}
