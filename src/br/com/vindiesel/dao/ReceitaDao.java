/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.dao;

import br.com.vindiesel.interfaces.DaoI;
import br.com.vindiesel.model.Receita;
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
 * @author William
 */
public class ReceitaDao extends DaoBD implements DaoI<Receita> {

    EntregaDao entregaDao;
    FormaPagamentoDao formaPagamentoDao;

    public ReceitaDao() {
        super();
        entregaDao = new EntregaDao();
        formaPagamentoDao = new FormaPagamentoDao();
    }

    @Override
    public int inserir(Receita receita) {
        String queryInsert = "INSERT INTO receitas (DATACADASTRO, DATAPAGAMENTO, DATAVENCIMENTO, VALORTOTAL, VALORRECEBIDO, ENTREGA_ID, FORMAPAGAMENTO_ID) VALUES(?, ?, ?, ?, ?, ?, ?)";
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
            stmt.setInt(6, receita.getEntrega().getId());
            stmt.setInt(7, receita.getFormaPagamento().getId());
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
        String queryUpdate = "UPDATE receita SET dataCadastro = ?, dataPagamento = ?, dataVencimento = ?,"
                + " valorRecebido = ?, valorTotal = ?, ENTREGA_ID = ?, FORMAPAGAMENTO_ID WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryUpdate);
            stmt.setTimestamp(1, Timestamp.valueOf(receita.getDataCadastro()));
            stmt.setTimestamp(2, Timestamp.valueOf(receita.getDataPagamento()));
            stmt.setDate(3, new Date(receita.getDataVencimento().getTime()));
            stmt.setDouble(4, receita.getValorRecebido());
            stmt.setDouble(5, receita.getValorTotal());
            stmt.setInt(6, receita.getEntrega().getId());
            stmt.setInt(7, receita.getFormaPagamento().getId());
            stmt.setInt(8, receita.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(Receita receita) {
        String queryDelete = "DELETE FROM RECEITA WHERE ID = ?";
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
        String queryDelete = "DELETE FROM RECEITA WHERE ID = ?";
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
        String querySelect = "SELECT * FROM RECEITA";
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
                receita.setEntrega(entregaDao.pesquisar(result.getInt("entrega_id")));
                receita.setFormaPagamento(formaPagamentoDao.pesquisar(result.getInt("formaPagamento_id")));
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
