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
import org.giefront.Service.ClientPersoService;
import org.giefront.Service.FournisseurPersoService;
import org.giefront.DTO.ContactType;
import org.giefront.Service.PersonneService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ClientPersoController {
    @FXML
    private TextField nom, prenom, phone, email, ville, quartier, num;
    @FXML
    private Button address;
    @FXML
    private TableView<Personne> clientTable;
    @FXML
    private TableColumn<Personne, Void> colEdit, colDelete;
    @FXML
    private TableColumn<Personne, Long> colId;
    @FXML
    private TableColumn<Personne, String> colNom, colPrenom, colPhone, colEmail;

    private ClientPersoService clientPersoService;
    private Personne selectedClient;

    public ClientPersoController() {
        this.clientPersoService = new ClientPersoService();
    }

    @FXML
    public void initialize() {
        setupTableColumns();
        fetchClient(null);
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
        clientTable.getColumns().setAll(colId, colNom, colPrenom, colPhone, colEmail, colEdit, colDelete);
        colEdit.setVisible(false);
        colDelete.setVisible(false);
    }

    private void setupRowSelection() {
        clientTable.setRowFactory(tv -> {
            TableRow<Personne> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 1) {
                    Personne clickedRow = row.getItem();
                    showClientDetails(clickedRow);
                }
            });
            return row;
        });
    }

    private void showClientDetails(Personne client) {
        selectedClient = client;
        colEdit.setVisible(true);
        colDelete.setVisible(true);
    }

    public List<Personne> fetchClient(ActionEvent event) {
        List<Personne> clients = clientPersoService.getAll(ContactType.CLIENT);
        ObservableList<Personne> clientList = FXCollections.observableArrayList(clients);
        clientTable.setItems(clientList);
        return clients;
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
                Personne client = getTableView().getItems().get(getIndex());
                editClient(client);
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
                Personne client = getTableView().getItems().get(getIndex());
                try {
                    deleteClient(client);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            setGraphic(empty ? null : deleteButton);
        }
    }

    private void editClient(Personne client) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/Interfaces/PersonneModification.fxml"));
            Parent root = loader.load();
            PersonneModificationController modificationController = loader.getController();
            modificationController.setPersonne(client);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteClient(Personne client) throws IOException {
        System.out.println("Deleting client: " + client.getNom());
        PersonneService ps =new PersonneService();
        ps.deleteType(Math.toIntExact(client.getId()));
        fetchClient(null);

    }

    private Stage stage;
    private Scene scene;

    public void switchToClientEntro(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/Interfaces/ClientEntroprise.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addClient(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Does this person already exists");
        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == buttonTypeYes) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/Interfaces/FPex.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("/org/Interfaces/person3.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void OnReturnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/Interfaces/Dashbord.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
