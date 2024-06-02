package org.giefront;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Data;
import org.giefront.DTO.Entreprise;
import org.giefront.DTO.Personne;
import org.giefront.Service.EntrepriseService;
import org.giefront.Service.PersonneService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
@Data
public class MainInterfaceController implements Initializable {

    @FXML
    private  MenuItem deco;
    @FXML
    private  Button returnBtn;
    @FXML
    private  Button advancedsearchBtn;
    @FXML
    private  Button createBtn;
    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private BorderPane dashboardBorderPane;
    @FXML
    private ImageView IconeC;
    @FXML
    private TextArea detailsC;
    @FXML
    private Button adresse;
    @FXML
    private TextArea adresseDetails;

    @FXML
    private Label labelCRM;
    @FXML
    private Button searchButton;

    @FXML
    private MenuButton profileButton;

    @FXML
    private TextField idInput;

    @FXML
    private ImageView profileImageView;

    @FXML
    private ChoiceBox<String> menuChoiceBox;
    @FXML
    private ChoiceBox<String> typeChoiceBox;
    @FXML
    private ChoiceBox<String> sortChoiceBox;
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
    private TableColumn<Entreprise, String> C_Phone_E;

    @FXML
    private TableColumn<Personne, String> C_Phone_P;

    @FXML
    private TableColumn<Personne, String> C_Prenom_P;

    @FXML
    private TableColumn<Entreprise, String> C_RS_E;


    @FXML
    private TableColumn<Personne, String> c_Email_P;

    @FXML
    public TableView<Entreprise> tableView_E;
    @FXML
    public TableView<Personne> tableView_P;


    @FXML
    public Button closeBtn;

    @FXML
    public Button maximizeBtn;

    @FXML
    public Button minimizeBtn;


    EntrepriseService e = new EntrepriseService();
    PersonneService p = new PersonneService();


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        tableView_E.setVisible(false);
        tableView_P.setVisible(false);
        detailsC.setVisible(false);
        IconeC.setVisible(false);
        adresse.setVisible(false);
        adresseDetails.setVisible(false);
        remplirMenu();
        remplirFilters();
        remplirType();
        importIcon();
        tableView_P.setRowFactory(tv -> {
            TableRow<Personne> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 1) {
                    Personne clickedRow = row.getItem();
                    showPDetails(clickedRow);
                }
            });
            return row;
        });
        tableView_E.setRowFactory(tv -> {
            TableRow<Entreprise> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 1) {
                    Entreprise clickedRow = row.getItem();
                    showEDetails(clickedRow);

                }
            });
            return row;
        });


    }


    private void showPDetails(Personne clickedRow) {
        IconeC.setVisible(true);
        IconeC.setImage(new Image("https://e7.pngegg.com/pngimages/92/319/png-clipart-computer-icons-person-name-miscellaneous-computer-wallpaper.png"));
        detailsC.setVisible(true);
        detailsC.setEditable(false);
        detailsC.setText("Nom et Prenom: " + clickedRow.getNom() + " " + clickedRow.getPrenom() + '\n' + "Email: " + clickedRow.getEmail() + '\n' + "Num de tel: " + clickedRow.getPhone() + '\n');
        adresse.setVisible(true);

    }


    private void showEDetails(Entreprise clickedRow) {
        IconeC.setVisible(true);
        IconeC.setImage(new Image("https://cdn-icons-png.flaticon.com/512/5343/5343441.png"));
        detailsC.setVisible(true);
        detailsC.setEditable(false);
        detailsC.setText("Raison social: " + clickedRow.getRaisonSocial() + '\n' + "Email: " + clickedRow.getEmail() + '\n' + "Num de tel: " + clickedRow.getPhone() + '\n' + "Forme Juridique: " + clickedRow.getFormeJuridique());
        adresse.setVisible(true);



    }


    private void remplirMenu() {
        ObservableList<String> options = FXCollections.observableArrayList();
        options.add("Gestion du Stock");
        options.add("Ventes");
        menuChoiceBox.setItems(options);
    }

    private void remplirFilters() {
        ObservableList<String> options = FXCollections.observableArrayList(
                "By Id ↗", "By Id ↘",
                "Raison Social ↗", "Raison Social ↘",
                "By Name ↗", "By Name ↘", "None");
        sortChoiceBox.setItems(options);
    }

    private void remplirType() {
        ObservableList<String> options = FXCollections.observableArrayList();
        options.add("Entreprise");
        options.add("Personne");
        typeChoiceBox.setItems(options);
    }

    private void importIcon() {
        Image profileImage = new Image("https://static.vecteezy.com/ti/vecteur-libre/t2/2318271-icone-de-profil-utilisateur-vectoriel.jpg");
        profileImageView.setImage(profileImage);
    }

    @FXML
    private void onSearchButtonClicked(ActionEvent event) throws IOException {
        String selectedType = typeChoiceBox.getValue();
        switch (selectedType) {
            case "Personne":
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
                break;
            case "Entreprise":
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
                break;

        }
        applyFilter();

    }

    private void applyFilter() throws IOException {
        String filter = sortChoiceBox.getValue();
        String selectedType = typeChoiceBox.getValue();
        switch (filter) {
            case "By Id ↗":
                if ("Personne".equals(selectedType)) {
                    ObservableList<Personne> personneObservableList = FXCollections.observableList(p.sortById("asc"));
                    C_ID_P.setCellValueFactory(new PropertyValueFactory<>("id"));
                    c_Email_P.setCellValueFactory(new PropertyValueFactory<>("email"));
                    C_Phone_P.setCellValueFactory(new PropertyValueFactory<>("phone"));
                    C_Nom_P.setCellValueFactory(new PropertyValueFactory<>("nom"));
                    C_Prenom_P.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                    C_Adresse_P.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse().toString()));
                    tableView_P.setItems(personneObservableList);
                    tableView_P.setVisible(true);
                    tableView_E.setVisible(false);
                } else if ("Entreprise".equals(selectedType)) {

                    ObservableList<Entreprise> entrepriseObservableList = FXCollections.observableList(e.sortById("asc"));
                    C_ID_E.setCellValueFactory(new PropertyValueFactory<>("id"));
                    C_Email_E.setCellValueFactory(new PropertyValueFactory<>("email"));
                    C_Phone_E.setCellValueFactory(new PropertyValueFactory<>("phone"));
                    C_FJ_E.setCellValueFactory(new PropertyValueFactory<>("formeJuridique"));
                    C_RS_E.setCellValueFactory(new PropertyValueFactory<>("raisonSocial"));
                    C_Adresse_E.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse().toString()));
                    tableView_E.setItems(entrepriseObservableList);
                    tableView_E.setVisible(true);
                    tableView_P.setVisible(false);
                }
                break;
            case "By Id ↘":
                if ("Personne".equals(selectedType)) {
                    ObservableList<Personne> personneObservableList = FXCollections.observableList(p.sortById("desc"));
                    C_ID_P.setCellValueFactory(new PropertyValueFactory<>("id"));
                    c_Email_P.setCellValueFactory(new PropertyValueFactory<>("email"));
                    C_Phone_P.setCellValueFactory(new PropertyValueFactory<>("phone"));
                    C_Nom_P.setCellValueFactory(new PropertyValueFactory<>("nom"));
                    C_Prenom_P.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                    C_Adresse_P.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse().toString()));
                    tableView_P.setItems(personneObservableList);
                    tableView_P.setVisible(true);
                    tableView_E.setVisible(false);

                } else if ("Entreprise".equals(selectedType)) {

                    ObservableList<Entreprise> entrepriseObservableList = FXCollections.observableList(e.sortById("desc"));
                    C_ID_E.setCellValueFactory(new PropertyValueFactory<>("id"));
                    C_Email_E.setCellValueFactory(new PropertyValueFactory<>("email"));
                    C_Phone_E.setCellValueFactory(new PropertyValueFactory<>("phone"));
                    C_FJ_E.setCellValueFactory(new PropertyValueFactory<>("formeJuridique"));
                    C_RS_E.setCellValueFactory(new PropertyValueFactory<>("raisonSocial"));
                    C_Adresse_E.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse().toString()));
                    tableView_E.setItems(entrepriseObservableList);
                    tableView_E.setVisible(true);
                    tableView_P.setVisible(false);
                }
                break;
            case "Raison Social ↘":
                if ("Entreprise".equals(selectedType)) {
                    ObservableList<Entreprise> entrepriseObservableList = FXCollections.observableList(e.sortByRs("desc"));
                    C_ID_E.setCellValueFactory(new PropertyValueFactory<>("id"));
                    C_Email_E.setCellValueFactory(new PropertyValueFactory<>("email"));
                    C_Phone_E.setCellValueFactory(new PropertyValueFactory<>("phone"));
                    C_FJ_E.setCellValueFactory(new PropertyValueFactory<>("formeJuridique"));
                    C_RS_E.setCellValueFactory(new PropertyValueFactory<>("raisonSocial"));
                    C_Adresse_E.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse().toString()));
                    tableView_E.setItems(entrepriseObservableList);
                    tableView_E.setVisible(true);
                    tableView_P.setVisible(false);
                } else {
                    showMessage("Impossible d'effectuer cette action");
                }
                break;
            case "Raison Social ↗":
                if ("Entreprise".equals(selectedType)) {
                    ObservableList<Entreprise> entrepriseObservableList = FXCollections.observableList(e.sortByRs("asc"));
                    C_ID_E.setCellValueFactory(new PropertyValueFactory<>("id"));
                    C_Email_E.setCellValueFactory(new PropertyValueFactory<>("email"));
                    C_Phone_E.setCellValueFactory(new PropertyValueFactory<>("phone"));
                    C_FJ_E.setCellValueFactory(new PropertyValueFactory<>("formeJuridique"));
                    C_RS_E.setCellValueFactory(new PropertyValueFactory<>("raisonSocial"));
                    C_Adresse_E.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse().toString()));
                    tableView_E.setItems(entrepriseObservableList);
                    tableView_E.setVisible(true);
                    tableView_P.setVisible(false);
                } else {
                    showMessage("Impossible d'effectuer cette action");
                }
                break;
            case "By Name ↘":
                if ("Personne".equals(selectedType)) {
                    ObservableList<Personne> personneObservableList = FXCollections.observableList(p.sortBynom("desc"));
                    C_ID_P.setCellValueFactory(new PropertyValueFactory<>("id"));
                    c_Email_P.setCellValueFactory(new PropertyValueFactory<>("email"));
                    C_Phone_P.setCellValueFactory(new PropertyValueFactory<>("phone"));
                    C_Nom_P.setCellValueFactory(new PropertyValueFactory<>("nom"));
                    C_Prenom_P.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                    C_Adresse_P.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse().toString()));
                    tableView_P.setItems(personneObservableList);
                    tableView_P.setVisible(true);
                    tableView_E.setVisible(false);

                } else {
                    showMessage("Impossible d'effectuer cette action");
                }
                break;
            case "By Name ↗":
                if ("Personne".equals(selectedType)) {
                    ObservableList<Personne> personneObservableList = FXCollections.observableList(p.sortBynom("asc"));
                    C_ID_P.setCellValueFactory(new PropertyValueFactory<>("id"));
                    c_Email_P.setCellValueFactory(new PropertyValueFactory<>("email"));
                    C_Phone_P.setCellValueFactory(new PropertyValueFactory<>("phone"));
                    C_Nom_P.setCellValueFactory(new PropertyValueFactory<>("nom"));
                    C_Prenom_P.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                    C_Adresse_P.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse().toString()));
                    tableView_P.setItems(personneObservableList);
                    tableView_P.setVisible(true);
                    tableView_E.setVisible(false);
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



    public void onCreateBtnClick(ActionEvent event) {
        String selectedType = typeChoiceBox.getValue();
        try {
            if ("Personne".equals(selectedType)) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("personne.fxml"));
                Node node = fxmlLoader.load();
                mainAnchor.getChildren().setAll(node);
            } else if ("Entreprise".equals(selectedType)) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("entreprise.fxml"));
                Node node = fxmlLoader.load();
                mainAnchor.getChildren().setAll(node);
            }
            else {
                showMessage("Veuillez selectionner un type pour la création");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onAdress(ActionEvent event) {
        adresseDetails.setVisible(true);
        adresseDetails.setEditable(false);
        if (tableView_P.isVisible()) {
            Personne selectedPerson = tableView_P.getSelectionModel().getSelectedItem();
            if (selectedPerson != null) {
                adresseDetails.setText("Numero: " + selectedPerson.getAdresse().getNumero() + '\n' + "Quartier: " + selectedPerson.getAdresse().getQuartier() + '\n' + "Ville: " + selectedPerson.getAdresse().getVille());
            }
        } else if (tableView_E.isVisible()) {
            Entreprise selectedEntreprise = tableView_E.getSelectionModel().getSelectedItem();
            if (selectedEntreprise != null) {
                adresseDetails.setText("Numero: " + selectedEntreprise.getAdresse().getNumero() + '\n' + "Quartier: " + selectedEntreprise.getAdresse().getQuartier() + '\n' + "Ville: " + selectedEntreprise.getAdresse().getVille());
            }

        }
    }


    public void onAdvancedSearch(ActionEvent event) {
        if(typeChoiceBox.getValue().equals("Entreprise")){
            try {
                FXMLLoader f = new FXMLLoader();
                f.setLocation(getClass().getResource("AdvancedSearchE.fxml"));
                Node n = f.load();
                mainAnchor.getChildren().setAll(n);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if (typeChoiceBox.getValue().equals("Personne")){
            try {
                FXMLLoader f = new FXMLLoader();
                f.setLocation(getClass().getResource("AdvancedSearchP.fxml"));
                Node n = f.load();
                mainAnchor.getChildren().setAll(n);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void onDeco(ActionEvent event) {
        try {
            FXMLLoader f = new FXMLLoader();
            f.setLocation(getClass().getResource("Login.fxml"));
            Node n = f.load();
            mainAnchor.getChildren().setAll(n);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onMess(ActionEvent event) {
        try {
            FXMLLoader f = new FXMLLoader();
            f.setLocation(getClass().getResource("Messagerie.fxml"));
            Node n = f.load();
            mainAnchor.getChildren().setAll(n);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    public void OnReturnAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/org/Interfaces/Dashbord.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}