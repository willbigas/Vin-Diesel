/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.dao;

import br.com.vindiesel.interfaces.DaoI;
import br.com.vindiesel.model.TipoTramite;
import br.com.vindiesel.model.Tramite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADJ-PC
 */
public class TipoTramiteDao extends DaoBD implements DaoI<TipoTramite> {

    EntregaDao entregaDao;

    public TipoTramiteDao() {
        super();
        entregaDao = new EntregaDao();
    }

    @Override
    public int inserir(TipoTramite obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean alterar(TipoTramite obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletar(TipoTramite obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(TipoTramite obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TipoTramite> pesquisar() {
        String querySelect = "SELECT * FROM TIPOTRAMITE";
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement(querySelect);
            ResultSet result = stmt.executeQuery();
            List<TipoTramite> lista = new ArrayList<>();
            while (result.next()) {
                TipoTramite tipoTramite = new TipoTramite();
                tipoTramite.setId(result.getInt("id"));
                tipoTramite.setNome(result.getString("nome"));
                lista.add(tipoTramite);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<TipoTramite> pesquisar(String termo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TipoTramite pesquisar(int id) {
        String querySelectComTermo = "SELECT * FROM TIPOTRAMITE WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                TipoTramite tipoTramite = new TipoTramite();
                tipoTramite.setId(result.getInt("id"));
                tipoTramite.setNome(result.getString("nome"));
                return tipoTramite;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
