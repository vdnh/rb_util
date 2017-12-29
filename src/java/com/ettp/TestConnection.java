/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ettp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import jdk.nashorn.internal.ir.CatchNode;

/**
 *
 * @author vdnh
 */
public class TestConnection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Connection con = OracleJDBCConnection.getJDBCConnection();
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM DIALOGS";
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("DIALOG_ID");
                System.out.println("DIALOG_ID : " + id);
            }
        } catch(SQLException e){
        }
    }
    
}
