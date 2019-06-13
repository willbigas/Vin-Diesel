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
    private TelaProdutoGerenciarControl telaProdutoGerenciarControl = null;
    private TelaEntradaGerenciarControl telaEntradaGerenciarControl = null;
    private TelaCategoriaGerenciarControl telaCategoriaGerenciarControl = null;
    private TelaClienteGerenciarControl telaClienteGerenciarControl = null;
    private TelaFornecedorGerenciarControl telaFornecedorGerenciarControl = null;
    private TelaTipoUsuarioGerenciarControl telaTipoUsuarioGerenciarControl = null;
    private TelaReceitaGerenciarControl telaReceitaGerenciarControl = null;
    private TelaDespesaGerenciarControl telaDespesaGerenciarControl = null;
    private TelaCategoriaRelatorioControl telaCategoriaRelatorioControl = null;
    private TelaVendaControl telaVendaControl = null;
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
            telaProdutoGerenciarControl = new TelaProdutoGerenciarControl();
            telaProdutoGerenciarControl.chamarTelaProdutoGerenciar();
        }
    }

    public void chamarTelaEntradaProdutoGerenciarAction() {
        if (telaEntradaGerenciarControl != null) {
            telaEntradaGerenciarControl.chamarTelaEntradaGerenciar();
        } else {
            telaEntradaGerenciarControl = new TelaEntradaGerenciarControl();
            telaEntradaGerenciarControl.chamarTelaEntradaGerenciar();
        }

    }

    public void chamarTelaCategoriaGerenciarAction() {
        if (telaCategoriaGerenciarControl != null) {
            telaCategoriaGerenciarControl.chamarTelaCategoriaGerenciar();
        } else {
            telaCategoriaGerenciarControl = new TelaCategoriaGerenciarControl();
            telaCategoriaGerenciarControl.chamarTelaCategoriaGerenciar();
        }

    }

    public void chamarTelaClienteGerenciarAction() {
        if (telaClienteGerenciarControl != null) {
            telaClienteGerenciarControl.chamarTelaClienteGerenciar();
        } else {
            telaClienteGerenciarControl = new TelaClienteGerenciarControl();
            telaClienteGerenciarControl.chamarTelaClienteGerenciar();
        }
    }

    public void chamarTelaFornecedorGerenciarAction() {
        if (telaFornecedorGerenciarControl != null) {
            telaFornecedorGerenciarControl.chamarTelaFornecedorGerenciar();
        } else {
            telaFornecedorGerenciarControl = new TelaFornecedorGerenciarControl();
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
    public void chamarTelaDespesaGerenciarAction() {
        if (telaDespesaGerenciarControl != null) {
            telaDespesaGerenciarControl.chamarTelaDespesaGerenciar();
        } else {
            telaDespesaGerenciarControl = new TelaDespesaGerenciarControl();
            telaDespesaGerenciarControl.chamarTelaDespesaGerenciar();
        }
    }
    public void chamarTelaCategoriaRelatorioAction() {
        if (telaCategoriaRelatorioControl != null) {
            telaCategoriaRelatorioControl.chamarTelaCategoriaRelatorio();
        } else {
            telaCategoriaRelatorioControl = new TelaCategoriaRelatorioControl();
            telaCategoriaRelatorioControl.chamarTelaCategoriaRelatorio();
        }
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
    
     public void chamarTelaConfiguracaoGerenciarAction() {
        if (telaConfiguracaoGerenciarControl != null) {
            telaConfiguracaoGerenciarControl.chamarTelaConfiguracaoGerenciar();
        } else {
            telaConfiguracaoGerenciarControl = new TelaConfiguracaoGerenciarControl();
            telaConfiguracaoGerenciarControl.chamarTelaConfiguracaoGerenciar();
        }
    }

}
