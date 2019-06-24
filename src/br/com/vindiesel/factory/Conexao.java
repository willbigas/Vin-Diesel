package br.com.vindiesel.factory;

import br.com.vindiesel.uteis.Mensagem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author William
 */
public class Conexao {

    private final static String URL = "jdbc:mysql://localhost:3306/vindiesel";
    private final static String USER = "root";
    private final static String PASS = "";
    private static Connection conexao;

    public static Connection getConexao() {
        if (conexao == null) {
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                conexao = DriverManager.getConnection(URL, USER, PASS);
            } catch (SQLException ex) {
                Mensagem.erro("Erro ao conectar no banco de dados, contate o administrador do sistema!");
                System.out.println(ex.getMessage());
            }
            System.out.println("Conectou...");
        }
        return conexao;
    }
}
