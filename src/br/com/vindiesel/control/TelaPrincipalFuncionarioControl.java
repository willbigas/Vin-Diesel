package br.com.vindiesel.control;

import br.com.vindiesel.view.TelaPrincipalFuncionario;
import javax.swing.JFrame;

/**
 *
 * @author William
 */
public class TelaPrincipalFuncionarioControl {

    TelaPrincipalFuncionario telaPrincipalCaixa;

    private TelaVendaControl telaVendaControl = null;
    private TelaSobreControl telaSobreControl = null;

    public TelaPrincipalFuncionarioControl() {
    }

    public void chamarTelaPrincipalCaixa() {
        telaPrincipalCaixa = new TelaPrincipalFuncionario();
        telaPrincipalCaixa.setLocationRelativeTo(null);
        telaPrincipalCaixa.setExtendedState(JFrame.MAXIMIZED_BOTH);
        telaPrincipalCaixa.setVisible(true);
    }

    public void chamarTelaVendaAction() {
        if (telaVendaControl != null) {
            telaVendaControl.chamarTelaVenda();
        } else {
            telaVendaControl = new TelaVendaControl();
            telaVendaControl.chamarTelaVenda();
        }
    }
    
    public void chamarTelaSobreAction() {
        if (telaSobreControl != null) {
            telaSobreControl.chamarTelaSobre();
        } else {
            telaSobreControl = new TelaSobreControl();
            telaSobreControl.chamarTelaSobre();
        }
    }

}
