package br.com.vindiesel.control.report;

import br.com.vindiesel.dao.ReceitaDao;
import br.com.vindiesel.dao.RemetenteDao;
import br.com.vindiesel.model.Receita;
import br.com.vindiesel.model.Remetente;
import br.com.vindiesel.uteis.Relatorio;
import br.com.vindiesel.view.TelaPrincipal;
import br.com.vindiesel.view.TelaReceitaGerenciarRelatorio;
import br.com.vindiesel.view.TelaRemetenteGerenciarRelatorio;
import java.io.InputStream;
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
        listReceitas = receitaDao.pesquisar("");
        chamarRelatorioDestinatario(listReceitas);

    }

    private void chamarRelatorioDestinatario(List<Receita> receitas) {
        InputStream jasperFile = getClass().getResourceAsStream("/reports/receitasList.jasper");
        List<Object> objects = (List<Object>) (Object) receitas;
        Relatorio.chamarRelatorio(jasperFile, null, objects);
    }

}
