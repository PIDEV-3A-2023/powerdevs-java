/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import java.sql.*;

/**
 *
 * @author neder
 */
public class MYDB {
    private Connection conn;
 
    String url = "jdbc:mysql://localhost:3306/dbdbdb";
    String user ="root";
    String pwd = "";
    
    private static MYDB instance ;
    public MYDB() {
        try {
            
            conn = DriverManager.getConnection(url,user,pwd);
            System.out.println("connexion établie!!!");
            
        }catch(Exception ex) {
            System.out.println("Probleme de connexion!!!"+ex.getMessage());
        }
    }
    
    //SINGLETON 
    public static MYDB getInstance() {
        
        if(MYDB.instance == null) {
            
            MYDB.instance = new MYDB();
        }
        return MYDB.instance;
    }
 
    
    // APPEL GET CONNECTION 
    
    
    
    public static Connection getConnection() {
        return MYDB.getInstance().conn;
    }
    
}
