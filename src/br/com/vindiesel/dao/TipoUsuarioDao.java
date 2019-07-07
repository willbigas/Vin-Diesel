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
public class TipoUsuarioDao extends GenericDao<TipoUsuario> implements DaoI<TipoUsuario> {

    public TipoUsuarioDao() {
        super();
    }

    public List<TipoUsuario> pesquisar(Boolean ativo) {
        String querySelect = "SELECT * FROM TIPOUSUARIO WHERE (ATIVO = " + ativo + " ";
        if (ativo == null) {
            querySelect = "SELECT * FROM TIPOUSUARIO";
        }
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
        String querySelectComTermo = "SELECT * FROM TIPOUSUARIO WHERE (NOME like ? or TIPOPERMISSAO like ?)";
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
                tipoUsuario.setAtivo(result.getBoolean("ativo"));
                lista.add(tipoUsuario);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<TipoUsuario> pesquisar(String termo, Boolean ativo) {
        String querySelectComTermo = "SELECT * FROM TIPOUSUARIO WHERE (NOME like ? or TIPOPERMISSAO like ?) AND ATIVO = " + ativo + " ";
        if (ativo == null) {
            querySelectComTermo = "SELECT * FROM TIPOUSUARIO WHERE (NOME like ? or TIPOPERMISSAO like ?)";
        }
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
