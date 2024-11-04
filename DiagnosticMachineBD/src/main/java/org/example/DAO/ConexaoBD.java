package org.example.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoBD {
    public static Connection con; //variavel de classe conexão -> consigo criar apenas uma conexão ativa

    static String server = "oracle.fiap.com.br";
    static String port = "1521";
    static String sid = "ORCL";
    private static String user = "RM558832";
    private static String passwd = "080705";

    public static String url = "jdbc:oracle:thin:@" + server + ":" +  port + ":" + sid;

    public static Connection criarConexao() {
        if (con == null) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                con = DriverManager.getConnection(url, user, passwd);
            } catch (ClassNotFoundException e) { //erro da linha 20
                System.out.println("Driver não funcionou");
            } catch (java.sql.SQLException e2) { //erro da linha 21
                System.out.println("Conexão não foi efetuada");
            }
        }

        return con;
    }
}
