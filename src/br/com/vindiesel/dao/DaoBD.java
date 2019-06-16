package br.com.vindiesel.dao;

import br.com.vindiesel.factory.Conexao;
import java.sql.Connection;

/**
 *
 * @author William
 */
public class DaoBD {

    protected Connection conexao;

    public DaoBD() {
        conexao = Conexao.getConexao();
    }
}
