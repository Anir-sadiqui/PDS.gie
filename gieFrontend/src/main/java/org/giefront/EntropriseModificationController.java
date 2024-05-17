package org.giefront.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
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

public class EntropriseModificationController implements IEntropriseModificationController {
    public static Entreprise ents ;


    @FXML
    private TextField Text_FJ;

    @FXML
    private TextField Text_RS;

    @FXML
    private TextField Text_id;

    @FXML
    private TextField Text_Field_Email;

    @FXML
    private TextField Text_Field_Po;

    @FXML
    private TextField Text_Field_V;

    @FXML
    private TextField Text_Field_Q;

    @FXML
    private TextField Text_Field_N;



    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private TableColumn<Entreprise, String> C_Adresse_E;



    @FXML
    private TableColumn<Entreprise, String> C_Email_E;

    @FXML
    private TableColumn<Entreprise, String> C_FJ_E;

    @FXML
    private TableColumn<Entreprise, Long> C_ID_E;

    @FXML
    private TableColumn<String, String> C_Phone_E;


    @FXML
    private TableColumn<Entreprise, String> C_RS_E;


    @FXML
    private Button adresse;

    @FXML
    private AnchorPane anchorepane_Adresse;

    private Entreprise currentEntroprise;

    public void setEntroprise(Entreprise entreprise) {
        this.currentEntroprise = entreprise;
        if (entreprise != null) {
            Text_RS.setText(entreprise.getRaisonSocial());
            Text_FJ.setText(entreprise.getFormeJuridique());
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
    public void OnMod(ActionEvent event) {
        if (areFieldsFilled()) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Êtes-vous sûr de vouloir modifier cette entroprise ?");
            confirmationAlert.setContentText("Cliquez sur OK pour confirmer.");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                EntrepriseService entrepriseService = new EntrepriseService();
                //Personne per = PersonneModificationController.pers;
                Entreprise ent  = currentEntroprise ;
                AdresseService as = new AdresseService();
                Adresse a = ent.getAdresse();
                HashMap<String,String> attributs = new HashMap<>();
                HashMap<String,String> attributsA = new HashMap<>();
                if (!Text_Field_Email.getText().isEmpty()){
                    attributs.put("email",Text_Field_Email.getText());
                }
                if (!Text_Field_N.getText().isEmpty()){
                    attributs.put("Nom",Text_Field_N.getText());
                }
                if (!Text_FJ.getText().isEmpty()){
                    attributs.put("Prenom",Text_FJ.getText());
                }
                if (!Text_Field_Po.getText().isEmpty()){
                    attributs.put("phone",Text_Field_Po.getText());
                }
                if (!Text_Field_V.getText().isEmpty()){
                    attributsA.put("ville",Text_Field_V.getText());
                }
                if (!Text_Field_Q.getText().isEmpty()){
                    attributsA.put("quartier",Text_Field_Q.getText());
                }
                if (!Text_Field_N.getText().isEmpty()){
                    attributsA.put("numero",Text_Field_N.getText());
                }

                try {
                    entrepriseService.modifierEntreprise(Long.valueOf(ent.getId()),attributs);
                    as.modifierAdresse(Math.toIntExact(a.getAdresse_id()),attributsA);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Client modifie avec succès!");
            } else {
                System.out.println("La modification de la personne a été annulé.");
            }
        }
    }

    @FXML
    void onClick_Adresse(ActionEvent event) {
        anchorepane_Adresse.setVisible(!anchorepane_Adresse.isVisible());

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        anchorepane_Adresse.setVisible(false);

    }

    private boolean areFieldsFilled() {
        return !Text_Field_Email.getText().isEmpty() ||
                !Text_RS.getText().isEmpty() ||
                !Text_Field_V.getText().isEmpty() ||
                !Text_Field_Q.getText().isEmpty() ||
                !Text_Field_N.getText().isEmpty() ||
                !Text_FJ.getText().isEmpty() ||
                !Text_Field_Po.getText().isEmpty();
    }
    @FXML
    private void onReturn(ActionEvent event) {
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
