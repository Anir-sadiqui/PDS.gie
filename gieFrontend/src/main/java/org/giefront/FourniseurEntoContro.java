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
import javafx.stage.Stage;
import org.giefront.DTO.ContactType;
import org.giefront.DTO.Entreprise;
import org.giefront.Service.FournisseurEntroService;
import org.giefront.TestFront;

import java.io.IOException;
import java.util.List;

public class FourniseurEntoContro {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    public void switchToFornEntro(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(TestFront.class.getResource("/org/Interfaces/fournisseurPerso.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void addFournisseur(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/Interfaces/entreprise.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

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
        private final Button editButton = new Button();
        public EditButtonCell() {
            Image editImage = new Image("https://cdn0.iconfinder.com/data/icons/mini-icon-set-general-office/91/General_-_Office_09-512.png");
            ImageView imageView = new ImageView(editImage);
            imageView.setFitHeight(30);
            imageView.setFitWidth(40);
            editButton.setGraphic(imageView);
            editButton.setStyle("-fx-background-color: transparent;");
            editButton.setOnAction(event -> {
                Entreprise fournisseur = getTableView().getItems().get(getIndex());
                editFournisseur(fournisseur);
            });
        }
        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            setGraphic(empty ? null : editButton);
        }
    }

    private class DeleteButtonCell extends TableCell<Entreprise, Void> {
        private final Button deleteButton = new Button();
        public DeleteButtonCell() {
            Image deleteImage = new Image("https://cdn-icons-png.flaticon.com/512/3807/3807871.png");
            ImageView imageView = new ImageView(deleteImage);
            imageView.setFitHeight(30);
            imageView.setFitWidth(40);
            deleteButton.setGraphic(imageView);
            deleteButton.setStyle("-fx-background-color: transparent;");
            deleteButton.setOnAction(event -> {
                Entreprise fournisseur = getTableView().getItems().get(getIndex());
                deleteFournisseur(fournisseur);
            });
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

    private void deleteFournisseur(Entreprise fournisseur) {
        System.out.println("Deleting fournisseur: " + fournisseur.getRaisonSocial());
        try {
            fournisseurService.deleteEntro(fournisseur.getId()); // Assuming getId() returns the ID of the fournisseur
            fetchFournisseurs(null); // Refresh the table after deletion
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

    @FXML
    private Button closeButton;
    @FXML
    private Button minimizeButton;
    @FXML
    private Button maximizeButton;

    @FXML
    void closeWindow() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void minimizeWindow() {
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void maximizeWindow() {
        Stage stage = (Stage) maximizeButton.getScene().getWindow();
        stage.setFullScreen(!stage.isFullScreen());
    }
}
