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
import org.giefront.DTO.Personne;
import org.giefront.Service.AdresseService;
import org.giefront.Service.PersonneService;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

public class PersonneModificationController implements Initializable {

    public static Personne pers ;


    @FXML
    private AnchorPane mainAnchor;
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

    private Personne currentPersonne;

    public void setPersonne(Personne personne) {
        this.currentPersonne = personne;
        if (personne != null) {
            Text_Field_N.setText(personne.getNom());
            Text_Field_P.setText(personne.getPrenom());
            Text_Field_Email.setText(personne.getEmail());
            Text_Field_Po.setText(personne.getPhone());
            if (personne.getAdresse() != null) {
                Text_Field_N1.setText(personne.getAdresse().getVille());
                Text_Field_N11.setText(personne.getAdresse().getQuartier());
                Text_Field_N111.setText(personne.getAdresse().getNumero());
            }
        }
    }

    @FXML
    public void OnMod(ActionEvent event) {
        if (areFieldsFilled()) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Êtes-vous sûr de vouloir modifier cette personne ?");
            confirmationAlert.setContentText("Cliquez sur OK pour confirmer.");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                PersonneService personneService = new PersonneService();
                //Personne per = PersonneModificationController.pers;
                Personne per  = currentPersonne ;
                AdresseService as = new AdresseService();
                Adresse a = per.getAdresse();
                HashMap<String,String> attributs = new HashMap<>();
                HashMap<String,String> attributsA = new HashMap<>();
                if (!Text_Field_Email.getText().isEmpty()){
                    attributs.put("email",Text_Field_Email.getText());
                }
                if (!Text_Field_N.getText().isEmpty()){
                    attributs.put("Nom",Text_Field_N.getText());
                }
                if (!Text_Field_P.getText().isEmpty()){
                    attributs.put("Prenom",Text_Field_P.getText());
                }
                if (!Text_Field_Po.getText().isEmpty()){
                    attributs.put("phone",Text_Field_Po.getText());
                }
                if (!Text_Field_N1.getText().isEmpty()){
                    attributsA.put("ville",Text_Field_N1.getText());
                }
                if (!Text_Field_N11.getText().isEmpty()){
                    attributsA.put("quartier",Text_Field_N11.getText());
                }
                if (!Text_Field_N111.getText().isEmpty()){
                    attributsA.put("numero",Text_Field_N111.getText());
                }

                try {
                    personneService.modifierPersonne(String.valueOf(per.getId()),attributs);
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
                !Text_Field_N.getText().isEmpty() ||
                !Text_Field_N1.getText().isEmpty() ||
                !Text_Field_N11.getText().isEmpty() ||
                !Text_Field_N111.getText().isEmpty() ||
                !Text_Field_P.getText().isEmpty() ||
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
