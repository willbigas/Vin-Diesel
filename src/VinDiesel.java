
import br.com.vindiesel.control.TelaLoginControl;
import br.com.vindiesel.uteis.Mensagem;
import br.com.vindiesel.uteis.Texto;
import de.javasoft.plaf.synthetica.SyntheticaPlainLookAndFeel;
import javax.swing.UIManager;

/**
 *
 * @author William
 */
public class VinDiesel {

    public static void main(String[] args) {
        

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
