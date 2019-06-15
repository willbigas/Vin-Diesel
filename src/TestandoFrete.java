
import br.com.vindiesel.control.DistanciaCalculoControl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Will
 */
public class TestandoFrete {

    public static void main(String[] args) {
        DistanciaCalculoControl calculoDeDistancia = new DistanciaCalculoControl();
        String distanciaEmKm = calculoDeDistancia.calculaDistanciaEmKm("88133810","88130400");
        System.out.println("Distancia em km :" + distanciaEmKm);
    }

}
