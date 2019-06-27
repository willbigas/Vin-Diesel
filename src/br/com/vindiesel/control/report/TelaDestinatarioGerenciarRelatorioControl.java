package br.com.vindiesel.control.report;

import br.com.vindiesel.dao.DestinatarioDao;
import br.com.vindiesel.model.Destinatario;
import br.com.vindiesel.uteis.Relatorio;
import br.com.vindiesel.view.TelaDestinatarioGerenciarRelatorio;
import br.com.vindiesel.view.TelaPrincipal;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author William
 */
public class TelaDestinatarioGerenciarRelatorioControl {

    TelaDestinatarioGerenciarRelatorio telaDestinatarioGerenciarRelatorio;
    DestinatarioDao destinatarioDao;
    Destinatario destinatario;
    List<Destinatario> listDestinatarios;

    public TelaDestinatarioGerenciarRelatorioControl() {
        destinatarioDao = new DestinatarioDao();
    }

    public void chamarTelaDestinatarioGerenciarRelatorio() {

        if (telaDestinatarioGerenciarRelatorio == null) {
            telaDestinatarioGerenciarRelatorio = new TelaDestinatarioGerenciarRelatorio(this);
            TelaPrincipal.desktopPane.add(telaDestinatarioGerenciarRelatorio);
            telaDestinatarioGerenciarRelatorio.setVisible(true);
        } else {
            if (telaDestinatarioGerenciarRelatorio.isVisible()) {
                telaDestinatarioGerenciarRelatorio.pack();
            } else {
                TelaPrincipal.desktopPane.add(telaDestinatarioGerenciarRelatorio);
                telaDestinatarioGerenciarRelatorio.setVisible(true);
            }
        }
    }

    public void acionarRelatorioAction() {
        listDestinatarios = destinatarioDao.pesquisar("cec");
        chamarRelatorioDestinatario(listDestinatarios);

    }

    private void chamarRelatorioDestinatario(List<Destinatario> destinatarios) {
        InputStream jasperFile = getClass().getResourceAsStream("/reports/destinatariosList.jasper");
        List<Object> objects = (List<Object>) (Object) destinatarios;
        Relatorio.chamarRelatorio(jasperFile, null, objects);
    }

}
