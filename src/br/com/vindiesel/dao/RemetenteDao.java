package br.com.vindiesel.dao;

import br.com.vindiesel.model.Remetente;
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
public class RemetenteDao extends GenericDao<Remetente> implements DaoI<Remetente> {

    EnderecoDao enderecoDao;

    public RemetenteDao() {
        super();
        enderecoDao = new EnderecoDao();
    }




    @Override
    public boolean desativar(Remetente remetente) {
        String sql = "UPDATE REMETENTE SET ativo = false WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, remetente.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean desativar(int id) {
        String sql = "UPDATE REMETENTE SET ativo = false WHERE id = ?";
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
    public List<Remetente> pesquisar(String termo) {
        String querySelectComTermo = "SELECT * FROM remetente WHERE (nome LIKE ? or telefone LIKE ? or codigoPessoa like ? )";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + termo + "%");
            stmt.setString(2, "%" + termo + "%");
            stmt.setString(3, "%" + termo + "%");
            ResultSet result = stmt.executeQuery();
            List<Remetente> lista = new ArrayList<>();
            while (result.next()) {
                Remetente remetente = new Remetente();
                remetente.setId(result.getInt("id"));
                remetente.setNome(result.getString("nome"));
                remetente.setCodigoPessoa(result.getString("codigoPessoa"));
                remetente.setTelefone(result.getString("telefone"));
                remetente.setEmail(result.getString("email"));
                remetente.setEndereco(enderecoDao.pesquisar(result.getInt("endereco_id")));
                lista.add(remetente);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    
     public List<Remetente> pesquisarPorCodigoPessoa(String codigoPessoa) {
        String querySelectComTermo = "SELECT * FROM remetente WHERE (codigoPessoa like ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + codigoPessoa + "%");
            ResultSet result = stmt.executeQuery();
            List<Remetente> lista = new ArrayList<>();
            while (result.next()) {
                Remetente remetente = new Remetente();
                remetente.setId(result.getInt("id"));
                remetente.setNome(result.getString("nome"));
                remetente.setCodigoPessoa(result.getString("codigoPessoa"));
                remetente.setTelefone(result.getString("telefone"));
                remetente.setEmail(result.getString("email"));
                remetente.setEndereco(enderecoDao.pesquisar(result.getInt("endereco_id")));
                lista.add(remetente);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
     public List<Remetente> pesquisarPorNome(String nome) {
        String querySelectComTermo = "SELECT * FROM remetente WHERE (nome like ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + nome + "%");
            ResultSet result = stmt.executeQuery();
            List<Remetente> lista = new ArrayList<>();
            while (result.next()) {
                Remetente remetente = new Remetente();
                remetente.setId(result.getInt("id"));
                remetente.setNome(result.getString("nome"));
                remetente.setCodigoPessoa(result.getString("codigoPessoa"));
                remetente.setTelefone(result.getString("telefone"));
                remetente.setEmail(result.getString("email"));
                remetente.setEndereco(enderecoDao.pesquisar(result.getInt("endereco_id")));
                lista.add(remetente);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
