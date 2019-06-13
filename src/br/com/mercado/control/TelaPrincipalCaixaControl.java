package br.com.mercado.control;

import br.com.mercado.view.TelaPrincipalCaixa;
import javax.swing.JFrame;

/**
 *
 * @author William
 */
public class TelaPrincipalCaixaControl {

    TelaPrincipalCaixa telaPrincipalCaixa;

    private TelaVendaControl telaVendaControl = null;
    private TelaSobreControl telaSobreControl = null;

    public TelaPrincipalCaixaControl() {
    }

    public void chamarTelaPrincipalCaixa() {
        telaPrincipalCaixa = new TelaPrincipalCaixa();
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
