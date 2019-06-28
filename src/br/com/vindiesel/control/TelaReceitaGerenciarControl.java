/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.control;

import br.com.vindiesel.dao.FormaPagamentoDao;
import br.com.vindiesel.dao.ReceitaDao;
import br.com.vindiesel.model.Entrega;
import br.com.vindiesel.model.FormaPagamento;
import br.com.vindiesel.model.Receita;
import br.com.vindiesel.model.tablemodel.ReceitaTableModel;
import br.com.vindiesel.uteis.Mensagem;
import br.com.vindiesel.uteis.Texto;
import br.com.vindiesel.uteis.UtilDate;
import br.com.vindiesel.uteis.DecimalFormat;
import br.com.vindiesel.uteis.UtilTable;
import br.com.vindiesel.uteis.Validacao;
import br.com.vindiesel.view.TelaPrincipal;
import br.com.vindiesel.view.TelaReceitaGerenciar;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author William
 */
public class TelaReceitaGerenciarControl {

    TelaReceitaGerenciar telaReceitaGerenciar;
    ReceitaTableModel receitaTableModel;
    ReceitaDao receitaDao;
    List<FormaPagamento> listFormaPagamentos;
    FormaPagamentoDao formaPagamentoDao;
    Receita receita;
    Integer indexSelecionada;

    public TelaReceitaGerenciarControl() {
        receitaDao = new ReceitaDao();
        receitaTableModel = new ReceitaTableModel();
        formaPagamentoDao = new FormaPagamentoDao();
    }

    public void chamarTelaReceitaGerenciar() {
        if (telaReceitaGerenciar == null) { // se tiver nulo chama janela normalmente
            telaReceitaGerenciar = new TelaReceitaGerenciar(this);
            TelaPrincipal.desktopPane.add(telaReceitaGerenciar);
            telaReceitaGerenciar.setVisible(true);
        } else {//se ele estiver criado
            if (telaReceitaGerenciar.isVisible()) {
                telaReceitaGerenciar.pack();//Redimensiona ao Quadro Original
            } else {
                TelaPrincipal.desktopPane.add(telaReceitaGerenciar);
                telaReceitaGerenciar.setVisible(true);
            }
        }
        telaReceitaGerenciar.getTblReceita().setModel(receitaTableModel);
        atualizaTotalDeValor(receitaDao.pesquisar());
        receitaTableModel.limpar();
        receitaTableModel.adicionar(receitaDao.pesquisar());
        carregarFormaPagamentoNaCombo();
        redimensionarTabelaReceita();
        telaReceitaGerenciar.getTpReceita().setEnabledAt(1, false); // disabilita o tabbed pane
        telaReceitaGerenciar.getTfPesquisarReceita().requestFocus();
    }

    private void redimensionarTabelaReceita() {
        UtilTable.centralizarCabecalho(telaReceitaGerenciar.getTblReceita());
        UtilTable.redimensionar(telaReceitaGerenciar.getTblReceita(), 0, 170);
        UtilTable.redimensionar(telaReceitaGerenciar.getTblReceita(), 1, 100);
        UtilTable.redimensionar(telaReceitaGerenciar.getTblReceita(), 2, 120);
        UtilTable.redimensionar(telaReceitaGerenciar.getTblReceita(), 3, 200);
    }

    private void carregarFormaPagamentoNaCombo() {
        listFormaPagamentos = formaPagamentoDao.pesquisar();
        DefaultComboBoxModel<FormaPagamento> model = new DefaultComboBoxModel(listFormaPagamentos.toArray());
        telaReceitaGerenciar.getCbFormaPagamento().setModel(model);
    }

    public boolean criarReceita(Entrega entrega, Date dataVencimento, Double valorFrete) {
        receita = new Receita();
        receita.setDataCadastro(new Timestamp(System.currentTimeMillis()));
        receita.setDataPagamento(null);
        receita.setDataVencimento(dataVencimento);
        receita.setFormaPagamento(null);
        receita.setValorRecebido(null);
        receita.setValorTotal(valorFrete);
        receita.setEntrega(entrega);

        if (Validacao.validaEntidade(receita) != null) {
            Mensagem.info(Validacao.validaEntidade(receita));
            receita = null;
            return false;
        }

        int inserido = receitaDao.inserir(receita);
        if (inserido != 0) {
            return true;
        } else {
            Mensagem.erro(Texto.ERRO_CADASTRAR);
            return false;
        }
    }

    public void carregarCamposReceitaAction() {
        indexSelecionada = telaReceitaGerenciar.getTblReceita().getSelectedRow();
        receita = receitaTableModel.pegaObjeto(telaReceitaGerenciar.getTblReceita().getSelectedRow());
        telaReceitaGerenciar.getLblValorTotalReceita().setText(DecimalFormat.decimalFormat(receita.getValorTotal()));
        Double valorRestante = receita.getValorTotal() - receita.getValorRecebido();
        telaReceitaGerenciar.getLblValorReceitaRestante().setText(DecimalFormat.decimalFormat(valorRestante));
        telaReceitaGerenciar.getCbFormaPagamento().getModel().setSelectedItem(receita.getFormaPagamento());
        telaReceitaGerenciar.getTfDataVencimento().setText(UtilDate.data(receita.getDataVencimento()));
        telaReceitaGerenciar.getTpReceita().setEnabledAt(1, true);
        telaReceitaGerenciar.getTpReceita().setSelectedIndex(1);
    }

    public void editarReceitaAction() {
        receita.setFormaPagamento((FormaPagamento) telaReceitaGerenciar.getCbFormaPagamento().getSelectedItem());
        Double valorRecebido = receita.getValorRecebido() + Double.valueOf(DecimalFormat.paraPonto(telaReceitaGerenciar.getTfValorRecebido().getText()));
        receita.setValorRecebido(valorRecebido);
        receita.setDataVencimento(UtilDate.data(telaReceitaGerenciar.getTfDataVencimento().getText()));
        if (telaReceitaGerenciar.getCheckFinalizarReceita().isSelected()) {
            receita.setDataPagamento(new Timestamp(System.currentTimeMillis()));
        }
        boolean alterado = receitaDao.alterar(receita);
        if (!alterado) {
            Mensagem.erro(Texto.ERRO_EDITAR);
            return;
        }
        receitaTableModel.atualizar(indexSelecionada, receita);
        Mensagem.info(Texto.SUCESSO_EDITAR);
        resetarReceitaAction();

    }

    public void pesquisarReceitaAction() {
        List<Receita> receitasPesquisadas = receitaDao.pesquisar(telaReceitaGerenciar.getTfPesquisarReceita().getText());
        if (receitasPesquisadas == null) {
            receitaTableModel.limpar();
            receitasPesquisadas = receitaDao.pesquisar();
            atualizaTotalDeValor(receitasPesquisadas);
        } else {
            receitaTableModel.limpar();
            receitaTableModel.adicionar(receitasPesquisadas);
            atualizaTotalDeValor(receitasPesquisadas);
        }

    }

    public void resetarReceitaAction() {
        receita = null;
        telaReceitaGerenciar.getTpReceita().setEnabledAt(1, false);
        telaReceitaGerenciar.getTpReceita().setSelectedIndex(0);
    }

    public void limparCamposTabReceberPagamento() {

    }

    public void atualizaTotalDeValor(List<Receita> receitas) {
        Double totalValorBanco = 0.0;
        Double totalValorFiltrado = 0.0;
        List<Receita> receitasDoBanco = receitaDao.pesquisar();
        for (Receita receitaDobanco : receitasDoBanco) {
            totalValorBanco += receitaDobanco.getValorTotal();
        }
        for (Receita receita : receitas) {
            totalValorFiltrado += receita.getValorTotal();
        }
        telaReceitaGerenciar.getLblValorTotal().setText(DecimalFormat.decimalFormatR$(totalValorBanco));
        telaReceitaGerenciar.getLblValorTotalFiltrado().setText(DecimalFormat.decimalFormatR$(totalValorFiltrado));
    }

    public void atualizaTotalReceber(List<Receita> receitas) {
        Double totalReceitaBanco = 0.0;
        Double totalReceitaFiltrado = 0.0;
        List<Receita> receitasDoBanco = receitaDao.pesquisar();
        List<Receita> receitasEmAberto = new ArrayList<>();
        List<Receita> receitasEmAbertoFiltradas = new ArrayList<>();

        for (Receita umaReceita : receitasDoBanco) {
            if (umaReceita.getEntrega().equals(false)) {
                receitasEmAberto.add(umaReceita);

            }
        }

        for (Receita umaReceitaFiltrada : receitas) {
            if (umaReceitaFiltrada.getEntrega().equals(false)) {
                receitasEmAbertoFiltradas.add(umaReceitaFiltrada);

            }
        }
        telaReceitaGerenciar.getLblValorReceberTotal().setText(DecimalFormat.decimalFormatR$(totalReceitaBanco));
        telaReceitaGerenciar.getLblValorReceberFiltrado().setText(DecimalFormat.decimalFormatR$(totalReceitaFiltrado));
    }
}
