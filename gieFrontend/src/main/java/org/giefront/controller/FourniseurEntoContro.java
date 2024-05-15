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
import org.giefront.DTO.ContactType;
import org.giefront.DTO.Entreprise;
import org.giefront.DTO.Personne;
import org.giefront.Service.FournisseurEntroService;
import org.giefront.Service.FournisseurPersoService;
import org.giefront.TestFront;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FourniseurEntoContro {

    private Stage stage ;
    private Scene scene ;
    private FXMLLoader fxmlLoader ;

    public void switchToFornEntro(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(TestFront.class.getResource("/org/giefront/fournisseurPerso.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow() ;
        scene = new Scene(fxmlLoader.load()) ;
        stage.setScene(scene);
        stage.show();

    }

    public void addFournisseur(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/giefront/entreprise.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

//
//    public void switchToFornPerso(ActionEvent event) throws IOException {
//
//        fxmlLoader = new FXMLLoader(TestFront.class.getResource("/org/giefront/fournisseurEntro.fxml"));
//        stage = (Stage) ((Node)event.getSource()).getScene().getWindow() ;
//        scene = new Scene(fxmlLoader.load()) ;
//        stage.setScene(scene);
//        stage.show();
//    }
////////////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    private TextField formeJuridique, raisonSocial, phone, email, ville, quartier, num;

    @FXML
    private Button address;

    @FXML
    private TableView<Entreprise> fournisseurTable;

    @FXML
    private TableColumn<Entreprise, Void> colEdit, colDelete;

    @FXML
    private TableColumn<Entreprise, Long> colId;

    @FXML
    private TableColumn<Entreprise, String> colFormJ, colRaisonS, colPhone, colEmail;

    private FournisseurEntroService fournisseurService;
    private Entreprise selectedFournisseur;

    private final Button editButton = new Button("Edit");
    private final Button deleteButton = new Button("Delete");

    public FourniseurEntoContro() {
        this.fournisseurService = new FournisseurEntroService();
    }

    @FXML
    public void initialize() {
        setupTableColumns();
        fetchFournisseurs(null);
        setupRowSelection();
    }

    private void setupTableColumns() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFormJ.setCellValueFactory(new PropertyValueFactory<>("formeJuridique"));
        colRaisonS.setCellValueFactory(new PropertyValueFactory<>("raisonSocial"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        colEdit.setCellFactory(param -> new FourniseurEntoContro.EditButtonCell());
        colDelete.setCellFactory(param -> new FourniseurEntoContro.DeleteButtonCell());

        fournisseurTable.getColumns().setAll(colId, colFormJ, colRaisonS, colPhone, colEmail, colEdit, colDelete);

        // Initially hide the Edit and Delete buttons
        colEdit.setVisible(false);
        colDelete.setVisible(false);
    }

    private void setupRowSelection() {
        fournisseurTable.setRowFactory(tv -> {
            TableRow<Entreprise> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 1) {
                    Entreprise clickedRow = row.getItem();
                    showFournisseurDetails(clickedRow);
                }
            });
            return row;
        });
    }

    private void showFournisseurDetails(Entreprise fournisseur) {
        selectedFournisseur = fournisseur;
        colEdit.setVisible(true);
        colDelete.setVisible(true);
    }

    public List<Entreprise> fetchFournisseurs(ActionEvent event) {
        List<Entreprise> fournisseurs = fournisseurService.getAll(ContactType.FOURNISSEUR);
        ObservableList<Entreprise> fournisseurList = FXCollections.observableArrayList(fournisseurs);
        fournisseurTable.setItems(fournisseurList);
        return fournisseurs;
    }

    private class EditButtonCell extends TableCell<Entreprise, Void> {
        private final Button editButton = new Button("Edit");


        public EditButtonCell() {
            editButton.setOnAction(event -> {
                Entreprise fournisseur = getTableView().getItems().get(getIndex());
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

    private class DeleteButtonCell extends TableCell<Entreprise, Void> {
        private final Button deleteButton = new Button("Delete");

        public DeleteButtonCell() {
            deleteButton.setOnAction(event -> {
                Entreprise fournisseur = getTableView().getItems().get(getIndex());
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

    private void editFournisseur(Entreprise fournisseur) {
        System.out.println("Editing fournisseur: " + fournisseur.getRaisonSocial());
        // Load the fournisseur data into the input fields and enable editing
    }

    private void deleteFournisseur(Entreprise fournisseur) {
        System.out.println("Deleting fournisseur: " + fournisseur.getRaisonSocial());
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
    @FXML
    private void showAddressFields() {
        quartier.setVisible(true);
        ville.setVisible(true);
        num.setVisible(true);
    }





}
