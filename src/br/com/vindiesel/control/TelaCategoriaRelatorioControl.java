package br.com.vindiesel.control;

import br.com.vindiesel.dao.CategoriaDao;
import br.com.vindiesel.model.Categoria;
import br.com.vindiesel.uteis.Relatorio;
import br.com.vindiesel.view.TelaCategoriaRelatorio;
import br.com.vindiesel.view.TelaPrincipal;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author William
 */
public class TelaCategoriaRelatorioControl {

    TelaCategoriaRelatorio telaCategoriaRelatorio;
    CategoriaDao categoriaDao;
    List<Categoria> listCategorias;

    public TelaCategoriaRelatorioControl() {
        categoriaDao = new CategoriaDao();

    }

    public void chamarTelaCategoriaRelatorio() {
        if (telaCategoriaRelatorio == null) {
            telaCategoriaRelatorio = new TelaCategoriaRelatorio(this);
            TelaPrincipal.desktopPane.add(telaCategoriaRelatorio);
            telaCategoriaRelatorio.setVisible(true);
        } else {
            if (telaCategoriaRelatorio.isVisible()) {
                telaCategoriaRelatorio.pack();
            } else {
                TelaPrincipal.desktopPane.add(telaCategoriaRelatorio);
                telaCategoriaRelatorio.setVisible(true);
            }
        }
        carregarCategoriasNaCombo();
        formandoButtonGroupAtivo();
    }

    private void carregarCategoriasNaCombo() {
        listCategorias = categoriaDao.pesquisar();
        DefaultComboBoxModel<Categoria> model = new DefaultComboBoxModel(listCategorias.toArray());
        TelaCategoriaRelatorio.cbCategoria.setModel(model);
    }

    public void formandoButtonGroupAtivo() {
        telaCategoriaRelatorio.getButtonGroupAtivo().add(telaCategoriaRelatorio.getRadioTrue());
        telaCategoriaRelatorio.getButtonGroupAtivo().add(telaCategoriaRelatorio.getRadioFalse());
    }

    public HashMap recebendoParametrosDaTela() {
        HashMap parametros = new HashMap<>();
        if (telaCategoriaRelatorio.getRadioTrue().isSelected()) {
            parametros.put("ativo", true);
        } else {
            parametros.put("ativo", false);
        }
        return parametros;
    }

    public void chamarRelatorioAction() {
        InputStream jasperFile = getClass().getResourceAsStream("/reports/categorias.jasper");
        HashMap parametros = recebendoParametrosDaTela();
        Relatorio.chamarRelatorio(jasperFile, parametros);
    }

}
