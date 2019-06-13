/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mercado.control;

import br.com.mercado.view.TelaSobre;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author Will
 */
public class TelaSobreControl {

    TelaSobre telaSobre;

    public TelaSobreControl() {
    }

    public void chamarTelaSobre() {
        telaSobre = new TelaSobre(this);
        telaSobre.setLocationRelativeTo(null);
        telaSobre.setVisible(true);
    }

    public void abrirLink(String endereco) {
        try {
            Desktop desktop = null;
            //Primeiro verificamos se é possível a integração com o desktop
            if (!Desktop.isDesktopSupported()) {
                throw new IllegalStateException("Erro ao acessar sua area de Trabalho , Contate o administrador do sistema.");
            }
            desktop = Desktop.getDesktop();
            //Agora vemos se é possível disparar o browser default.
            if (!desktop.isSupported(Desktop.Action.BROWSE)) {
                throw new IllegalStateException("Navegador Padrão não encontrado!");
            }
            URI uri = new URI(endereco);
            desktop.browse(uri);
            //Dispara o browser default, que pode ser o Explorer, Firefox ou outro.
        } catch (IllegalStateException illegalStateException) {
        } catch (URISyntaxException uRISyntaxException) {
        } catch (IOException iOException) {
        }
    }

}
