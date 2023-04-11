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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.ServiceRdv;
import entites.RDV;
import java.io.IOException;

import java.util.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author neder
 */
public class AjouterRDVController implements Initializable {

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
    private Button ajoutbtn;

     ServiceRdv sr = new ServiceRdv() ;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          etatR.setText("Pending");
        etatR.setEditable(false);
    }    

    @FXML
    private void AjoutRdvHandel(ActionEvent event) {
        String titre  = nomR.getText();
        String etat  = etatR.getText();
       LocalDate dater = dateR.getValue();
        LocalTime heuredebut = startR.getValue();
         LocalTime heurefin = endR.getValue();
         ZoneId defaultZoneId = ZoneId.systemDefault();
         Date date = (Date) Date.from(dater.atStartOfDay(defaultZoneId).toInstant());
         Time timedebut = java.sql.Time.valueOf(heuredebut);
          Time timefin = java.sql.Time.valueOf(heurefin);
        
     
        
      
        
     if (titre.isEmpty()) {
        showAlert("Nom obligatoire", "Nom doit être non vide");
    } else if (etat.isEmpty()) {
        showAlert("etat doit etre non vide ", "etat doit être non vide");
    } else {
       
            RDV rdv = new RDV(date, timedebut, timefin, titre, etat);
            sr.ajouterRdv(rdv);
            showAlert("rendez vous  ajouté", "rendez vous ajouté avec succès");
    }
     

    
}

    private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

    @FXML
    private void retour(ActionEvent event) {
        try {
                  Parent   root = FXMLLoader.load(getClass().getResource("AffichageRdv.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                };
    }
    
}
