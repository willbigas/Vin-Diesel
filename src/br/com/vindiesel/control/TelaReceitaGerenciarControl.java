/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.control;

import br.com.vindiesel.dao.ReceitaDao;
import br.com.vindiesel.model.Entrega;
import br.com.vindiesel.model.Receita;
import br.com.vindiesel.uteis.Mensagem;
import br.com.vindiesel.uteis.Texto;
import br.com.vindiesel.uteis.Validacao;
import br.com.vindiesel.view.TelaPrincipal;
import br.com.vindiesel.view.TelaReceitaGerenciar;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author William
 */
public class TelaReceitaGerenciarControl {

    TelaReceitaGerenciar telaReceitaGerenciar;
    ReceitaDao receitaDao;
    Receita receita;

    public TelaReceitaGerenciarControl() {
        receitaDao = new ReceitaDao();
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
    }

    public void criarReceita(Entrega entrega, Date dataVencimento, Double valorTotal) {
        receita = new Receita();
        receita.setDataCadastro(LocalDateTime.now());
        receita.setDataPagamento(null);
        receita.setDataVencimento(dataVencimento);
        receita.setValorRecebido(null);
        receita.setValorTotal(valorTotal);
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
    }
}
