/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.uteis;

import br.com.vindiesel.view.TelaPrincipal;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Will
 */
public class InterfaceJanela {

    /**
     * Muda o visual das janelas para Interface que o Sistema executar por
     * Padrão. Se o sistema operacional for windows , a aplicação executará em
     * interface windows.
     *
     * @throws Exception
     */
    public static void MudaSwingParaPadraoDoSO() throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

    }

    /**
     * Muda o visual da Janela para Interface a escolhida.
     * <b>Metal</b><br>
     * <b>Nimbus</b><br>
     * <b>CDE/Motif</b><br>
     * <b>Windows</b><br>
     * <b>Windows Classic</b>
     *
     * @param nomeVisual - Recebe como Parametro uma String (Digitar
     * Corretamente)
     */
    public static void mudaInterfaceSwing(String nomeVisual) throws UnsupportedLookAndFeelException {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if (nomeVisual.equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(InterfaceJanela.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(InterfaceJanela.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(InterfaceJanela.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(InterfaceJanela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    /**
     * Imprime os Look Anf Feel Default instalados no JDK.
     */
    public static void imprimeVisualSwingInstalado() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            System.out.println(info.getName());
        }
    }

    public static void alteraIconePrincipalDoFrame(JFrame jframe, String enderecoArq) {
        URL url;
        url = jframe.getClass().getResource(enderecoArq);
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        jframe.setIconImage(imagemTitulo);
    }
    public static void alteraIconePrincipalDoFrame(JDialog dialog, String enderecoArq) {
        URL url;
        url = dialog.getClass().getResource(enderecoArq);
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        dialog.setIconImage(imagemTitulo);
    }

    public static void alteraIconePrincipaldoJInternalFrame(JInternalFrame internalFrame, String enderecoArq) {
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(enderecoArq));
        internalFrame.setFrameIcon(icon);
    }

    public static void centralizarInternalFrame(JInternalFrame frame) {
        Dimension desktopSize = TelaPrincipal.desktopPane.getSize();
        Dimension jInternalFrameSize = frame.getSize();
        frame.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
    }

}
