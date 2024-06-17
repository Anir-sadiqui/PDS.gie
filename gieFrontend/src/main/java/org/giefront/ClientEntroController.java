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
import org.giefront.DTO.Personne;
import org.giefront.Service.*;
import org.giefront.TestFront;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ClientEntroController {

    private Stage stage ;
    private Scene scene ;
    private FXMLLoader fxmlLoader ;


    public void switchToClientPerso(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(TestFront.class.getResource("/org/Interfaces/ClientPerson.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow() ;
        scene = new Scene(fxmlLoader.load()) ;
        stage.setScene(scene);
        stage.show();

    }

    public void addClient(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Does this company already exists");
        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);
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
            Parent root = FXMLLoader.load(getClass().getResource("/org/Interfaces/entroprise3.fxml"));
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
    private TableView<Entreprise> clientTable;

    @FXML
    private TableColumn<Entreprise, Void> colEdit, colDelete;

    @FXML
    private TableColumn<Entreprise, Long> colId;

    @FXML
    private TableColumn<Entreprise, String> colFormJ, colRaisonS, colPhone, colEmail;

    private ClientEntroService clientEntroService;
    private Entreprise selectedClient;

    private final Button editButton = new Button("Edit");
    private final Button deleteButton = new Button("Delete");

    public ClientEntroController() {
        this.clientEntroService = new ClientEntroService();
    }

    @FXML
    public void initialize() {
        setupTableColumns();
        fetchClient(null);
        setupRowSelection();
    }

    private void setupTableColumns() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFormJ.setCellValueFactory(new PropertyValueFactory<>("formeJuridique"));
        colRaisonS.setCellValueFactory(new PropertyValueFactory<>("raisonSocial"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        colEdit.setCellFactory(param -> new ClientEntroController.EditButtonCell());
        colDelete.setCellFactory(param -> new ClientEntroController.DeleteButtonCell());

        clientTable.getColumns().setAll(colId, colFormJ, colRaisonS, colPhone, colEmail, colEdit, colDelete);

        // Initially hide the Edit and Delete buttons
        colEdit.setVisible(false);
        colDelete.setVisible(false);
    }

    private void setupRowSelection() {
        clientTable.setRowFactory(tv -> {
            TableRow<Entreprise> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 1) {
                    Entreprise clickedRow = row.getItem();
                    showClientDetails(clickedRow);
                }
            });
            return row;
        });
    }

    private void showClientDetails(Entreprise client) {
        selectedClient = client;
        colEdit.setVisible(true);
        colDelete.setVisible(true);
    }

    public List<Entreprise> fetchClient(ActionEvent event) {
        List<Entreprise> clients = clientEntroService.getAll(ContactType.CLIENT);
        ObservableList<Entreprise> clientList = FXCollections.observableArrayList(clients);
        clientTable.setItems(clientList);
        return clients;
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
                Entreprise client = getTableView().getItems().get(getIndex());
                editClient(client);
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
                Entreprise client = getTableView().getItems().get(getIndex());
                try {
                    deleteClient(client);
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

    private void editClient(Entreprise client) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/Interfaces/EntrepriseModification.fxml"));
            Parent root = loader.load();

            // Get the controller for PersonneModification.fxml
            EntrepriseModifController modificationController = loader.getController();

            // Pass the selected Personne data to the modification controller
            modificationController.setEntroprise(client);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteClient(Entreprise client) throws IOException {
        System.out.println("Deleting client: " + client.getRaisonSocial());

        EntrepriseService es = new EntrepriseService();
        es.deleteType(Math.toIntExact(client.getId()));
        fetchClient(null); // Refresh the table after deletion

    }

    @FXML
    private void showAddressFields() {
        quartier.setVisible(true);
        ville.setVisible(true);
        num.setVisible(true);
    }


}
