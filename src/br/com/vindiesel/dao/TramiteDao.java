/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.dao;

import br.com.vindiesel.interfaces.DaoI;
import br.com.vindiesel.model.Entrega;
import br.com.vindiesel.model.Tramite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADJ-PC
 */
public class TramiteDao extends GenericDao<Tramite> implements DaoI<Tramite> {

    EntregaDao entregaDao;
    TipoTramiteDao tipoTramiteDao;

    public TramiteDao() {
        super();
        entregaDao = new EntregaDao();
        tipoTramiteDao = new TipoTramiteDao();
    }


    
    @Override
    public boolean desativar(Tramite obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<Tramite> pesquisar(String termo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public List<Tramite> pesquisarTramitesPorEntrega(Entrega entrega) {
        String querySelect = "SELECT * FROM TRAMITE where entrega_id = ? ";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            stmt.setInt(1, entrega.getId());
            ResultSet result = stmt.executeQuery();
            List<Tramite> lista = new ArrayList<>();
            while (result.next()) {
                Tramite tramite = new Tramite();
                tramite.setId(result.getInt("id"));
                tramite.setDataHora((result.getTimestamp("dataHora").toLocalDateTime()));
                tramite.setNome(result.getString("nome"));
                tramite.setObservacao(result.getString("observacao"));
                tramite.setEntrega(entregaDao.pesquisar(result.getInt("entrega_id")));
                tramite.setTipoTramite(tipoTramiteDao.pesquisar(result.getInt("tipoTramite_id")));
                lista.add(tramite);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
