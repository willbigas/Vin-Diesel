/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mercado.control;

import br.com.mercado.dao.DespesaDao;
import br.com.mercado.dao.TipoDespesaDao;
import br.com.mercado.model.Despesa;
import br.com.mercado.model.TipoDespesa;
import br.com.mercado.uteis.Mensagem;
import br.com.mercado.uteis.Texto;
import br.com.mercado.uteis.Validacao;
import br.com.mercado.view.TelaDespesaGerenciar;
import br.com.mercado.view.TelaPrincipal;
import java.time.LocalDateTime;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author William
 */
public class TelaDespesaGerenciarControl {

    TelaDespesaGerenciar telaDespesaGerenciar;
    TipoDespesa tipoDespesa;
    TipoDespesaDao tipoDespesaDao;
    Despesa despesa;
    DespesaDao despesaDao;

    public TelaDespesaGerenciarControl() {
        tipoDespesaDao = new TipoDespesaDao();
        despesaDao = new DespesaDao();
    }

    public void chamarTelaDespesaGerenciar() {
        if (telaDespesaGerenciar == null) { // se tiver nulo chama janela normalmente
            telaDespesaGerenciar = new TelaDespesaGerenciar(this);
            TelaPrincipal.desktopPane.add(telaDespesaGerenciar);
            telaDespesaGerenciar.setVisible(true);
        } else {//se ele estiver criado
            if (telaDespesaGerenciar.isVisible()) {
                telaDespesaGerenciar.pack();//Redimensiona ao Quadro Original
            } else {
                TelaPrincipal.desktopPane.add(telaDespesaGerenciar);
                telaDespesaGerenciar.setVisible(true);
            }
        }
    }

    public void criarDespesa(Integer codEntrada, Date dataVencimento, Double valorRestante) {
        despesa = new Despesa();
        despesa.setDataCadastro(LocalDateTime.now());
        despesa.setDataPagamento(null);
        despesa.setDataVencimento(dataVencimento);
        despesa.setValorPago(null);
        despesa.setPago(false);
        despesa.setValorPagoRestante(valorRestante);

        if (codEntrada != null) {
            despesa.setTipoDespesa(tipoDespesaDao.pesquisar(1)); // retorna o tipo Fornecedor
            despesa.setCodEntrada(codEntrada);
        }

        if (codEntrada == null) {
            despesa.setTipoDespesa(tipoDespesaDao.pesquisar(2)); // retorna o tipo Funcionario
        }

        if (Validacao.validaEntidade(despesa) != null) {
            Mensagem.info(Validacao.validaEntidade(despesa));
            despesa = null;
            return;
        }
        
        int inserido = despesaDao.inserir(despesa);
        if (inserido != 0) {
            Mensagem.info(Texto.SUCESSO_CADASTRAR);
        } else {
            Mensagem.erro(Texto.ERRO_CADASTRAR);
        }
    }

}
