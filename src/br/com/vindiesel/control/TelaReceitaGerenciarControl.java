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
import br.com.vindiesel.uteis.UtilDecimalFormat;
import br.com.vindiesel.uteis.Validacao;
import br.com.vindiesel.view.TelaPrincipal;
import br.com.vindiesel.view.TelaReceitaGerenciar;
import java.time.LocalDateTime;
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
        receitaTableModel.adicionar(receitaDao.pesquisar());
        carregarFormaPagamentoNaCombo();
        telaReceitaGerenciar.getTpReceita().setEnabledAt(1, false); // disabilita o tabbed pane
    }

    private void carregarFormaPagamentoNaCombo() {
        listFormaPagamentos = formaPagamentoDao.pesquisar();
        DefaultComboBoxModel<FormaPagamento> model = new DefaultComboBoxModel(listFormaPagamentos.toArray());
        telaReceitaGerenciar.getCbFormaPagamento().setModel(model);
    }

    public void criarReceita(Entrega entrega, Date dataVencimento, Double valorFrete) {
        receita = new Receita();
        receita.setDataCadastro(LocalDateTime.now());
        receita.setDataPagamento(null);
        receita.setDataVencimento(dataVencimento);
        receita.setFormaPagamento(null);
        receita.setValorRecebido(null);
        receita.setValorTotal(valorFrete);
        receita.setEntrega(entrega);

        if (Validacao.validaEntidade(receita) != null) {
            Mensagem.info(Validacao.validaEntidade(receita));
            receita = null;
            return;
        }

        int inserido = receitaDao.inserir(receita);
        if (inserido != 0) {
            Mensagem.info(Texto.SUCESSO_CADASTRAR);
        } else {
            Mensagem.erro(Texto.ERRO_CADASTRAR);
        }
        receita = null;
    }

    public void carregarCamposReceitaAction() {
        indexSelecionada = telaReceitaGerenciar.getTblReceita().getSelectedRow();
        receita = receitaTableModel.pegaObjeto(telaReceitaGerenciar.getTblReceita().getSelectedRow());
        telaReceitaGerenciar.getLblValorTotalReceita().setText(UtilDecimalFormat.decimalFormat(receita.getValorTotal()));
        Double valorRestante = receita.getValorTotal() - receita.getValorRecebido();
        telaReceitaGerenciar.getLblValorReceitaRestante().setText(UtilDecimalFormat.decimalFormat(valorRestante));
        telaReceitaGerenciar.getCbFormaPagamento().getModel().setSelectedItem(receita.getFormaPagamento());
        telaReceitaGerenciar.getTfDataVencimento().setText(UtilDate.data(receita.getDataVencimento()));
        telaReceitaGerenciar.getTpReceita().setEnabledAt(1, true);
        telaReceitaGerenciar.getTpReceita().setSelectedIndex(1);
    }

    public void editarReceitaAction() {
        receita.setFormaPagamento((FormaPagamento) telaReceitaGerenciar.getCbFormaPagamento().getSelectedItem());
        Double valorRecebido = receita.getValorRecebido() + Double.valueOf(telaReceitaGerenciar.getTfValorRecebido().getText());
        receita.setValorRecebido(valorRecebido);
        receita.setDataVencimento(UtilDate.data(telaReceitaGerenciar.getTfDataVencimento().getText()));
        if (telaReceitaGerenciar.getCheckFinalizarReceita().isSelected()) {
            receita.setDataPagamento(LocalDateTime.now());
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

    public void resetarReceitaAction() {
        receita = null;
        telaReceitaGerenciar.getTpReceita().setEnabledAt(1, false);
        telaReceitaGerenciar.getTpReceita().setSelectedIndex(0);
    }
    
    public void limparCamposTabReceberPagamento() {
        
    }
}
