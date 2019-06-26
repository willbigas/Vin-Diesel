package br.com.vindiesel.control;

import br.com.vindiesel.dao.DimensaoDao;
import br.com.vindiesel.dao.EncomendaDao;
import br.com.vindiesel.model.Dimensao;
import br.com.vindiesel.model.Encomenda;
import br.com.vindiesel.model.tablemodel.EncomendaTableModel;
import br.com.vindiesel.uteis.Mensagem;
import br.com.vindiesel.uteis.Texto;
import br.com.vindiesel.uteis.DecimalFormat;
import br.com.vindiesel.uteis.UtilTable;
import br.com.vindiesel.uteis.Validacao;
import br.com.vindiesel.view.TelaPrincipal;
import br.com.vindiesel.view.TelaEncomendaGerenciar;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author William
 */
public class TelaEncomendaGerenciarControl {

    TelaEncomendaGerenciar telaEncomendaGerenciar;
    EncomendaTableModel encomendaTableModel;
    EncomendaDao encomendaDao;
    DimensaoDao dimensaoDao;
    Encomenda encomenda;
    Dimensao dimensao;
    Integer linhaSelecionada;

    public TelaEncomendaGerenciarControl() {
        encomendaDao = new EncomendaDao();
        dimensaoDao = new DimensaoDao();
        encomendaTableModel = new EncomendaTableModel();
    }

    public void chamarTelaEncomendaGerenciar() {
        if (telaEncomendaGerenciar == null) {
            telaEncomendaGerenciar = new TelaEncomendaGerenciar(this);
            TelaPrincipal.desktopPane.add(telaEncomendaGerenciar);
            telaEncomendaGerenciar.setVisible(true);
        } else {
            if (telaEncomendaGerenciar.isVisible()) {
                telaEncomendaGerenciar.pack();
            } else {
                TelaPrincipal.desktopPane.add(telaEncomendaGerenciar);
                telaEncomendaGerenciar.setVisible(true);
            }
        }

        telaEncomendaGerenciar.getTblProduto().setModel(encomendaTableModel);
        encomendaTableModel.limpar();
        encomendaTableModel.adicionar(encomendaDao.pesquisar());
        telaEncomendaGerenciar.getTfCodigoRastreio().setEditable(false);
        redimensionarTela();
    }

    private void redimensionarTela() {
        UtilTable.centralizarCabecalho(telaEncomendaGerenciar.getTblProduto());
        UtilTable.redimensionar(telaEncomendaGerenciar.getTblProduto(), 0, 130);
        UtilTable.redimensionar(telaEncomendaGerenciar.getTblProduto(), 1, 130);
        UtilTable.redimensionar(telaEncomendaGerenciar.getTblProduto(), 2, 82);
        UtilTable.redimensionar(telaEncomendaGerenciar.getTblProduto(), 3, 82);
        UtilTable.redimensionar(telaEncomendaGerenciar.getTblProduto(), 4, 82);
        UtilTable.redimensionar(telaEncomendaGerenciar.getTblProduto(), 5, 82);
    }

    private void cadastrarEncomenda() {
        encomenda = new Encomenda();
        try {
            encomenda.setCodigoRastreio(telaEncomendaGerenciar.getTfCodigoRastreio().getText());
            encomenda.setPeso(Double.valueOf(DecimalFormat.paraPonto(telaEncomendaGerenciar.getTfPeso().getText())));
            encomenda.setValorNotaFiscal(Double.valueOf(DecimalFormat.paraPonto(telaEncomendaGerenciar.getTfValorNf().getText())));

            dimensao = new Dimensao();
            dimensao.setComprimento(Double.valueOf(DecimalFormat.paraPonto(telaEncomendaGerenciar.getTfComprimento().getText())));
            dimensao.setAltura(Double.valueOf(DecimalFormat.paraPonto(telaEncomendaGerenciar.getTfAltura().getText())));
            dimensao.setLargura(Double.valueOf(DecimalFormat.paraPonto(telaEncomendaGerenciar.getTfLargura().getText())));
        } catch (NumberFormatException numberFormatException) {
            Mensagem.info(Texto.ERRO_COVERTER_CAMPO_DECIMAL);
            dimensao = null;
            encomenda = null;
            return;
        }

        if (Validacao.validaEntidade(dimensao) != null) {
            Mensagem.info(Validacao.validaEntidade(dimensao));
            dimensao = null;
            encomenda = null;
            return;
        }
        int idDimensaoInserida = dimensaoDao.inserir(dimensao);
        dimensao.setId(idDimensaoInserida);
        encomenda.setDimensao(dimensao);

        if (Validacao.validaEntidade(encomenda) != null) {
            Mensagem.info(Validacao.validaEntidade(encomenda));
            dimensao = null;
            encomenda = null;
            return;
        }

        Integer idInserido = encomendaDao.inserir(encomenda);
        if (idInserido != 0) {
            encomenda.setId(idInserido);
            encomendaTableModel.adicionar(encomenda);
            limparCampos();
            Mensagem.info(Texto.SUCESSO_CADASTRAR);
        } else {
            Mensagem.erro(Texto.ERRO_CADASTRAR);
        }
        encomenda = null;
    }

    private void alterarEncomenda() {
        linhaSelecionada = telaEncomendaGerenciar.getTblProduto().getSelectedRow();
        encomenda = encomendaTableModel.pegaObjeto(telaEncomendaGerenciar.getTblProduto().getSelectedRow());

        try {

            encomenda.setCodigoRastreio(telaEncomendaGerenciar.getTfCodigoRastreio().getText());
            encomenda.setPeso(Double.valueOf(DecimalFormat.paraPonto(telaEncomendaGerenciar.getTfPeso().getText())));
            encomenda.setValorNotaFiscal(Double.valueOf(DecimalFormat.paraPonto(telaEncomendaGerenciar.getTfValorNf().getText())));

            dimensao = encomenda.getDimensao();
            dimensao.setComprimento(Double.valueOf(DecimalFormat.paraPonto(telaEncomendaGerenciar.getTfComprimento().getText())));
            dimensao.setAltura(Double.valueOf(DecimalFormat.paraPonto(telaEncomendaGerenciar.getTfAltura().getText())));
            dimensao.setLargura(Double.valueOf(DecimalFormat.paraPonto(telaEncomendaGerenciar.getTfLargura().getText())));
        } catch (NumberFormatException numberFormatException) {
            Mensagem.info(Texto.ERRO_COVERTER_CAMPO_DECIMAL);
            return;
        }

        if (Validacao.validaEntidade(dimensao) != null) {
            Mensagem.info(Validacao.validaEntidade(dimensao));
            return;
        }

        boolean dimensaoAlterada = dimensaoDao.alterar(dimensao);
        encomenda.setDimensao(dimensao);

        if (Validacao.validaEntidade(encomenda) != null) {
            Mensagem.info(Validacao.validaEntidade(encomenda));
            return;
        }
        // atributos de encomenda
        boolean alterado = encomendaDao.alterar(encomenda);

        if (alterado) {
            encomendaTableModel.atualizar(linhaSelecionada, encomenda);
            Mensagem.info(Texto.SUCESSO_EDITAR);
            limparCampos();
        } else {
            Mensagem.erro(Texto.ERRO_EDITAR);
        }
        encomenda = null;
        dimensao = null;
    }

    public void gravarProdutoAction() {
        if (encomenda == null) {
            cadastrarEncomenda();
        } else {
            alterarEncomenda();
        }
    }

    public void carregarEncomendaAction() {
        encomenda = encomendaTableModel.pegaObjeto(telaEncomendaGerenciar.getTblProduto().getSelectedRow());
        telaEncomendaGerenciar.getTfCodigoRastreio().setText(encomenda.getCodigoRastreio());
        telaEncomendaGerenciar.getTfPeso().setText(DecimalFormat.paraVirgula(String.valueOf(encomenda.getPeso())));
        telaEncomendaGerenciar.getTfValorNf().setText(DecimalFormat.paraVirgula(String.valueOf(encomenda.getValorNotaFiscal())));
        telaEncomendaGerenciar.getTfComprimento().setText(DecimalFormat.paraVirgula(String.valueOf(encomenda.getDimensao().getComprimento())));
        telaEncomendaGerenciar.getTfAltura().setText(DecimalFormat.paraVirgula(String.valueOf(encomenda.getDimensao().getAltura())));
        telaEncomendaGerenciar.getTfLargura().setText(DecimalFormat.paraVirgula(String.valueOf(encomenda.getDimensao().getLargura())));
        telaEncomendaGerenciar.getTpProduto().setSelectedIndex(1);
        // Atributos de encomenda
    }

    public void excluirEncomendaAction() {
        int retorno = Mensagem.confirmacao(Texto.PERGUNTA_EXCLUIR);
        if (retorno == JOptionPane.NO_OPTION) {
            return;
        }

        if (retorno == JOptionPane.YES_OPTION) {
            encomenda = encomendaTableModel.pegaObjeto(telaEncomendaGerenciar.getTblProduto().getSelectedRow());
            if (encomendaDao.deletar(encomenda.getId())) {
                encomendaTableModel.remover(telaEncomendaGerenciar.getTblProduto().getSelectedRow());
                telaEncomendaGerenciar.getTblProduto().clearSelection();
                Mensagem.info(Texto.SUCESSO_REMOVER);
            } else {
                Mensagem.erro(Texto.ERRO_DELETAR);
            }
        }
        encomenda = null;
    }

    public void pesquisarProdutoAction() {
        List<Encomenda> encomendasPesquisadas = encomendaDao.pesquisar(telaEncomendaGerenciar.getTfPesquisar().getText());
        if (encomendasPesquisadas == null) {
            encomendaTableModel.limpar();
            encomendasPesquisadas = encomendaDao.pesquisar();
        } else {
            encomendaTableModel.limpar();
            encomendaTableModel.adicionar(encomendasPesquisadas);
        }

    }

    public void limparCampos() {
        telaEncomendaGerenciar.getTfCodigoRastreio().setText("");
        telaEncomendaGerenciar.getTfPeso().setText("");
        telaEncomendaGerenciar.getTfValorNf().setText("");
        telaEncomendaGerenciar.getTfAltura().setText("");
        telaEncomendaGerenciar.getTfComprimento().setText("");
        telaEncomendaGerenciar.getTfLargura().setText("");
        telaEncomendaGerenciar.getTfPesquisar().setText("");
        telaEncomendaGerenciar.getTblProduto().clearSelection();
    }

    private String gerarCodigoRandomico() {
        Random rand = new Random();
        String prefixo = "VD-";
        String randomico = Integer.toHexString(rand.nextInt()).toUpperCase();
        String codigoMontado = prefixo + randomico;
        return codigoMontado;
    }

    public void geraCodigoRastreamentoRandomicoAction() {
        String codigoFinal = "";
        String codigoGeradoRancomicamente = gerarCodigoRandomico();
        if (encomendaDao.pesquisar(codigoGeradoRancomicamente) != null) {
            codigoFinal = gerarCodigoRandomico();
        } else {
            codigoFinal = codigoGeradoRancomicamente;
        }
        telaEncomendaGerenciar.getTfCodigoRastreio().setText(codigoFinal);
    }
}
