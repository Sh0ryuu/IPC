package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Acount;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.File;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;






public class MenuRegistroControlador implements Initializable {
    @FXML
    private TextField register_name;
    @FXML
    private TextField register_nickname;
    @FXML
    private TextField register_surname;
    @FXML
    private TextField register_password;
    @FXML
    private TextField register_password_ch;
    @FXML
    private TextField register_email;
    @FXML
    private Label iemail;
    @FXML
    private Label inickname;
    @FXML
    private Label ipassword;
    @FXML
    private Label ipassword_ch; 
    @FXML
    private Label usedName_label;
    @FXML
    private Circle profilePicViewer;
    
    
    
    private BooleanProperty validEmail;
    private BooleanProperty validNickname;
    private BooleanProperty validPassword;
    private BooleanProperty validPasswordCh;
    private File selectedImageFile;
    @FXML
    private Button register_button;
    @FXML
    private ImageView profileImageView;
    @FXML
    private Button selectImageButton;
    @FXML
    private Button register_login;
    
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        validEmail = new SimpleBooleanProperty();
        validEmail.setValue(Boolean.FALSE);
 
        register_email.focusedProperty().addListener((observable, oldValue, newValue)->{
            if(!newValue){
                checkEditEmail();
            }
        });
        
        validNickname = new SimpleBooleanProperty();
        validNickname.setValue(Boolean.FALSE);
        
        register_nickname.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                try {
                    checkEditNickname();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        
        validPassword = new SimpleBooleanProperty();
        validPassword.setValue(Boolean.FALSE);
        
        register_password.focusedProperty().addListener((observable, oldValue, newValue)->{
            if(!newValue){
                checkEditPassword();
            }
        });
        
        register_password.focusedProperty().addListener((observable, oldValue, newValue)->{
            if(!newValue){
                checkEditConfirmPassword();
            }
        });
        
        validPasswordCh = new SimpleBooleanProperty();
        validPasswordCh.setValue(Boolean.FALSE);
        
        register_password_ch.focusedProperty().addListener((observable, oldValue, newValue)->{
            if(!newValue){
                checkEditConfirmPassword();
            }
        });
        
    }
    
    @FXML
    private void regLoginButtonAction(ActionEvent event) throws Exception{
        Utility.changeScene(getClass(), event, "MenuInicioSesion.fxml");
    }
    
    @FXML
    private void regRCompletedButtonAction(ActionEvent event) throws Exception{
        if (validateFields() && validEmail.get() && validNickname.get() && validPassword.get() && validPasswordCh.get()) {
            showAlert(Alert.AlertType.INFORMATION, "Registro exitoso", "El usuario se registró correctamente. Debe iniciar sesión");
            Utility.changeScene(getClass(), event, "MenuInicioSesion.fxml");
        } else {
            showAlert(Alert.AlertType.ERROR, "Error de validación", "Por favor, complete todos los campos correctamente.");
        }
    }

    
    @FXML
    private void selectImageButtonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Todos los archivos", "*.*")
        );
        selectedImageFile = fileChooser.showOpenDialog(null);
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString(), 300, 300, true, true);
            profilePicViewer.setFill(new ImagePattern(image));
        }
    }

    private boolean validateFields() throws Exception{
 
        String name = register_name.getText();
        String surname = register_surname.getText();
        String nickname = register_nickname.getText();
        String password = register_password.getText();
        String password_ch = register_password_ch.getText();
        String email = register_email.getText();
        LocalDate date = LocalDate.now();
        Image avatar = null;
        
        
        if (selectedImageFile != null) {
            avatar = new Image(selectedImageFile.toURI().toString());
        } else {
            System.out.println("La imagen no está presente.");
        }


        if (name.isEmpty() || nickname.isEmpty() || password.isEmpty() || password_ch.isEmpty() || email.isEmpty()) {
            return false;
        }
         
        if (password == password_ch){ return false; }
        
        boolean registrado = Acount.getInstance().registerUser(name, surname, email, nickname, password, avatar, date);
        
        if(!registrado){return false;}

        return true;
    }
    
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    private void manageError(Label label, TextField textField, BooleanProperty validProperty) {
        label.setVisible(true); // Hacer visible la etiqueta de error
        textField.setStyle("-fx-border-color: red; -fx-border-radius: 4px 4px 4px 4px; -fx-border-width: 2px 2px 2px 2px;"); // Cambiar el borde del campo de texto a rojo
        validProperty.set(false); // Establecer la propiedad de validez como false
    }
    
    private void manageCorrect(TextField textField, Label label, BooleanProperty validProperty) {
        textField.setStyle("-fx-border-color:  #75d06c;");
        label.setVisible(false); // Hacer invisible la etiqueta de error
        validProperty.set(true); // Establecer la propiedad de validez como true
    }
    
    private void checkEditEmail(){
        String email = register_email.getText();
        if (isValidEmail(email)) {
            manageCorrect(register_email, iemail, validEmail);
        } else {
            manageError(iemail, register_email, validEmail);
        }
    }
    
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.matches(emailRegex, email);            
    }

    private void checkEditPassword(){
        String password = register_password.getText();
        if (password.isEmpty() || password.length() < 6 || password.matches("^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$")) {
            manageError(ipassword, register_password, validPassword);
        } else {
            manageCorrect(register_password, ipassword, validPassword);
        }
    }

    private void checkEditConfirmPassword(){
        if (!register_password.getText().equals(register_password_ch.getText())) {
            manageError(ipassword_ch, register_password_ch, validPasswordCh);
        } else {
            manageCorrect(register_password_ch, ipassword_ch, validPasswordCh);
        }
    }
    @FXML
    private void exit(ActionEvent event) {
    // Lógica para salir de la aplicación
    System.exit(0); // Este es un ejemplo, puedes cambiarlo según sea necesario
}
    private void checkEditNickname() throws Exception{
        String nickname = register_nickname.getText().trim();
        if (nickname.isEmpty() || nickname.contains(" ")) {
            manageError(inickname, register_nickname, validNickname);
        } else {
            manageCorrect(register_nickname, inickname, validNickname);
        }
        if (Acount.getInstance().existsLogin(nickname)) {
            manageError(usedName_label, register_nickname, validNickname);
        }
        else{
            manageCorrect(register_nickname, usedName_label, validNickname);
        }

    }

 }


    