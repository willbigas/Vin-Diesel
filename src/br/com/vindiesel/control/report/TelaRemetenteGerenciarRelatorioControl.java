package br.com.vindiesel.control.report;

import br.com.vindiesel.dao.RemetenteDao;
import br.com.vindiesel.model.Remetente;
import br.com.vindiesel.uteis.Relatorio;
import br.com.vindiesel.view.TelaPrincipal;
import br.com.vindiesel.view.TelaRemetenteGerenciarRelatorio;
import java.io.InputStream;
import java.util.ArrayList;
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

    private static final int CB_OPCAO_NENHUMA = 0;
    private static final int CB_OPCAO_CODIGO_PESSOA = 1;
    private static final int CB_OPCAO_NOME = 2;
    private static final int CB_OPCAO_CIDADE = 3;

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
        if (telaRemetenteGerenciarRelatorio.getCbOpcaoPesquisa().getSelectedIndex() == CB_OPCAO_NENHUMA) {
            listRemetentes = remetenteDao.pesquisar();
        }
        if (telaRemetenteGerenciarRelatorio.getCbOpcaoPesquisa().getSelectedIndex() == CB_OPCAO_CODIGO_PESSOA) {
            listRemetentes = remetenteDao.pesquisarPorCodigoPessoa(telaRemetenteGerenciarRelatorio.getTfCampoPesquisa().getText());
        }
        if (telaRemetenteGerenciarRelatorio.getCbOpcaoPesquisa().getSelectedIndex() == CB_OPCAO_NOME) {
            listRemetentes = remetenteDao.pesquisarPorNome(telaRemetenteGerenciarRelatorio.getTfCampoPesquisa().getText());
        }
        if (telaRemetenteGerenciarRelatorio.getCbOpcaoPesquisa().getSelectedIndex() == CB_OPCAO_CIDADE) {
            String campoParaPesquisar = telaRemetenteGerenciarRelatorio.getTfCampoPesquisa().getText();
            List<Remetente> tudoDoBanco = remetenteDao.pesquisar();
            listRemetentes = new ArrayList<>();
            for (Remetente remetente : tudoDoBanco) {
                if (remetente.getEndereco().getCidade().toUpperCase().contains(campoParaPesquisar.toUpperCase())) {
                    listRemetentes.add(remetente);
                }
            }
        }
        chamarRelatorioDestinatario(listRemetentes);

    }

    private void chamarRelatorioDestinatario(List<Remetente> remetentes) {
        InputStream jasperFile = getClass().getResourceAsStream("/reports/remetentesList.jasper");
        List<Object> objects = (List<Object>) (Object) remetentes;
        Relatorio.chamarRelatorio(jasperFile, null, objects);
    }

}
