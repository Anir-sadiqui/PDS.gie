package org.giefront;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.giefront.DTO.Adresse;
import org.giefront.DTO.ContactType;
import org.giefront.DTO.Entreprise;
import org.giefront.DTO.Personne;
import org.giefront.Service.EntrepriseService;
import org.giefront.Service.PersonneService;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EntrepriseController implements Initializable {

    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private Button Return;
    @FXML
    private Button BtnAddP;
    @FXML
    private ChoiceBox<ContactType> choiceBox; // Ensure this is properly initialized
    @FXML
    private TextField Text_Field_Email;
    @FXML
    private TextField Text_Field_N;
    @FXML
    private TextField Text_Field_N1;
    @FXML
    private TextField Text_Field_N11;
    @FXML
    private TextField Text_Field_N111;
    @FXML
    private TextField Text_Field_P;
    @FXML
    private TextField Text_Field_Po;
    @FXML
    private Button adresse;
    @FXML
    private AnchorPane anchorepane_Adresse;



    private final EntrepriseService personneService = new EntrepriseService();

    @FXML
    void OnBtnPClick(ActionEvent event) {
        if (areFieldsFilled()) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Êtes-vous sûr de vouloir ajouter cette Entreprise ?");
            confirmationAlert.setContentText("Cliquez sur OK pour confirmer.");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Entreprise entreprise = new Entreprise();
                entreprise.setEmail(Text_Field_Email.getText());
                entreprise.setRaisonSocial(Text_Field_N.getText());
                entreprise.setFormeJuridique(Text_Field_P.getText());
                entreprise.setPhone(Text_Field_Po.getText());
                entreprise.setContactType(choiceBox.getValue());

                Adresse a = new Adresse();
                a.setVille(Text_Field_N1.getText());
                a.setNumero(Text_Field_N111.getText());
                a.setQuartier(Text_Field_N11.getText());
                entreprise.setAdresse(a);

                personneService.add(entreprise);

                System.out.println("Entreprise ajoutée avec succès!");
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Ajout réussi");
                successAlert.setHeaderText(null);
                successAlert.setContentText("L'entreprise a été ajoutée avec succès.");
                successAlert.showAndWait();

                clearFields();
            } else {
                System.out.println("L'ajout de l'entreprise a été annulé.");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs incomplets");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
        }
    }

    private void clearFields() {
        Text_Field_Email.clear();
        Text_Field_N.clear();
        Text_Field_N1.clear();
        Text_Field_N11.clear();
        Text_Field_N111.clear();
        Text_Field_P.clear();
        Text_Field_Po.clear();
    }

    @FXML
    void onClick_Adresse(ActionEvent event) {
        anchorepane_Adresse.setVisible(!anchorepane_Adresse.isVisible());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        anchorepane_Adresse.setVisible(false); // Initially, this should be hidden
        choiceBox.getItems().addAll(ContactType.FOURNISSEUR, ContactType.CLIENT);
    }



    private boolean areFieldsFilled() {
        return !Text_Field_Email.getText().isEmpty() &&
                !Text_Field_N.getText().isEmpty() &&
                !Text_Field_N1.getText().isEmpty() &&
                !Text_Field_N11.getText().isEmpty() &&
                !Text_Field_N111.getText().isEmpty() &&
                !Text_Field_P.getText().isEmpty() &&
                !Text_Field_Po.getText().isEmpty();
    }

//    public void onReturn(ActionEvent event) {
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            fxmlLoader.setLocation(getClass().getResource("MainInterface.fxml"));
//            Node node = fxmlLoader.load();
//            mainAnchor.getChildren().setAll(node);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    public void retour(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/giefront/fournisseurEntro.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
