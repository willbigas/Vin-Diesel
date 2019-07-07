package br.com.vindiesel.dao;

import br.com.vindiesel.interfaces.DaoI;
import br.com.vindiesel.model.Receita;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author William
 */
public class ReceitaDao extends GenericDao<Receita> implements DaoI<Receita> {

    EntregaDao entregaDao;
    FormaPagamentoDao formaPagamentoDao;

    public ReceitaDao() {
        super();
        entregaDao = new EntregaDao();
        formaPagamentoDao = new FormaPagamentoDao();
    }



    @Override
    public boolean desativar(Receita obj) {
        String sql = "UPDATE RECEITA SET ativo = false WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean desativar(int id) {
        String sql = "UPDATE RECEITA SET ativo = false WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }


    @Override
    public List<Receita> pesquisar(String termo) {
        String querySelectComTermo = "SELECT * FROM receita WHERE (dataVencimento LIKE ? or valorRecebido LIKE ? or valorTotal like ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + termo + "%");
            stmt.setString(2, "%" + termo + "%");
            stmt.setString(3, "%" + termo + "%");
            ResultSet result = stmt.executeQuery();
            List<Receita> lista = new ArrayList<>();
            while (result.next()) {
                Receita receita = new Receita();
                receita.setId(result.getInt("id"));
                receita.setDataCadastro((result.getDate("dataCadastro")));
                receita.setDataPagamento((result.getDate("dataPagamento")));
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

    public List<Receita> pesquisarPorCodigoEntrega(String codigoEntrega) {
        String querySelectComTermo = "SELECT * FROM receita WHERE (entrega_id = ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, codigoEntrega);
            ResultSet result = stmt.executeQuery();
            List<Receita> lista = new ArrayList<>();
            while (result.next()) {
                Receita receita = new Receita();
                receita.setId(result.getInt("id"));
                receita.setDataCadastro((result.getDate("dataCadastro")));
                receita.setDataPagamento((result.getDate("dataPagamento")));
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

    public List<Receita> pesquisarPorDataEfetivacao(String dataEfeitvacao) {
        String querySelectComTermo = "SELECT * FROM receita WHERE (dataCadastro like ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + dataEfeitvacao + "%");
            ResultSet result = stmt.executeQuery();
            List<Receita> lista = new ArrayList<>();
            while (result.next()) {
                Receita receita = new Receita();
                receita.setId(result.getInt("id"));
                receita.setDataCadastro((result.getDate("dataCadastro")));
                receita.setDataPagamento((result.getDate("dataPagamento")));
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

}
