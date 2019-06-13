package br.com.mercado.dao;

import br.com.mercado.model.Categoria;
import br.com.mercado.interfaces.DaoI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author William
 */
public class CategoriaDao extends Dao implements DaoI<Categoria> {

    public CategoriaDao() {
        super();
    }

    @Override
    public List<Categoria> pesquisar() {
        String querySelect = "SELECT * FROM CATEGORIAS WHERE ATIVO = TRUE";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            ResultSet result = stmt.executeQuery();
            List<Categoria> lista = new ArrayList<>();
            while (result.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(result.getInt("id"));
                categoria.setNome(result.getString("nome"));
                categoria.setAtivo(result.getBoolean("ativo"));
                lista.add(categoria);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public int inserir(Categoria categoria) {
        String queryInsert = "INSERT INTO CATEGORIAS(NOME, ATIVO) VALUES(?, ?)";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(queryInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, categoria.getNome());
            stmt.setBoolean(2, categoria.getAtivo());
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
    public boolean alterar(Categoria categoria) {
        String queryUpdate = "UPDATE CATEGORIAS SET NOME = ?, ATIVO = ? WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryUpdate);
            stmt.setString(1, categoria.getNome());
            stmt.setBoolean(2, categoria.getAtivo());
            stmt.setInt(3, categoria.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(Categoria categoria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.  
    }

    @Override
    public boolean deletar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categoria> pesquisar(String termo) {
        String querySelectComTermo = "select * from categorias where ativo = true and nome LIKE ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + termo + "%");
            ResultSet result = stmt.executeQuery();
            List<Categoria> lista = new ArrayList<>();
            while (result.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(result.getInt("id"));
                categoria.setNome(result.getString("nome"));
                categoria.setAtivo(result.getBoolean("ativo"));
                lista.add(categoria);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

    @Override
    public Categoria pesquisar(int id) {
        String querySelectPorId = "SELECT * FROM CATEGORIAS WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectPorId);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(result.getInt("id"));
                categoria.setNome(result.getString("nome"));
                categoria.setAtivo(result.getBoolean("ativo"));
                return categoria;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean desativar(Categoria categoria) {
        String sql = "UPDATE CATEGORIAS SET ativo = false WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, categoria.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean desativar(int id) {
        String sql = "UPDATE CATEGORIAS SET ativo = false WHERE id = ?";
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
