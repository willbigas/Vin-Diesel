/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.dao;

import br.com.vindiesel.model.TipoDespesa;
import br.com.vindiesel.interfaces.DaoI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author william.mauro
 */
public class TipoDespesaDao extends Dao implements DaoI<TipoDespesa> {

    public TipoDespesaDao() {
        super();
    }

    @Override
    public int inserir(TipoDespesa tipoDespesa) {
        String queryInsert = "INSERT INTO tipoDespesa (NOME) VALUES(?)";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(queryInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, tipoDespesa.getNome());
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
    public boolean alterar(TipoDespesa tipoDespesa) {
        String queryUpdate = "UPDATE tipoDespesa SET NOME = ? WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryUpdate);
            stmt.setString(1, tipoDespesa.getNome());
            stmt.setInt(2, tipoDespesa.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(TipoDespesa tipoDespesa) {
        String queryDelete = "DELETE FROM tipoDespesa WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryDelete);
            stmt.setInt(1, tipoDespesa.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(int id) {
        String queryDelete = "DELETE FROM tipoDespesa WHERE ID = ?";
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
    public boolean desativar(TipoDespesa obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TipoDespesa> pesquisar() {
        String querySelect = "SELECT * FROM TIPODESPESA";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            ResultSet result = stmt.executeQuery();
            List<TipoDespesa> lista = new ArrayList<>();
            while (result.next()) {
                TipoDespesa tipoDespesa = new TipoDespesa();
                tipoDespesa.setId(result.getInt("id"));
                tipoDespesa.setNome(result.getString("nome"));
                lista.add(tipoDespesa);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<TipoDespesa> pesquisar(String termo) {
        String querySelectComTermo = "SELECT * FROM tipoDespesa WHERE (NOME like ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + termo + "%");
            ResultSet result = stmt.executeQuery();
            List<TipoDespesa> lista = new ArrayList<>();
            while (result.next()) {
                TipoDespesa tipoDespesa = new TipoDespesa();
                tipoDespesa.setId(result.getInt("id"));
                tipoDespesa.setNome(result.getString("nome"));
                lista.add(tipoDespesa);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public TipoDespesa pesquisar(int id) {
        String querySelect = "SELECT * FROM TIPODESPESA WHERE ID = ?";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                TipoDespesa tipoDespesa = new TipoDespesa();
                tipoDespesa.setId(result.getInt("id"));
                tipoDespesa.setNome(result.getString("nome"));
                return tipoDespesa;
            }
            return null;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
