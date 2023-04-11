/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entites.Ordonnance;
import entites.RDV;

import java.sql.Connection;

import java.sql.Date ;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MYDB;
/**
 *
 * @author neder
 */
public class ServiceOrdonnance implements IServiceOrd <Ordonnance> {
ObservableList<Ordonnance>obListO = FXCollections.observableArrayList();
    Connection conn ; 
    public ServiceOrdonnance(){
      conn = MYDB.getInstance().getConnection();
    }
    @Override
    public void ajouterOrd(Ordonnance O) {
        String  req ="INSERT INTO ordonnance (contenue, dateord )values(?,?)";

       PreparedStatement stm;
        try {
            stm = conn.prepareStatement(req);
               stm.setString(1, O.getContenue());
                     stm.setDate(2,(Date) new java.sql.Date(O.getDateord().getTime()));
                   
            stm.executeUpdate();
                    System.out.println("ordonnance ajoutée");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }

    @Override
    public ObservableList<Ordonnance> affichageOrd() {
          String req= "SELECT * FROM ordonnance";;
        List<Ordonnance>listO = new ArrayList<>();
        
        
        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(req);
            
            while(rs.next()) {
                int id= rs.getInt("id");
                
                java.util.Date dateord= rs.getDate("dateord");
       String contenue = rs.getString("contenue");
              
                
                Ordonnance c = new Ordonnance(id,contenue , dateord);
              
               obListO.add(c);

              
            }   
            
            
            
        }catch(Exception ex) {
            System.out.println("exception ="+ex.getMessage() );
        }
        return obListO;
    }

    @Override
    public void supprimerOrd(Ordonnance O) {
       String req="DELETE from ordonnance WHERE id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, O.getId());
            ps.executeUpdate();
            System.out.println("Ordonnance supprimé avec succés!!!");
            
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Ordonnance getOrd(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierOrd(Ordonnance O) {
        String req ="UPDATE ordonnance SET contenue=? WHERE id=?" ;
      
        
       PreparedStatement stm;
        try {
            System.out.println(O);
            stm = conn.prepareStatement(req);
               
                  
                    stm.setString(1, O.getContenue());
    
        stm.setInt(2, O.getId());

        
            stm.executeUpdate();
                    System.out.println("Ordonnance modifié avec succés");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
