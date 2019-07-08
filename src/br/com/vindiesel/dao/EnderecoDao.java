/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.dao;

import br.com.vindiesel.factory.HibernateUtil;
import br.com.vindiesel.model.Endereco;
import br.com.vindiesel.interfaces.DaoI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ADJ-PC
 */
public class EnderecoDao extends GenericDao<Endereco> implements DaoI<Endereco> {

    public EnderecoDao() {
        super();
    }


    @Override
    public boolean desativar(Endereco obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<Endereco> pesquisar(String termo) {
        String querySelectComTermo = "FROM Endereco WHERE (cep LIKE :termo or rua LIKE :termo or  cidade LIKE :termo or bairro LIKE :termo or estado LIKE :termo or numero LIKE :termo)";
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        transacao = sessao.beginTransaction();
        try {
            List<Endereco> resultado = (List<Endereco>) sessao.createQuery(querySelectComTermo)
                    .setParameter("termo", "%" + termo + "%")
                    .setParameter("termo", "%" + termo + "%")
                    .setParameter("termo", "%" + termo + "%")
                    .setParameter("termo", "%" + termo + "%")
                    .setParameter("termo", "%" + termo + "%")
                    .setParameter("termo", "%" + termo + "%")
                    .getResultList();
            transacao.commit();
            return resultado;
        } catch (RuntimeException erro) {
            throw erro;

        } finally {
            sessao.close();
        }
    }

}
