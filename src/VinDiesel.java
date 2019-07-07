
import br.com.vindiesel.control.TelaLoginControl;
import br.com.vindiesel.dao.DestinatarioDao;
import br.com.vindiesel.factory.HibernateUtil;
import br.com.vindiesel.model.Destinatario;
import br.com.vindiesel.uteis.Mensagem;
import br.com.vindiesel.uteis.Texto;
import de.javasoft.plaf.synthetica.SyntheticaPlainLookAndFeel;
import java.util.List;
import javax.swing.UIManager;
import org.hibernate.Session;

/**
 *
 * @author William
 */
public class VinDiesel {

    public static void main(String[] args) {

        DestinatarioDao destinatarioDao = new DestinatarioDao();
       List<Destinatario> destinatarios = destinatarioDao.pesquisar();
       
        for (Destinatario destinatario : destinatarios) {
            System.out.println(destinatario);
        }
        

//        try {
//            UIManager.put("Synthetica.window.decoration", Boolean.FALSE);
//            UIManager.setLookAndFeel(new SyntheticaPlainLookAndFeel());
//        } catch (Exception erro) {
//            Mensagem.erro(Texto.ERRO_INTERFACE_SYNTHETICA);
//        }
//        TelaLoginControl telaLogin = new TelaLoginControl();
//        telaLogin.chamarTelaLoginAction();
    }
}
