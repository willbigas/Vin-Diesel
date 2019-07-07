/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.dao;

import br.com.vindiesel.interfaces.DaoI;
import br.com.vindiesel.model.FormaPagamento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADJ-PC
 */
public class FormaPagamentoDao extends GenericDao<FormaPagamento> implements DaoI<FormaPagamento> {

    public FormaPagamentoDao() {
        super();
    }



    @Override
    public boolean desativar(FormaPagamento obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<FormaPagamento> pesquisar(String termo) {
        String querySelectComTermo = "SELECT * FROM endereco WHERE (nome LIKE ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + termo + "%");
            ResultSet result = stmt.executeQuery();
            List<FormaPagamento> lista = new ArrayList<>();
            while (result.next()) {
                FormaPagamento formaPagamento = new FormaPagamento();
                formaPagamento.setId(result.getInt("id"));
                formaPagamento.setNome(result.getString("nome"));
                lista.add(formaPagamento);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
