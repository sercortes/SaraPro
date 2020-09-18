/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionSer {

    private static String bd = "saradb";
    private static String user = "root";
    private static String pass = "";
    private static String url = "jdbc:mysql://localhost/" + bd +"?useUnicode=true&amp;characterEncoding=utf8";
    private Connection conn = null;
    
    public ConexionSer(){
       try{
           
           Class.forName("com.mysql.jdbc.Driver");
           conn = DriverManager.getConnection(url, user, pass);
           
       }catch(SQLException e){
           e.getMessage();
       }catch(ClassNotFoundException e){
           e.getMessage();
       }
       
    }
    
    public Connection getConnection(){
       return conn; 
    }
    
     public static void close(ResultSet rs){
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(PreparedStatement stmt){
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(Connection conn){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
           ex.printStackTrace(System.out);
        }
    }
    
    
}
