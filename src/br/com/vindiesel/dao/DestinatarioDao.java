/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vindiesel.dao;

import br.com.vindiesel.factory.HibernateUtil;
import br.com.vindiesel.model.Destinatario;
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
        String queryHql = "from Destinatario where (nome like :termo or codigoPessoa like :termo)";
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        transacao = sessao.beginTransaction();
        try {
            List<Destinatario> resultado = (List<Destinatario>) (Destinatario) sessao.createQuery(queryHql)
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

    public List<Destinatario> pesquisarPorCodigoPessoa(String codigoPessoa) {
        String queryHql = "from Destinatario where (codigoPessoa like :termo)";
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        transacao = sessao.beginTransaction();
        try {
            List<Destinatario> resultado = sessao.createQuery(queryHql)
                    .setParameter("termo", "%" + codigoPessoa + "%")
                    .getResultList();
            transacao.commit();
            return resultado;
        } catch (RuntimeException erro) {
            throw erro;

        } finally {
            sessao.close();
        }
    }

    public List<Destinatario> pesquisarPorNome(String nome) {
        String queryHql = "from Destinatario where (nome like :termo)";
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        transacao = sessao.beginTransaction();
        try {
            List<Destinatario> resultado = sessao.createQuery(queryHql)
                    .setParameter("termo", "%" + nome + "%")
                    .getResultList();
            transacao.commit();
            return resultado;
        } catch (RuntimeException erro) {
            throw erro;

        } finally {
            sessao.close();
        }
    }

    @Override
    public boolean desativar(Destinatario destinatario) {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public boolean desativar(int id) {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
