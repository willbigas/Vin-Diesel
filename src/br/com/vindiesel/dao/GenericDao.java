package br.com.vindiesel.dao;

import br.com.vindiesel.factory.HibernateUtil;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Will
 */
public class GenericDao<Entidade> extends DaoBD {

    private Class<Entidade> classeGenerica;

    public GenericDao() {
        this.classeGenerica = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    public int inserir(Entidade entidade) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            transacao = sessao.beginTransaction();
            Integer idInserido = (Integer) sessao.save(entidade);
            transacao.commit();
            return idInserido;
        } catch (RuntimeException erro) {
            if (transacao != null) {
                transacao.rollback();
            }
            System.out.println(erro.getMessage());
            return 0;
        } finally {
            sessao.close();
        }
    }

    public List<Entidade> pesquisar() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        transacao = sessao.beginTransaction();
        CriteriaBuilder builder = sessao.getCriteriaBuilder();
        try {

            CriteriaQuery<Entidade> criteria = builder.createQuery(classeGenerica);
            criteria.from(classeGenerica);
            List<Entidade> resultado = sessao.createQuery(criteria).getResultList();
            transacao.commit();
            return resultado;
        } catch (RuntimeException erro) {
            throw erro;
        } finally {
            sessao.close();
        }
    }

    public Entidade pesquisar(int codigo) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();
            Entidade resultado = (Entidade) sessao.get(classeGenerica, codigo);
            transacao.commit();
            return resultado;
        } catch (RuntimeException erro) {
            transacao.rollback();
            throw erro;
        } finally {
            sessao.close();
        }
    }

    public boolean deletar(Entidade entidade) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            transacao = sessao.beginTransaction();
            sessao.delete(entidade);
            transacao.commit();
            return true;
        } catch (RuntimeException erro) {
            if (transacao != null) {
                transacao.rollback();
                return false;
            }
            throw erro;
        } finally {
            sessao.close();
        }
    }
    public boolean deletar(int id) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            transacao = sessao.beginTransaction();
            Entidade resultado = (Entidade) sessao.get(classeGenerica, id);
            sessao.delete(resultado);
            transacao.commit();
            return true;
        } catch (RuntimeException erro) {
            if (transacao != null) {
                transacao.rollback();
                return false;
            }
            throw erro;
        } finally {
            sessao.close();
        }
    }

    public boolean alterar(Entidade entidade) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;

        try {
            transacao = sessao.beginTransaction();
            sessao.update(entidade);
            transacao.commit();
            return true;
        } catch (RuntimeException erro) {
            if (transacao != null) {
                transacao.rollback();
            }
            erro.printStackTrace();
            return false;
        } finally {
            sessao.close();
        }
    }
}
