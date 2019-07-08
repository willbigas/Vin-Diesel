
import br.com.vindiesel.control.TelaLoginControl;
import br.com.vindiesel.dao.EnderecoDao;
import br.com.vindiesel.model.Endereco;
import br.com.vindiesel.uteis.Mensagem;
import br.com.vindiesel.uteis.Texto;
import de.javasoft.plaf.synthetica.SyntheticaPlainLookAndFeel;
import java.util.List;
import javax.swing.UIManager;

/**
 *
 * @author William
 */
public class VinDiesel {

    public static void main(String[] args) {
        
//        EnderecoDao enderecoDao = new EnderecoDao();
//        List<Endereco>  enderecos =  enderecoDao.pesquisar("748");
//        System.out.println(enderecos);
        

        try {
            UIManager.put("Synthetica.window.decoration", Boolean.FALSE);
            UIManager.setLookAndFeel(new SyntheticaPlainLookAndFeel());
        } catch (Exception erro) {
            Mensagem.erro(Texto.ERRO_INTERFACE_SYNTHETICA);
        }
        TelaLoginControl telaLogin = new TelaLoginControl();
        telaLogin.chamarTelaLoginAction();
    }
}
