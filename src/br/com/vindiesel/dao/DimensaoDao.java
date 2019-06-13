package br.com.vindiesel.dao;

import br.com.vindiesel.interfaces.DaoI;
import br.com.vindiesel.model.Dimensao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author William
 */
public class DimensaoDao extends Dao implements DaoI<Dimensao> {

    @Override
    public int inserir(Dimensao obj) {
        String queryInsert = "INSERT INTO dimensao (COMPRIMENTO, LARGURA, ALTURA) VALUES(?, ?, ?)";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(queryInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, obj.getComprimento());
            stmt.setDouble(2, obj.getLargura());
            stmt.setDouble(3, obj.getAltura());
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
    public boolean alterar(Dimensao obj) {
        String queryUpdate = "UPDATE DIMENSAO SET comprimento = ?, largura = ?, altura = ? WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryUpdate);
            stmt.setDouble(1, obj.getComprimento());
            stmt.setDouble(2, obj.getLargura());
            stmt.setDouble(3, obj.getAltura());
            stmt.setInt(4, obj.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(Dimensao obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(Dimensao obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Dimensao> pesquisar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Dimensao> pesquisar(String termo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Dimensao pesquisar(int id) {
         String querySelect = "SELECT * FROM DIMENSAO WHERE id = ?";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                Dimensao dimensao = new Dimensao();
                dimensao.setId(result.getInt("id"));
                dimensao.setComprimento(result.getDouble("comprimento"));
                dimensao.setLargura(result.getDouble("largura"));
                dimensao.setAltura(result.getDouble("altura"));
                return dimensao;
            }
            return null;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
