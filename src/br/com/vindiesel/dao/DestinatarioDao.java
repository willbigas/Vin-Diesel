/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.dao;

import br.com.vindiesel.model.Destinatario;
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
public class DestinatarioDao extends Dao implements DaoI<Destinatario> {

    EnderecoDao enderecoDao;

    public DestinatarioDao() {
        super();
        enderecoDao = new EnderecoDao();
    }

    @Override
    public int inserir(Destinatario destinatario) {
        String queryInsert = "INSERT INTO DESTINATARIO (NOME, CODIGOPESSOA, ENDERECO_ID) VALUES(?, ?, ?)";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(queryInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, destinatario.getNome());
            stmt.setString(2, destinatario.getCodigoPessoa());
            stmt.setInt(3, destinatario.getEndereco().getId());
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
    public boolean alterar(Destinatario destinatario) {
        String queryUpdate = "UPDATE destinatario SET nome = ?, codigoPessoa = ?, endereco_id = ? WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryUpdate);
            stmt.setString(1, destinatario.getNome());
            stmt.setString(2, destinatario.getCodigoPessoa());
            stmt.setInt(3, destinatario.getEndereco().getId());
            stmt.setInt(4, destinatario.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(Destinatario obj) {
        String queryDelete = "DELETE FROM destinatario WHERE ID = ?";
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
          String queryDelete = "DELETE FROM destinatario WHERE ID = ?";
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
    public List<Destinatario> pesquisar() {
        String querySelect = "SELECT * FROM DESTINATARIO";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            ResultSet result = stmt.executeQuery();
            List<Destinatario> lista = new ArrayList<>();
            while (result.next()) {
                Destinatario cliente = new Destinatario();
                cliente.setId(result.getInt("id"));
                cliente.setNome(result.getString("nome"));
                cliente.setEndereco(enderecoDao.pesquisar(result.getInt("endereco_id")));
                lista.add(cliente);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Destinatario> pesquisar(String termo) {
        String querySelectComTermo = "SELECT * FROM destinatario WHERE (nome LIKE ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + termo + "%");
            ResultSet result = stmt.executeQuery();
            List<Destinatario> lista = new ArrayList<>();
            while (result.next()) {
                Destinatario cliente = new Destinatario();
                cliente.setId(result.getInt("id"));
                cliente.setNome(result.getString("nome"));
                cliente.setEndereco(enderecoDao.pesquisar(result.getInt("endereco_id")));
                lista.add(cliente);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Destinatario pesquisar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(Destinatario cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
