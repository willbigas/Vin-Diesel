package br.com.vindiesel.control;

import br.com.vindiesel.view.TelaPrincipal;
import javax.swing.JFrame;

/**
 *
 * @author William
 */
public class TelaPrincipalControl {

    TelaPrincipal telaPrincipal;

    private TelaUsuarioGerenciarControl telaUsuarioGerenciarControl = null;
    private TelaEncomendaGerenciarControl telaProdutoGerenciarControl = null;
    private TelaDestinatarioGerenciarControl telaClienteGerenciarControl = null;
    private TelaRemetenteGerenciarControl telaFornecedorGerenciarControl = null;
    private TelaTipoUsuarioGerenciarControl telaTipoUsuarioGerenciarControl = null;
    private TelaReceitaGerenciarControl telaReceitaGerenciarControl = null;
    private TelaEntregaControl telaVendaControl = null;
    private TelaSobreControl telaSobreControl = null;
    private TelaConfiguracaoGerenciarControl telaConfiguracaoGerenciarControl = null;

    public TelaPrincipalControl() {
    }

    public void chamarTelaPrincipal() {
        telaPrincipal = new TelaPrincipal();
        telaPrincipal.setLocationRelativeTo(null);
        telaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
        telaPrincipal.setVisible(true);
    }

    public void chamarTelaGerenciarFuncionarioAction() {
        if (telaUsuarioGerenciarControl != null) {
            telaUsuarioGerenciarControl.chamarTelaFuncionarioGerenciar();
        } else {
            telaUsuarioGerenciarControl = new TelaUsuarioGerenciarControl();
            telaUsuarioGerenciarControl.chamarTelaFuncionarioGerenciar();
        }
    }

    public void chamarTelaProdutoGerenciarAction() {
        if (telaProdutoGerenciarControl != null) {
            telaProdutoGerenciarControl.chamarTelaProdutoGerenciar();
        } else {
            telaProdutoGerenciarControl = new TelaEncomendaGerenciarControl();
            telaProdutoGerenciarControl.chamarTelaProdutoGerenciar();
        }
    }


    public void chamarTelaClienteGerenciarAction() {
        if (telaClienteGerenciarControl != null) {
            telaClienteGerenciarControl.chamarTelaClienteGerenciar();
        } else {
            telaClienteGerenciarControl = new TelaDestinatarioGerenciarControl();
            telaClienteGerenciarControl.chamarTelaClienteGerenciar();
        }
    }

    public void chamarTelaFornecedorGerenciarAction() {
        if (telaFornecedorGerenciarControl != null) {
            telaFornecedorGerenciarControl.chamarTelaFornecedorGerenciar();
        } else {
            telaFornecedorGerenciarControl = new TelaRemetenteGerenciarControl();
            telaFornecedorGerenciarControl.chamarTelaFornecedorGerenciar();
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

    public void chamarTelaVendaAction() {
        if (telaVendaControl != null) {
            telaVendaControl.chamarTelaVenda();
        } else {
            telaVendaControl = new TelaEntregaControl();
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
    
     public void chamarTelaConfiguracaoGerenciarAction() {
        if (telaConfiguracaoGerenciarControl != null) {
            telaConfiguracaoGerenciarControl.chamarTelaConfiguracaoGerenciar();
        } else {
            telaConfiguracaoGerenciarControl = new TelaConfiguracaoGerenciarControl();
            telaConfiguracaoGerenciarControl.chamarTelaConfiguracaoGerenciar();
        }
    }

}
