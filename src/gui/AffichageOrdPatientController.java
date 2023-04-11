/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import entites.Ordonnance ; 
import java.io.IOException;
import java.util.Date;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ServiceOrdonnance;

/**
 * FXML Controller class
 *
 * @author neder
 */
public class AffichageOrdPatientController implements Initializable {

    @FXML
    private TableView<Ordonnance> tableView;
    @FXML
    private TableColumn<String, Date> Coldate;
    @FXML
    private TableColumn<String, String> ColCon;

    ServiceOrdonnance c = new  ServiceOrdonnance();
     ObservableList<Ordonnance> obList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           c = new ServiceOrdonnance();
        obList = c.affichageOrd();
        
        ColCon.setCellValueFactory(new PropertyValueFactory<>("contenue"));
   
        Coldate.setCellValueFactory(new PropertyValueFactory<>("dateord"));
        tableView.setItems(obList);
    }    

    @FXML
    private void RetourAdmin(ActionEvent event) {
           try {
                  Parent   root = FXMLLoader.load(getClass().getResource("AffichageRdvAdmin.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                };
    }
    
}
