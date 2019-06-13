package br.com.vindiesel.dao;

import br.com.vindiesel.factory.Conexao;
import java.sql.Connection;

/**
 *
 * @author William
 */
public class Dao {

    protected Connection conexao;

    public Dao() {
        conexao = Conexao.getConexao();
    }
}
