package org.giefront.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.giefront.DTO.Achat;
import org.giefront.DTO.Commande;
import org.giefront.Service.CommandeService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CommandeController {

        @FXML
        private Button afficherCommandesButton;

        @FXML
        private ChoiceBox<String> etatChoiceBox;

        @FXML
        private Text etatFilterLabel;

        @FXML
        private Button ajouterButton;

        @FXML
        private Rectangle headerRectangle;

        @FXML
        private Text headerText;

        @FXML
        private TableView<Commande> commandeTableView;

        @FXML
        private TableColumn<Commande, Long> idColumn;

        @FXML
        private TableColumn<Commande, LocalDate> dateColumn;

        @FXML
        private TableColumn<Commande, String> etatColumn;

        @FXML
        private TableColumn<Commande, Double> totalPrixColumn;

        @FXML
        private Text instructions1;

        @FXML
        private Text instructions2;

        @FXML
        private DatePicker datePicker;

        @FXML
        private Text dateFilterLabel;

        @FXML
        private Button supprimerButton;

        @FXML
        private Button validerButton;

        @FXML
        private ListView<Achat> achatsListView;

        private Stage stage;
        private Scene scene;
        private FXMLLoader fxmlLoader;

        private CommandeService commandeService = new CommandeService();

        private ObservableList<Commande> commandesList = FXCollections.observableArrayList();

        @FXML
        private void initialize() {
                idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                dateColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
                etatColumn.setCellValueFactory(new PropertyValueFactory<>("e"));
                totalPrixColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice")); // Assuming getTotalPrice() method exists

                etatChoiceBox.setItems(FXCollections.observableArrayList("EN_COURS", "LIVREE", "ANNULEE"));

                afficherCommandesButton.setOnAction(event -> loadCommandes());
                ajouterButton.setOnAction(event -> handleAddButton());
                supprimerButton.setOnAction(event -> handleDeleteButton());
                validerButton.setOnAction(event -> handleValidateButton());

                commandeTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> showAchats(newSelection));
        }

        private void loadCommandes() {
                try {
                        List<Commande> commandes = commandeService.getAll();
                        commandesList.setAll(commandes);
                        filterCommandes();
                } catch (Exception e) {
                        showAlert("Error", "Failed to load commandes: " + e.getMessage());
                }
        }

//        private void handleAddButton() {
//                // Logic to show Add Commande dialog or new window
//        }

        private void handleAddButton() {
                try {
                        // Load the Add Commande FXML file
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/giefront/addCommande.fxml"));
                        Parent root = loader.load();

                        // Get the AddCommandeController instance (if you need to pass data or call methods)
                        AddCommandeController addCommandeController = loader.getController();

                        // Create a new Stage (window)
                        Stage addStage = new Stage();
                        addStage.setTitle("Add Commande");
                        addStage.setScene(new Scene(root));

                        // Show the window
                        addStage.showAndWait(); // Use showAndWait() if you want the current window to wait until the new window is closed
                } catch (IOException e) {
                        e.printStackTrace();
                        showAlert("Error", "Failed to load add commande UI: " + e.getMessage());
                }
        }

        private void handleDeleteButton() {
                Commande selectedCommande = commandeTableView.getSelectionModel().getSelectedItem();
                if (selectedCommande != null) {
                        // Logic to delete the selected Commande
                        commandesList.remove(selectedCommande);
                } else {
                        showAlert("Warning", "No Commande selected for deletion.");
                }
        }

        private void handleValidateButton() {
                filterCommandes();
        }

        private void filterCommandes() {
                String selectedEtat = etatChoiceBox.getValue();
                LocalDate selectedDate = datePicker.getValue();

                List<Commande> filteredList = commandesList.stream()
                        .filter(c -> (selectedEtat == null || c.getE().toString().equals(selectedEtat)) &&
                                (selectedDate == null || c.getPurchaseDate().equals(selectedDate)))
                        .collect(Collectors.toList());

                commandeTableView.setItems(FXCollections.observableArrayList(filteredList));
        }

        private void showAchats(Commande commande) {
                if (commande != null) {
                        achatsListView.setItems(FXCollections.observableArrayList(commande.getAchats()));
                } else {
                        achatsListView.setItems(FXCollections.emptyObservableList());
                }
        }

        private void showAlert(String title, String message) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(title);
                alert.setHeaderText(null);
                alert.setContentText(message);
                alert.showAndWait();
        }

        public void addCommand(ActionEvent event) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("/org/giefront/addCommande.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }


        @FXML
        private Button closeButton;

        @FXML
        private void handleCloseButtonAction() {
                // Get the current stage
                Stage stage = (Stage) closeButton.getScene().getWindow();
                // Close the stage
                stage.close();
        }





}
