/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author jsoler
 */
public class MenuInicioSesionControlador implements Initializable {
    private Label labelMessage;
    @FXML
    private AnchorPane content_area;
    
    //=========================================================
    // event handler, fired when button is clicked or 
    //                      when the button has the focus and enter is pressed
    private void handleButtonAction(ActionEvent event) {
        labelMessage.setText("Hello, this is your first JavaFX project - IPC");
    }
    
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }


    @FXML
    private void abrir_registrar(MouseEvent event) throws IOException {
           Parent fxml = FXMLLoader.load(getClass().getResource("MenuRegistro.fxml"));
           content_area.getChildren().removeAll();
           content_area.getChildren().setAll(fxml);
    }

    @FXML
    private void BotonRegistrar(ActionEvent event) {
    }
    
}
