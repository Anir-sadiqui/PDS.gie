package org.giefront.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.giefront.DTO.ContactType;
import org.giefront.DTO.Entreprise;
import org.giefront.Service.EntrepriseService;
import org.giefront.TestFront;

import java.io.IOException;
import java.util.List;

public class ContactEntroController {



    @FXML
    private TextField formeJuridique, raisonSocial, phone, email, ville, quartier, num;

    @FXML
    private Button address;

    @FXML
    private TableView<Entreprise> ContactTable;

    @FXML
    private TableColumn<Entreprise, Void> colEdit, colDelete;

    @FXML
    private TableColumn<Entreprise, Long> colId;

    @FXML
    private TableColumn<Entreprise, String> colFormJ, colRaisonS, colPhone, colEmail;

    private EntrepriseService entrepriseService;
    private Entreprise selectedContact;

    public ContactEntroController() {
        this.entrepriseService = new EntrepriseService();
    }

    @FXML
    public void initialize() {
        setupTableColumns();
        fetchContacts(null);
        setupRowSelection();
    }

    private void setupTableColumns() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFormJ.setCellValueFactory(new PropertyValueFactory<>("formeJuridique"));
        colRaisonS.setCellValueFactory(new PropertyValueFactory<>("raisonSocial"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Créer automatiquement les cellules de bouton
        colEdit.setCellFactory(param -> new EditButtonCell());
        colDelete.setCellFactory(param -> new DeleteButtonCell());

        ContactTable.getColumns().setAll(colId, colFormJ, colRaisonS, colPhone, colEmail, colEdit, colDelete);

        // Initially hide the Edit and Delete buttons
        colEdit.setVisible(false);
        colDelete.setVisible(false);
    }

    private void setupRowSelection() {
        ContactTable.setRowFactory(tv -> {
            TableRow<Entreprise> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 1) {
                    Entreprise clickedRow = row.getItem();
                    showEntrepriseDetails(clickedRow);
                }
            });
            return row;
        });
    }

    private void showEntrepriseDetails(Entreprise entreprise) {
        selectedContact = entreprise;
        colEdit.setVisible(true);
        colDelete.setVisible(true);
    }

    public List<Entreprise> fetchContacts(ActionEvent event) {
//        List<Entreprise> entrepriseList = entrepriseService.getAll(ContactType.CLIENT);
//        ObservableList<Entreprise> fournisseurList = FXCollections.observableArrayList(entrepriseList);
//        ContactTable.setItems(fournisseurList);
//        return entrepriseList;
        return null;
    }
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    public void switchToFornEntro(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(TestFront.class.getResource("/org/giefront/ContactPersonne.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow() ;
        scene = new Scene(fxmlLoader.load()) ;
        stage.setScene(scene);
        stage.show();

    }



    public void addContact(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/giefront/entreprise.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    private class EditButtonCell extends TableCell<Entreprise, Void> {
        private final Button editButton = new Button("Edit");

        public EditButtonCell() {
            editButton.setOnAction(event -> {
                Entreprise entreprise = getTableView().getItems().get(getIndex());
                editEntreprise(entreprise);
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            setGraphic(empty ? null : editButton);
        }
    }

    private class DeleteButtonCell extends TableCell<Entreprise, Void> {
        private final Button deleteButton = new Button("Delete");

        public DeleteButtonCell() {
            deleteButton.setOnAction(event -> {
                Entreprise entreprise = getTableView().getItems().get(getIndex());
                deleteEntreprise(entreprise);
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            setGraphic(empty ? null : deleteButton);
        }
    }

    private void editEntreprise(Entreprise entreprise) {
        System.out.println("Editing entreprise: " + entreprise.getRaisonSocial());
        // Load the fournisseur data into the input fields and enable editing
    }

    private void deleteEntreprise(Entreprise entreprise) {

            System.out.println("Deleting entreprie: " + entreprise.getRaisonSocial());
            try {
                entrepriseService.deleteEntro(entreprise.getId()); // Assuming getId() returns the ID of the fournisseur
                fetchContacts(null); // Refresh the table after deletion
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }

    @FXML
    private void showAddressFields() {
        quartier.setVisible(true);
        ville.setVisible(true);
        num.setVisible(true);
    }

    public void switchToFornPerso(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/giefront/ContactPersonne.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

