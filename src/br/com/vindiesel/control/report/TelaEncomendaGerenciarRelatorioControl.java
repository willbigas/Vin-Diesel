package br.com.vindiesel.control.report;

import br.com.vindiesel.dao.EncomendaDao;
import br.com.vindiesel.model.Encomenda;
import br.com.vindiesel.uteis.Relatorio;
import br.com.vindiesel.view.TelaEncomendaGerenciarRelatorio;
import br.com.vindiesel.view.TelaPrincipal;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author William
 */
public class TelaEncomendaGerenciarRelatorioControl {

    TelaEncomendaGerenciarRelatorio telaEncomendaGerenciarRelatorio;
    EncomendaDao encomendaDao;
    Encomenda encomenda;
    List<Encomenda> listEncomendas;

    public TelaEncomendaGerenciarRelatorioControl() {
        encomendaDao = new EncomendaDao();
    }

    public void chamarTelaEncomendaGerenciarRelatorio() {

        if (telaEncomendaGerenciarRelatorio == null) {
            telaEncomendaGerenciarRelatorio = new TelaEncomendaGerenciarRelatorio(this);
            TelaPrincipal.desktopPane.add(telaEncomendaGerenciarRelatorio);
            telaEncomendaGerenciarRelatorio.setVisible(true);
        } else {
            if (telaEncomendaGerenciarRelatorio.isVisible()) {
                telaEncomendaGerenciarRelatorio.pack();
            } else {
                TelaPrincipal.desktopPane.add(telaEncomendaGerenciarRelatorio);
                telaEncomendaGerenciarRelatorio.setVisible(true);
            }
        }
    }

    public void acionarRelatorioAction() {
        listEncomendas = encomendaDao.pesquisar(telaEncomendaGerenciarRelatorio.getTfCodigoRastreio().getText());
        chamarRelatorioEncomendas(listEncomendas);

    }

    private void chamarRelatorioEncomendas(List<Encomenda> encomendas) {
        InputStream jasperFile = getClass().getResourceAsStream("/reports/encomendasList.jasper");
        List<Object> objects = (List<Object>) (Object) encomendas;
        Relatorio.chamarRelatorio(jasperFile, null, objects);
    }

}
