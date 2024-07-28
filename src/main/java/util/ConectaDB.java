package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectaDB {

    private static final String Driver = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/trabalhoPoo";
    private static final String USER = "postgres";
    private static final String PASS = "1234";



    public Connection getConexao(){
        Connection conn = null;

        try{
            Class.forName(this.Driver);
            conn = DriverManager.getConnection(this.URL, this.USER, this.PASS);

        }catch(Exception e){
            e.printStackTrace();
        }


        return conn;
    }

}
