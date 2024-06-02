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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
        private final Button editButton = new Button();
        private final ImageView editImageView = new ImageView(new Image("https://logowik.com/content/uploads/images/888_edit.jpg"));

        public EditButtonCell() {
            editImageView.setFitHeight(30);
            editImageView.setFitWidth(40);
            editButton.setGraphic(editImageView);
            editButton.setStyle("-fx-background-color: transparent;");

            editButton.setOnAction(event -> {
                Personne fournisseur = getTableView().getItems().get(getIndex());
                editFournisseur(fournisseur);
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            setGraphic(empty ? null : editButton);
        }
    }

    private class DeleteButtonCell extends TableCell<Personne, Void> {
        private final Button deleteButton = new Button();
        private final ImageView deleteImageView = new ImageView(new Image("https://cdn-icons-png.flaticon.com/512/3807/3807871.png"));

        public DeleteButtonCell() {
            deleteImageView.setFitHeight(30);
            deleteImageView.setFitWidth(40);
            deleteButton.setGraphic(deleteImageView);
            deleteButton.setStyle("-fx-background-color: transparent;");

            deleteButton.setOnAction(event -> {
                Personne fournisseur = getTableView().getItems().get(getIndex());
                deleteFournisseur(fournisseur);
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            setGraphic(empty ? null : deleteButton);
        }
    }

    private void editFournisseur(Personne fournisseur) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/Interfaces/PersonneModification.fxml"));
            Parent root = loader.load();
            PersonneModificationController modificationController = loader.getController();
            modificationController.setPersonne(fournisseur);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteFournisseur(Personne fournisseur) {
        System.out.println("Deleting fournisseur: " + fournisseur.getNom());
        try {
            fournisseurService.deletePersonne(fournisseur.getId());
            fetchFournisseurs(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Stage stage;
    private Scene scene;

    public void switchToFornPerso(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/Interfaces/fournisseurEntro.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addFournisseur(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/Interfaces/personne.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void OnReturnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/Interfaces/Dashbord.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
