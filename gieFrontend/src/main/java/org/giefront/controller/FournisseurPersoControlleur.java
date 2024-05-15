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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.giefront.DTO.Personne;
import org.giefront.Service.FournisseurPersoService;
import org.giefront.DTO.ContactType;

import java.io.IOException;
import java.util.List;

public class FournisseurPersoControlleur {

    @FXML
    private TextField nom, prenom, phone, email, ville, quartier, num;

    @FXML
    private Button address;

    @FXML
    private TableView<Personne> fournisseurTable;

    @FXML
    private TableColumn<Personne, Void> colEdit, colDelete;

    @FXML
    private TableColumn<Personne, Long> colId;

    @FXML
    private TableColumn<Personne, String> colNom, colPrenom, colPhone, colEmail;

    private FournisseurPersoService fournisseurService;
    private Personne selectedFournisseur;

    private final Button editButton = new Button("Edit");
    private final Button deleteButton = new Button("Delete");

    public FournisseurPersoControlleur() {
        this.fournisseurService = new FournisseurPersoService();
    }

    @FXML
    public void initialize() {
        setupTableColumns();
        fetchFournisseurs(null);
        setupRowSelection();
    }

    private void setupTableColumns() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        colEdit.setCellFactory(param -> new EditButtonCell());
        colDelete.setCellFactory(param -> new DeleteButtonCell());

        fournisseurTable.getColumns().setAll(colId, colNom, colPrenom, colPhone, colEmail, colEdit, colDelete);

        // Initially hide the Edit and Delete buttons
        colEdit.setVisible(false);
        colDelete.setVisible(false);
    }

    private void setupRowSelection() {
        fournisseurTable.setRowFactory(tv -> {
            TableRow<Personne> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 1) {
                    Personne clickedRow = row.getItem();
                    showFournisseurDetails(clickedRow);
                }
            });
            return row;
        });
    }

    private void showFournisseurDetails(Personne fournisseur) {
        selectedFournisseur = fournisseur;
        colEdit.setVisible(true);
        colDelete.setVisible(true);
    }

    public List<Personne> fetchFournisseurs(ActionEvent event) {
        List<Personne> fournisseurs = fournisseurService.getAll(ContactType.FOURNISSEUR);
        ObservableList<Personne> fournisseurList = FXCollections.observableArrayList(fournisseurs);
        fournisseurTable.setItems(fournisseurList);
        return fournisseurs;
    }

    private class EditButtonCell extends TableCell<Personne, Void> {
        private final Button editButton = new Button("Edit");


        public EditButtonCell() {
            editButton.setOnAction(event -> {
                Personne fournisseur = getTableView().getItems().get(getIndex());
                editFournisseur(fournisseur);
                setGraphic(editButton);
            });

        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            setGraphic(empty ? null : editButton);
        }
    }

    private class DeleteButtonCell extends TableCell<Personne, Void> {
        private final Button deleteButton = new Button("Delete");

        public DeleteButtonCell() {
            deleteButton.setOnAction(event -> {
                Personne fournisseur = getTableView().getItems().get(getIndex());
                deleteFournisseur(fournisseur);
            });
            setGraphic(deleteButton);
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            setGraphic(empty ? null : deleteButton);
        }
    }

    private void editFournisseur(Personne fournisseur) {
        System.out.println("Editing fournisseur: " + fournisseur.getNom());
        // Load the fournisseur data into the input fields and enable editing
    }

    private void deleteFournisseur(Personne fournisseur) {
        System.out.println("Deleting fournisseur: " + fournisseur.getNom());
        /*
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Are you sure you want to delete this fournisseur?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                fournisseurService.deletePersonne(fournisseur.getId());
                fetchFournisseurs(null);
            }
        });

         */
    }

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    public void switchToFornPerso(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/giefront/fournisseurEntro.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addFournisseur(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/giefront/personne.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    // Method to show address fields when "Address" button is clicked
    @FXML
    private void showAddressFields() {
        quartier.setVisible(true);
        ville.setVisible(true);
        num.setVisible(true);
    }
}