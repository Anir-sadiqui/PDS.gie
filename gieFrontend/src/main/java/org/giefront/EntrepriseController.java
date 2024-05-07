package org.giefront;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import org.giefront.DTO.Adresse;
import org.giefront.DTO.Entreprise;
import org.giefront.Service.FournisseurPersoService;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EntrepriseController implements Initializable {

    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private Button BtnAddP;

    @FXML
    private TextField Text_Field_Email;

    @FXML
    private TextField Text_Field_FJ;

    @FXML
    private TextField Text_Field_N;

    @FXML
    private TextField Text_Field_Po;

    @FXML
    private TextField Text_Field_Q;

    @FXML
    private TextField Text_Field_RS;

    @FXML
    private TextField Text_Field_V;

    @FXML
    private Button adresse;

    @FXML
    private AnchorPane anchorepane_Adresse;

    @FXML
    void OnBtnPClick() {
        if (areFieldsFilled()) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Êtes-vous sûr de vouloir ajouter cette entreprise ?");
            confirmationAlert.setContentText("Cliquez sur OK pour confirmer.");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                FournisseurPersoService fournisseurPersoService = new FournisseurPersoService();
                Entreprise entreprise = new Entreprise();
                entreprise.setEmail(Text_Field_Email.getText());
                entreprise.setRaisonSocial(Text_Field_RS.getText());
                entreprise.setFormeJuridique(Text_Field_FJ.getText());
                entreprise.setPhone(Text_Field_Po.getText());
                Adresse a = new Adresse();
                a.setVille(Text_Field_V.getText());
                a.setNumero(Text_Field_Q.getText());
                a.setQuartier(Text_Field_N.getText());
                entreprise.setAdresse(a);
                System.out.println(entreprise);

                fournisseurPersoService.add(entreprise);

                System.out.println("Entreprise ajoutée avec succès!");
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

    @FXML
    void onClick_Adresse() {
        anchorepane_Adresse.setVisible(!anchorepane_Adresse.isVisible());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private boolean areFieldsFilled() {
        return !Text_Field_Email.getText().isEmpty() &&
                !Text_Field_RS.getText().isEmpty() &&
                !Text_Field_V.getText().isEmpty() &&
                !Text_Field_Q.getText().isEmpty() &&
                !Text_Field_N.getText().isEmpty() &&
                !Text_Field_FJ.getText().isEmpty() &&
                !Text_Field_Po.getText().isEmpty();
    }

    public void onReturn(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("MainInterface.fxml"));
            Node node = fxmlLoader.load();
            mainAnchor.getChildren().setAll(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
