package br.com.mercado.dao;

import br.com.mercado.factory.Conexao;
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
