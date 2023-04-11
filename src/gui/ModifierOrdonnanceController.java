/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.sql.Date;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ServiceOrdonnance ;
import entites.Ordonnance ; 
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import javafx.scene.Node;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author neder
 */
public class ModifierOrdonnanceController implements Initializable {

  
    @FXML
    private TextArea ContenueMO;
    @FXML
    private Button modifbtn;
    @FXML
    private AnchorPane anchorPaneE1;

  
    private String contenue ; 
    private int id;
    
    private Ordonnance ord = gui.AffichageOrdonnanceController.ord ;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Chargez le fichier FXML de la vue affichant la liste des ordonnances
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AffichageOrdonnance.fxml"));
        
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage prStage = new Stage();
            prStage.setScene(scene);
            
            // Obtenez le contrôleur de la vue affichant la liste des ordonnances
            AffichageOrdonnanceController irc = loader.getController();
            
            // Obtenez l'id de l'ordonnance sélectionnée dans la vue affichant la liste des ordonnances
            System.out.println("here");
            System.out.println(ord.getId());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    int idS;

    public void setId(int id) {

        idS = id;
        System.out.println("her id " + idS);
    }
    
    @FXML
    private void modifierOrdonnanceHandle(ActionEvent event) {
        
        try {
            ServiceOrdonnance ss = new ServiceOrdonnance();
            
            // Créez un nouvel objet Ordonnance avec le contenu du champ texte et l'id récupéré précédemment
            Ordonnance s = new Ordonnance();
            s.setContenue(ord.getContenue());
            s.setId(ord.getId());
            System.out.println(s);
            // Appelez la méthode modifierOrd du service pour mettre à jour l'ordonnance dans la base de données
            ss.modifierOrd(s);
            
            // Affichez une boîte de dialogue d'information pour indiquer que la modification a réussi
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Ordonnance modifiée avec succès !");
            alert.setContentText("Validé !");
            alert.showAndWait();
            
            // Chargez la vue affichant la liste des ordonnances pour afficher la mise à jour
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AffichageOrdonnance.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Utilisez cette méthode pour définir l'id de l'ordonnance et son contenu à afficher dans le champ texte
    public void setParameters(int id, String contenu) {
        this.id = id;
        this.ContenueMO.setText(contenu);
    }


    void setContenue(String contenue) {
       ContenueMO.setText(contenue);
    }

    
    
}
