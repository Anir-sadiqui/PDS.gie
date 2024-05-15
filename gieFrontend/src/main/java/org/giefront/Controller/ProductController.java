package org.giefront.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.giefront.DTO.Product;
import org.giefront.Service.ProductService;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Text_Field_Q.setDisable(true);
        Text_Field_P.setDisable(true);
        choiceBox.getItems().addAll("Option 1", "Option 2", "Option 3");
    }
    @FXML
    void OnBtnPClick(ActionEvent event) {
        if (areFieldsFilled()) {
            // Désactiver les champs de texte quantité et prix
            Text_Field_Q.setDisable(true);
            Text_Field_P.setDisable(true);

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
                product.setQ(Integer.parseInt(Text_Field_Q.getText())); // Convertir en int
                product.setPrix(Double.parseDouble(Text_Field_P.getText())); // Convertir en double

                productService.add(product);

                System.out.println("Product added successfully!");
            } else {
                System.out.println("Product addition cancelled.");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incomplete Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields!");
            alert.showAndWait();
        }
    }



    private boolean areFieldsFilled() {
        boolean fieldsFilled = !Text_Field_N.getText().isEmpty() &&
                !Text_Field_D.getText().isEmpty() &&
                choiceBox.getValue() != null;

        // Si les champs sont remplis, désactiver les champs de texte de quantité et de prix
        if (fieldsFilled) {
            Text_Field_Q.setDisable(true);
            Text_Field_P.setDisable(true);
        }

        return fieldsFilled;
    }



    @FXML
    void onReturn(ActionEvent event) {
        try {
            // Charger le fichier FXML de l'interface principale
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Stock.fxml"));
            AnchorPane mainInterface = fxmlLoader.load();

            // Remplacer le contenu actuel par l'interface principale chargée
            Pane mainAnchor = new Pane();
            mainAnchor.getChildren().setAll(mainInterface);
        } catch (IOException e) {
            e.printStackTrace(); // Gérer l'exception de chargement du fichier FXML
        }
    }

}

