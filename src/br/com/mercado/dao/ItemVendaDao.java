/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mercado.dao;

import br.com.mercado.interfaces.DaoI;
import br.com.mercado.model.ItemVenda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author william.mauro
 */
public class ItemVendaDao extends Dao implements DaoI<ItemVenda> {

    VendaDao vendaDao = new VendaDao();
    ProdutoDao produtoDao = new ProdutoDao();

    public ItemVendaDao() {
        super();
    }

    @Override
    public int inserir(ItemVenda itemVenda) {
        String queryInsert = "INSERT INTO itemVenda (VALORPRODUTO, QUANTIDADE, FK_VENDA , FK_PRODUTO) VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(queryInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, itemVenda.getValorProduto());
            stmt.setInt(2, itemVenda.getQuantidade());
            stmt.setInt(3, itemVenda.getVenda().getId());
            stmt.setInt(4, itemVenda.getProduto().getId());
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
    public boolean alterar(ItemVenda itemVenda) {
        String queryUpdate = "UPDATE itemVenda SET valorProduto = ?, quantidade = ?, fk_entrada = ? , fk_produto = ?  WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryUpdate);
            stmt.setDouble(1, itemVenda.getValorProduto());
            stmt.setInt(2, itemVenda.getQuantidade());
            stmt.setInt(3, itemVenda.getVenda().getId());
            stmt.setInt(4, itemVenda.getProduto().getId());
            stmt.setInt(5, itemVenda.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(ItemVenda itemVenda) {
        String queryDelete = "DELETE FROM ITEMVENDA WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryDelete);
            stmt.setInt(1, itemVenda.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(int id) {
        String queryDelete = "DELETE FROM ITEMVENDA WHERE ID = ?";
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
    public boolean desativar(ItemVenda itemVenda) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItemVenda> pesquisar() {
        String querySelect = "SELECT * FROM ITEMVENDA";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            ResultSet result = stmt.executeQuery();
            List<ItemVenda> lista = new ArrayList<>();
            while (result.next()) {
                ItemVenda itemVenda = new ItemVenda();
                itemVenda.setId(result.getInt("id"));
                itemVenda.setValorProduto(result.getDouble("valorProduto"));
                itemVenda.setQuantidade(result.getInt("quantidade"));
                itemVenda.setVenda(vendaDao.pesquisar(result.getInt("fk_venda")));
                itemVenda.setProduto(produtoDao.pesquisar(result.getInt("fk_produto")));
                lista.add(itemVenda);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<ItemVenda> pesquisar(String termo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItemVenda pesquisar(int id) {
        String querySelectComTermo = "SELECT * FROM itemVenda WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                ItemVenda itemVenda = new ItemVenda();
                itemVenda.setId(result.getInt("id"));
                itemVenda.setValorProduto(result.getDouble("valorProduto"));
                itemVenda.setQuantidade(result.getInt("quantidade"));
                itemVenda.setVenda(vendaDao.pesquisar(result.getInt("fk_venda")));
                itemVenda.setProduto(produtoDao.pesquisar(result.getInt("fk_produto")));
                return itemVenda;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<ItemVenda> pesquisarPorVenda(int id) {
        String querySelectComTermo = "SELECT * FROM itemVenda WHERE fk_venda = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            List<ItemVenda> lista = new ArrayList<>();
            while (result.next()) {
                ItemVenda itemVenda = new ItemVenda();
                itemVenda.setId(result.getInt("id"));
                itemVenda.setValorProduto(result.getDouble("valorProduto"));
                itemVenda.setQuantidade(result.getInt("quantidade"));
                itemVenda.setVenda(vendaDao.pesquisar(result.getInt("fk_venda")));
                itemVenda.setProduto(produtoDao.pesquisar(result.getInt("fk_produto")));
                lista.add(itemVenda);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
