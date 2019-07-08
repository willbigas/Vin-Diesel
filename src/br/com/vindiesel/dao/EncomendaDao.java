package br.com.vindiesel.dao;

import br.com.vindiesel.factory.HibernateUtil;
import br.com.vindiesel.model.Encomenda;
import br.com.vindiesel.interfaces.DaoI;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        String querySelectComTermo = "from Encomenda where (CODIGORASTREIO LIKE :termo)";
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        transacao = sessao.beginTransaction();
        try {
            List<Encomenda> resultado = (List<Encomenda>) sessao.createQuery(querySelectComTermo)
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

    @Override
    public boolean desativar(Encomenda produto) {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desativar(int id) {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
