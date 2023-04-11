/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.RDV;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ServiceRdv;

/**
 * FXML Controller class
 *
 * @author neder
 */
public class AffichageRdvAdminController implements Initializable {

   @FXML
    private TableColumn<String, Date> ColDate;
    @FXML
    private TableColumn<String,Time> ColStart;
    @FXML
    private TableColumn<String,Time> ColEnd;
    @FXML
    private TableColumn<String, String> ColTitre;
    @FXML
    private TableColumn<String, String> ColEtat;
    @FXML
    private TableView<RDV> tableView;
 ServiceRdv a = new ServiceRdv();
     ObservableList<RDV> obList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          a = new ServiceRdv();
        obList = a.affichageRdv();
        
        ColTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
       ColEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        ColDate.setCellValueFactory(new PropertyValueFactory<>("date_rdv"));
         ColStart.setCellValueFactory(new PropertyValueFactory<>("starttime"));
           ColEnd.setCellValueFactory(new PropertyValueFactory<>("endtime"));
           
            tableView.setItems(obList);
    }    

    @FXML
    private void OrdonnanceAdmin(ActionEvent event) {
          try {
                  Parent   root = FXMLLoader.load(getClass().getResource("AffichageOrdAdmin.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                };
    }

    @FXML
    private void PatientAffichage(ActionEvent event) {
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

    @FXML
    private void DocteurAffichage(ActionEvent event) {
         try {
                  Parent   root = FXMLLoader.load(getClass().getResource("AffichageRdvDocteur.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                };
    }
    
}
