package org.giefront;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import org.giefront.DTO.Category;
import org.giefront.DTO.EtatStock;
import org.giefront.DTO.Product;
import org.giefront.Service.ProductService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    @FXML
    private Button BtnImage;
    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField Text_Field_N;

    @FXML
    private TextField Text_Field_D;

    @FXML
    private TextField Text_Field_Q;

    @FXML
    private TextField Text_Field_P;

    @FXML
    private ChoiceBox<String> choiceBox;

    private String imagePath ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        remplirType();
    }
    @FXML
    void OnBtnPClick(ActionEvent event) {
        if (areFieldsFilled() && !imagePath.isEmpty()) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Are you sure you want to add this product?");
            confirmationAlert.setContentText("Click OK to confirm.");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                ProductService productService = new ProductService();
                Product product = new Product();
                product.setDescription(Text_Field_D.getText());
                product.setName(Text_Field_N.getText());
                product.setPrix(Double.parseDouble(Text_Field_Q.getText()));
                product.setImagePath(imagePath);
                product.setCategory(Category.valueOf(choiceBox.getValue()));
                if (productService.getbyname(Text_Field_N.getText()).equals(null)){
                    productService.add(product);
                    showMessage("Product added successfully!");
                }
                else {
                    showMessage("Product Already exists!");
                }
            }
            else {
                showMessage("Product addition cancelled.");
            }
        }
        else {
            showMessage("Please fill in all fields!");
        }
    }


    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private boolean areFieldsFilled() {
        boolean fieldsFilled = !Text_Field_N.getText().isEmpty() &&
                !Text_Field_D.getText().isEmpty() &&
                choiceBox.getValue() != null ;

        // Si les champs sont remplis, désactiver les champs de texte de quantité et de prix

        return fieldsFilled;
    }





    @FXML
    void Addimg(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(BtnImage.getScene().getWindow()); // Utiliser la fenêtre du bouton comme parent
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imagePath = selectedFile.toURI().toString(); // Enregistrer le chemin de l'image
        }
    }
    private void remplirType() {
        ObservableList<String> options = FXCollections.observableArrayList();
        for (Category c : Category.values()){
            options.add(c.name());
        }
        choiceBox.setItems(options);
    }

}

