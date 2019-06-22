
import br.com.vindiesel.control.TelaLoginControl;
import de.javasoft.plaf.synthetica.SyntheticaPlainLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel;
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
            erro.printStackTrace();
        }
//        try {
////            InterfaceJanela.MudaSwingParaPadraoDoSO();
//        } catch (Exception exception) {
//            Mensagem.erro(Texto.ERRO_INTERFACE);
//        }
        TelaLoginControl telaLogin = new TelaLoginControl();
        telaLogin.chamarTelaLoginAction();

    }
}
