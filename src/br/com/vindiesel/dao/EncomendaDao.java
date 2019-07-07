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
public class EncomendaDao extends GenericDao<Encomenda> implements DaoI<Encomenda> {

    DimensaoDao dimensaoDao;

    public EncomendaDao() {
        super();
        dimensaoDao = new DimensaoDao();
    }



    @Override
    public List<Encomenda> pesquisar(String termo) {
        String querySelectComTermo = "SELECT * FROM ENCOMENDA WHERE (CODIGORASTREIO LIKE ? or valorNotaFiscal LIKE ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + termo + "%");
            stmt.setString(2, "%" + termo + "%");
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
    public boolean desativar(Encomenda produto) {
        String sql = "UPDATE ENCOMENDA SET ativo = false WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, produto.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean desativar(int id) {
        String sql = "UPDATE ENCOMENDA SET ativo = false WHERE id = ?";
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
