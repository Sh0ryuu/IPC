/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxmlapplication;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.Acount;
import model.Category;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import model.AcountDAOException;

public class MenuGastosDialogController {

    @FXML
    private TextField titleField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField costField;
    @FXML
    private TextField unitsField;
    @FXML
    private DatePicker dateField;
    @FXML
    private ComboBox<Category> categoryComboBox;
    @FXML
    private ImageView receiptImageView;
    
    private File receiptImageFile;
    
    @FXML
    public void initialize() throws IOException, AcountDAOException {
        Acount acount = Acount.getInstance();
        categoryComboBox.setItems(FXCollections.observableArrayList(acount.getUserCategories()));
    }

    @FXML
    private void handleAddGasto(ActionEvent event) {
        try {
            String title = titleField.getText();
            String description = descriptionField.getText();
            double cost = Double.parseDouble(costField.getText());
            int units = Integer.parseInt(unitsField.getText());
            LocalDate date = dateField.getValue();
            Category category = categoryComboBox.getValue();
            Image receiptImage = null;

            if (receiptImageFile != null) {
                receiptImage = new Image(receiptImageFile.toURI().toString());
            }

            if (title.isEmpty() || cost <= 0 || units <= 0 || date == null || category == null) {
                showAlert("Error", "Todos los campos deben ser llenados correctamente.");
                return;
            }

            Acount acount = Acount.getInstance();
            acount.registerCharge(title, description, cost, units, receiptImage, date, category);

            showAlert("Éxito", "El gasto ha sido añadido correctamente.");

            ((Button) event.getSource()).getScene().getWindow().hide();

        } catch (NumberFormatException e) {
            showAlert("Error", "Por favor, introduce valores numéricos válidos para el costo y las unidades.");
        } catch (Exception e) {
            showAlert("Error", "Ha ocurrido un error al añadir el gasto: " + e.getMessage());
        }
    }

    @FXML
    private void handleUploadImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );
        receiptImageFile = fileChooser.showOpenDialog(null);
        if (receiptImageFile != null) {
            receiptImageView.setImage(new Image(receiptImageFile.toURI().toString()));
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

