package br.com.vindiesel.dao;

import br.com.vindiesel.model.Entrega;
import br.com.vindiesel.interfaces.DaoI;
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
public class EntregaDao extends DaoBD implements DaoI<Entrega> {

    RemetenteDao remetenteDao;
    DestinatarioDao destinatarioDao;
    EncomendaDao encomendaDao;

    public EntregaDao() {
        super();
        remetenteDao = new RemetenteDao();
        destinatarioDao = new DestinatarioDao();
        encomendaDao = new EncomendaDao();
    }

    @Override
    public int inserir(Entrega entrega) {
        String queryInsert = "INSERT INTO entrega(valorTotal, dataCadastro, dataEntrega , entregue , "
                + " remetente_id , destinatario_id , encomenda_id) VALUES(?, ?, ? , ?, ? ,? , ?)";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(queryInsert, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, entrega.getValorTotal());
            stmt.setTimestamp(2, new Timestamp(entrega.getDataCadastro().getTime()));
            if (entrega.getDataEntrega() == null) {
                stmt.setNull(3, Types.TIMESTAMP);
            } else {
                stmt.setTimestamp(3, new Timestamp(entrega.getDataEntrega().getTime()));
            }
            stmt.setBoolean(4, entrega.getEntregue());
            stmt.setInt(5, entrega.getRemetente().getId());
            stmt.setInt(6, entrega.getDestinatario().getId());
            stmt.setInt(7, entrega.getEncomenda().getId());
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
    public boolean alterar(Entrega entrega) {
        String queryUpdate = "UPDATE entrega SET valorTotal = ?, dataCadastro = ?, dataEntrega = ? , "
                + " entregue = ? , remetente_id = ? , destinatario_id = ? , encomenda_id = ?  WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryUpdate);
            stmt.setDouble(1, entrega.getValorTotal());
            stmt.setTimestamp(2, new Timestamp(entrega.getDataCadastro().getTime()));
            stmt.setTimestamp(3, new Timestamp(entrega.getDataEntrega().getTime()));
            stmt.setBoolean(4, entrega.getEntregue());
            stmt.setInt(5, entrega.getRemetente().getId());
            stmt.setInt(6, entrega.getDestinatario().getId());
            stmt.setInt(7, entrega.getEncomenda().getId());
            stmt.setInt(8, entrega.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(Entrega entrega) {
        String queryDelete = "DELETE FROM ENTREGA WHERE ID = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryDelete);
            stmt.setInt(1, entrega.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(int id) {
        String queryDelete = "DELETE FROM ENTREGA WHERE ID = ?";
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
    public boolean desativar(Entrega obj) {
        String sql = "UPDATE ENTREGA SET ativo = false WHERE id = ?";
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
        String sql = "UPDATE ENTREGA SET ativo = false WHERE id = ?";
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
    public List<Entrega> pesquisar() {
        String querySelect = "SELECT * FROM ENTREGA";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            ResultSet result = stmt.executeQuery();
            List<Entrega> lista = new ArrayList<>();
            while (result.next()) {
                Entrega entrega = new Entrega();
                entrega.setId(result.getInt("id"));
                entrega.setValorTotal(result.getDouble("valorTotal"));
                entrega.setDataCadastro((result.getDate("dataCadastro")));
                if ((result.getDate("dataEntrega")) == null) {
                    entrega.setDataEntrega(null);
                } else {
                    entrega.setDataEntrega(result.getDate("dataEntrega"));
                }
                entrega.setEntregue(result.getBoolean("entregue"));
                entrega.setRemetente(remetenteDao.pesquisar(result.getInt("remetente_id")));
                entrega.setDestinatario(destinatarioDao.pesquisar(result.getInt("destinatario_id")));
                entrega.setEncomenda(encomendaDao.pesquisar(result.getInt("encomenda_id")));
                lista.add(entrega);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Entrega> pesquisar(String termo) {
        String queryAvancada = "SELECT * FROM ENTREGA  AS E INNER JOIN ENCOMENDA AS EC ON E.ENCOMENDA_ID = EC.ID "
                + " INNER JOIN REMETENTE AS R ON E.REMETENTE_ID = R.ID"
                + " INNER JOIN DESTINATARIO AS D ON E.DESTINATARIO_ID = D.ID "
                + " WHERE(EC.CODIGORASTREIO like ? or  R.NOME like ? or  D.NOME like ? )";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryAvancada);
            stmt.setString(1, "%" + termo + "%");
            stmt.setString(2, "%" + termo + "%");
            stmt.setString(3, "%" + termo + "%");
            ResultSet result = stmt.executeQuery();
            List<Entrega> lista = new ArrayList<>();
            while (result.next()) {
                Entrega entrega = new Entrega();
                entrega.setId(result.getInt("id"));
                entrega.setValorTotal(result.getDouble("valorTotal"));
                entrega.setDataCadastro((result.getDate("dataCadastro")));
                if ((result.getDate("dataEntrega")) == null) {
                    entrega.setDataEntrega(null);
                } else {
                    entrega.setDataEntrega(result.getDate("dataEntrega"));
                }
                entrega.setEntregue(result.getBoolean("entregue"));
                entrega.setRemetente(remetenteDao.pesquisar(result.getInt("remetente_id")));
                entrega.setDestinatario(destinatarioDao.pesquisar(result.getInt("destinatario_id")));
                entrega.setEncomenda(encomendaDao.pesquisar(result.getInt("encomenda_id")));
                lista.add(entrega);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Entrega pesquisar(int id) {
        String querySelectComTermo = "SELECT * FROM ENTREGA WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                Entrega entrega = new Entrega();
                entrega.setId(result.getInt("id"));
                entrega.setValorTotal(result.getDouble("valorTotal"));
                entrega.setDataCadastro((result.getDate("dataCadastro")));
                if ((result.getDate("dataEntrega")) == null) {
                    entrega.setDataEntrega(null);
                } else {
                    entrega.setDataEntrega(result.getDate("dataEntrega"));
                }
                entrega.setEntregue(result.getBoolean("entregue"));
                entrega.setRemetente(remetenteDao.pesquisar(result.getInt("remetente_id")));
                entrega.setDestinatario(destinatarioDao.pesquisar(result.getInt("destinatario_id")));
                entrega.setEncomenda(encomendaDao.pesquisar(result.getInt("encomenda_id")));
                return entrega;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<Entrega> pesquisarPorDataEntrega(String dataEntrega) {
        String query = "SELECT * FROM ENTREGA WHERE(dataEntrega = ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, "%" + dataEntrega + "%");
            ResultSet result = stmt.executeQuery();
            List<Entrega> lista = new ArrayList<>();
            while (result.next()) {
                Entrega entrega = new Entrega();
                entrega.setId(result.getInt("id"));
                entrega.setValorTotal(result.getDouble("valorTotal"));
                entrega.setDataCadastro((result.getDate("dataCadastro")));
                if ((result.getDate("dataEntrega")) == null) {
                    entrega.setDataEntrega(null);
                } else {
                    entrega.setDataEntrega(result.getDate("dataEntrega"));
                }
                entrega.setEntregue(result.getBoolean("entregue"));
                entrega.setRemetente(remetenteDao.pesquisar(result.getInt("remetente_id")));
                entrega.setDestinatario(destinatarioDao.pesquisar(result.getInt("destinatario_id")));
                entrega.setEncomenda(encomendaDao.pesquisar(result.getInt("encomenda_id")));
                lista.add(entrega);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<Entrega> pesquisarPorDataCadastro(String dataCadastro) {
        String query = "SELECT * FROM ENTREGA WHERE(dataCadastro like ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, "%" + dataCadastro + "%");
            ResultSet result = stmt.executeQuery();
            List<Entrega> lista = new ArrayList<>();
            while (result.next()) {
                Entrega entrega = new Entrega();
                entrega.setId(result.getInt("id"));
                entrega.setValorTotal(result.getDouble("valorTotal"));
                entrega.setDataCadastro((result.getDate("dataCadastro")));
                if ((result.getDate("dataEntrega")) == null) {
                    entrega.setDataEntrega(null);
                } else {
                    entrega.setDataEntrega(result.getDate("dataEntrega"));
                }
                entrega.setEntregue(result.getBoolean("entregue"));
                entrega.setRemetente(remetenteDao.pesquisar(result.getInt("remetente_id")));
                entrega.setDestinatario(destinatarioDao.pesquisar(result.getInt("destinatario_id")));
                entrega.setEncomenda(encomendaDao.pesquisar(result.getInt("encomenda_id")));
                lista.add(entrega);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
