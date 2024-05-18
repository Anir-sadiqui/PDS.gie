package org.giefront;


import javafx.scene.control.Alert;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import org.giefront.DTO.Achat;
import org.giefront.DTO.AchatDetail;
import org.giefront.DTO.Contact;
import org.giefront.Service.AchatService;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AchatController implements Initializable {

    @FXML
    private TableView<Achat> achatsTableView; // Define TableView for displaying achats

    private final AchatService achatService = new AchatService();

    @FXML
    private TableColumn<Achat, ?> DateColmn;

    @FXML
    private DatePicker FiltrerParDateDatePicker;

    @FXML
    private ChoiceBox<?> FiltrerParFournisseurComboBox;

    @FXML
    private TableColumn<Achat, ?> FornisseurColmn;

    @FXML
    private TableColumn<Achat, ?> IDColmn;

    @FXML
    private TableColumn<Achat, ?> PrixTotaleColmn;

    @FXML
    private TableColumn<Achat, ?> ProduitColmn;

    @FXML
    private TableColumn<Achat, ?> QuantiteColmn;

    @FXML
    private Button btnAfficherAchat;

    @FXML
    private Button btnAjouterAchat;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnSupprimer;


    @FXML
    void afficherAchat(ActionEvent event) {
        // Step 1: Retrieve the list of purchases from the database
        List<Achat> achats = retrieveAchatsFromDatabase();

        // Step 2: Populate the table view with the retrieved purchases
        populateAchatsTableView(achats);
    }

    private List<Achat> retrieveAchatsFromDatabase() {

        return achatService.getAll(); // Example method to retrieve all purchases
    }

    private void populateAchatsTableView(List<Achat> achats) {
        // Clear any existing items in the table view
        achatsTableView.getItems().clear();


        ObservableList<Achat> observableList = FXCollections.observableArrayList(achats);


        achatsTableView.setItems(observableList);


        TableColumn<Achat, Long> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Achat, LocalDate> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));

        TableColumn<Achat, AchatDetail> productColumn = new TableColumn<>("Product");
        productColumn.setCellValueFactory(new PropertyValueFactory<>("details"));

        TableColumn<Achat, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Achat, Contact> supplierColumn = new TableColumn<>("Supplier");
        supplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplier"));

        TableColumn<Achat, Double> totalPriceColumn = new TableColumn<>("Total Price");
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        // Add the columns to the table view
        achatsTableView.getColumns().setAll(idColumn, dateColumn, productColumn, quantityColumn, supplierColumn, totalPriceColumn);
    }


    @FXML
    void filtrer(ActionEvent event) {
        // Code to handle "Filtrer" action
    }

    @FXML
    void modifier(ActionEvent event) {
        // Code to handle "Modifier" button action
    }
    @FXML
    void supprimer(ActionEvent event) {
        // Assuming you have access to the selected Achat's ID
        int idToDelete = 123; // Replace 123 with the actual ID of the Achat to delete

        try {
            AchatService achatService = new AchatService();
            achatService.deleteAchatById(idToDelete);
            showAlert("Achat supprimé avec succès!");

            // Optionally, refresh the table view or update the UI
            // Example: achatsTableView.refresh();
        } catch (IOException e) {
            showAlert("Erreur lors de la suppression de l'achat: " + e.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialization code if needed
    }

    @FXML
    void ajouterAchat(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/giefront/NouveauAchat.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);

            // Get the stage from the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the scene on the stage
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
