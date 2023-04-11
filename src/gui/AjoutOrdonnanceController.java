/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import services.ServiceOrdonnance ;
import entites.Ordonnance;
import java.io.IOException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
public class AjoutOrdonnanceController implements Initializable {

    @FXML
    private JFXDatePicker DateO;
    @FXML
    private TextArea contenueO;
    @FXML
    private Button ajoutbtn;
 ServiceOrdonnance so = new ServiceOrdonnance() ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjoutOrdHandle(ActionEvent event) {
        
         String contenue  = contenueO.getText();
       LocalDate dateo = DateO.getValue();
       ZoneId defaultZoneId = ZoneId.systemDefault();
         Date date = (Date) Date.from(dateo.atStartOfDay(defaultZoneId).toInstant());
         
          if (contenue.isEmpty()) {
        showAlert("contenue obligatoire", "contenue doit être non vide");
          }
    
     else {
       
            Ordonnance ord = new Ordonnance(contenue, date  );
            so.ajouterOrd(ord);
            showAlert("ordonnance   ajouté", "ordonnance ajouté avec succès");
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
    private void retourL(ActionEvent event) {
          try {
                  Parent   root = FXMLLoader.load(getClass().getResource("AffichageOrdonnance.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                };
    }
    }
    

