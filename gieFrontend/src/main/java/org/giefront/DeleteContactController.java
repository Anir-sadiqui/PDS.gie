package org.giefront;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import org.gieback.Entity.Contact;
import org.gieback.Entity.Entreprise;
import org.gieback.Entity.Personne;
import org.giefront.Service.EntrepriseService;
import org.giefront.Service.PersonneService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DeleteContactController {

    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private TextField idInput;
    @FXML
    private ListView<Contact> contactsList;

    private PersonneService personService = new PersonneService();
    private EntrepriseService entrepriseService = new EntrepriseService();

    @FXML
    public void initialize() {
        choiceBox.getItems().addAll("Personne", "Entreprise");
    }

    @FXML
    protected void onSearch() {
        String id = idInput.getText();
        if (id.isEmpty()) {
            showAlert("Veuillez donner un ID");
            return;
        }

        String selectedType = choiceBox.getValue();
        ObservableList<Contact> observableList;



        try {
            int parsedId = Integer.parseInt(id);
            if ("Personne".equals(selectedType)) {
                Personne person = personService.getById(parsedId);
                if (person == null) {
                    showAlert("Contact introuvable");
                } else {
                    List<Personne> ListP = null;
                    Personne p  = personService.getById(parsedId);
                    ListP.add(p);
                    observableList = FXCollections.observableArrayList(ListP);
                    contactsList.setItems(observableList);
                }
            } else if ("Entreprise".equals(selectedType)) {
                Entreprise entreprise = entrepriseService.getById(parsedId);
                if (entreprise == null) {
                    showAlert("Contact introuvable");
                } else {
                    List<Entreprise> ListE = null;
                    Entreprise e  = entrepriseService.getById(parsedId);
                    ListE.add(e);
                    observableList = FXCollections.observableArrayList(entreprise);
                    contactsList.setItems(observableList);
                }
            }
        } catch (NumberFormatException e) {
            showAlert("L'ID doit être un nombre.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onDelete() {
        String id = idInput.getText();
        if (id.isEmpty()) {
            showAlert("Veuillez donner un ID");
            return;
        }

        String selectedType = choiceBox.getValue();

        try {
            int parsedId = Integer.parseInt(id);
            if ("Personne".equals(selectedType)) {
                personService.getById(parsedId);
            } else if ("Entreprise".equals(selectedType)) {
                entrepriseService.deleteEntreprise(parsedId);
            }
        } catch (NumberFormatException e) {
            showAlert("L'ID doit être un nombre.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    protected void onModify(){
        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    if (newValue.equals("Personne")) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("personneModification.fxml"));
                            Parent root = loader.load();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (newValue.equals("Entreprise")) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("entrepriseModification.fxml"));
                            Parent root = loader.load();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            });
    }
}