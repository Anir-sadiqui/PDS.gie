package org.giefront;
import javafx.scene.control.Alert;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import org.giefront.DTO.Category;
import org.giefront.DTO.EtatStock;
import org.giefront.DTO.Product;
import org.giefront.Service.ProductService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ProductModifController implements Initializable {
    @FXML
    private Button btnimg;
    @FXML
    private TextField Text_Field_N;
    @FXML
    private TextField Text_Field_D;
    @FXML
    private TextField Text_Field_Q;
    @FXML
    private Button BtnAddP;
    @FXML
    private ChoiceBox choiceBox;
    Product p = StockController.product;
    ProductService ps = new ProductService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Text_Field_N.setText(p.getName());
        Text_Field_D.setText(p.getDescription());
        Text_Field_Q.setText(String.valueOf(p.getPrix()));
        ObservableList<String> options = FXCollections.observableArrayList();
        for (Category c : Category.values()) {
            options.add(c.name());
        }
        choiceBox.setItems(options);
        choiceBox.setValue(p.getCategory());

    }



    public void OnBtnPClick(ActionEvent event) throws IOException {
        Map<String, String> attributs = new HashMap<>();
        if (!Text_Field_N.getText().isEmpty()) {
            attributs.put("Name", Text_Field_N.getText());
        }
        if (!Text_Field_D.getText().isEmpty()) {
            attributs.put("Description", Text_Field_D.getText());
        }
        if (!Text_Field_Q.getText().isEmpty()) {
            attributs.put("Price", Text_Field_Q.getText());
        }
        if (choiceBox.getValue() != null) {
            attributs.put("Category", choiceBox.getValue().toString());
        }

        // Modifier les attributs
        ps.modify(Math.toIntExact(p.getId()), attributs);


        showAlert("Modification successfully completed");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    public void modImg(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(btnimg.getScene().getWindow()); // Utiliser la fenêtre du bouton comme parent
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            p.setImagePath(selectedFile.toURI().toString()); // Enregistrer le chemin de l'image
        }
    }
}
