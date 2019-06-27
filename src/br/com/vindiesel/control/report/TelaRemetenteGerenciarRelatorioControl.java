package br.com.vindiesel.control.report;

import br.com.vindiesel.dao.DestinatarioDao;
import br.com.vindiesel.dao.RemetenteDao;
import br.com.vindiesel.model.Destinatario;
import br.com.vindiesel.model.Remetente;
import br.com.vindiesel.uteis.Relatorio;
import br.com.vindiesel.view.TelaDestinatarioGerenciarRelatorio;
import br.com.vindiesel.view.TelaPrincipal;
import br.com.vindiesel.view.TelaRemetenteGerenciarRelatorio;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author William
 */
public class TelaRemetenteGerenciarRelatorioControl {

    TelaRemetenteGerenciarRelatorio telaRemetenteGerenciarRelatorio;
    RemetenteDao remetenteDao;
    Remetente remetente;
    List<Remetente> listRemetentes;

    public TelaRemetenteGerenciarRelatorioControl() {
        remetenteDao = new RemetenteDao();
    }

    public void chamarTelaRemetenteGerenciarRelatorio() {

        if (telaRemetenteGerenciarRelatorio == null) {
            telaRemetenteGerenciarRelatorio = new TelaRemetenteGerenciarRelatorio(this);
            TelaPrincipal.desktopPane.add(telaRemetenteGerenciarRelatorio);
            telaRemetenteGerenciarRelatorio.setVisible(true);
        } else {
            if (telaRemetenteGerenciarRelatorio.isVisible()) {
                telaRemetenteGerenciarRelatorio.pack();
            } else {
                TelaPrincipal.desktopPane.add(telaRemetenteGerenciarRelatorio);
                telaRemetenteGerenciarRelatorio.setVisible(true);
            }
        }
    }

    public void acionarRelatorioAction() {
        listRemetentes = remetenteDao.pesquisar("Ltda");
        chamarRelatorioDestinatario(listRemetentes);

    }

    private void chamarRelatorioDestinatario(List<Remetente> remetentes) {
        InputStream jasperFile = getClass().getResourceAsStream("/reports/remetentesList.jasper");
        List<Object> objects = (List<Object>) (Object) remetentes;
        Relatorio.chamarRelatorio(jasperFile, null, objects);
    }

}
