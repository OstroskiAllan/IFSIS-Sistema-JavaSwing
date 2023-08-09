package controlador;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Classe Conexao usando o padrão de projeto Singleton
 *
 * @author Marcelo Rafael Borth
 */
public class Conexao {

    private static Connection conexao;

    private Conexao() throws Exception {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://127.0.0.1:3306/ifsis";
            String usuario = "root";
            String senha = "123456";

            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
            conexao.setAutoCommit(true);

        } catch (Exception e) {
            throw e;
        }
    }

    public static Connection getConexao() throws Exception {
        if (conexao == null) {
            new Conexao();
        }

        return conexao;
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println(Conexao.getConexao());
        System.out.println(Conexao.getConexao());
    }
    
}
