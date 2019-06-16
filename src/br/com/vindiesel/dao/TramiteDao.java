/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.dao;

import br.com.vindiesel.interfaces.DaoI;
import br.com.vindiesel.model.Tramite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADJ-PC
 */
public class TramiteDao extends DaoBD implements DaoI<Tramite> {

    EntregaDao entregaDao;

    public TramiteDao() {
        super();
        entregaDao = new EntregaDao();
    }

    @Override
    public int inserir(Tramite obj) {
        String queryInsert = "INSERT INTO TRAMITE (DATAHORA, NOME , OBSERVACAO, ENTREGA_ID) VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(queryInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setTimestamp(1, Timestamp.valueOf(obj.getDataHora()));
            stmt.setString(2, obj.getNome());
            stmt.setString(3, obj.getObservacao());
            stmt.setInt(4, obj.getEntrega().getId());
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
    public boolean alterar(Tramite obj) {
        String queryUpdate = "UPDATE TRAMITE SET dataHora = ?, nome = ?, observacao = ?, entrega_id = ? WHERE ID = ? ";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryUpdate);
            stmt.setTimestamp(1, Timestamp.valueOf(obj.getDataHora()));
            stmt.setString(2, obj.getNome());
            stmt.setString(3, obj.getObservacao());
            stmt.setInt(4, obj.getEntrega().getId());
            stmt.setInt(5, obj.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(Tramite obj) {
        String queryDelete = "DELETE FROM TRAMITE WHERE ID = ?";
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
        String queryDelete = "DELETE FROM TRAMITE WHERE ID = ?";
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
    public boolean desativar(Tramite obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tramite> pesquisar() {
        String querySelect = "SELECT * FROM TRAMITE";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            ResultSet result = stmt.executeQuery();
            List<Tramite> lista = new ArrayList<>();
            while (result.next()) {
                Tramite tramite = new Tramite();
                tramite.setId(result.getInt("id"));
                tramite.setDataHora((result.getTimestamp("dataHora").toLocalDateTime()));
                tramite.setNome(result.getString("nome"));
                tramite.setObservacao(result.getString("observacao"));
                lista.add(tramite);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Tramite> pesquisar(String termo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tramite pesquisar(int id) {
        String querySelectComTermo = "SELECT * FROM TRAMITE WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                Tramite tramite = new Tramite();
                tramite.setId(result.getInt("id"));
                tramite.setId(result.getInt("id"));
                tramite.setDataHora((result.getTimestamp("dataHora").toLocalDateTime()));
                tramite.setNome(result.getString("nome"));
                tramite.setObservacao(result.getString("observacao"));
                return tramite;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
