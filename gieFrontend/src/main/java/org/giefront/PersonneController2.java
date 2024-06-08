package org.giefront;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.giefront.DTO.Adresse;
import org.giefront.DTO.ContactType;
import org.giefront.DTO.Personne;
import org.giefront.Service.PersonneService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PersonneController2 implements Initializable {
    @FXML
    private Button BtnImage;
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
    private String imagePath ;


    private final PersonneService personneService = new PersonneService();

    @FXML
    void OnBtnPClick(ActionEvent event) {
        if (areFieldsFilled()) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Are you sure you want to add this person?");
            confirmationAlert.setContentText("Click OK to confirm.");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Personne personne = new Personne();
                personne.setEmail(Text_Field_Email.getText());
                personne.setNom(Text_Field_N.getText());
                personne.setPrenom(Text_Field_P.getText());
                personne.setPhone(Text_Field_Po.getText());
                personne.setContactType(choiceBox.getValue());
                personne.setImagePath(imagePath);
                Adresse a = new Adresse();
                a.setVille(Text_Field_N1.getText());
                a.setNumero(Text_Field_N111.getText());
                a.setQuartier(Text_Field_N11.getText());
                personne.setAdresse(a);

                personneService.add(personne);

                System.out.println("Person added successfully!");
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Addition Successful");
                successAlert.setHeaderText(null);
                successAlert.setContentText("The person has been added successfully.");
                successAlert.showAndWait();

                clearFields();
            } else {
                System.out.println("Adding the person was canceled.");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incomplete Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields!");
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

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    public void retour(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/Interfaces/MainInterface.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setPersonne(Personne personne) {
        Text_Field_N.setText(personne.getNom());
        Text_Field_P.setText(personne.getPrenom());
        Text_Field_Email.setText(personne.getEmail());
        Text_Field_Po.setText(personne.getPhone());
    }


    @FXML
    void Addimg(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(BtnImage.getScene().getWindow()); // Utiliser la fenêtre du bouton comme parent
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imagePath = selectedFile.toURI().toString(); // Enregistrer le chemin de l'image
        }
    }
}