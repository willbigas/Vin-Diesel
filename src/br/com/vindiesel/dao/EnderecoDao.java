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
public class EnderecoDao extends DaoBD implements DaoI<Endereco> {

    public EnderecoDao() {
        super();
    }

    @Override
    public int inserir(Endereco endereco) {
        String queryInsert = "INSERT INTO endereco (cep, estado, cidade, bairro, rua, complemento, numero) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(queryInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, endereco.getCep());
            stmt.setString(2, endereco.getEstado());
            stmt.setString(3, endereco.getCidade());
            stmt.setString(4, endereco.getBairro());
            stmt.setString(5, endereco.getRua());
            stmt.setString(6, endereco.getComplemento());
            stmt.setString(7, endereco.getNumero());
            ResultSet res;
            if (stmt.executeUpdate() > 0) {
                res = stmt.getGeneratedKeys();
                res.next();
                return res.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    @Override
    public boolean alterar(Endereco endereco) {
        String queryUpdate = "UPDATE endereco SET CEP = ?, ESTADO = ?, CIDADE =?, BAIRRO = ?, RUA = ?, COMPLEMENTO = ?, NUMERO = ? WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryUpdate);
            stmt.setInt(1, endereco.getCep());
            stmt.setString(2, endereco.getEstado());
            stmt.setString(3, endereco.getCidade());
            stmt.setString(4, endereco.getBairro());
            stmt.setString(5, endereco.getRua());
            stmt.setString(6, endereco.getComplemento());
            stmt.setString(7, endereco.getNumero());
            stmt.setInt(8, endereco.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(Endereco endereco) {
        String queryDelete = "DELETE FROM endereco WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryDelete);
            stmt.setInt(1, endereco.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(int id) {
        String queryDelete = "DELETE FROM endereco WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryDelete);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
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
    public List<Endereco> pesquisar() {
        String querySelect = "SELECT * FROM ENDERECO";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            ResultSet result = stmt.executeQuery();
            List<Endereco> lista = new ArrayList<>();
            while (result.next()) {
                Endereco endereco = new Endereco();
                endereco.setId(result.getInt("id"));
                endereco.setCep(result.getInt("cep"));
                endereco.setCidade(result.getString("cidade"));
                endereco.setBairro(result.getString("bairro"));
                endereco.setComplemento(result.getString("complemento"));
                endereco.setEstado(result.getString("estado"));
                endereco.setNumero(result.getString("numero"));
                endereco.setRua(result.getString("rua"));
                lista.add(endereco);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
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
                endereco.setCep(result.getInt("cep"));
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

    @Override
    public Endereco pesquisar(int id) {
        String querySelect = "SELECT * FROM ENDERECO WHERE id = ?";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                Endereco endereco = new Endereco();
                endereco.setId(result.getInt("id"));
                endereco.setCep(result.getInt("cep"));
                endereco.setCidade(result.getString("cidade"));
                endereco.setBairro(result.getString("bairro"));
                endereco.setComplemento(result.getString("complemento"));
                endereco.setEstado(result.getString("estado"));
                endereco.setNumero(result.getString("numero"));
                endereco.setRua(result.getString("rua"));
                return endereco;
            }
            return null;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
