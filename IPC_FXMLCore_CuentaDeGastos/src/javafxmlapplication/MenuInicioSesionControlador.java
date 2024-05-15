/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.Acount;

/**
 *
 * @author jsoler
 */
public class MenuInicioSesionControlador implements Initializable {
    
    @FXML
    private TextField login_username;
    @FXML
    private TextField login_password;
    @FXML
    private Label login_label; 
    @FXML
    private Button login_btn;
    @FXML
    private Hyperlink login_fyp_btn;
    @FXML
    private Button login_su_btn;
        

    //=========================================================
    // event handler, fired when button is clicked or 
    //                      when the button has the focus and enter is pressed
    
    /*
    @FXML
    private void handleButtonAction(ActionEvent event)throws Exception {
        Utility.changeScene(getClass(), event, "FXMLRegisterPage.fxml");
    }
    */
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        login_username.setOnKeyPressed(event -> handleEnterPressed(event));        
        login_password.setOnKeyPressed(event -> handleEnterPressed(event));
    }    
    private void handleEnterPressed(KeyEvent event) {
    if (event.getCode() == KeyCode.ENTER) {
        try {
            LoginButtonAction(null); // Intenta llamar a LoginButtonAction
        } catch (Exception ex) {
            Logger.getLogger(MenuInicioSesionControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    @FXML
    private void regRegistrationButtonAction(ActionEvent event) throws Exception {
         Utility.changeScene(getClass(), event, "MenuRegistro.fxml");
    }
    @FXML
    private void LoginButtonAction(ActionEvent event) throws Exception {
        String username = login_username.getText();
        String password = login_password.getText();
    
        boolean login = Acount.getInstance().logInUserByCredentials(username, password);
        System.out.println(login);
        
        if (!login){
            login_label.setVisible(true); // Hacer visible la etiqueta de error
            login_username.setStyle("-fx-border-color: red; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 4px 4px 4px 4px"); // Cambiar el borde del campo de texto a rojo
            login_password.setStyle("-fx-border-color: red; -fx-border-width: 2px 2px 2px 2px; -fx-border-radius: 4px 4px 4px 4px");
        }
        else{
            if(event != null){
                Utility.changeScene(getClass(), event, "MenuGastos.fxml");
            } else{
                changeSceneWithoutEvent("MenuGastos.fxml");
            }
            
        }
    }
    @FXML
    private void exit(ActionEvent event) {
    // Lógica para salir de la aplicación
    System.exit(0); // Este es un ejemplo, puedes cambiarlo según sea necesario
}
    private void changeSceneWithoutEvent(String fxmlFile) throws Exception {
    // Método que cambia la escena sin necesidad de un ActionEvent
    Stage stage = (Stage) login_btn.getScene().getWindow(); // Obtiene la ventana actual
    Parent root = FXMLLoader.load(getClass().getResource(fxmlFile)); // Carga el FXML
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}

    
    
}
