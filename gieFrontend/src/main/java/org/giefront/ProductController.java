package org.giefront;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import org.giefront.DTO.Product;
import org.giefront.Service.ProductService;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
public class ProductController implements Initializable {

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private Button BtnAddP;

    @FXML
    private Button BtnRtrn;

    @FXML
    private ChoiceBox<String> choiceBox; // Changez le type de ChoiceBox

    @FXML
    private TextField Text_Field_D;

    @FXML
    private TextField Text_Field_N;

    @FXML
    private TextField Text_Field_P;

    @FXML
    private TextField Text_Field_Q;

    @FXML
    void OnBtnPClick(ActionEvent event) {
        if (areFieldsFilled()) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Are you sure you want to add this product?");
            confirmationAlert.setContentText("Click OK to confirm.");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                ProductService productservice = new ProductService();
                Product product = new Product();
                product.setDescription(Text_Field_D.getText());
                product.setName(Text_Field_N.getText());
                product.setQ(Integer.parseInt(Text_Field_Q.getText())); // Convertir en int
                product.setPrix(Double.parseDouble(Text_Field_P.getText())); // Convertir en double

                System.out.println(product);

                productservice.add(product);

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

    @FXML
    void onReturn(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("MainInterface.fxml"));
            Node node = fxmlLoader.load();
            mainAnchor.getChildren().setAll(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Ajouter des options au ChoiceBox
        choiceBox.getItems().addAll("Option 1", "Option 2", "Option 3");
    }

    private boolean areFieldsFilled() {
        return !Text_Field_Q.getText().isEmpty() &&
                !Text_Field_N.getText().isEmpty() &&
                !Text_Field_P.getText().isEmpty() &&
                !Text_Field_D.getText().isEmpty() &&
                choiceBox.getValue() != null;
    }
}
