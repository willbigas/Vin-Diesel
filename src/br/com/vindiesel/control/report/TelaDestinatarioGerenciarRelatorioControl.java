package br.com.vindiesel.control.report;

import br.com.vindiesel.dao.DestinatarioDao;
import br.com.vindiesel.model.Destinatario;
import br.com.vindiesel.uteis.Relatorio;
import br.com.vindiesel.view.TelaDestinatarioGerenciarRelatorio;
import br.com.vindiesel.view.TelaPrincipal;
import java.io.InputStream;
import java.util.ArrayList;
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

    private static final int CB_OPCAO_NENHUMA = 0;
    private static final int CB_OPCAO_CODIGO_PESSOA = 1;
    private static final int CB_OPCAO_NOME = 2;
    private static final int CB_OPCAO_CIDADE = 3;

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
        if (telaDestinatarioGerenciarRelatorio.getCbOpcaoPesquisa().getSelectedIndex() == CB_OPCAO_NENHUMA) {
            listDestinatarios = destinatarioDao.pesquisar();
        }
        if (telaDestinatarioGerenciarRelatorio.getCbOpcaoPesquisa().getSelectedIndex() == CB_OPCAO_CODIGO_PESSOA) {
            listDestinatarios = destinatarioDao.pesquisarPorCodigoPessoa(telaDestinatarioGerenciarRelatorio.getTfCampoPesquisa().getText());
        }
        if (telaDestinatarioGerenciarRelatorio.getCbOpcaoPesquisa().getSelectedIndex() == CB_OPCAO_NOME) {
            listDestinatarios = destinatarioDao.pesquisarPorNome(telaDestinatarioGerenciarRelatorio.getTfCampoPesquisa().getText());
        }
        if (telaDestinatarioGerenciarRelatorio.getCbOpcaoPesquisa().getSelectedIndex() == CB_OPCAO_CIDADE) {
            String campoParaPesquisar = telaDestinatarioGerenciarRelatorio.getTfCampoPesquisa().getText();
           List<Destinatario> tudoDoBanco = destinatarioDao.pesquisar();
           listDestinatarios = new ArrayList<>();
            for (Destinatario destinatario : tudoDoBanco) {
                if (destinatario.getEndereco().getCidade().toUpperCase().contains(campoParaPesquisar.toUpperCase())) {
                    listDestinatarios.add(destinatario);
                }
            }
        }
        chamarRelatorioDestinatario(listDestinatarios);

    }

    private void chamarRelatorioDestinatario(List<Destinatario> destinatarios) {
        InputStream jasperFile = getClass().getResourceAsStream("/reports/destinatariosList.jasper");
        List<Object> objects = (List<Object>) (Object) destinatarios;
        Relatorio.chamarRelatorio(jasperFile, null, objects);
    }

}
