/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.User;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class MenuRegistroControlador implements Initializable {

    @FXML
    private AnchorPane content_areaR;
    @FXML
    private Button btnCrearCuenta;
    @FXML
    private TextField nombre_apellido;
    @FXML
    private TextField usuario;
    @FXML
    private TextField contraseña;
    @FXML
    private TextField correo;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void volver_menu(MouseEvent event) throws IOException {
     Parent fxml = FXMLLoader.load(getClass().getResource("MenuInicioSesion.fxml"));
           content_areaR.getChildren().removeAll();
           content_areaR.getChildren().setAll(fxml);
    }

    @FXML
    private void btnCrearCuenta(ActionEvent event) {
        String nombreyapellido=nombre_apellido.getText();
        String[] partes = nombreyapellido.split(" ");
        String name = partes[0];
        String surname = partes[1];
        
        String nickname=usuario.getText();
        String email=correo.getText();
        String password=contraseña.getText();
        LocalDate registerDate;
        Image image;
        User user = new User(name,  surname, email,  nickname,  password,image,registerDate);
    }
}
   
