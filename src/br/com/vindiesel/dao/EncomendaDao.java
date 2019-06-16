package br.com.vindiesel.dao;

import br.com.vindiesel.model.Encomenda;
import br.com.vindiesel.interfaces.DaoI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author William
 */
public class EncomendaDao extends DaoBD implements DaoI<Encomenda> {

    DimensaoDao dimensaoDao;

    public EncomendaDao() {
        super();
        dimensaoDao = new DimensaoDao();
    }

    @Override
    public int inserir(Encomenda encomenda) {
        String queryInsert = "INSERT INTO ENCOMENDA (CODIGORASTREIO, PESO, VALORNOTAFISCAL , DIMENSAO_ID) VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(queryInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, encomenda.getCodigoRastreio());
            stmt.setDouble(2, encomenda.getPeso());
            stmt.setDouble(3, encomenda.getValorNotaFiscal());
            stmt.setInt(4, encomenda.getDimensao().getId());
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
    public boolean alterar(Encomenda encomenda) {
        String queryUpdate = "UPDATE ENCOMENDA SET CODIGORASTREIO = ?, PESO = ?, VALORNOTAFISCAL = ?, DIMENSAO_ID = ? "
                + "WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryUpdate);
            stmt.setString(1, encomenda.getCodigoRastreio());
            stmt.setDouble(2, encomenda.getPeso());
            stmt.setDouble(3, encomenda.getValorNotaFiscal());
            stmt.setInt(4, encomenda.getDimensao().getId());
            stmt.setInt(5, encomenda.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(Encomenda obj) {
        String queryDelete = "DELETE FROM ENCOMENDA WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryDelete);
            stmt.setInt(1, obj.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(int id) {
        String queryDelete = "DELETE FROM ENCOMENDA WHERE ID = ?";
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
    public List<Encomenda> pesquisar() {
        String querySelect = "SELECT * FROM ENCOMENDA";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            ResultSet result = stmt.executeQuery();
            List<Encomenda> lista = new ArrayList<>();
            while (result.next()) {
                Encomenda encomenda = new Encomenda();
                encomenda.setId(result.getInt("id"));
                encomenda.setCodigoRastreio(result.getString("codigoRastreio"));
                encomenda.setPeso(result.getDouble("peso"));
                encomenda.setValorNotaFiscal(result.getDouble("valorNotaFiscal"));
                encomenda.setDimensao(dimensaoDao.pesquisar(result.getInt("dimensao_id")));
                lista.add(encomenda);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Encomenda> pesquisar(String codigoRastreio) {
        String querySelectComTermo = "SELECT * FROM ENCOMENDA WHERE CODIGORASTREIO LIKE ? ";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + codigoRastreio + "%");
            ResultSet result = stmt.executeQuery();
            List<Encomenda> lista = new ArrayList<>();
            while (result.next()) {
                Encomenda encomenda = new Encomenda();
                encomenda.setId(result.getInt("id"));
                encomenda.setCodigoRastreio(result.getString("codigoRastreio"));
                encomenda.setPeso(result.getDouble("peso"));
                encomenda.setValorNotaFiscal(result.getDouble("valorNotaFiscal"));
                encomenda.setDimensao(dimensaoDao.pesquisar(result.getInt("dimensao_id")));
                lista.add(encomenda);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Encomenda pesquisar(int id) {
        String querySelectComTermo = "SELECT * FROM ENCOMENDA WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                Encomenda encomenda = new Encomenda();
                encomenda.setId(result.getInt("id"));
                encomenda.setCodigoRastreio(result.getString("codigoRastreio"));
                encomenda.setPeso(result.getDouble("peso"));
                encomenda.setValorNotaFiscal(result.getDouble("valorNotaFiscal"));
                encomenda.setDimensao(dimensaoDao.pesquisar(result.getInt("dimensao_id")));
                return encomenda;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

    @Override
    public boolean desativar(Encomenda produto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
