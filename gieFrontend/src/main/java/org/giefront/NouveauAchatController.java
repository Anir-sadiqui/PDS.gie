package org.giefront;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import org.giefront.DTO.Category;
import org.giefront.DTO.Entreprise;
import org.giefront.DTO.Personne;
import org.giefront.Service.EntrepriseService;
import org.giefront.Service.PersonneService;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class NouveauAchatController implements Initializable {

    private final PersonneService personneService = new PersonneService(); // Example service for fetching personnes
    private final EntrepriseService entrepriseService = new EntrepriseService(); // Example service for fetching entreprises

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
    void EcrireQuantite(ActionEvent event) {

    }

    @FXML
    void Fournisseur(ActionEvent event) {
        if (EntrepriseRadioButton.isSelected()) {
            loadEntreprises(); // Load entreprise data if entreprise radio button is selected
        } else if (PersonneRadioButton.isSelected()) {
            loadPersonnes(); // Load personne data if personne radio button is selected
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
        loadEntreprises(); // Load entreprise data if entreprise radio button is selected
    }

    @FXML
    void selectPersonne(ActionEvent event) {
        loadPersonnes(); // Load personne data if personne radio button is selected
    }

    @FXML
    void Back(ActionEvent event) {
        // Implement the functionality to go back
    }
    @FXML
    void Nom(ActionEvent event) {
        // Implement the functionality to handle the product selection based on the category
        // This method will be called when the user selects a category from the ComboBox

        // Assuming you have a mapping of category names to products
        String selectedCategory = CategorieComboBox.getValue();

        // Your logic here to retrieve the associated product based on the selected category
        // For example, if you have a map<Category, List<String>> where each category maps to a list of product names,
        // you would retrieve the list of products associated with the selected category and update the corresponding ComboBox

        // Example:
        // Map<Category, List<String>> categoryProductMap = ...
        // List<String> products = categoryProductMap.get(selectedCategory);
        // Then update the ComboBox with the list of products
    }


    @FXML
    void Quantite(ActionEvent event) {
        // Implement the functionality to handle the quantity input
        // Assuming txtQuantite is a TextField where the user types the quantity
        String input = txtQuantite.getText();
        try {
            int quantity = Integer.parseInt(input);
            // Use the quantity as needed, e.g., validate it or perform some action
        } catch (NumberFormatException e) {
            // Handle the case where the input is not a valid integer
            showAlert("Please enter a valid quantity!");
        }
    }



    @FXML
    void ajouter(ActionEvent event) {
        // Handle validating the purchase

        // First, check if the required fields are filled
        if (FournisseurComboBox.getValue() == null ||
                CategorieComboBox.getValue() == null ||
                txtQuantite.getText().isEmpty()) {
            showAlert("Please fill all fields!");
            return; // Exit the method if fields are not filled
        }

        // Get the selected values
        String fournisseur = FournisseurComboBox.getValue().toString();
        String categorie = CategorieComboBox.getValue().toString();
        String quantite = txtQuantite.getText();

        // Display the selected values
        showAlert("Fournisseur: " + fournisseur + "\n" +
                "Categorie: " + categorie + "\n" +
                "Quantite: " + quantite);
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
    }




    private void loadEntreprises() {
        FournisseurComboBox.getItems().clear(); // Clear previous items

        // Create 10 Entreprise objects with only their names
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

        // Add entreprise names to the ComboBox
        FournisseurComboBox.getItems().addAll(entrepriseNames);
    }


    private void loadPersonnes() {
        FournisseurComboBox.getItems().clear(); // Clear previous items

        // Create 10 Personne objects with only their names
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

        // Add personne names to the ComboBox
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
