package org.giefront;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.giefront.DTO.Contact;
import org.giefront.DTO.Entreprise;
import org.giefront.DTO.Personne;
import org.giefront.Service.EntrepriseService;
import org.giefront.Service.PersonneService;

import java.io.IOException;
import java.util.List;

public class SearchContactController {
    @FXML
    private Button addButton;

    @FXML
    private Button advancedSearchButton;

    @FXML
    private ChoiceBox<String> typeContact;

    @FXML
    private Button searchButton;

    @FXML
    private ChoiceBox<String> filters;

    @FXML
    private Text filterText;

    @FXML
    private Text searchText;

    @FXML
    private ListView<Contact> contactListView;

    private PersonneService personneService = new PersonneService();
    private EntrepriseService entrepriseService = new EntrepriseService();

    @FXML
    public void initialize() {
        remplirChoiceBox();
        remplirChoiceBoxFiltres();
    }

    private void remplirChoiceBox() {
        ObservableList<String> options = FXCollections.observableArrayList("Personne", "Entreprise");
        typeContact.setItems(options);
    }

    private void remplirChoiceBoxFiltres() {
        ObservableList<String> options = FXCollections.observableArrayList(
                "By Id ↗", "By Id ↘",
                "Raison Social ↗", "Raison Social ↘",
                "By Name ↗", "By Name ↘");
        filters.setItems(options);
    }

    @FXML
    private void onSearchButtonClicked(ActionEvent event) {
        String selectedType = typeContact.getValue();
        switch (selectedType) {
            case "Personne":
                List<Personne> ListP = null;
                ListP = personneService.getAll();
                ObservableList<Contact> observableList = FXCollections.observableArrayList(ListP);
                contactListView.setItems(observableList);
                break;
            case "Entreprise":
                List<Entreprise> ListE = null;
                ListE = entrepriseService.getAll();
                ObservableList<Contact> observableListE = FXCollections.observableArrayList(ListE);
                contactListView.setItems(observableListE);
                break;
        }
        applyFilter();
    }


    private void applyFilter() {
        String filter = filters.getValue();
        String selectedType = typeContact.getValue();
        switch (filter) {
            case "By Id ↗":
                if ("Personne".equals(selectedType)) {
                    List<Personne> sortedList = null;
                    try {
                        sortedList = personneService.sortById("asc");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    ObservableList<Contact> observableList = FXCollections.observableArrayList(sortedList);
                    contactListView.setItems(observableList);
                } else if ("Entreprise".equals(selectedType)) {
                    List<Entreprise> sortedList = null;
                    try {
                        sortedList = entrepriseService.sortById("asc");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    ObservableList<Contact> observableList = FXCollections.observableArrayList(sortedList);
                    contactListView.setItems(observableList);
                }
                break;
            case "By Id ↘":
                 if ("Personne".equals(selectedType)) {
                     List<Personne> sortedList = null;
                     try {
                         sortedList = personneService.sortById("desc");
                     } catch (IOException e) {
                         throw new RuntimeException(e);
                     }
                     ObservableList<Contact> observableList = FXCollections.observableArrayList(sortedList);
                     contactListView.setItems(observableList);
                } else if ("Entreprise".equals(selectedType)) {
                     List<Entreprise> sortedList = null;
                     try {
                         sortedList = entrepriseService.sortById("desc");
                     } catch (IOException e) {
                         throw new RuntimeException(e);
                     }
                     ObservableList<Contact> observableList = FXCollections.observableArrayList(sortedList);
                     contactListView.setItems(observableList);
                }
                break;
            case "Raison Social ↘":
                if ("Entreprise".equals(selectedType)) {
                    List<Entreprise> sortedList = null;
                    try {
                        sortedList = entrepriseService.sortByRs("desc");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    ObservableList<Contact> observableList = FXCollections.observableArrayList(sortedList);
                    contactListView.setItems(observableList);
                } else {
                    showMessage("Impossible d'effectuer cette action");
                }
                break;
            case "Raison Social ↗":
                if ("Entreprise".equals(selectedType)) {
                    List<Entreprise> sortedList = null;
                    try {
                        sortedList = entrepriseService.sortByRs("asc");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    ObservableList<Contact> observableList = FXCollections.observableArrayList(sortedList);
                    contactListView.setItems(observableList);
                } else {
                    showMessage("Impossible d'effectuer cette action");
                }
                break;
            case "By Name ↘":
                if ("Personne".equals(selectedType)) {
                    List<Personne> sortedList = null;
                    try {
                        sortedList = personneService.sortBynom("desc");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    ObservableList<Contact> observableList = FXCollections.observableArrayList(sortedList);
                    contactListView.setItems(observableList);
                } else {
                    showMessage("Impossible d'effectuer cette action");
                }
                break;
            case "By Name ↗":
                if ("Personne".equals(selectedType)) {
                    List<Personne> sortedList = null;
                    try {
                        sortedList = personneService.sortBynom("asc");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    ObservableList<Contact> observableList = FXCollections.observableArrayList(sortedList);
                    contactListView.setItems(observableList);
                } else {
                    showMessage("Impossible d'effectuer cette action");
                }
                break;
        }
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
