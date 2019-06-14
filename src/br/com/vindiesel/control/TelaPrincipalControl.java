package br.com.vindiesel.control;

import br.com.vindiesel.view.TelaPrincipal;
import javax.swing.JFrame;

/**
 *
 * @author William
 */
public class TelaPrincipalControl extends TelaLoginControl {

    TelaPrincipal telaPrincipal;
    TelaLoginControl telaLoginControl;

    private TelaUsuarioGerenciarControl telaUsuarioGerenciarControl = null;
    private TelaEncomendaGerenciarControl telaEncomendaGerenciarControl = null;
    private TelaDestinatarioGerenciarControl telaDestinatarioGerenciarControl = null;
    private TelaRemetenteGerenciarControl telaRemetenteGerenciarControl = null;
    private TelaTipoUsuarioGerenciarControl telaTipoUsuarioGerenciarControl = null;
    private TelaReceitaGerenciarControl telaReceitaGerenciarControl = null;
    private TelaEntregaControl telaEntregaControl = null;
    private TelaSobreControl telaSobreControl = null;
    private TelaConfiguracaoGerenciarControl telaConfiguracaoGerenciarControl = null;

    public TelaPrincipalControl() {
    }

    public void chamarTelaPrincipal() {
        telaPrincipal = new TelaPrincipal(this);
        verificarAcesso();
        telaPrincipal.setLocationRelativeTo(null);
        telaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
        telaPrincipal.setVisible(true);
    }

    public void chamarTelaGerenciarFuncionarioAction() {
        if (telaUsuarioGerenciarControl != null) {
            telaUsuarioGerenciarControl.chamarTelaUsuarioGerenciar();
        } else {
            telaUsuarioGerenciarControl = new TelaUsuarioGerenciarControl();
            telaUsuarioGerenciarControl.chamarTelaUsuarioGerenciar();
        }
    }

    public void chamarTelaEncomendaAction() {
        if (telaEncomendaGerenciarControl != null) {
            telaEncomendaGerenciarControl.chamarTelaEncomendaGerenciar();
        } else {
            telaEncomendaGerenciarControl = new TelaEncomendaGerenciarControl();
            telaEncomendaGerenciarControl.chamarTelaEncomendaGerenciar();
        }
    }


    public void chamarTelaDestinatarioGerenciarAction() {
        if (telaDestinatarioGerenciarControl != null) {
            telaDestinatarioGerenciarControl.chamarTelaDestinatarioGerenciar();
        } else {
            telaDestinatarioGerenciarControl = new TelaDestinatarioGerenciarControl();
            telaDestinatarioGerenciarControl.chamarTelaDestinatarioGerenciar();
        }
    }

    public void chamarTelaRemetenteGerenciarAction() {
        if (telaRemetenteGerenciarControl != null) {
            telaRemetenteGerenciarControl.chamarTelaRemetenteGerenciar();
        } else {
            telaRemetenteGerenciarControl = new TelaRemetenteGerenciarControl();
            telaRemetenteGerenciarControl.chamarTelaRemetenteGerenciar();
        }
    }

    public void chamarTelaTipoUsuarioGerenciarAction() {
        if (telaTipoUsuarioGerenciarControl != null) {
            telaTipoUsuarioGerenciarControl.chamarTelaTipoUsuarioGerenciar();
        } else {
            telaTipoUsuarioGerenciarControl = new TelaTipoUsuarioGerenciarControl();
            telaTipoUsuarioGerenciarControl.chamarTelaTipoUsuarioGerenciar();
        }
    }
    public void chamarTelaReceitaGerenciarAction() {
        if (telaReceitaGerenciarControl != null) {
            telaReceitaGerenciarControl.chamarTelaReceitaGerenciar();
        } else {
            telaReceitaGerenciarControl = new TelaReceitaGerenciarControl();
            telaReceitaGerenciarControl.chamarTelaReceitaGerenciar();
        }
    }

    public void chamarTelaEntregaAction() {
        if (telaEntregaControl != null) {
            telaEntregaControl.chamarTelaEntrega();
        } else {
            telaEntregaControl = new TelaEntregaControl();
            telaEntregaControl.chamarTelaEntrega();
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
    
     public void chamarTelaConfiguracaoGerenciarAction() {
        if (telaConfiguracaoGerenciarControl != null) {
            telaConfiguracaoGerenciarControl.chamarTelaConfiguracaoGerenciar();
        } else {
            telaConfiguracaoGerenciarControl = new TelaConfiguracaoGerenciarControl();
            telaConfiguracaoGerenciarControl.chamarTelaConfiguracaoGerenciar();
        }
    }
     
    public void verificarAcesso() {
        if (tipoUsuarioLogado == 2) {
            telaPrincipal.getMenuFinanceiro().setVisible(false);
        }
    } 

}
