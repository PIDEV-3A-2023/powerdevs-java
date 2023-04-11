/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import entites.RDV;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import services.ServiceRdv;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


/**
 * FXML Controller class
 *
 * @author neder
 */
public class ModifierRdvController implements Initializable {

    @FXML
    private AnchorPane anchorePaneEl;
    @FXML
    private JFXTimePicker startR;
    @FXML
    private JFXTimePicker endR;
    @FXML
    private JFXDatePicker dateR;
    @FXML
    private TextField nomR;
    @FXML
    private TextField etatR;
    @FXML
    private Button modifierbtn;

    private String etat;
    private String titre;
    private int id;
    private RDV rdv = gui.AffichageRdvController.rdv ;

     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         etatR.setText("Pending");
        etatR.setEditable(false);
        System.out.println("heree");
           System.out.println(rdv);
           FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/gui/AffichageRdv.fxml"));
            Stage prStage = new Stage();

            Parent root;
        
        try {
                    root = loader.load();
                Scene scene = new Scene(root);
                prStage.setScene(scene);
                AffichageRdvController irc = loader.getController();
                ServiceRdv sp = new ServiceRdv();

                id = irc.A.getId();
                 

            } catch (IOException ex) {
            }
    }  
           int idS;

    public void setId(int id) {

        idS = id;
        System.out.println("her id " + idS);
    }

    
    

    @FXML
    private void ModifierRdvHandle(ActionEvent event) {
          LocalDate dater = dateR.getValue();
          LocalTime heuredebut = startR.getValue();
         LocalTime heurefin = endR.getValue();
         ZoneId defaultZoneId = ZoneId.systemDefault();
         Date date = (Date) Date.from(dater.atStartOfDay(defaultZoneId).toInstant());
         Time timedebut = java.sql.Time.valueOf(heuredebut);
          Time timefin = java.sql.Time.valueOf(heurefin);
        try {

            ServiceRdv ss = new ServiceRdv();

            RDV s = new RDV();
            s.setEtat(etatR.getText());
            s.setTitre(nomR.getText());
           s.setDate_rdv(Date.from(dateR.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
          s.setStarttime(java.sql.Time.valueOf(startR.getValue()));
           s.setEndtime(java.sql.Time.valueOf(endR.getValue()));
             System.out.println("idddd"+idS);

            s.setId(idS);

            ss.modifierRdv(s);
           
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("succes");
            alert.setHeaderText("rendez vous modifié avec succés !!!");
            alert.setContentText("Validé !");
            alert.showAndWait();

             FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AffichageRdv.fxml"));
                                Parent root = loader.load();
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                stage.setScene(new Scene(root));
                                stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

      
    }

    public void setEtat(String etat) {
        etatR.setText(etat);
    }
    public void setTitre(String titre) {
        nomR.setText(titre);
    }

    public void setDate_rdv(Date date) {
         LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    dateR.setValue(localDate);
    }

    public void setStarttime(Time timedebut) {
           startR.setValue(timedebut.toLocalTime());
    }

    public void setEndtime(Time timefin) {
          endR.setValue(timefin.toLocalTime());
    }

   
}
