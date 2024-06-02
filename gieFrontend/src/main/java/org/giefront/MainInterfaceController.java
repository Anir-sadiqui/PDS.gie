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
        remplirType();
        getAllP();
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
        if (clickedRow.getImagePath() != null){
            IconeC.setImage(new Image(clickedRow.getImagePath()));
        }
        else {
            IconeC.setImage(new Image("https://e7.pngegg.com/pngimages/92/319/png-clipart-computer-icons-person-name-miscellaneous-computer-wallpaper.png"));
        }
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

    private void remplirType() {
        ObservableList<String> options = FXCollections.observableArrayList();
        options.add("Enterprise");
        options.add("Person");
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
            case "Person":
                getAllP();
                break;
            case "Enterprise":
               getAllE();
                break;
        }
    }
    public void getAllP(){
        ObservableList<Personne> personneObservableList = FXCollections.observableList(p.getAll());
        C_ID_P.setCellValueFactory(new PropertyValueFactory<>("id"));
        c_Email_P.setCellValueFactory(new PropertyValueFactory<>("email"));
        C_Phone_P.setCellValueFactory(new PropertyValueFactory<>("phone"));
        C_Nom_P.setCellValueFactory(new PropertyValueFactory<>("nom"));
        C_Prenom_P.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tableView_P.setItems(personneObservableList);
        tableView_P.setVisible(true);
        tableView_E.setVisible(false);
    }
    public void getAllE(){
        ObservableList<Entreprise> entrepriseObservableList = FXCollections.observableList(e.getAll());
        C_ID_E.setCellValueFactory(new PropertyValueFactory<>("id"));
        C_Email_E.setCellValueFactory(new PropertyValueFactory<>("email"));
        C_Phone_E.setCellValueFactory(new PropertyValueFactory<>("phone"));
        C_FJ_E.setCellValueFactory(new PropertyValueFactory<>("formeJuridique"));
        C_RS_E.setCellValueFactory(new PropertyValueFactory<>("raisonSocial"));
        tableView_E.setItems(entrepriseObservableList);
        tableView_E.setVisible(true);
        tableView_P.setVisible(false);
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
            if ("Person".equals(selectedType)) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/org/Interfaces/personne2.fxml"));
                Node node = fxmlLoader.load();
                mainAnchor.getChildren().setAll(node);
            } else if ("Enterprise".equals(selectedType)) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/org/Interfaces/entreprise2.fxml"));
                Node node = fxmlLoader.load();
                mainAnchor.getChildren().setAll(node);
            }
            else {
                showMessage("Please choose a creation type");
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
                adresseDetails.setText("Num: " + selectedPerson.getAdresse().getNumero() + '\n' + "Quartier: " + selectedPerson.getAdresse().getQuartier() + '\n' + "Ville: " + selectedPerson.getAdresse().getVille());
            }
        } else if (tableView_E.isVisible()) {
            Entreprise selectedEntreprise = tableView_E.getSelectionModel().getSelectedItem();
            if (selectedEntreprise != null) {
                adresseDetails.setText("Num: " + selectedEntreprise.getAdresse().getNumero() + '\n' + "Quartier: " + selectedEntreprise.getAdresse().getQuartier() + '\n' + "Ville: " + selectedEntreprise.getAdresse().getVille());
            }

        }
    }


    public void onAdvancedSearch(ActionEvent event) {
        if(typeChoiceBox.getValue().equals("Enterprise")){
            try {
                FXMLLoader f = new FXMLLoader();
                f.setLocation(getClass().getResource("/org/Interfaces/AdvancedSearchE.fxml"));
                Node n = f.load();
                mainAnchor.getChildren().setAll(n);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if (typeChoiceBox.getValue().equals("Person")){
            try {
                FXMLLoader f = new FXMLLoader();
                f.setLocation(getClass().getResource("/org/Interfaces/AdvancedSearchP.fxml"));
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
            f.setLocation(getClass().getResource("/org/Interfaces/Login.fxml"));
            Node n = f.load();
            mainAnchor.getChildren().setAll(n);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onMess(ActionEvent event) {
        try {
            FXMLLoader f = new FXMLLoader();
            f.setLocation(getClass().getResource("/org/Interfaces/Notifier.fxml"));
            Node n = f.load();
            mainAnchor.getChildren().setAll(n);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void returnBTN(ActionEvent event) {
        try {
            FXMLLoader f = new FXMLLoader();
            f.setLocation(getClass().getResource("/org/Interfaces/Dashbord.fxml"));
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