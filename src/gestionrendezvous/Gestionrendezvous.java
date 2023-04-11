/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrendezvous;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author neder
 */
public class Gestionrendezvous extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
     Parent p = FXMLLoader.load(getClass().getResource("/gui/AffichageOrdAdmin.fxml"));
     Scene scene = new Scene(p);
     Stage stage = new Stage();
     stage.setScene(scene);
     
     stage.show();
     
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
