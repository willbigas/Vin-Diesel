package br.com.vindiesel.interfaces;

import br.com.vindiesel.control.BuscaCepControl;
import javax.swing.JOptionPane;

/**
 *
 * @author Will
 */
public class BuscaCepEventosImpl implements BuscaCepEventos {

    @Override
    public void sucessoAoEncontrar(BuscaCepControl buscaCep) {
        JOptionPane.showMessageDialog(null, "Cep " + buscaCep.getCep() + " Encontrado");
    }

    @Override
    public void erroAoEncontrar(String cep) {
        JOptionPane.showMessageDialog(null, "Cep " + cep + "Nao Encontrado!");

    }

}
