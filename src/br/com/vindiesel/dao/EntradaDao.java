/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.dao;

import br.com.vindiesel.model.Entrada;
import br.com.vindiesel.interfaces.DaoI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author william.mauro
 */
public class EntradaDao extends Dao implements DaoI<Entrada> {

    FornecedorDao fornecedorDao = new FornecedorDao();

    public EntradaDao() {
        super();
    }

    @Override
    public int inserir(Entrada entrada) {
        String queryInsert = "INSERT INTO entradas (DATAENTRADA, FK_FORNECEDOR) VALUES(?,?)";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(queryInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setTimestamp(1, Timestamp.valueOf(entrada.getDataEntrada()));
            stmt.setInt(2, entrada.getFornecedor().getId());
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
    public boolean alterar(Entrada entrada) {
        String queryUpdate = "UPDATE entradas SET DATAENTRADA = ?, FK_FORNECEDOR WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryUpdate);
            stmt.setTimestamp(1, Timestamp.valueOf(entrada.getDataEntrada()));
            stmt.setInt(2, entrada.getFornecedor().getId());
            stmt.setInt(3, entrada.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(Entrada entrada) {
        String queryDelete = "DELETE FROM ENTRADAS WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryDelete);
            stmt.setInt(1, entrada.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(int id) {
        String queryDelete = "DELETE FROM ENTRADAS WHERE ID = ?";
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
    public boolean desativar(Entrada entrada) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Entrada> pesquisar() {
        String querySelect = "SELECT * FROM ENTRADAS";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            ResultSet result = stmt.executeQuery();
            List<Entrada> lista = new ArrayList<>();
            while (result.next()) {
                Entrada entrada = new Entrada();
                entrada.setId(result.getInt("id"));
                entrada.setDataEntrada(result.getTimestamp("dataEntrada").toLocalDateTime());
                entrada.setFornecedor(fornecedorDao.pesquisar(result.getInt("fk_fornecedor")));
                lista.add(entrada);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Entrada> pesquisar(String termo) {
        String querySelectComTermo = "SELECT * FROM entradas WHERE (dataEntrada LIKE ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + termo + "%");
            ResultSet result = stmt.executeQuery();
            List<Entrada> lista = new ArrayList<>();
            while (result.next()) {
                Entrada entrada = new Entrada();
                entrada.setId(result.getInt("id"));
                entrada.setDataEntrada(result.getTimestamp("dataEntrada").toLocalDateTime());
                entrada.setFornecedor(fornecedorDao.pesquisar(result.getInt("fk_fornecedor")));
                lista.add(entrada);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Entrada pesquisar(int id) {
        String querySelectComTermo = "SELECT * FROM entradas WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                Entrada entrada = new Entrada();
                entrada.setId(result.getInt("id"));
                entrada.setDataEntrada(result.getTimestamp("dataEntrada").toLocalDateTime());
                entrada.setFornecedor(fornecedorDao.pesquisar(result.getInt("fk_fornecedor")));
                return entrada;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

}
