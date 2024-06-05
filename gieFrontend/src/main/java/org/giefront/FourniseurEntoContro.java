package org.giefront;

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
import org.giefront.Service.EntrepriseService;
import org.giefront.Service.FournisseurEntroService;
import org.giefront.TestFront;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class FourniseurEntoContro {

    private Stage stage ;
    private Scene scene ;
    private FXMLLoader fxmlLoader ;


    public void switchToFornEntro(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(TestFront.class.getResource("/org/Interfaces/fournisseurPerso.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow() ;
        scene = new Scene(fxmlLoader.load()) ;
        stage.setScene(scene);
        stage.show();

    }

    public void addFournisseur(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Does this company already exists");
        ButtonType buttonTypeYes = new ButtonType("Oui", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == buttonTypeYes) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/Interfaces/FEex.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("/org/Interfaces/entreprise.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }



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

        colEdit.setCellFactory(param -> new EditButtonCell());
        colDelete.setCellFactory(param -> new DeleteButtonCell());

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

    public void OnReturnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/Interfaces/Dashbord.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
                try {
                    deleteFournisseur(fournisseur);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/Interfaces/EntrepriseModification.fxml"));
            Parent root = loader.load();

            // Get the controller for PersonneModification.fxml
            EntrepriseModifController modificationController = loader.getController();

            // Pass the selected Personne data to the modification controller
            modificationController.setEntroprise(fournisseur);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteFournisseur(Entreprise fournisseur) throws IOException {
        System.out.println("Deleting fournisseur: " + fournisseur.getRaisonSocial());

            EntrepriseService es = new EntrepriseService();
            es.deleteType(Math.toIntExact(fournisseur.getId()));
            fetchFournisseurs(null); // Refresh the table after deletion

    }

    @FXML
    private void showAddressFields() {
        quartier.setVisible(true);
        ville.setVisible(true);
        num.setVisible(true);
    }






}
