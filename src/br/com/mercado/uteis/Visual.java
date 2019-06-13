package br.com.mercado.uteis;

import java.awt.Color;
import javax.swing.JTextField;

/**
 *
 * @author Will
 */
public class Visual {

    public static void criarPlaceHolder(JTextField textField, String mensagem) {
        textField.setText("mensagem");
        textField.setForeground(Color.GRAY);
    }

    public static void limparPlaceHolder(JTextField textField) {
        textField.setText("");
    }

}
