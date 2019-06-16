
import br.com.vindiesel.control.DistanciaCalculoControl;

public class TestandoFrete {

    public static void main(String[] args) {
        DistanciaCalculoControl calculoDeDistancia = new DistanciaCalculoControl();
        String distanciaEmKm = calculoDeDistancia.calculaDistanciaEmKm("88133810","88130400");
        System.out.println("Distancia em km :" + distanciaEmKm);
    }

}
