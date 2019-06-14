
import br.com.vindiesel.control.DistanciaCalculoControl;



/**
 *
 * @author William
 */
public class VinDiesel {

    public static void main(String[] args) {
        
        DistanciaCalculoControl calculoDeDistancia = new DistanciaCalculoControl();
        String distanciaEmKm = calculoDeDistancia.calculaDistanciaEmKm("88132772", "88104780");
        Integer DistanciaEmMetros = calculoDeDistancia.calculaDistanciaEmMetros("88132772", "88104780");
        System.out.println("Distancia dos pontos em KM : " + distanciaEmKm);
        System.out.println("Distancia dos pontos em Metros : " + String.valueOf(DistanciaEmMetros));

//        try {
//            InterfaceJanela.MudaSwingParaPadraoDoSO();
//        } catch (Exception exception) {
//            Mensagem.erro(Texto.ERRO_INTERFACE);
//        }
//        TelaLoginControl telaLogin = new TelaLoginControl();
//        telaLogin.chamarTelaLoginAction();

    }

}
