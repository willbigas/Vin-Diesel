package br.com.vindiesel.control.report;

import br.com.vindiesel.dao.ReceitaDao;
import br.com.vindiesel.model.Receita;
import br.com.vindiesel.uteis.Relatorio;
import br.com.vindiesel.uteis.UtilDate;
import br.com.vindiesel.view.TelaPrincipal;
import br.com.vindiesel.view.TelaReceitaGerenciarRelatorio;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author William
 */
public class TelaReceitaGerenciarRelatorioControl {

    TelaReceitaGerenciarRelatorio telaReceitaGerenciarRelatorio;
    ReceitaDao receitaDao;
    Receita receita;
    List<Receita> listReceitas;

    private static final int CB_OPCAO_CODIGO_ENTREGA = 0;
    private static final int CB_OPCAO_FORMA_PAGAMENTO = 1;
    private static final int CB_OPCAO_DATA_EFETIVACAO = 2;

    public TelaReceitaGerenciarRelatorioControl() {
        receitaDao = new ReceitaDao();
    }

    public void chamarTelaRemetenteGerenciarRelatorio() {

        if (telaReceitaGerenciarRelatorio == null) {
            telaReceitaGerenciarRelatorio = new TelaReceitaGerenciarRelatorio(this);
            TelaPrincipal.desktopPane.add(telaReceitaGerenciarRelatorio);
            telaReceitaGerenciarRelatorio.setVisible(true);
        } else {
            if (telaReceitaGerenciarRelatorio.isVisible()) {
                telaReceitaGerenciarRelatorio.pack();
            } else {
                TelaPrincipal.desktopPane.add(telaReceitaGerenciarRelatorio);
                telaReceitaGerenciarRelatorio.setVisible(true);
            }
        }
    }

    public void acionarRelatorioAction() {
        if (telaReceitaGerenciarRelatorio.getCbOpcaoPesquisa().getSelectedIndex() == CB_OPCAO_CODIGO_ENTREGA) {
            listReceitas = receitaDao.pesquisarPorCodigoEntrega(telaReceitaGerenciarRelatorio.getTfCampoPesquisa().getText());
        }
        if (telaReceitaGerenciarRelatorio.getCbOpcaoPesquisa().getSelectedIndex() == CB_OPCAO_FORMA_PAGAMENTO) {
            String campoParaPesquisar = telaReceitaGerenciarRelatorio.getTfCampoPesquisa().getText();
            List<Receita> tudoDoBanco = receitaDao.pesquisar();
            listReceitas = new ArrayList<>();
            for (Receita receita : tudoDoBanco) {
                if (receita.getFormaPagamento() == null) {
                    continue;
                }
                if (receita.getFormaPagamento().getNome().toUpperCase().contains(campoParaPesquisar.toUpperCase())) {
                    listReceitas.add(receita);
                }
            }
        }
        if (telaReceitaGerenciarRelatorio.getCbOpcaoPesquisa().getSelectedIndex() == CB_OPCAO_DATA_EFETIVACAO) {
            listReceitas = receitaDao.pesquisarPorDataEfetivacao(UtilDate.deStringParaStringBanco(telaReceitaGerenciarRelatorio.getTfCampoPesquisa().getText()));
        }

        chamarRelatorioDestinatario(listReceitas);

    }

    private void chamarRelatorioDestinatario(List<Receita> receitas) {
        InputStream jasperFile = getClass().getResourceAsStream("/reports/receitasList.jasper");
        List<Object> objects = (List<Object>) (Object) receitas;
        Relatorio.chamarRelatorio(jasperFile, null, objects);
    }

}
