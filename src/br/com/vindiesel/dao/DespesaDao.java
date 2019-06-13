/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.dao;

import br.com.vindiesel.model.Despesa;
import br.com.vindiesel.interfaces.DaoI;
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
public class DespesaDao extends Dao implements DaoI<Despesa> {

    TipoDespesaDao tipoDespesaDao;

    public DespesaDao() {
        super();
        tipoDespesaDao = new TipoDespesaDao();
    }

    @Override
    public int inserir(Despesa despesa) {
        String queryInsert = "INSERT INTO despesas(DATACADASTRO, DATAPAGAMENTO, "
                + "DATAVENCIMENTO, VALORPAGO, VALORPAGORESTANTE , PAGO , CODENTRADA, FK_TIPODESPESA ) VALUES(?, ?, ?, ?, ?, ? , ? , ?)";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(queryInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setTimestamp(1, Timestamp.valueOf(despesa.getDataCadastro()));
            if (despesa.getDataPagamento() == null) {
                stmt.setNull(2, Types.TIMESTAMP);
            } else {
                stmt.setTimestamp(2, Timestamp.valueOf(despesa.getDataPagamento()));
            }
            
            stmt.setDate(3, new Date(despesa.getDataVencimento().getTime()));
            
            if (despesa.getValorPago() == null) {
                stmt.setNull(4, Types.DOUBLE);
            } else {
                stmt.setDouble(4, despesa.getValorPago());
            }
            stmt.setDouble(5, despesa.getValorPagoRestante());
            stmt.setBoolean(6, despesa.getPago());
            stmt.setInt(7, despesa.getCodEntrada());
            stmt.setInt(8, despesa.getTipoDespesa().getId());
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
    public boolean alterar(Despesa despesa) {
        String queryUpdate = "UPDATE despesas SET dataCadastro = ?, dataPagamento = ?"
                + ", dataVencimento = ?, valorPago = ? , valorPagoRestante = ? , pago = ? , codEntrada = ?, fk_tipoDespesa = ? WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryUpdate);
            stmt.setTimestamp(1, Timestamp.valueOf(despesa.getDataCadastro()));
            stmt.setTimestamp(2, Timestamp.valueOf(despesa.getDataPagamento()));
            stmt.setDate(3, new Date(despesa.getDataVencimento().getTime()));
            stmt.setDouble(4, despesa.getValorPago());
            stmt.setDouble(5, despesa.getValorPagoRestante());
            stmt.setBoolean(6, despesa.getPago());
            stmt.setInt(7, despesa.getCodEntrada());
            stmt.setInt(8, despesa.getTipoDespesa().getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(Despesa despesa) {
        String queryDelete = "DELETE FROM DESPESAS WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryDelete);
            stmt.setInt(1, despesa.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(int id) {
        String queryDelete = "DELETE FROM DESPESAS WHERE ID = ?";
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
    public boolean desativar(Despesa despesa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Despesa> pesquisar() {
        String querySelect = "SELECT * FROM DESPESAS";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            ResultSet result = stmt.executeQuery();
            List<Despesa> lista = new ArrayList<>();
            while (result.next()) {
                Despesa despesa = new Despesa();
                despesa.setId(result.getInt("id"));
                despesa.setDataCadastro((result.getTimestamp("dataCadastro").toLocalDateTime()));
                despesa.setDataPagamento((result.getTimestamp("dataPagamento").toLocalDateTime()));
                despesa.setDataVencimento(result.getDate("dataVencimento"));
                despesa.setValorPago(result.getDouble("valorPago"));
                despesa.setValorPagoRestante(result.getDouble("valorPagoRestante"));
                despesa.setPago(result.getBoolean("pago"));
                despesa.setCodEntrada(result.getInt("codEntrada"));
                despesa.setTipoDespesa(tipoDespesaDao.pesquisar(result.getInt("fk_tipoDespesa")));
                lista.add(despesa);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Despesa> pesquisar(String termo) {
        String querySelectComTermo = "SELECT * FROM despesas WHERE (codEntrada LIKE ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + termo + "%");
            ResultSet result = stmt.executeQuery();
            List<Despesa> lista = new ArrayList<>();
            while (result.next()) {
                Despesa despesa = new Despesa();
                despesa.setId(result.getInt("id"));
                despesa.setDataCadastro((result.getTimestamp("dataCadastro").toLocalDateTime()));
                despesa.setDataPagamento((result.getTimestamp("dataPagamento").toLocalDateTime()));
                despesa.setDataVencimento(result.getDate("dataVencimento"));
                despesa.setValorPago(result.getDouble("valorPago"));
                despesa.setValorPagoRestante(result.getDouble("valorPagoRestante"));
                despesa.setPago(result.getBoolean("pago"));
                despesa.setCodEntrada(result.getInt("codEntrada"));
                despesa.setTipoDespesa(tipoDespesaDao.pesquisar(result.getInt("fk_tipoDespesa")));
                lista.add(despesa);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Despesa pesquisar(int id) {
        String querySelectComTermo = "SELECT * FROM despesas WHERE = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                Despesa despesa = new Despesa();
                despesa.setId(result.getInt("id"));
                despesa.setDataCadastro((result.getTimestamp("dataCadastro").toLocalDateTime()));
                despesa.setDataPagamento((result.getTimestamp("dataPagamento").toLocalDateTime()));
                despesa.setDataVencimento(result.getDate("dataVencimento"));
                despesa.setValorPago(result.getDouble("valorPago"));
                despesa.setValorPagoRestante(result.getDouble("valorPagoRestante"));
                despesa.setPago(result.getBoolean("pago"));
                despesa.setCodEntrada(result.getInt("codEntrada"));
                despesa.setTipoDespesa(tipoDespesaDao.pesquisar(result.getInt("fk_tipoDespesa")));
                return despesa;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    public List<Despesa> pesquisar(Boolean pago) {
        String querySelect = "SELECT * FROM DESPESAS where pago = ?";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            stmt.setBoolean(1, pago);
            ResultSet result = stmt.executeQuery();
            List<Despesa> lista = new ArrayList<>();
            while (result.next()) {
                Despesa despesa = new Despesa();
                despesa.setId(result.getInt("id"));
                despesa.setDataCadastro((result.getTimestamp("dataCadastro").toLocalDateTime()));
                despesa.setDataPagamento((result.getTimestamp("dataPagamento").toLocalDateTime()));
                despesa.setDataVencimento(result.getDate("dataVencimento"));
                despesa.setValorPago(result.getDouble("valorPago"));
                despesa.setValorPagoRestante(result.getDouble("valorPagoRestante"));
                despesa.setPago(result.getBoolean("pago"));
                despesa.setCodEntrada(result.getInt("codEntrada"));
                despesa.setTipoDespesa(tipoDespesaDao.pesquisar(result.getInt("fk_tipoDespesa")));
                lista.add(despesa);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    
    
    
}
