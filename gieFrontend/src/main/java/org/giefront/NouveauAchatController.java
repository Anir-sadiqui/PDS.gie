package org.giefront;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import org.giefront.DTO.Category;
import org.giefront.DTO.Product;
import org.giefront.DTO.ProductData;
import org.giefront.Service.EntrepriseService;
import org.giefront.Service.PersonneService;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class NouveauAchatController implements Initializable {

    private final PersonneService personneService = new PersonneService();
    private final EntrepriseService entrepriseService = new EntrepriseService();

    @FXML
    private ComboBox<String> FournisseurComboBox;

    @FXML
    private ComboBox<String> CategorieComboBox;

    @FXML
    private RadioButton EntrepriseRadioButton;

    @FXML
    private RadioButton PersonneRadioButton;

    @FXML
    private ToggleGroup Type;

    @FXML
    private Button btnRetour;

    @FXML
    private Button btnValider;

    @FXML
    private TextField txtQuantite;

    @FXML
    private ComboBox<String> NomComboBox;

    @FXML
    void Fournisseur(ActionEvent event) {
        if (EntrepriseRadioButton.isSelected()) {
            loadEntreprises();
        } else if (PersonneRadioButton.isSelected()) {
            loadPersonnes();
        }
    }

    @FXML
    void Product(ActionEvent event) {
    }

    @FXML
    void retour(ActionEvent event) {
    }

    @FXML
    void selectEntreprise(ActionEvent event) {
        loadEntreprises();
    }

    @FXML
    void selectPersonne(ActionEvent event) {
        loadPersonnes();
    }

    @FXML
    void Back(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Achat.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Nom(ActionEvent event) {
        String selectedCategory = CategorieComboBox.getValue();
        if (selectedCategory != null) {
            List<Product> products = ProductData.getProducts();
            List<String> productNames = products.stream()
                    .filter(product -> product.getCategory().name().equals(selectedCategory))
                    .map(Product::getName)
                    .collect(Collectors.toList());
            NomComboBox.setItems(FXCollections.observableArrayList(productNames));
        }
    }

    @FXML
    void Quantite(ActionEvent event) {
        String input = txtQuantite.getText();
        try {
            int quantity = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            showAlert("Please enter a valid quantity!");
        }
    }

    @FXML
    void ajouter(ActionEvent event) {
        if (Type.getSelectedToggle() == null ||
                FournisseurComboBox.getValue() == null ||
                CategorieComboBox.getValue() == null ||
                NomComboBox.getValue() == null ||
                txtQuantite.getText().isEmpty()) {
            showAlert("Veuillez remplir tous les champs !");
            return;
        }

        RadioButton selectedType = (RadioButton) Type.getSelectedToggle();
        String type = selectedType.getText();

        String fournisseur = FournisseurComboBox.getValue();
        String categorie = CategorieComboBox.getValue();
        String produit = NomComboBox.getValue();
        String quantite = txtQuantite.getText();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categorie();
        CategorieComboBox.setOnAction(this::Nom);
    }

    private void loadEntreprises() {
        FournisseurComboBox.getItems().clear();
        List<String> entrepriseNames = Arrays.asList(
                "ABC Company",
                "XYZ Corporation",
                "123 Enterprises",
                "Smith & Sons",
                "Johnson Ltd.",
                "Miller Inc.",
                "Davis Co.",
                "Garcia Group",
                "Martinez Enterprises",
                "Lopez Industries"
        );
        FournisseurComboBox.getItems().addAll(entrepriseNames);
    }

    private void loadPersonnes() {
        FournisseurComboBox.getItems().clear();
        List<String> personneNames = Arrays.asList(
                "John Doe",
                "Alice Smith",
                "Bob Johnson",
                "Emily Brown",
                "Michael Wilson",
                "Sophia Miller",
                "William Davis",
                "Olivia Garcia",
                "James Martinez",
                "Emma Lopez"
        );
        FournisseurComboBox.getItems().addAll(personneNames);
    }

    @FXML
    void categorie() {
        List<String> categoryNames = Arrays.stream(Category.values())
                .map(Category::name)
                .collect(Collectors.toList());
        CategorieComboBox.getItems().addAll(categoryNames);
    }
}
