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
public class FormaPagamentoDao extends DaoBD implements DaoI<FormaPagamento> {

    public FormaPagamentoDao() {
        super();
    }

    @Override
    public int inserir(FormaPagamento obj) {
        String queryInsert = "INSERT INTO FORMAPAGAMENTO (NOME) VALUES(?)";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(queryInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNome());
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
    public boolean alterar(FormaPagamento obj) {
        String queryUpdate = "UPDATE FORMAPAGAMENTO SET NOME = ? WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryUpdate);
            stmt.setString(1, obj.getNome());
            stmt.setInt(4, obj.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(FormaPagamento obj) {
        String queryDelete = "DELETE FROM FORMAPAGAMENTO WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryDelete);
            stmt.setInt(1, obj.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(int id) {
        String queryDelete = "DELETE FROM FORMAPAGAMENTO WHERE ID = ?";
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
    public boolean desativar(FormaPagamento obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<FormaPagamento> pesquisar() {
        String querySelect = "SELECT * FROM FORMAPAGAMEMTO";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
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

    @Override
    public FormaPagamento pesquisar(int id) {
        String querySelect = "SELECT * FROM FORMAPAGAMEMTO WHERE id = ?";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                FormaPagamento formaPagamento = new FormaPagamento();
                formaPagamento.setId(result.getInt("id"));
                formaPagamento.setNome(result.getString("nome"));
                return formaPagamento;
            }
            return null;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
