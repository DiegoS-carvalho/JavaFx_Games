package br.ds.senac.gamesfx.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSQLite {


    private static Connection conexao;

    public static Connection getConexao(){

//String de conexão - URL do banco de dados
        String url = "jdbc:sqlite:/C:\\Users\\diego.csilva45\\Banco_de_dados\\db_games.db";
        try {
           conexao = DriverManager.getConnection(url);
            return conexao;
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro durante a conexão com o banco. ");
            e.printStackTrace();
            return null;
        }
    }
public static void fecharConexao(){
    try {
        if(!conexao.isClosed()){
            conexao.close();
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }


}
}
