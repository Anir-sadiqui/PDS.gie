package org.giefront;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
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
        choiceBox.setValue(p.getCategory());

    }

    public void OnBtnPClick(ActionEvent event) throws IOException {
        Map<String,String> attributs = new HashMap<>();
        attributs.put("Name",Text_Field_N.getText());
        attributs.put("Description",Text_Field_D.getText());
        attributs.put("Price",Text_Field_Q.getText());
        attributs.put("Category", choiceBox.getValue().toString());
        ps.modify(Math.toIntExact(p.getId()),attributs);
        StockController.C_TableProduct.refresh();
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
