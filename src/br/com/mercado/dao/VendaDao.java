package br.com.mercado.dao;

import br.com.mercado.model.Cliente;
import br.com.mercado.model.Usuario;
import br.com.mercado.model.Venda;
import br.com.mercado.interfaces.DaoI;
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
public class VendaDao extends Dao implements DaoI<Venda> {

    public VendaDao() {
        super();
    }

    @Override
    public int inserir(Venda venda) {
        String queryInsert = "INSERT INTO vendas(DATAVENDA, FK_CLIENTE, FK_USUARIO) VALUES(?, ?, ?)";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(queryInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setTimestamp(1, Timestamp.valueOf(venda.getDataVenda()));
            stmt.setDouble(2, venda.getCliente().getId());
            stmt.setInt(3, venda.getUsuario().getId());
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
    public boolean alterar(Venda venda) {
        String queryUpdate = "UPDATE VENDAS SET dataVenda = ?, fk_cliente = ?, fk_usuario = ?WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryUpdate);
            stmt.setTimestamp(1, Timestamp.valueOf(venda.getDataVenda()));
            stmt.setInt(2, venda.getCliente().getId());
            stmt.setInt(3, venda.getUsuario().getId());
            stmt.setInt(4, venda.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(Venda venda) {
        String queryDelete = "DELETE FROM VENDAS WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryDelete);
            stmt.setInt(1, venda.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(int id) {
        String queryDelete = "DELETE FROM VENDAS WHERE ID = ?";
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
    public boolean desativar(Venda obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Venda> pesquisar() {
        String querySelect = "SELECT * FROM VENDAS";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            ResultSet result = stmt.executeQuery();
            List<Venda> lista = new ArrayList<>();
            while (result.next()) {
                Venda venda = new Venda();
                venda.setId(result.getInt("id"));
                venda.setDataVenda((result.getTimestamp("dataVenda").toLocalDateTime()));
                Cliente cliente = new Cliente();
                cliente.setId(result.getInt("fk_cliente"));
                Usuario funcionario = new Usuario();
                funcionario.setId(result.getInt("fk_usuario"));
                lista.add(venda);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Venda> pesquisar(String termo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Venda pesquisar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
