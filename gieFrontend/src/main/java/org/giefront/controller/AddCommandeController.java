package org.giefront.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.giefront.DTO.*;
import org.giefront.Service.CommandeService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AddCommandeController {

    @FXML
    private TextField idField;

    @FXML
    private TextField achatsField;

    @FXML
    private DatePicker purchaseDatePicker;

    @FXML
    private ChoiceBox<EtatCommande> etatCommandeChoiceBox;

    @FXML
    private Button addCommandeButton;

    private final CommandeService commandeService = new CommandeService();

    @FXML
    public void initialize() {
        etatCommandeChoiceBox.setItems(FXCollections.observableArrayList(EtatCommande.values()));
    }

    @FXML
    private void handleAddButton() {
        try {
            Long id = idField.getText().isEmpty() ? null : Long.parseLong(idField.getText());
            List<Achat> achats = parseAchats(achatsField.getText());
            LocalDate purchaseDate = purchaseDatePicker.getValue();
            EtatCommande etatCommande = etatCommandeChoiceBox.getValue();

            Commande newCommande = new Commande(id, achats, purchaseDate, etatCommande);
            commandeService.addComm(newCommande);

            showAlert("Success", "Commande added successfully!");

            // Close the window after adding
            Stage stage = (Stage) addCommandeButton.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to add commande: " + e.getMessage());
        }
    }

    private List<Achat> parseAchats(String achatsText) {
        List<Achat> achats = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Expecting input format: "date1,supplier1,details1;date2,supplier2,details2;..."
        String[] achatsArray = achatsText.split(";");
        for (String achatStr : achatsArray) {
            String[] parts = achatStr.split(",");
            if (parts.length == 3) {
                LocalDate date = LocalDate.parse(parts[0], formatter);
                Contact supplier = new Contact(null, parts[1], null, null);  // Assuming a constructor for Contact
                AchatDetail details = new AchatDetail(null , null , 5 , 100 );      // Assuming a constructor for AchatDetail
                Achat achat = new Achat(null, date, supplier, details, null);
                achats.add(achat);
            }
        }
        return achats;
    }

    @FXML
    private void handleReturnButton() {
        // Close the window
        Stage stage = (Stage) addCommandeButton.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
