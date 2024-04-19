package org.giefront;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.giefront.DTO.Adresse;
import org.giefront.DTO.Contact;
import org.giefront.DTO.Entreprise;
import org.giefront.DTO.Personne;
import org.giefront.Service.AdresseService;
import org.giefront.Service.ContactService;
import org.giefront.Service.EntrepriseService;
import org.giefront.Service.PersonneService;

public class ContactController implements Initializable {
    @FXML
    private TableColumn<Entreprise, String> C_Adresse_E;

    @FXML
    private TableColumn<Personne, String> C_Adresse_P;

    @FXML
    private TableColumn<Entreprise, String> C_Email_E;

    @FXML
    private TableColumn<Entreprise, String> C_FJ_E;

    @FXML
    private TableColumn<Entreprise, Long> C_ID_E;

    @FXML
    private TableColumn<Personne, Long> C_ID_P;

    @FXML
    private TableColumn<Personne, String> C_Nom_P;

    @FXML
    private TableColumn<Personne, String> C_Phone_E;

    @FXML
    private TableColumn<Personne, String> C_Phone_P;

    @FXML
    private TableColumn<Personne, String> C_Prenom_P;

    @FXML
    private TableColumn<Entreprise, String> C_RS_E;

    @FXML
    private ChoiceBox<String> ChoiceBox_Contact;

    @FXML
    private TableColumn<Personne, String> c_Email_P;

    @FXML
    private TableView<Entreprise> tableView_E;
    @FXML
    private TableView<Personne> tableView_P;
    EntrepriseService e=new EntrepriseService();
    PersonneService p=new PersonneService();
    ContactService c=new ContactService();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView_E.setVisible(false);
        tableView_P.setVisible(false);

        remplirChoiceBox();
//
        ChoiceBox_Contact.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.equals("Entreprise")) {
                    ObservableList<Entreprise> entrepriseObservableList = FXCollections.observableList(e.getAll());
                    C_ID_E.setCellValueFactory(new PropertyValueFactory<>("id"));
                    C_Email_E.setCellValueFactory(new PropertyValueFactory<>("email"));
                    C_Phone_E.setCellValueFactory(new PropertyValueFactory<>("phone"));
                    C_FJ_E.setCellValueFactory(new PropertyValueFactory<>("formeJuridique"));
                    C_RS_E.setCellValueFactory(new PropertyValueFactory<>("raisonSocial"));
                    C_Adresse_E.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse().toString()));
                    tableView_E.setItems(entrepriseObservableList);
                    tableView_E.setVisible(true);
                    tableView_P.setVisible(false);
                } else if (newValue.equals("Personne")) {
                    ObservableList<Personne> personneObservableList = FXCollections.observableList(p.getAll());
                    C_ID_P.setCellValueFactory(new PropertyValueFactory<>("id"));
                    c_Email_P.setCellValueFactory(new PropertyValueFactory<>("email"));
                    C_Phone_P.setCellValueFactory(new PropertyValueFactory<>("phone"));
                    C_Nom_P.setCellValueFactory(new PropertyValueFactory<>("nom"));
                    C_Prenom_P.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                    C_Adresse_P.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse().toString()));
                    tableView_P.setItems(personneObservableList);
                    tableView_P.setVisible(true);
                    tableView_E.setVisible(false);
                }
            }
        });
    }
    private void remplirChoiceBox() {
        ObservableList<String> options = FXCollections.observableArrayList();
        options.add("Entreprise");
        options.add("Personne");
        ChoiceBox_Contact.setItems(options);
    }

}