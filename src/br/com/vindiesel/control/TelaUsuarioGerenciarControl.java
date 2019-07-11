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
import br.com.vindiesel.uteis.DecimalFormat;
import br.com.vindiesel.uteis.Mensagem;
import br.com.vindiesel.uteis.Texto;
import br.com.vindiesel.uteis.UtilDate;
import br.com.vindiesel.uteis.UtilTable;
import br.com.vindiesel.uteis.Validacao;
import br.com.vindiesel.view.TelaPrincipal;
import br.com.vindiesel.view.TelaUsuarioFicha;
import br.com.vindiesel.view.TelaUsuarioGerenciar;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author William
 */
public class TelaUsuarioGerenciarControl {

    private static final int CPF = 0;
    private static final int NOME = 1;
    private static final int EMAIL = 2;
    private static final int TELEFONE = 3;
    private static final int TIPO_USUARIO = 4;
    private static final int ATIVO = 5;

    private static final int CB_OPCAO_TODOS = 0;
    private static final int CB_OPCAO_ATIVOS = 1;
    private static final int CB_OPCAO_INATIVOS = 2;

    TelaUsuarioGerenciar telaUsuarioGerenciar;
    TelaUsuarioFicha telaUsuarioFicha;
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

    public void carregarFiltrosNaComboBox() {
        telaUsuarioGerenciar.getCbFiltroTabela().setSelectedIndex(0);
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
        carregarFiltrosNaComboBox();
        carregarTiposUsuariosNaCombo();
        carregarEstadosNaComboBox();
        telaUsuarioGerenciar.getTblUsuario().setModel(usuarioTableModel);
        usuarioTableModel.limpar();
        usuarioTableModel.adicionar(usuarioDao.pesquisar());
        atualizaTotalUsuarios(usuarioDao.pesquisar());
        telaUsuarioGerenciar.getTpGerenciarUsuario().setEnabledAt(1, false);
        telaUsuarioGerenciar.getTfPesquisar().requestFocus();
        redimensionarTabela();
    }

    public void chamarDialogUsuarioFichaAction() {
        telaUsuarioFicha = new TelaUsuarioFicha(telaUsuarioGerenciar, true, this);
        carregarUsuarioDialogFicha();
        carregarEnderecoDialogFicha();
        telaUsuarioFicha.setVisible(true);
    }

    private void redimensionarTabela() {
        UtilTable.centralizarCabecalho(telaUsuarioGerenciar.getTblUsuario());
        UtilTable.redimensionar(telaUsuarioGerenciar.getTblUsuario(), CPF, 135);
        UtilTable.redimensionar(telaUsuarioGerenciar.getTblUsuario(), NOME, 264);
        UtilTable.redimensionar(telaUsuarioGerenciar.getTblUsuario(), EMAIL, 215);
        UtilTable.redimensionar(telaUsuarioGerenciar.getTblUsuario(), TELEFONE, 118);
        UtilTable.redimensionar(telaUsuarioGerenciar.getTblUsuario(), TIPO_USUARIO, 118);
        UtilTable.redimensionar(telaUsuarioGerenciar.getTblUsuario(), ATIVO, 96);
    }

    private void carregarTiposUsuariosNaCombo() {
        listTipoUsuarios = tipoUsuarioDao.pesquisar();
        DefaultComboBoxModel<TipoUsuario> model = new DefaultComboBoxModel(listTipoUsuarios.toArray());
        telaUsuarioGerenciar.getCbTipoUsuario().setModel(model);
    }

    public void novoUsuarioAction() {
        limparCamposAction();
        telaUsuarioGerenciar.getTpGerenciarUsuario().setEnabledAt(1, true);
        UtilTable.limparSelecaoDaTabela(telaUsuarioGerenciar.getTblUsuario());
        usuario = null;
        telaUsuarioGerenciar.getTpGerenciarUsuario().setSelectedIndex(1);
    }

    private void inserirUsuario() {
        if (verificarEmailMaiorQue45()) {
            Mensagem.atencao(Texto.ERRO_EMAIL);
            return;
        }

        try {
            usuario = new Usuario();

            setandoAtributosDeUsuario();

            if (verificaSeTemEmailNoBanco(usuario.getEmail())) {
                usuario = null;
                Mensagem.atencao(Texto.ERRO_USUARIO_DUPLICADO);
                return;
            }

        } catch (NumberFormatException numberFormatException) {
            Mensagem.info(Texto.ERRO_COVERTER_CAMPO_PIS_SALARIO);
            usuario = null;
            return;
        }

        // modifica os atributos baseado no que o usuario modificar.
        endereco = new Endereco();
        setandoAtributosDeEndereco();

        Integer idEndereco = enderecoDao.inserir(endereco);
        endereco.setId(idEndereco);
        usuario.setEndereco(endereco);

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
            atualizaTotalUsuarios(usuarioDao.pesquisar());
            limparCamposAction();
            Mensagem.info(Texto.SUCESSO_CADASTRAR_USUARIO);
        } else {
            Mensagem.info(Texto.ERRO_CADASTRAR_USUARIO);
        }
        usuario = null;
        endereco = null;
    }

    private boolean verificarEmailMaiorQue45() {
        String emailUsuario = telaUsuarioGerenciar.getTfEmail().getText();
        if (emailUsuario.length() > 45) {
            return true;
        }
        return false;
    }

    private void setandoAtributosDeEndereco() throws NumberFormatException {
        endereco.setBairro(telaUsuarioGerenciar.getTfBairro().getText());
        endereco.setCep(Integer.valueOf(telaUsuarioGerenciar.getTfCep().getText()));
        endereco.setCidade(telaUsuarioGerenciar.getTfCidade().getText());
        endereco.setComplemento(telaUsuarioGerenciar.getTfComplemento().getText());
        endereco.setEstado((String) telaUsuarioGerenciar.getCbEstado().getSelectedItem());
        endereco.setNumero(telaUsuarioGerenciar.getTfNumero().getText());
        endereco.setRua(telaUsuarioGerenciar.getTfRua().getText());
    }

    private void alterarUsuario() {
        if (verificarEmailMaiorQue45()) {
            Mensagem.atencao(Texto.ERRO_EMAIL);
            return;
        }

        try {
            usuario = usuarioTableModel.pegaObjeto(telaUsuarioGerenciar.getTblUsuario().getSelectedRow());

            setandoAtributosDeUsuario();

        } catch (NumberFormatException numberFormatException) {
            Mensagem.info(Texto.ERRO_COVERTER_CAMPO_PIS_SALARIO);
            return;
        }
        endereco = usuario.getEndereco();

        setandoAtributosDeEndereco();

        boolean enderecoAlterado = enderecoDao.alterar(endereco);

        if (!enderecoAlterado) {
            Mensagem.erro(Texto.ERRO_EDITAR);
            return;
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
            atualizaTotalUsuarios(usuarioDao.pesquisar());
            limparCamposAction();
        } else {
            Mensagem.erro(Texto.ERRO_EDITAR);
        }
        usuario = null;
        endereco = null;
    }

    private void setandoAtributosDeUsuario() throws NumberFormatException {
        usuario.setNome(telaUsuarioGerenciar.getTfNome().getText());
        usuario.setDataNascimento(UtilDate.pegaLocalDate(telaUsuarioGerenciar.getTfDataNascimento().getDate()));
        usuario.setTelefone(telaUsuarioGerenciar.getTfTelefone().getText());
        usuario.setEmail(telaUsuarioGerenciar.getTfEmail().getText());
        usuario.setCpf(telaUsuarioGerenciar.getTfCpf().getText());
        usuario.setPis(Integer.valueOf(telaUsuarioGerenciar.getTfPis().getText()));
        usuario.setSalario(Double.valueOf(DecimalFormat.paraPonto(telaUsuarioGerenciar.getTfSalario().getText())));
        usuario.setSenha(telaUsuarioGerenciar.getTfSenha().getText());
        usuario.setTipoUsuario((TipoUsuario) telaUsuarioGerenciar.getCbTipoUsuario().getSelectedItem());

        if (telaUsuarioGerenciar.getCheckAtivo().isSelected()) {
            usuario.setAtivo(true);
        } else {
            usuario.setAtivo(false);
        }
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

        } catch (NumberFormatException numberFormatException) {
            Mensagem.erro(Texto.ERRO_COVERTER_CAMPO_CEP);
            System.out.println(numberFormatException.getMessage());
            numberFormatException.printStackTrace();
            return;
        } catch (BuscaCepException buscaCepException) {
            Mensagem.erro(Texto.ERRO_CEP_NAO_ENCONTRADO);
            System.out.println(buscaCepException.getMessage());
            buscaCepException.printStackTrace();
            return;
        } catch (Exception exception) {
            Mensagem.erro(Texto.ERRO_CEP_GENERICO);
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            return;
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
        if (telaUsuarioGerenciar.getTblUsuario().getSelectedRow() == -1) {
            Mensagem.info(Texto.SELECIONADA_LINHA);
            return;
        }
        usuario = usuarioTableModel.pegaObjeto(telaUsuarioGerenciar.getTblUsuario().getSelectedRow());
        int retorno = Mensagem.confirmacao(Texto.PERGUNTA_DESATIVAR + usuario.getNome() + " ?");

        if (retorno == JOptionPane.NO_OPTION || retorno == JOptionPane.CLOSED_OPTION) {
            return;
        }

        if (usuarioDao.desativar(usuario.getId())) {
            usuarioTableModel.remover(telaUsuarioGerenciar.getTblUsuario().getSelectedRow());
            telaUsuarioGerenciar.getTblUsuario().clearSelection();
            atualizaTotalUsuarios(usuarioDao.pesquisar());
            Mensagem.info(usuario.getNome() + " " + Texto.SUCESSO_DESATIVAR);
        } else {
            Mensagem.erro(Texto.ERRO_DESATIVAR + " " + usuario.getNome());
        }
        usuario = null;
    }

    public void carregarUsuarioAction() {
        if (validaLinhaNaoSelecionada()) {
            return;
        }

        usuario = usuarioTableModel.pegaObjeto(telaUsuarioGerenciar.getTblUsuario().getSelectedRow());
        popularCamposDeUsuario();
        popularCamposDeEndereco();

        if (usuario.getAtivo() == true) {
            telaUsuarioGerenciar.getCheckAtivo().setSelected(true);
        } else {
            telaUsuarioGerenciar.getCheckAtivo().setSelected(false);
        }
        telaUsuarioGerenciar.getTpGerenciarUsuario().setEnabledAt(1, true); // habilita o tabbed pane
        telaUsuarioGerenciar.getTpGerenciarUsuario().setSelectedIndex(1);
        telaUsuarioGerenciar.getTfNome().requestFocus();
        telaUsuarioGerenciar.getTfEmail().setEnabled(false);
    }

    private boolean validaLinhaNaoSelecionada() {
        if (telaUsuarioGerenciar.getTblUsuario().getSelectedRow() == -1) {
            Mensagem.atencao(Texto.SELECIONADA_LINHA);
            return true;
        }
        return false;
    }

    private void popularCamposDeEndereco() {
        telaUsuarioGerenciar.getTfBairro().setText(usuario.getEndereco().getBairro());
        telaUsuarioGerenciar.getTfCidade().setText(usuario.getEndereco().getCidade());
        telaUsuarioGerenciar.getTfComplemento().setText(usuario.getEndereco().getComplemento());
        telaUsuarioGerenciar.getCbEstado().getModel().setSelectedItem(usuario.getEndereco().getEstado());
        telaUsuarioGerenciar.getTfNumero().setText(usuario.getEndereco().getNumero());
        telaUsuarioGerenciar.getTfRua().setText(usuario.getEndereco().getRua());
        telaUsuarioGerenciar.getTfCep().setText(String.valueOf(usuario.getEndereco().getCep()));
    }

    private void popularCamposDeUsuario() {
        telaUsuarioGerenciar.getTfNome().setText(usuario.getNome());
        telaUsuarioGerenciar.getTfDataNascimento().setDate(UtilDate.toDate(usuario.getDataNascimento()));
        telaUsuarioGerenciar.getTfCpf().setText(usuario.getCpf());
        telaUsuarioGerenciar.getTfTelefone().setText(usuario.getTelefone());
        telaUsuarioGerenciar.getTfEmail().setText(usuario.getEmail());
        telaUsuarioGerenciar.getTfPis().setText(String.valueOf(usuario.getPis()));
        telaUsuarioGerenciar.getTfSalario().setText(DecimalFormat.paraVirgula(String.valueOf(usuario.getSalario())));
        telaUsuarioGerenciar.getTfSenha().setText(usuario.getSenha());
    }

    private void carregarUsuarioDialogFicha() {
        usuario = usuarioTableModel.pegaObjeto(telaUsuarioGerenciar.getTblUsuario().getSelectedRow());
        telaUsuarioFicha.getLblNome().setText(usuario.getNome());
        telaUsuarioFicha.getLblNascimento().setText(UtilDate.dataLocal(usuario.getDataNascimento()));
        telaUsuarioFicha.getLblCpf().setText(usuario.getCpf());
        telaUsuarioFicha.getLblTelefone().setText(usuario.getTelefone());
        telaUsuarioFicha.getLblEmail().setText(usuario.getEmail());
        telaUsuarioFicha.getLblPis().setText(String.valueOf(usuario.getPis()));
        telaUsuarioFicha.getLblSalario().setText(DecimalFormat.decimalFormatR$((usuario.getSalario())));
        telaUsuarioFicha.getLblSenha().setText(usuario.getSenha());
        telaUsuarioFicha.getLblTipoUsuario().setText(String.valueOf(usuario.getTipoUsuario()));
    }

    private void carregarEnderecoDialogFicha() {
        telaUsuarioFicha.getLblBairro().setText(usuario.getEndereco().getBairro());
        telaUsuarioFicha.getLblCidade().setText(usuario.getEndereco().getCidade());
        telaUsuarioFicha.getLblComplemento().setText(usuario.getEndereco().getComplemento());
        telaUsuarioFicha.getLblUf().setText(usuario.getEndereco().getEstado());
        telaUsuarioFicha.getLblNumero().setText(usuario.getEndereco().getNumero());
        telaUsuarioFicha.getLblRua().setText(usuario.getEndereco().getRua());
        telaUsuarioFicha.getLblCep().setText(String.valueOf(usuario.getEndereco().getCep()));
    }

    public void pesquisarUsuarioAction() {
        List<Usuario> usuariosPesquisados = pesquisarPorComboBoxAction(telaUsuarioGerenciar.getTfPesquisar().getText());
        if (usuariosPesquisados == null) {
            usuarioTableModel.limpar();
            usuariosPesquisados = usuarioDao.pesquisar();
            atualizaTotalUsuarios(usuariosPesquisados);
        } else {
            usuarioTableModel.limpar();
            usuarioTableModel.adicionar(usuariosPesquisados);
            atualizaTotalUsuarios(usuariosPesquisados);
        }

    }

    public List<Usuario> pesquisarPorComboBoxAction(String termo) {
        List<Usuario> usuariosPesquisados = new ArrayList<>();
        int result = telaUsuarioGerenciar.getCbFiltroTabela().getSelectedIndex();
        if (result == CB_OPCAO_TODOS) {
            usuarioTableModel.limpar();
            usuariosPesquisados = usuarioDao.pesquisar(termo, null);
            usuarioTableModel.adicionar(usuarioDao.pesquisar());
            atualizaTotalUsuarios(usuarioDao.pesquisar());
        }
        if (result == CB_OPCAO_ATIVOS) {
            usuarioTableModel.limpar();
            usuariosPesquisados = usuarioDao.pesquisar(termo, true);
            usuarioTableModel.adicionar(usuarioDao.pesquisar(termo, true));
            atualizaTotalUsuarios(usuarioDao.pesquisar(termo, true));
        }
        if (result == CB_OPCAO_INATIVOS) {
            usuarioTableModel.limpar();
            usuariosPesquisados = usuarioDao.pesquisar(termo, false);
            usuarioTableModel.adicionar(usuarioDao.pesquisar(termo, false));
            atualizaTotalUsuarios(usuarioDao.pesquisar(termo, false));
        }
        return usuariosPesquisados;

    }

    public void atualizaTotalUsuarios(List<Usuario> usuarios) {
        Integer totalUsuario = 0;
        Integer totalUsuarioFiltrado = 0;
        List<Usuario> usuariosDobanco = usuarioDao.pesquisar();

        totalUsuario = usuariosDobanco.size();
        totalUsuarioFiltrado = usuarios.size();

        telaUsuarioGerenciar.getLblUsuarioTotal().setText(String.valueOf(totalUsuario));
        telaUsuarioGerenciar.getLblUsuarioFiltrado().setText(String.valueOf(totalUsuarioFiltrado));
    }

    public Boolean verificaSeTemEmailNoBanco(String email) {
        if (usuarioDao.pesquisarPorLogin(email) != null) {
            return true;
        }
        return false;
    }

    public void limparCamposAction() {
        telaUsuarioGerenciar.getTfNome().setText("");
        telaUsuarioGerenciar.getTfCpf().setText("");
        telaUsuarioGerenciar.getTfDataNascimento().setDate(Date.from(Instant.now()));
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
        telaUsuarioGerenciar.getCbTipoUsuario().getModel().setSelectedItem(tipoUsuarioDao.pesquisar(1));
        telaUsuarioGerenciar.getCbEstado().getModel().setSelectedItem("AC");
        telaUsuarioGerenciar.getTfNome().requestFocus();
        telaUsuarioGerenciar.getTfEmail().setEnabled(true);
    }

}
