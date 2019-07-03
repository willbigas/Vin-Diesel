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
import br.com.vindiesel.view.TelaDestinatarioFicha;
import br.com.vindiesel.view.TelaDestinatarioGerenciar;
import br.com.vindiesel.view.TelaPrincipal;
import java.text.ParseException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Will
 */
public class TelaDestinatarioGerenciarControl {

    private TelaDestinatarioGerenciar telaDestinatarioGerenciar;
    private TelaDestinatarioFicha telaDestinatarioFicha;
    private Destinatario destinatario;
    private Endereco endereco;
    private DestinatarioDao destinatarioDao;
    private DestinatarioTableModel destinatarioTableModel;
    private EnderecoDao enderecoDao;
    private Integer linhaSelecionada;
    MaskFormatter mascaraFormatadoraCPF;
    MaskFormatter mascaraFormatadoraCNPJ;

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
        redimensionarTabela();
        telaDestinatarioGerenciar.getTpDestinatario().setEnabledAt(1, false);
        telaDestinatarioGerenciar.getTfPesquisa().requestFocus();
        atualizaTotalDestinatarios(destinatarioDao.pesquisar());
        criaInstanciasDeMascarasFormatadas();
    }

    private void redimensionarTabela() {
        UtilTable.centralizarCabecalho(telaDestinatarioGerenciar.getTblDestinatario());
        UtilTable.redimensionar(telaDestinatarioGerenciar.getTblDestinatario(), 0, 80);
        UtilTable.redimensionar(telaDestinatarioGerenciar.getTblDestinatario(), 1, 350);
        UtilTable.redimensionar(telaDestinatarioGerenciar.getTblDestinatario(), 2, 155);
        UtilTable.redimensionar(telaDestinatarioGerenciar.getTblDestinatario(), 3, 160);
    }

    public void chamarDialogDestinatarioFichaAction() {
        telaDestinatarioFicha = new TelaDestinatarioFicha(telaDestinatarioGerenciar, true, this);
        carregarDestinatarioJdialogFicha();
        carregarEnderecoDialogFicha();
        telaDestinatarioFicha.setVisible(true);
    }

    private void carregarEstadosNaComboBox() {
        telaDestinatarioGerenciar.getCbEstado().setModel(new DefaultComboBoxModel<>(EnderecoSigla.ESTADOS_BRASILEIROS));
    }

    public void novoDestinatarioAction() {
        limparCampos();
        telaDestinatarioGerenciar.getTpDestinatario().setEnabledAt(1, true);
        UtilTable.limparSelecaoDaTabela(telaDestinatarioGerenciar.getTblDestinatario());
        destinatario = null;
        telaDestinatarioGerenciar.getTpDestinatario().setSelectedIndex(1);
    }

    private void cadastrarDestinatario() {
        destinatario = new Destinatario();
        destinatario.setNome(telaDestinatarioGerenciar.getTfNome().getText());
        destinatario.setCodigoPessoa(telaDestinatarioGerenciar.getTfCodigoPessoa().getText());
        endereco = new Endereco();
        endereco.setBairro(telaDestinatarioGerenciar.getTfBairro().getText());

        try {
            endereco.setCep(Integer.valueOf(telaDestinatarioGerenciar.getTfCep().getText()));

        } catch (NumberFormatException numberFormatException) {
            Mensagem.info(Texto.ERRO_COVERTER_CAMPO_CEP);
        }
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
            atualizaTotalDestinatarios(destinatarioDao.pesquisar());
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
            atualizaTotalDestinatarios(destinatarioDao.pesquisar());
            limparCampos();
        } else {
            Mensagem.erro(Texto.ERRO_EDITAR);
        }
        destinatario = null;
    }

    public void gravarDestinatarioAction() {
        if (destinatario == null) {
            cadastrarDestinatario();
        } else {
            alterarDestinatario();
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
            atualizaTotalDestinatarios(destinatariosPesquisados);
        } else {
            destinatarioTableModel.limpar();
            destinatarioTableModel.adicionar(destinatariosPesquisados);
            atualizaTotalDestinatarios(destinatariosPesquisados);
        }
    }

    public void carregarDestinatarioAction() {
        destinatario = destinatarioTableModel.pegaObjeto(telaDestinatarioGerenciar.getTblDestinatario().getSelectedRow());
        telaDestinatarioGerenciar.getTfNome().setText(destinatario.getNome());
        String codigoPessoa = destinatario.getCodigoPessoa();
        if (codigoPessoa.length() > 15) {
            formataTfCodigoPessoaParaCNPJ();
        } else {
            formataTfCodigoPessoaParaCPF();
        }
        telaDestinatarioGerenciar.getTfCodigoPessoa().setText(destinatario.getCodigoPessoa());

        telaDestinatarioGerenciar.getTfBairro().setText(destinatario.getEndereco().getBairro());
        telaDestinatarioGerenciar.getTfCidade().setText(destinatario.getEndereco().getCidade());
        telaDestinatarioGerenciar.getTfComplemento().setText(destinatario.getEndereco().getComplemento());
        telaDestinatarioGerenciar.getCbEstado().getModel().setSelectedItem(destinatario.getEndereco().getEstado());
        telaDestinatarioGerenciar.getTfNumero().setText(destinatario.getEndereco().getNumero());
        telaDestinatarioGerenciar.getTfRua().setText(destinatario.getEndereco().getRua());
        telaDestinatarioGerenciar.getTfCep().setText(String.valueOf(destinatario.getEndereco().getCep()));
        telaDestinatarioGerenciar.getTpDestinatario().setEnabledAt(1, true);
        telaDestinatarioGerenciar.getTpDestinatario().setSelectedIndex(1); // seleciona o tabbed pane
        telaDestinatarioGerenciar.getTfNome().requestFocus();
    }

    private void carregarDestinatarioJdialogFicha() {
        destinatario = destinatarioTableModel.pegaObjeto(telaDestinatarioGerenciar.getTblDestinatario().getSelectedRow());
        telaDestinatarioFicha.getLblNome().setText(destinatario.getNome());
        String codigoPessoa = destinatario.getCodigoPessoa();

        if (codigoPessoa.length() > 15) {
            formataTfCodigoPessoaParaCNPJ();
        } else {
            formataTfCodigoPessoaParaCPF();
        }
        telaDestinatarioFicha.getLblCodigoPessoa().setText(destinatario.getCodigoPessoa());

    }

    private void carregarEnderecoDialogFicha() {
        telaDestinatarioFicha.getLblBairro().setText(destinatario.getEndereco().getBairro());
        telaDestinatarioFicha.getLblCidade().setText(destinatario.getEndereco().getCidade());
        telaDestinatarioFicha.getLblComplemento().setText(destinatario.getEndereco().getComplemento());
        telaDestinatarioFicha.getLblEstado().setText(destinatario.getEndereco().getEstado());
        telaDestinatarioFicha.getLblNumero().setText(destinatario.getEndereco().getNumero());
        telaDestinatarioFicha.getLblRua().setText(destinatario.getEndereco().getRua());
        telaDestinatarioFicha.getLblCep().setText(String.valueOf(destinatario.getEndereco().getCep()));
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

    private void criaInstanciasDeMascarasFormatadas() {
        try {
            mascaraFormatadoraCPF = new javax.swing.text.MaskFormatter("###.###.###-##");
            mascaraFormatadoraCNPJ = new javax.swing.text.MaskFormatter("##.###.###/####-##");
        } catch (ParseException parseException) {
            Mensagem.erro(Texto.ERRO_CONVERTER_CAMPO_MASCARA_CNPJ);
        }

    }

    public void formataTfCodigoPessoaParaCNPJ() {
        mascaraFormatadoraCNPJ.install(telaDestinatarioGerenciar.getTfCodigoPessoa());
    }

    public void formataTfCodigoPessoaParaCPF() {
        mascaraFormatadoraCPF.install(telaDestinatarioGerenciar.getTfCodigoPessoa());

    }

    public void atualizaTotalDestinatarios(List<Destinatario> destinatarios) {
        Integer totalDestinatario = 0;
        Integer totalDestinatarioFiltrado = 0;
        List<Destinatario> destinatariosDobanco = destinatarioDao.pesquisar();

        totalDestinatario = destinatariosDobanco.size();
        totalDestinatarioFiltrado = destinatarios.size();

        telaDestinatarioGerenciar.getLblTotalDestinatarios().setText(String.valueOf(totalDestinatario));
        telaDestinatarioGerenciar.getLblDestinatariosFiltrados().setText(String.valueOf(totalDestinatarioFiltrado));
    }

    private void limparCampos() {
        telaDestinatarioGerenciar.getTfNome().setText("");
        telaDestinatarioGerenciar.getTfBairro().setText("");
        telaDestinatarioGerenciar.getTfCodigoPessoa().setText("");
        telaDestinatarioGerenciar.getTfCep().setText("");
        telaDestinatarioGerenciar.getTfCidade().setText("");
        telaDestinatarioGerenciar.getTfComplemento().setText("");
        telaDestinatarioGerenciar.getCbEstado().setSelectedIndex(0);
        telaDestinatarioGerenciar.getTfNumero().setText("");
        telaDestinatarioGerenciar.getTfPesquisa().setText("");
        telaDestinatarioGerenciar.getTfRua().setText("");
        telaDestinatarioGerenciar.getTfNome().requestFocus();
        UtilTable.limparSelecaoDaTabela(telaDestinatarioGerenciar.getTblDestinatario());
    }

}
