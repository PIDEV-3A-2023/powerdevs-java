/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.RDV ;
import java.util.List;
import java.sql.*;
import utils.MYDB;
import java.util.Date;
import java.sql.Time ;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author neder
 */
public class ServiceRdv implements IService<RDV> {
    ObservableList<RDV>obListR = FXCollections.observableArrayList();
    Connection conn ; 
    public ServiceRdv(){
      conn = MYDB.getInstance().getConnection();
    }

    @Override
    public void ajouterRdv(RDV R) {
     
       String  req ="INSERT INTO rdv(date_rdv,starttime,endtime,titre,etat )values(?,?,?,?,?)";

       PreparedStatement stm;
        try {
            stm = conn.prepareStatement(req);
                     stm.setDate(1, new java.sql.Date(R.getDate_rdv().getTime()));
                    stm.setTime(2, R.getStarttime());           
                    stm.setTime(3, R.getEndtime());

                    stm.setString(4, R.getTitre());
                    stm.setString(5, R.getEtat());
   


        
            stm.executeUpdate();
                    System.out.println("rendez vous ajoutée");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }

    @Override
    public ObservableList<RDV> affichageRdv() {
         String req= "SELECT * FROM rdv";;
        List<RDV>listR = new ArrayList<>();
        
        
        try{
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(req);
            
            while(rs.next()) {
                int id= rs.getInt("id");
                Date date_rdv = rs.getDate("date_rdv");
                Time starttime = rs.getTime("starttime");
                Time endtime = rs.getTime("endtime");
                String titre = rs.getString("titre");
               String etat = rs.getString("etat");
                
                RDV p = new RDV(id,date_rdv,starttime,endtime,titre,etat);
              
               obListR.add(p);

              
            }   
            
            
            
        }catch(Exception ex) {
            System.out.println("exception ="+ex.getMessage() );
        }
        return obListR;
    }

    @Override
    public void supprimerRdv(int id) {
       String req="DELETE from rdv WHERE id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("rendez vous supprimé avec succés!!!");
            
        }catch (SQLException ex) {
        Logger.getLogger(ServiceRdv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

    @Override
    public void modifierRdv(RDV R) {
         String req ="UPDATE rdv  SET date_rdv =?, starttime=?, endtime=?, titre=?, etat=? WHERE id=?";
      
        
       PreparedStatement stm;
        try {
            stm = conn.prepareStatement(req);
                 stm.setDate(1, new java.sql.Date(R.getDate_rdv().getTime()));
                    stm.setTime(2, R.getStarttime());           
                    stm.setTime(3, R.getEndtime());
                    stm.setString(4, R.getTitre());
                    stm.setString(5, R.getEtat());
   
        stm.setInt(6, R.getId());

        
            stm.executeUpdate();
                    System.out.println("rendez vous modifié avec succés");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }

    @Override
    public RDV getRdv(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    }

 