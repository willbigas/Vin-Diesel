package br.com.vindiesel.control;

import br.com.vindiesel.control.report.TelaDestinatarioGerenciarRelatorioControl;
import br.com.vindiesel.control.report.TelaEncomendaGerenciarRelatorioControl;
import br.com.vindiesel.control.report.TelaEntregaGerenciarRelatorioControl;
import br.com.vindiesel.control.report.TelaReceitaGerenciarRelatorioControl;
import br.com.vindiesel.control.report.TelaRemetenteGerenciarRelatorioControl;
import br.com.vindiesel.control.report.TelaUsuarioGerenciarRelatorioControl;
import br.com.vindiesel.view.TelaPrincipal;
import javax.swing.JFrame;

/**
 *
 * @author William
 */
public class TelaPrincipalControl {

    TelaPrincipal telaPrincipal;
    TelaLoginControl telaLoginControl;

    private TelaUsuarioGerenciarControl telaUsuarioGerenciarControl = null;
    private TelaUsuarioGerenciarRelatorioControl usuarioGerenciarRelatorioControl = null;

    private TelaEncomendaGerenciarControl telaEncomendaGerenciarControl = null;
    private TelaEncomendaGerenciarRelatorioControl encomendaGerenciarRelatorioControl = null;

    private TelaDestinatarioGerenciarControl telaDestinatarioGerenciarControl = null;
    private TelaDestinatarioGerenciarRelatorioControl destinatarioGerenciarRelatorioControl = null;

    private TelaRemetenteGerenciarControl telaRemetenteGerenciarControl = null;
    private TelaRemetenteGerenciarRelatorioControl remetenteGerenciarRelatorioControl = null;

    private TelaTipoUsuarioGerenciarControl telaTipoUsuarioGerenciarControl = null;

    private TelaReceitaGerenciarControl telaReceitaGerenciarControl = null;
    private TelaReceitaGerenciarRelatorioControl receitaGerenciarRelatorioControl = null;

    private TelaEntregaControl telaEntregaControl = null;
    private TelaEntregaGerenciarRelatorioControl entregaGerenciarRelatorioControl = null;

    private TelaSobreControl telaSobreControl = null;

    public TelaPrincipalControl() {
    }

    public void chamarTelaPrincipal() {
        telaPrincipal = new TelaPrincipal(this);
        verificarPermissaoPorTipoUsuario();
        telaPrincipal.setLocationRelativeTo(null);
        telaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
        telaPrincipal.setVisible(true);
    }

    public void chamarTelaGerenciarUsuarioAction() {
        if (telaUsuarioGerenciarControl != null) {
            telaUsuarioGerenciarControl.chamarTelaUsuarioGerenciar();
        } else {
            telaUsuarioGerenciarControl = new TelaUsuarioGerenciarControl();
            telaUsuarioGerenciarControl.chamarTelaUsuarioGerenciar();
        }
    }

    public void chamarTelaEncomendaGerenciarRelatorioAction() {
        if (encomendaGerenciarRelatorioControl != null) {
            encomendaGerenciarRelatorioControl.chamarTelaEncomendaGerenciarRelatorio();
        } else {
            encomendaGerenciarRelatorioControl = new TelaEncomendaGerenciarRelatorioControl();
            encomendaGerenciarRelatorioControl.chamarTelaEncomendaGerenciarRelatorio();
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

    public void chamarTelaUsuarioGerenciarRelatorioAction() {
        if (usuarioGerenciarRelatorioControl != null) {
            usuarioGerenciarRelatorioControl.chamarTelaUsuarioGerenciarRelatorio();
        } else {
            usuarioGerenciarRelatorioControl = new TelaUsuarioGerenciarRelatorioControl();
            usuarioGerenciarRelatorioControl.chamarTelaUsuarioGerenciarRelatorio();
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

    public void chamarTelaDestinatarioGerenciarRelatorioAction() {
        if (destinatarioGerenciarRelatorioControl != null) {
            destinatarioGerenciarRelatorioControl.chamarTelaDestinatarioGerenciarRelatorio();
        } else {
            destinatarioGerenciarRelatorioControl = new TelaDestinatarioGerenciarRelatorioControl();
            destinatarioGerenciarRelatorioControl.chamarTelaDestinatarioGerenciarRelatorio();
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

    public void chamarTelaRemetenteGerenciarRelatorioAction() {
        if (remetenteGerenciarRelatorioControl != null) {
            remetenteGerenciarRelatorioControl.chamarTelaRemetenteGerenciarRelatorio();
        } else {
            remetenteGerenciarRelatorioControl = new TelaRemetenteGerenciarRelatorioControl();
            remetenteGerenciarRelatorioControl.chamarTelaRemetenteGerenciarRelatorio();
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

    public void chamarTelaReceitaGerenciarRelatorioAction() {
        if (receitaGerenciarRelatorioControl != null) {
            receitaGerenciarRelatorioControl.chamarTelaRemetenteGerenciarRelatorio();
        } else {
            receitaGerenciarRelatorioControl = new TelaReceitaGerenciarRelatorioControl();
            receitaGerenciarRelatorioControl.chamarTelaRemetenteGerenciarRelatorio();
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

    public void chamarTelaEntregaGerenciarRelatorioAction() {
        if (entregaGerenciarRelatorioControl != null) {
            entregaGerenciarRelatorioControl.chamarTelaEncomendaGerenciarRelatorio();
        } else {
            entregaGerenciarRelatorioControl = new TelaEntregaGerenciarRelatorioControl();
            entregaGerenciarRelatorioControl.chamarTelaEncomendaGerenciarRelatorio();
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

    public void verificarPermissaoPorTipoUsuario() {
        if (TelaLoginControl.tipoUsuarioLogado == 2) {
            telaPrincipal.getMenuFinanceiro().setVisible(false);
            telaPrincipal.getMenuRelatorio().setVisible(false);
            telaPrincipal.getMenuUsuario().setVisible(false);
        }
    }

}
