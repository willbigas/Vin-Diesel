package br.com.vindiesel.report;

import br.com.vindiesel.dao.EntregaDao;
import br.com.vindiesel.model.Encomenda;
import br.com.vindiesel.model.Entrega;
import br.com.vindiesel.uteis.Relatorio;
import br.com.vindiesel.view.TelaEntregaGerenciarRelatorio;
import br.com.vindiesel.view.TelaPrincipal;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author William
 */
public class EntregaGerenciarRelatorioControl {

    TelaEntregaGerenciarRelatorio telaEntregaGerenciarRelatorio;
    EntregaDao entregaDao;
    Entrega entrega;
    List<Entrega> listEntregas;

    public EntregaGerenciarRelatorioControl() {
        entregaDao = new EntregaDao();
    }

    public void chamarTelaEncomendaGerenciarRelatorio() {

        if (telaEntregaGerenciarRelatorio == null) {
            telaEntregaGerenciarRelatorio = new TelaEntregaGerenciarRelatorio(this);
            TelaPrincipal.desktopPane.add(telaEntregaGerenciarRelatorio);
            telaEntregaGerenciarRelatorio.setVisible(true);
        } else {
            if (telaEntregaGerenciarRelatorio.isVisible()) {
                telaEntregaGerenciarRelatorio.pack();
            } else {
                TelaPrincipal.desktopPane.add(telaEntregaGerenciarRelatorio);
                telaEntregaGerenciarRelatorio.setVisible(true);
            }
        }
    }

    public void acionarRelatorioAction() {
        listEntregas = entregaDao.pesquisar("");
        chamarRelatorioEncomendas(listEntregas);

    }

    private void chamarRelatorioEncomendas(List<Entrega> entregas) {
        InputStream jasperFile = getClass().getResourceAsStream("/reports/entregasList.jasper");
        List<Object> objects = (List<Object>) (Object) entregas;
        Relatorio.chamarRelatorio(jasperFile, null, objects);
    }

}
