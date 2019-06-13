/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mercado.dao;

import br.com.mercado.model.Receita;
import br.com.mercado.interfaces.DaoI;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author william.mauro
 */
public class ReceitaDao extends Dao implements DaoI<Receita> {

    public ReceitaDao() {
        super();
    }

    @Override
    public int inserir(Receita receita) {
        String queryInsert = "INSERT INTO receitas (DATACADASTRO, DATAPAGAMENTO, DATAVENCIMENTO, VALORTOTAL, VALORRECEBIDO, CODVENDA) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(queryInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setTimestamp(1, Timestamp.valueOf(receita.getDataCadastro()));
            if (receita.getDataPagamento() == null) {
                stmt.setNull(2, Types.TIMESTAMP);
            } else {
                stmt.setTimestamp(2, Timestamp.valueOf(receita.getDataPagamento()));
            }
            stmt.setDate(3, new Date(receita.getDataVencimento().getTime()));
            stmt.setDouble(4, receita.getValorTotal());
            if (receita.getValorRecebido() == null) {
                stmt.setNull(5, Types.DOUBLE);
            } else {
                stmt.setDouble(5, receita.getValorRecebido());
            }
            stmt.setInt(6, receita.getCodVenda());
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
    public boolean alterar(Receita receita) {
        String queryUpdate = "UPDATE receitas SET dataCadastro = ?, dataPagamento = ?, dataVencimento = ?, valorRecebido = ?, valorTotal = ?, codVenda = ? WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryUpdate);
            stmt.setTimestamp(1, Timestamp.valueOf(receita.getDataCadastro()));
            stmt.setTimestamp(2, Timestamp.valueOf(receita.getDataPagamento()));
            stmt.setDate(3, new Date(receita.getDataVencimento().getTime()));
            stmt.setDouble(4, receita.getValorRecebido());
            stmt.setDouble(5, receita.getValorTotal());
            stmt.setInt(6, receita.getCodVenda());
            stmt.setInt(7, receita.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(Receita receita) {
        String queryDelete = "DELETE FROM RECEITAS WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryDelete);
            stmt.setInt(1, receita.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(int id) {
        String queryDelete = "DELETE FROM RECEITAS WHERE ID = ?";
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
    public boolean desativar(Receita obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Receita> pesquisar() {
        String querySelect = "SELECT * FROM RECEITAS";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            ResultSet result = stmt.executeQuery();
            List<Receita> lista = new ArrayList<>();
            while (result.next()) {
                Receita receita = new Receita();
                receita.setId(result.getInt("id"));
                receita.setDataCadastro((result.getTimestamp("dataCadastro").toLocalDateTime()));
                receita.setDataPagamento((result.getTimestamp("dataCadastro").toLocalDateTime()));
                receita.setDataVencimento(result.getDate("dataVencimento"));
                receita.setValorRecebido(result.getDouble("valorRecebido"));
                receita.setValorTotal(result.getDouble("valorTotal"));
                receita.setCodVenda(result.getInt("codVenda"));
                lista.add(receita);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Receita> pesquisar(String termo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Receita pesquisar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
