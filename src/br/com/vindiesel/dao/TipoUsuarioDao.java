/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.dao;

import br.com.vindiesel.model.TipoUsuario;
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
public class TipoUsuarioDao extends Dao implements DaoI<TipoUsuario> {

    public TipoUsuarioDao() {
        super();
    }

    @Override
    public int inserir(TipoUsuario tipoUsuario) {
        String queryInsert = "INSERT INTO tipoUsuario (NOME, TIPOPERMISSAO, ATIVO) VALUES(?, ?, ?)";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(queryInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, tipoUsuario.getNome());
            stmt.setInt(2, tipoUsuario.getTipoPermissao());
            stmt.setBoolean(3, tipoUsuario.getAtivo());
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
    public boolean alterar(TipoUsuario tipoUsuario) {
        String queryUpdate = "UPDATE tipoUsuario SET NOME = ?, TIPOPERMISSAO = ?, ATIVO = ? WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryUpdate);
            stmt.setString(1, tipoUsuario.getNome());
            stmt.setInt(2, tipoUsuario.getTipoPermissao());
            stmt.setBoolean(3, tipoUsuario.getAtivo());
            stmt.setInt(4, tipoUsuario.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(TipoUsuario tipoUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TipoUsuario> pesquisar() {
        String querySelect = "SELECT * FROM TIPOUSUARIO WHERE ATIVO = TRUE";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            ResultSet result = stmt.executeQuery();
            List<TipoUsuario> lista = new ArrayList<>();
            while (result.next()) {
                TipoUsuario tipoUsuario = new TipoUsuario();
                tipoUsuario.setId(result.getInt("id"));
                tipoUsuario.setNome(result.getString("nome"));
                tipoUsuario.setTipoPermissao(result.getInt("tipoPermissao"));
                tipoUsuario.setAtivo(result.getBoolean("ativo"));
                lista.add(tipoUsuario);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<TipoUsuario> pesquisar(String termo) {
        String querySelectComTermo = "SELECT * FROM TIPOUSUARIO WHERE (NOME like ?, TIPOPERMISSAO like ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + termo + "%");
            stmt.setString(2, "%" + termo + "%");
            ResultSet result = stmt.executeQuery();
            List<TipoUsuario> lista = new ArrayList<>();
            while (result.next()) {
                TipoUsuario tipoUsuario = new TipoUsuario();
                tipoUsuario.setId(result.getInt("id"));
                tipoUsuario.setNome(result.getString("nome"));
                tipoUsuario.setTipoPermissao(result.getInt("tipoPermissao"));
                lista.add(tipoUsuario);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public TipoUsuario pesquisar(int id) {
        String querySelect = "SELECT * FROM TIPOUSUARIO WHERE (id = ?)";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                TipoUsuario tipoUsuario = new TipoUsuario();
                tipoUsuario.setId(result.getInt("id"));
                tipoUsuario.setNome(result.getString("nome"));
                tipoUsuario.setTipoPermissao(result.getInt("tipoPermissao"));
                tipoUsuario.setAtivo(result.getBoolean("ativo"));
                return tipoUsuario;
            }
            return null;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean desativar(TipoUsuario obj) {
        String sql = "UPDATE tipoUsuario SET ativo = false WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean desativar(int id) {
        String sql = "UPDATE tipoUsuario SET ativo = false WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

}
