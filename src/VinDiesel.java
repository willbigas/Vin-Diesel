
import br.com.vindiesel.control.TelaLoginControl;
import br.com.vindiesel.uteis.Mensagem;
import br.com.vindiesel.uteis.InterfaceJanela;
import br.com.vindiesel.uteis.Texto;



/**
 *
 * @author William
 */
public class VinDiesel {

    public static void main(String[] args) {
        

        try {
            InterfaceJanela.MudaSwingParaPadraoDoSO();
        } catch (Exception exception) {
            Mensagem.erro(Texto.ERRO_INTERFACE);
        }
        TelaLoginControl telaLogin = new TelaLoginControl();
        telaLogin.chamarTelaLoginAction();

    }

}
