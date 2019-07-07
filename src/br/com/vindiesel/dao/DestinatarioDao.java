/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.dao;

import br.com.vindiesel.model.Destinatario;
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
public class DestinatarioDao extends GenericDao<Destinatario> implements DaoI<Destinatario> {

    EnderecoDao enderecoDao;

    public DestinatarioDao() {
        super();
        enderecoDao = new EnderecoDao();
    }


    @Override
    public List<Destinatario> pesquisar(String termo) {
        String querySelectComTermo = "SELECT * FROM destinatario WHERE (nome LIKE ? or codigoPessoa like ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + termo + "%");
            stmt.setString(2, "%" + termo + "%");
            ResultSet result = stmt.executeQuery();
            List<Destinatario> lista = new ArrayList<>();
            while (result.next()) {
                Destinatario destinatario = new Destinatario();
                destinatario.setId(result.getInt("id"));
                destinatario.setNome(result.getString("nome"));
                destinatario.setCodigoPessoa(result.getString("codigoPessoa"));
                destinatario.setEndereco(enderecoDao.pesquisar(result.getInt("endereco_id")));
                lista.add(destinatario);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    public List<Destinatario> pesquisarPorCodigoPessoa(String codigoPessoa) {
        String querySelectComTermo = "SELECT * FROM destinatario WHERE (codigoPessoa like ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + codigoPessoa + "%");
            ResultSet result = stmt.executeQuery();
            List<Destinatario> lista = new ArrayList<>();
            while (result.next()) {
                Destinatario destinatario = new Destinatario();
                destinatario.setId(result.getInt("id"));
                destinatario.setNome(result.getString("nome"));
                destinatario.setCodigoPessoa(result.getString("codigoPessoa"));
                destinatario.setEndereco(enderecoDao.pesquisar(result.getInt("endereco_id")));
                lista.add(destinatario);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    public List<Destinatario> pesquisarPorNome(String nome) {
        String querySelectComTermo = "SELECT * FROM destinatario WHERE (nome like ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(querySelectComTermo);
            stmt.setString(1, "%" + nome + "%");
            ResultSet result = stmt.executeQuery();
            List<Destinatario> lista = new ArrayList<>();
            while (result.next()) {
                Destinatario destinatario = new Destinatario();
                destinatario.setId(result.getInt("id"));
                destinatario.setNome(result.getString("nome"));
                destinatario.setCodigoPessoa(result.getString("codigoPessoa"));
                destinatario.setEndereco(enderecoDao.pesquisar(result.getInt("endereco_id")));
                lista.add(destinatario);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean desativar(Destinatario destinatario) {
        String sql = "UPDATE DESTINATARIO SET ativo = false WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, destinatario.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean desativar(int id) {
        String sql = "UPDATE DESTINATARIO SET ativo = false WHERE id = ?";
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
