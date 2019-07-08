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
public class EntregaDao extends GenericDao<Entrega> implements DaoI<Entrega> {

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
    public boolean desativar(Entrega obj) {
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(int id) {
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public List<Entrega> pesquisar(Boolean entregue) {
        String querySelect = "SELECT * FROM ENTREGA WHERE (ENTREGUE = " + entregue + " ";
        if (entregue = null) {
            querySelect = "SELECT * FROM ENTREGA";
        }
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

    public List<Entrega> pesquisar(String termo, Boolean entregue) {
        String queryAvancada = "SELECT * FROM ENTREGA  AS E INNER JOIN ENCOMENDA AS EC ON E.ENCOMENDA_ID = EC.ID "
                + " LEFT JOIN REMETENTE AS R ON E.REMETENTE_ID = R.ID"
                + " LEFT JOIN DESTINATARIO AS D ON E.DESTINATARIO_ID = D.ID "
                + " WHERE(EC.CODIGORASTREIO like ? or  R.NOME like ? or  D.NOME like ? ) AND ENTREGUE = " + entregue + " ";
        if (entregue == null) {
            queryAvancada = "SELECT * FROM ENTREGA  AS E INNER JOIN ENCOMENDA AS EC ON E.ENCOMENDA_ID = EC.ID "
                    + " LEFT JOIN REMETENTE AS R ON E.REMETENTE_ID = R.ID"
                    + " LEFT JOIN DESTINATARIO AS D ON E.DESTINATARIO_ID = D.ID "
                    + " WHERE(EC.CODIGORASTREIO like ? or  R.NOME like ? or  D.NOME like ? )";
        }
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


    public List<Entrega> pesquisarPorDataEntrega(String dataEntrega) {
        String query = "SELECT * FROM ENTREGA WHERE(dataEntrega like ?)";
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

    public List<Entrega> pesquisarPorEncomenda(String codigoEncomenda) {
        String queryAvancada = "SELECT * FROM ENTREGA  AS E INNER JOIN ENCOMENDA AS EC ON E.ENCOMENDA_ID = EC.ID "
                + " INNER JOIN REMETENTE AS R ON E.REMETENTE_ID = R.ID"
                + " INNER JOIN DESTINATARIO AS D ON E.DESTINATARIO_ID = D.ID "
                + " WHERE(EC.CODIGORASTREIO like ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryAvancada);
            stmt.setString(1, "%" + codigoEncomenda + "%");
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

    public List<Entrega> pesquisarPorCodigoRemetente(String codigoRemetente) {
        String queryAvancada = "SELECT * FROM ENTREGA  AS E INNER JOIN ENCOMENDA AS EC ON E.ENCOMENDA_ID = EC.ID "
                + " INNER JOIN REMETENTE AS R ON E.REMETENTE_ID = R.ID"
                + " INNER JOIN DESTINATARIO AS D ON E.DESTINATARIO_ID = D.ID "
                + " WHERE(R.CODIGOPESSOA like ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryAvancada);
            stmt.setString(1, "%" + codigoRemetente + "%");
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

    public List<Entrega> pesquisarPorCodigoDestinatario(String codigoDestinatario) {
        String queryAvancada = "SELECT * FROM ENTREGA  AS E INNER JOIN ENCOMENDA AS EC ON E.ENCOMENDA_ID = EC.ID "
                + " INNER JOIN REMETENTE AS R ON E.REMETENTE_ID = R.ID"
                + " INNER JOIN DESTINATARIO AS D ON E.DESTINATARIO_ID = D.ID "
                + " WHERE(D.CODIGOPESSOA like ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(queryAvancada);
            stmt.setString(1, "%" + codigoDestinatario + "%");
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
