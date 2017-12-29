package com.ettp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author vdnh
 */
public class OracleJDBCConnection {
    public static Connection getJDBCConnection(){
        final String url = "jdbc:oracle:thin:@server2003:1521:orcl";
        final String user = "robot";
        final String password = "orclrobo";
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(url, user, password);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            //System.out.println("There is problem with Connection");
            return null;
        }        
    }
}
