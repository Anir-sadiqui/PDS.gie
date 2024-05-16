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
import javafx.stage.Stage;
import org.giefront.DTO.Personne;

import org.giefront.DTO.ContactType;
import org.giefront.Service.PersonneService;

import java.io.IOException;
import java.util.List;

public class ContactPersoController {

    @FXML
    private TextField nom, prenom, phone, email, ville, quartier, num;

    @FXML
    private Button address;

    @FXML
    private TableView<Personne> PersonneTable;

    @FXML
    private TableColumn<Personne, Void> colEdit, colDelete;

    @FXML
    private TableColumn<Personne, Long> colId;

    @FXML
    private TableColumn<Personne, String> colNom, colPrenom, colPhone, colEmail;

    private PersonneService personneService;
    private Personne selectedPersonne;

    private final Button editButton = new Button("Edit");
    private final Button deleteButton = new Button("Delete");

    public ContactPersoController() {
        this.personneService = new PersonneService();
    }

    @FXML
    public void initialize() {
        setupTableColumns();
        fetchContacts(null);
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

        PersonneTable.getColumns().setAll(colId, colNom, colPrenom, colPhone, colEmail, colEdit, colDelete);

        // Initially hide the Edit and Delete buttons
        colEdit.setVisible(false);
        colDelete.setVisible(false);
    }

    private void setupRowSelection() {
       PersonneTable.setRowFactory(tv -> {
            TableRow<Personne> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 1) {
                    Personne clickedRow = row.getItem();
                    showContactDetails(clickedRow);
                }
            });
            return row;
        });
    }

    private void showContactDetails(Personne contact) {
        selectedPersonne = contact;
        colEdit.setVisible(true);
        colDelete.setVisible(true);
    }

    public List<Personne> fetchContacts(ActionEvent event) {
        List<Personne> contacts = personneService.getAll(ContactType.CLIENT);
        ObservableList<Personne> contactList = FXCollections.observableArrayList(contacts);
        PersonneTable.setItems(contactList);
        return contacts;
    }

    private class EditButtonCell extends TableCell<Personne, Void> {
        private final Button editButton = new Button("Edit");


        public EditButtonCell() {
            editButton.setOnAction(event -> {
                Personne personne = getTableView().getItems().get(getIndex());
                editPersonne(personne);
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
                Personne personne = getTableView().getItems().get(getIndex());
                deletePersonne(personne);
            });
            setGraphic(deleteButton);
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            setGraphic(empty ? null : deleteButton);
        }
    }

    private void editPersonne(Personne personne) {
        System.out.println("Editing personne: " + personne.getNom());
        // Load the contact data into the input fields and enable editing
    }

    private void deletePersonne(Personne personne) {

        System.out.println("Deleting fournisseur: " + personne.getNom());
        try {
            personneService.deletePersonne(personne.getId()); // Assuming getId() returns the ID of the fournisseur
            fetchContacts(null); // Refresh the table after deletion
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }


    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    public void switchToFornPerso(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/giefront/ContactEntreprise.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addPersonne(ActionEvent event) throws IOException {
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



//    @FXML
//    private void switchToEditPersonne(Personne personne) {
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/giefront/personneModification.fxml")); // Correct path to your FXML
//            Parent root = fxmlLoader.load();
//
//            // Get the controller and pass the data
//            PersonneModificationController modificationController = fxmlLoader.getController();
//            modificationController.setPersonne(personne);
//
//            stage = (Stage) PersonneTable.getScene().getWindow();
//            scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
