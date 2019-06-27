package br.com.vindiesel.control;

import br.com.vindiesel.report.DestinatarioGerenciarRelatorioControl;
import br.com.vindiesel.report.EncomendaGerenciarRelatorioControl;
import br.com.vindiesel.report.EntregaGerenciarRelatorioControl;
import br.com.vindiesel.report.RemetenteGerenciarRelatorioControl;
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

    private TelaEncomendaGerenciarControl telaEncomendaGerenciarControl = null;
    private EncomendaGerenciarRelatorioControl encomendaGerenciarRelatorioControl = null;

    private TelaDestinatarioGerenciarControl telaDestinatarioGerenciarControl = null;
    private DestinatarioGerenciarRelatorioControl destinatarioGerenciarRelatorioControl = null;

    private TelaRemetenteGerenciarControl telaRemetenteGerenciarControl = null;
    private RemetenteGerenciarRelatorioControl remetenteGerenciarRelatorioControl = null;

    private TelaTipoUsuarioGerenciarControl telaTipoUsuarioGerenciarControl = null;
    private TelaReceitaGerenciarControl telaReceitaGerenciarControl = null;

    private TelaEntregaControl telaEntregaControl = null;
    private EntregaGerenciarRelatorioControl entregaGerenciarRelatorioControl = null;

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

    public void chamarTelaEncomendaGerenciarRelatorioAction() {
        if (encomendaGerenciarRelatorioControl != null) {
            encomendaGerenciarRelatorioControl.chamarTelaEncomendaGerenciarRelatorio();
        } else {
            encomendaGerenciarRelatorioControl = new EncomendaGerenciarRelatorioControl();
            encomendaGerenciarRelatorioControl.chamarTelaEncomendaGerenciarRelatorio();
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
            destinatarioGerenciarRelatorioControl = new DestinatarioGerenciarRelatorioControl();
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
            remetenteGerenciarRelatorioControl = new RemetenteGerenciarRelatorioControl();
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
            entregaGerenciarRelatorioControl = new EntregaGerenciarRelatorioControl();
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
