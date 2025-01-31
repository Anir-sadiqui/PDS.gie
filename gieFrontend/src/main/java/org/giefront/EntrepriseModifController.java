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
import org.giefront.Service.AdresseService;
import org.giefront.Service.EntrepriseService;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

public class EntrepriseModifController implements Initializable {

    @FXML
    private Button BtnModE;
    @FXML
    private Button BtnRetour;
    @FXML
    private AnchorPane mainAnchor;

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


    private Entreprise currentEntroprise;

    public void setEntroprise(Entreprise entreprise) {
        this.currentEntroprise = entreprise;
        if (entreprise != null) {
            Text_Field_RS.setText(entreprise.getRaisonSocial());
            Text_Field_FJ.setText(entreprise.getFormeJuridique());
            Text_Field_Email.setText(entreprise.getEmail());
            Text_Field_Po.setText(entreprise.getPhone());
            if (entreprise.getAdresse() != null) {
                Text_Field_V.setText(entreprise.getAdresse().getVille());
                Text_Field_Q.setText(entreprise.getAdresse().getQuartier());
                Text_Field_N.setText(entreprise.getAdresse().getNumero());
            }
        }
    }

    @FXML
    public void OnMod() {
        if (areEntrFieldsFilled()) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Are you sure you want to modify this company?");
            confirmationAlert.setContentText("Click OK to confirm.");
            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                EntrepriseService entrepriseService = new EntrepriseService();
                Entreprise entr = currentEntroprise;
                AdresseService as = new AdresseService();
                Adresse a = entr.getAdresse();
                HashMap<String,String> attributs = new HashMap<>();
                HashMap<String,String> attributsA = new HashMap<>();
                if (!Text_Field_Email.getText().isEmpty()){
                    attributs.put("email",Text_Field_Email.getText());
                }
                if (!Text_Field_RS.getText().isEmpty()){
                    attributs.put("raisonSocial",Text_Field_RS.getText());
                }
                if (!Text_Field_FJ.getText().isEmpty()){
                    attributs.put("formeJuridique",Text_Field_FJ.getText());
                }
                if (!Text_Field_Po.getText().isEmpty()){
                    attributs.put("phone",Text_Field_Po.getText());
                }
                if (!Text_Field_V.getText().isEmpty()){
                    attributsA.put("ville",Text_Field_V.getText());
                }
                if (!Text_Field_N.getText().isEmpty()){
                    attributsA.put("numero",Text_Field_N.getText());
                }
                if (!Text_Field_Q.getText().isEmpty()){
                    attributsA.put("quartier",Text_Field_Q.getText());
                }

                try {
                    entrepriseService.modifierEntreprise((long) Math.toIntExact(entr.getId()),attributs);
                    as.modifierAdresse(Math.toIntExact(a.getAdresse_id()),attributsA);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Company modified successfully!"); // Message changed to English
            } else {
                System.out.println("Modifying the company was canceled."); // Message changed to English
            }

        }
    }


    @FXML
    void onClick_Adresse() {
        anchorepane_Adresse.setVisible(!anchorepane_Adresse.isVisible());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        anchorepane_Adresse.setVisible(false);

    }

    private boolean areEntrFieldsFilled() {
        return !Text_Field_Email.getText().isEmpty() ||
                !Text_Field_RS.getText().isEmpty() ||
                !Text_Field_V.getText().isEmpty() ||
                !Text_Field_Q.getText().isEmpty() ||
                !Text_Field_N.getText().isEmpty() ||
                !Text_Field_FJ.getText().isEmpty() ||
                !Text_Field_Po.getText().isEmpty();
    }

    public void OnReturn(ActionEvent event) {
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
