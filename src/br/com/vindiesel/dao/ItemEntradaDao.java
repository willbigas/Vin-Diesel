/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.dao;

import br.com.vindiesel.model.Entrada;
import br.com.vindiesel.model.ItemEntrada;
import br.com.vindiesel.model.Produto;
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
public class ItemEntradaDao extends Dao implements DaoI<ItemEntrada> {

    EntradaDao entradaDao = new EntradaDao();
    ProdutoDao produtoDao = new ProdutoDao();

    public ItemEntradaDao() {
        super();
    }

    @Override
    public int inserir(ItemEntrada itemEntrada) {
        String queryInsert = "INSERT INTO itemEntrada (VALORPRODUTO, NUMEROLOTE, QUANTIDADE, FK_PRODUTO, FK_ENTRADA) VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(queryInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, itemEntrada.getValorProduto());
            stmt.setInt(2, itemEntrada.getNumeroLote());
            stmt.setInt(3, itemEntrada.getQuantidade());
            stmt.setInt(4, itemEntrada.getProduto().getId());
            stmt.setInt(5, itemEntrada.getEntrada().getId());
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
    public boolean alterar(ItemEntrada itemEntrada) {
        String queryUpdate = "UPDATE itemEntrada SET valorProduto = ?, numeroLote = ?, quantidade = ?, fk_produto = ?, fk_entrada = ? WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryUpdate);
            stmt.setDouble(1, itemEntrada.getValorProduto());
            stmt.setInt(2, itemEntrada.getNumeroLote());
            stmt.setInt(3, itemEntrada.getQuantidade());
            stmt.setInt(4, itemEntrada.getProduto().getId());
            stmt.setInt(5, itemEntrada.getEntrada().getId());
            stmt.setInt(6, itemEntrada.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(ItemEntrada itemEntrada) {
        String queryDelete = "DELETE FROM ITEMENTRADA WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryDelete);
            stmt.setInt(1, itemEntrada.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(int id) {
        String queryDelete = "DELETE FROM ITEMENTRADA WHERE ID = ?";
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
    public boolean desativar(ItemEntrada itemEntrada) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItemEntrada> pesquisar() {
        String querySelect = "SELECT * FROM ITEMENTRADA";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            ResultSet result = stmt.executeQuery();
            List<ItemEntrada> lista = new ArrayList<>();
            while (result.next()) {
                ItemEntrada itemEntrada = new ItemEntrada();
                itemEntrada.setId(result.getInt("id"));
                itemEntrada.setNumeroLote(result.getInt("numeroLote"));
                itemEntrada.setQuantidade(result.getInt("quantidade"));
                itemEntrada.setValorProduto(result.getDouble("valorProduto"));
                itemEntrada.setEntrada(entradaDao.pesquisar(result.getInt("fk_entrada")));
                itemEntrada.setProduto(produtoDao.pesquisar(result.getInt("fk_produto")));

            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<ItemEntrada> pesquisar(String termo) {
        String querySelectComTermo = "SELECT * FROM itemEntrada WHERE (numeroLote LIKE ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + termo + "%");
            ResultSet result = stmt.executeQuery();
            List<ItemEntrada> lista = new ArrayList<>();
            while (result.next()) {
                ItemEntrada itemEntrada = new ItemEntrada();
                itemEntrada.setId(result.getInt("id"));
                itemEntrada.setNumeroLote(result.getInt("numeroLote"));
                itemEntrada.setQuantidade(result.getInt("quantidade"));
                itemEntrada.setValorProduto(result.getDouble("valorProduto"));
                itemEntrada.setEntrada(entradaDao.pesquisar(result.getInt("fk_entrada")));
                itemEntrada.setProduto(produtoDao.pesquisar(result.getInt("fk_produto")));
                lista.add(itemEntrada);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public ItemEntrada pesquisar(int id) {
        String querySelectComTermo = "SELECT * FROM itemEntrada WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                ItemEntrada itemEntrada = new ItemEntrada();
                itemEntrada.setId(result.getInt("id"));
                itemEntrada.setNumeroLote(result.getInt("numeroLote"));
                itemEntrada.setQuantidade(result.getInt("quantidade"));
                itemEntrada.setValorProduto(result.getDouble("valorProduto"));
                itemEntrada.setEntrada(entradaDao.pesquisar(result.getInt("fk_entrada")));
                itemEntrada.setProduto(produtoDao.pesquisar(result.getInt("fk_produto")));
                return itemEntrada;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<ItemEntrada> pesquisarPorEntrada(int id) {
        String querySelectComTermo = "SELECT * FROM itemEntrada WHERE fk_entrada = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            List<ItemEntrada> lista = new ArrayList<>();
            while (result.next()) {
                ItemEntrada itemEntrada = new ItemEntrada();
                itemEntrada.setId(result.getInt("id"));
                itemEntrada.setNumeroLote(result.getInt("numeroLote"));
                itemEntrada.setQuantidade(result.getInt("quantidade"));
                itemEntrada.setValorProduto(result.getDouble("valorProduto"));
                itemEntrada.setEntrada(entradaDao.pesquisar(result.getInt("fk_entrada")));
                itemEntrada.setProduto(produtoDao.pesquisar(result.getInt("fk_produto")));
                lista.add(itemEntrada);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
