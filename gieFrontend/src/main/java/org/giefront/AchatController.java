package org.giefront;

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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AchatController implements Initializable {

    @FXML
    private TableColumn<?, ?> DateColmn;

    @FXML
    private DatePicker FiltrerParDateDatePicker;

    @FXML
    private ChoiceBox<?> FiltrerParFournisseurComboBox;

    @FXML
    private TableColumn<?, ?> FornisseurColmn;

    @FXML
    private TableColumn<?, ?> IDColmn;

    @FXML
    private TableColumn<?, ?> PrixTotaleColmn;

    @FXML
    private TableColumn<?, ?> ProduitColmn;

    @FXML
    private TableColumn<?, ?> QuantiteColmn;

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
        // Code to handle "Supprimer" button action
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialization code if needed
    }

    @FXML
    void ajouterAchat(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NouveauAchat.fxml"));
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
