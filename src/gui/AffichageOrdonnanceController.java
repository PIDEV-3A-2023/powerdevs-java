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
import entites.RDV;
import java.io.IOException;


import java.util.Date;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ServiceOrdonnance;



/**
 * FXML Controller class
 *
 * @author neder
 */
public class AffichageOrdonnanceController implements Initializable {

    @FXML
    private TableColumn<String, Date> ColDate;
    @FXML
    private TableColumn<String, String> Colcontenue;
    @FXML
    private TableView<Ordonnance> tableView;
    
     private TableColumn<Ordonnance, Void> colModifBtn;
    private TableColumn<Ordonnance, Void> colSuppBtn;
    private TableColumn<Ordonnance, Void> colExpBtn;
    ServiceOrdonnance b = new  ServiceOrdonnance();
     ObservableList<Ordonnance> obList;
    public static Ordonnance ord ; 
    @FXML
    private TableColumn<String, Integer> ColId;
    @FXML
    private TextField recherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colSuppBtn = new TableColumn<>("Supprimer");
        tableView.getColumns().add(colSuppBtn);

        colModifBtn = new TableColumn<>("Modifier");
        tableView.getColumns().add(colModifBtn);


        b = new ServiceOrdonnance();
        obList = b.affichageOrd();
        ColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        Colcontenue.setCellValueFactory(new PropertyValueFactory<>("contenue"));
   
        ColDate.setCellValueFactory(new PropertyValueFactory<>("dateord"));
       
                

        addButtonModifToTable();
        addButtonDeleteToTable();
        


  


        
        





      

        tableView.setItems(obList);

        addButtonModifToTable();

      

        //addButtonPDFToTable();
    }    

    
    Button btn;
  Ordonnance od = new Ordonnance();

    private void addButtonModifToTable() {
                 Callback<TableColumn<Ordonnance , Void>, TableCell<Ordonnance, Void>> cellFactory = new Callback<TableColumn<Ordonnance, Void>, TableCell<Ordonnance, Void>>() {
            @Override
            public TableCell<Ordonnance, Void> call(final TableColumn<Ordonnance, Void> param) {

                final TableCell<Ordonnance, Void> cell = new TableCell<Ordonnance, Void>() {

                    {
                        btn = new Button("Modifier");
                        btn.setOnAction((ActionEvent event) -> {
                            try {
                   od = tableView.getSelectionModel().getSelectedItem();
                   ord = od ;
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifierOrdonnance.fxml"));
                      Parent root = loader.load();
                   ModifierOrdonnanceController controller = loader.getController();
                     controller.setParameters(od.getId(), od.getContenue()); // passer l'id et le contenu à ModifierOrdonnanceController
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                     stage.show();
                 } catch (Exception e) {
                      e.printStackTrace();
                      }
 });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colModifBtn.setCellFactory(cellFactory);
    }
    Button btnSupprimer;
    private Label label;

    private void showConfirmation(Ordonnance ord) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setHeaderText("Voulez-vous vraiment supprimer cette ordonnance ?");
        alert.setContentText("");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {
            this.label.setText("pas selection!");
        } else if (option.get() == ButtonType.OK) {
            System.out.println(" iddd=" + ord.getId());
            b.supprimerOrd(ord);
            obList.clear();
            
        } else if (option.get() == ButtonType.CANCEL) {
            this.label.setText("Exit!");
        } else {
            this.label.setText("-");
        }
    }

    private void addButtonDeleteToTable() {
       Callback<TableColumn<Ordonnance, Void>, TableCell<Ordonnance, Void>> cellFactory = new Callback<TableColumn<Ordonnance, Void>, TableCell<Ordonnance, Void>>() {
            @Override
            public TableCell<Ordonnance, Void> call(final TableColumn<Ordonnance, Void> param) {

                final TableCell<Ordonnance, Void> cell = new TableCell<Ordonnance, Void>() {

                    {
                        btnSupprimer = new Button("Supprimer");
                        btnSupprimer = new Button("Supprimer");
                        btnSupprimer.setOnAction((ActionEvent event) -> {

                            od = tableView.getSelectionModel().getSelectedItem();
                         showConfirmation(od);
                          try {
                  Parent   root = FXMLLoader.load(getClass().getResource("AffichageOrdonnance.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                };
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnSupprimer);
                        }
                    }
                };
                return cell;
            }
        };
        colSuppBtn.setCellFactory(cellFactory);
    }

    @FXML
    private void AddOrd(ActionEvent event) {
        try {
                  Parent   root = FXMLLoader.load(getClass().getResource("AjoutOrdonnance.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                };
    }

    @FXML
    private void RechercheHandle(KeyEvent event) {
         FilteredList<Ordonnance> filteredList = new FilteredList<>(obList, b -> true);

        recherche.textProperty().addListener((observable, oldValue, newValue) -> {

            if (recherche.getText().isEmpty()) {

                addButtonModifToTable();
                addButtonDeleteToTable();

            }
            filteredList.setPredicate(reclamation -> {
                if (newValue == null || newValue.isEmpty()) {
                    btn = new Button("Modifier");
                    btnSupprimer = new Button("Supprimer");

                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
 if (String.valueOf(reclamation.getDateord()).toLowerCase().indexOf(lowerCaseFilter) != -1) {

                    return true;
                } else if (String.valueOf(reclamation.getContenue()).toLowerCase().indexOf(lowerCaseFilter) != -1) {

                    return true;
                } 
                else {
                    btn = new Button("Modifier");
                    btnSupprimer = new Button("Supprimer");
                    return false;
                }

            });

        });
        SortedList<Ordonnance> sortedData = new SortedList<>(filteredList);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(sortedData);

    }

  

   
    
}
