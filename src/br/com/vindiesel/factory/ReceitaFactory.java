package br.com.vindiesel.factory;

import br.com.vindiesel.model.Receita;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author William
 */
public class ReceitaFactory {

    private static List<Receita> receitas = new ArrayList<>();

    public static java.util.Collection geraColecao() {
        return receitas;
    }

    public static void populaOjetosNoRelatório(List<Receita> receitasList) {
        receitas = receitasList;
    }
}
