/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.dao;

import br.com.vindiesel.model.Endereco;
import br.com.vindiesel.interfaces.DaoI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADJ-PC
 */
public class EnderecoDao extends GenericDao<Endereco> implements DaoI<Endereco> {

    public EnderecoDao() {
        super();
    }


    @Override
    public boolean desativar(Endereco obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<Endereco> pesquisar(String termo) {
        String querySelectComTermo = "SELECT * FROM endereco WHERE (cep LIKE ?, rua LIKE ?, cidade LIKE ?, bairro LIKE ?, estado LIKE ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + termo + "%");
            stmt.setString(2, "%" + termo + "%");
            stmt.setString(3, "%" + termo + "%");
            stmt.setString(4, "%" + termo + "%");
            stmt.setString(5, "%" + termo + "%");
            ResultSet result = stmt.executeQuery();
            List<Endereco> lista = new ArrayList<>();
            while (result.next()) {
                Endereco endereco = new Endereco();
                endereco.setId(result.getInt("id"));
                endereco.setCep(result.getString("cep"));
                endereco.setRua(result.getString("rua"));
                endereco.setCidade(result.getString("cidade"));
                endereco.setBairro(result.getString("bairro"));
                endereco.setEstado(result.getString("estado"));
                lista.add(endereco);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
