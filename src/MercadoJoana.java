
import br.com.mercado.control.TelaLoginControl;
import br.com.mercado.uteis.InterfaceJanela;
import br.com.mercado.uteis.Mensagem;
import br.com.mercado.uteis.Texto;

/**
 *
 * @author William
 */
public class MercadoJoana {

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
