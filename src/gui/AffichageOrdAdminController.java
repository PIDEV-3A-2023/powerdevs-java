/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Ordonnance;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ServiceOrdonnance;

/**
 * FXML Controller class
 *
 * @author neder
 */
public class AffichageOrdAdminController implements Initializable {

    @FXML
    private AnchorPane anchorPaneE1;
   @FXML
    private TableColumn<String, Date> ColDate;
    @FXML
    private TableColumn<String, String> Colcontenue;
    @FXML
    private TableView<Ordonnance> tableView;
   @FXML
    private TableColumn<String, Integer> ColId;
   
     ServiceOrdonnance b = new  ServiceOrdonnance();
     ObservableList<Ordonnance> obList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         b = new ServiceOrdonnance();
        obList = b.affichageOrd();
        ColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        Colcontenue.setCellValueFactory(new PropertyValueFactory<>("contenue"));
   
        ColDate.setCellValueFactory(new PropertyValueFactory<>("dateord"));
         tableView.setItems(obList);
    }    

    @FXML
    private void RDVAdmin(ActionEvent event) {
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
