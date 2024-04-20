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
import org.giefront.Service.PersonneService;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

    public class PersonneController implements Initializable {
        @FXML
        private AnchorPane mainAnchor;
        @FXML
        private Button Return;
        @FXML
        private Button BtnAddP;

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

        @FXML
        void OnBtnPClick(ActionEvent event) {
            if (areFieldsFilled()) {
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Confirmation");
                confirmationAlert.setHeaderText("Êtes-vous sûr de vouloir ajouter cette personne ?");
                confirmationAlert.setContentText("Cliquez sur OK pour confirmer.");

                Optional<ButtonType> result = confirmationAlert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {


                    PersonneService personneService = new PersonneService();
                    Personne personne = new Personne();
                    personne.setEmail(Text_Field_Email.getText());
                    personne.setNom(Text_Field_N.getText());
                    personne.setPrenom(Text_Field_P.getText());
                    personne.setPhone(Text_Field_Po.getText());
                    Adresse a = new Adresse();
                    a.setVille(Text_Field_N1.getText());
                    a.setNumero(Text_Field_N111.getText());
                    a.setQuartier(Text_Field_N11.getText());
                    personne.setAdresse(a);
                    System.out.println(personne);

                    personneService.add(personne);

                    System.out.println("Client ajouté avec succès!");
                } else {
                    System.out.println("L'ajout de la personne a été annulé.");
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
        void onClick_Adresse(ActionEvent event) {
            anchorepane_Adresse.setVisible(!anchorepane_Adresse.isVisible());


        }
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            anchorepane_Adresse.setVisible(!anchorepane_Adresse.isVisible());

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

