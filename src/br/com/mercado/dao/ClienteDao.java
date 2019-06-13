/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mercado.dao;

import br.com.mercado.model.Cliente;
import br.com.mercado.interfaces.DaoI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author william.mauro
 */
public class ClienteDao extends Dao implements DaoI<Cliente> {

    EnderecoDao enderecoDao;

    public ClienteDao() {
        super();
        enderecoDao = new EnderecoDao();
    }

    @Override
    public int inserir(Cliente cliente) {
        String queryInsert = "INSERT INTO CLIENTES (NOME, TELEFONE, EMAIL, FK_ENDERECO, ATIVO) VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(queryInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            stmt.setInt(4, cliente.getEndereco().getId());
            stmt.setBoolean(5, cliente.getAtivo());
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
    public boolean alterar(Cliente cliente) {
        String queryUpdate = "UPDATE clientes SET nome = ?, telefone = ?, email = ?, , ativo ? fk_endereco = ? WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryUpdate);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            stmt.setBoolean(4, cliente.getAtivo());
            stmt.setInt(5, cliente.getEndereco().getId());
            stmt.setInt(6, cliente.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(Cliente obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> pesquisar() {
        String querySelect = "SELECT * FROM CLIENTES where ativo = true";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            ResultSet result = stmt.executeQuery();
            List<Cliente> lista = new ArrayList<>();
            while (result.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(result.getInt("id"));
                cliente.setNome(result.getString("nome"));
                cliente.setEmail(result.getString("email"));
                cliente.setTelefone(result.getString("telefone"));
                cliente.setAtivo(result.getBoolean("ativo"));
                cliente.setEndereco(enderecoDao.pesquisar(result.getInt("fk_endereco")));
                lista.add(cliente);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Cliente> pesquisar(String termo) {
        String querySelectComTermo = "SELECT * FROM clientes WHERE (nome LIKE ? or email LIKE ? or telefone LIKE ?) and ativo = true";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + termo + "%");
            stmt.setString(2, "%" + termo + "%");
            stmt.setString(3, "%" + termo + "%");
            ResultSet result = stmt.executeQuery();
            List<Cliente> lista = new ArrayList<>();
            while (result.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(result.getInt("id"));
                cliente.setNome(result.getString("nome"));
                cliente.setEmail(result.getString("email"));
                cliente.setTelefone(result.getString("telefone"));
                cliente.setAtivo(result.getBoolean("ativo"));
                cliente.setEndereco(enderecoDao.pesquisar(result.getInt("fk_endereco")));
                lista.add(cliente);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Cliente pesquisar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(Cliente cliente) {
        String sql = "UPDATE clientes SET ativo = false WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, cliente.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean desativar(int id) {
        String sql = "UPDATE clientes SET ativo = false WHERE id = ?";
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
