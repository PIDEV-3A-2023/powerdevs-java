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
import entites.RDV;
import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ServiceRdv;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


/**
 * FXML Controller class
 *
 * @author neder
 */
public class AffichageRdvController implements Initializable {

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

     private TableColumn<RDV, Void> colModifBtn;
    private TableColumn<RDV, Void> colSuppBtn;
    private TableColumn<RDV, Void> colExpBtn;
    ServiceRdv a = new ServiceRdv();
     ObservableList<RDV> obList;
     
   
    @FXML
    private TableColumn<String, Integer> ColID;
    public static RDV rdv ; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colSuppBtn = new TableColumn<>("Supprimer");
        tableView.getColumns().add(colSuppBtn);

        colModifBtn = new TableColumn<>("Modifier");
        tableView.getColumns().add(colModifBtn);


        a = new ServiceRdv();
        obList = a.affichageRdv();
        ColID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
       ColEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        ColDate.setCellValueFactory(new PropertyValueFactory<>("date_rdv"));
         ColStart.setCellValueFactory(new PropertyValueFactory<>("starttime"));
           ColEnd.setCellValueFactory(new PropertyValueFactory<>("endtime"));
                

        addButtonModifToTable();
        addButtonDeleteToTable();
   
        tableView.setItems(obList);

        addButtonModifToTable();

      

        //addButtonPDFToTable();
    }    

    
     Button btn;
     RDV A = new RDV();
  

    private void addButtonModifToTable() {
           Callback<TableColumn<RDV, Void>, TableCell<RDV, Void>> cellFactory = new Callback<TableColumn<RDV, Void>, TableCell<RDV, Void>>() {
            @Override
            public TableCell<RDV, Void> call(final TableColumn<RDV, Void> param) {

                 TableCell<RDV, Void> cell = new TableCell<RDV, Void>() {

                    {
                        btn = new Button("Modifier");
                        btn.setOnAction((ActionEvent event) -> {
                            try {
                                A = tableView.getSelectionModel().getSelectedItem();//
                                System.out.println("hello");
                                System.out.println("DATA ="+A);
                                                                rdv = new RDV (A.getId(), A.getDate_rdv(),A.getStarttime(), A.getEndtime(), A.getTitre(),A.getEtat()) ;

                                  FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifierRdv.fxml"));
                                Parent root = loader.load();
                                ModifierRdvController controller = loader.getController();
                               // controller.setDate_rdv(A.getDate_rdv());
                                controller.setStarttime(A.getStarttime());
                                controller.setId(A.getId());
                                controller.setEndtime(A.getEndtime());
                                controller.setEtat(A.getEtat());
                                controller.setTitre(A.getTitre());
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

    private void showConfirmation(RDV rdv) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setHeaderText("Voulez-vous vraiment supprimer ce rendez vous ?");
        alert.setContentText("");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {
            this.label.setText("pas selection!");
        } else if (option.get() == ButtonType.OK) {
            System.out.println(" iddd=" + rdv.getId());
            a.supprimerRdv(rdv.getId());
            obList.clear();
            
        } else if (option.get() == ButtonType.CANCEL) {
            this.label.setText("Exit!");
        } else {
            this.label.setText("-");
        }
    }

    private void addButtonDeleteToTable() {
      Callback<TableColumn<RDV, Void>, TableCell<RDV, Void>> cellFactory = new Callback<TableColumn<RDV, Void>, TableCell<RDV, Void>>() {
            @Override
            public TableCell<RDV, Void> call(final TableColumn<RDV, Void> param) {

                 TableCell<RDV, Void> cell = new TableCell<RDV, Void>() {

                    {
                        btnSupprimer = new Button("Supprimer");
                        btnSupprimer = new Button("Supprimer");
                        btnSupprimer.setOnAction((ActionEvent event) -> {

                            A = tableView.getSelectionModel().getSelectedItem();
                            rdv=A;
                          showConfirmation(rdv);
                          reload();
                          try {
                  Parent   root = FXMLLoader.load(getClass().getResource("AffichageRdv.fxml"));
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
    public void reload(){


    }

    @FXML
    private void Add(ActionEvent event) {
         try {
                  Parent   root = FXMLLoader.load(getClass().getResource("AjouterRDV.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                };
    }

    @FXML
    private void ordonnanceliste(ActionEvent event) {
          try {
                  Parent   root = FXMLLoader.load(getClass().getResource("AffichageOrdPatient.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                };

    }

   
    
    }    
    

