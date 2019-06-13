/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mercado.control;

import br.com.mercado.view.TelaConfiguracaoGerenciar;
import br.com.mercado.view.TelaPrincipal;

/**
 *
 * @author William
 */
public class TelaConfiguracaoGerenciarControl {
    
    TelaConfiguracaoGerenciar telaConfiguracaoGerenciar;

    public TelaConfiguracaoGerenciarControl() {
    }
    
    public void chamarTelaConfiguracaoGerenciar() {
        if (telaConfiguracaoGerenciar == null) {
            telaConfiguracaoGerenciar = new TelaConfiguracaoGerenciar(this);
            TelaPrincipal.desktopPane.add(telaConfiguracaoGerenciar);
            telaConfiguracaoGerenciar.setVisible(true);
        } else {
            if (telaConfiguracaoGerenciar.isVisible()) {
                telaConfiguracaoGerenciar.pack();
            } else {
                TelaPrincipal.desktopPane.add(telaConfiguracaoGerenciar);
                telaConfiguracaoGerenciar.setVisible(true);
            }
        }
    }
    
    
}
