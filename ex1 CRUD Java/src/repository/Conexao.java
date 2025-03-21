package repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private Connection conexao;
    
    public Conexao() throws Exception {
        String url = "jdbc:postgresql://localhost:5432/LP2DB"; ///// Crie o banco de dados no Postgres com o nome LP2DB, ou modifique o nome do banco de dados
        String driver = "org.postgresql.Driver"; 
        String usuario = "----"; /////// Coloque seu usuário do Postgres
        String senha = "------";  //// Senha do usuário no Postgres
        
        Class.forName(driver);
        conexao = DriverManager.getConnection(url, usuario, senha);
    }
    
    public Connection getConexao() {
        return conexao;
    }
}