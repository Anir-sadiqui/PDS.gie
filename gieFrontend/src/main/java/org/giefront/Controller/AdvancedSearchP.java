package org.giefront.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import org.giefront.DTO.Personne;
import org.giefront.Service.PersonneService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class AdvancedSearchP implements Initializable {

    public static Personne pers;

    @FXML
    private  AnchorPane mainAnchor;
    private PersonneService ps = new PersonneService();
    @FXML
    private TextField Text_Id;
    @FXML
    private TextField Text_Nom;
    @FXML
    private TextField Text_Prenom;
    @FXML
    private Button Return;
    @FXML
    private Button supp;
    @FXML
    private TextArea detailsC;
    @FXML
    private Button mod;
    @FXML
    private Button adresse;
    @FXML
    private TextArea adresseDetails;
    public ImageView IconeC;
    @FXML
    private TableColumn<Personne, String> C_Adresse_P;

    @FXML
    private TableColumn<Personne, Long> C_ID_P;

    @FXML
    private TableColumn<Personne, String> C_Nom_P;


    @FXML
    private TableColumn<Personne, String> C_Phone_P;

    @FXML
    private TableColumn<Personne, String> C_Prenom_P;

    @FXML
    private TableColumn<Personne, String> c_Email_P;
    @FXML
    private TableView<Personne> tableView_P;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableView_P.setVisible(false);
        supp.setVisible(false);
        mod.setVisible(false);
        detailsC.setVisible(false);
        IconeC.setVisible(false);
        adresse.setVisible(false);
        adresseDetails.setVisible(false);
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
    }

    private void showPDetails(Personne clickedRow) {
        IconeC.setVisible(true);
        IconeC.setImage(new Image("https://e7.pngegg.com/pngimages/92/319/png-clipart-computer-icons-person-name-miscellaneous-computer-wallpaper.png"));
        detailsC.setVisible(true);
        detailsC.setEditable(false);
        detailsC.setText("Nom et Prenom: " + clickedRow.getNom() + " " + clickedRow.getPrenom() + '\n' + "Email: " + clickedRow.getEmail() + '\n' + "Num de tel: " + clickedRow.getPhone() + '\n');
        adresse.setVisible(true);
        mod.setVisible(true);
        supp.setVisible(true);
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
    public void onDelete(ActionEvent event) throws IOException {
        Personne selectedPerson =  tableView_P.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            ps.deletePersonne(Math.toIntExact(selectedPerson.getId()));
            tableView_P.getItems().remove(selectedPerson);
            showAlert("Suppression reussie");
        }
    }

    private void showAlert(String m) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(m);
        alert.showAndWait();
    }

    public void onModify(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("PersonneModification.fxml"));
            Node node = fxmlLoader.load();
            mainAnchor.getChildren().setAll(node);
            Personne selectedPerson = tableView_P.getSelectionModel().getSelectedItem();
            if (selectedPerson != null) {
                pers = selectedPerson;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onAdress(ActionEvent event) {
        adresseDetails.setVisible(true);
        adresseDetails.setEditable(false);
        Personne selectedPerson =  tableView_P.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            adresseDetails.setText("Numero: " + selectedPerson.getAdresse().getNumero() + '\n' + "Quartier: " + selectedPerson.getAdresse().getQuartier() + '\n' + "Ville: " + selectedPerson.getAdresse().getVille());
        }
    }

    public void onSearch(ActionEvent event) throws IOException {

        if (!Text_Id.getText().isEmpty()) {
            ObservableList<Personne> personneObservableList = FXCollections.observableList(Collections.singletonList(ps.getById(Integer.parseInt(Text_Id.getText()))));
            C_ID_P.setCellValueFactory(new PropertyValueFactory<>("id"));
            c_Email_P.setCellValueFactory(new PropertyValueFactory<>("email"));
            C_Phone_P.setCellValueFactory(new PropertyValueFactory<>("phone"));
            C_Nom_P.setCellValueFactory(new PropertyValueFactory<>("nom"));
            C_Prenom_P.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            C_Adresse_P.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse().toString()));
            tableView_P.setItems(personneObservableList);
            tableView_P.setVisible(true);
        }
        else if(!Text_Nom.getText().isEmpty() && Text_Id.getText().isEmpty() ){
            ObservableList<Personne> personneObservableList =  FXCollections.observableList(ps.getBynom(Text_Nom.getText()));
            C_ID_P.setCellValueFactory(new PropertyValueFactory<>("id"));
            c_Email_P.setCellValueFactory(new PropertyValueFactory<>("email"));
            C_Phone_P.setCellValueFactory(new PropertyValueFactory<>("phone"));
            C_Nom_P.setCellValueFactory(new PropertyValueFactory<>("nom"));
            C_Prenom_P.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            C_Adresse_P.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse().toString()));
            tableView_P.setItems(personneObservableList);
            tableView_P.setVisible(true);
        }
        else if(!Text_Prenom.getText().isEmpty() && Text_Id.getText().isEmpty()){
            if (Text_Nom.getText().isEmpty()  ) {
                ObservableList<Personne> personneObservableList = FXCollections.observableList(ps.getByPrenom(Text_Prenom.getText()));
                C_ID_P.setCellValueFactory(new PropertyValueFactory<>("id"));
                c_Email_P.setCellValueFactory(new PropertyValueFactory<>("email"));
                C_Phone_P.setCellValueFactory(new PropertyValueFactory<>("phone"));
                C_Nom_P.setCellValueFactory(new PropertyValueFactory<>("nom"));
                C_Prenom_P.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                C_Adresse_P.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse().toString()));
                tableView_P.setItems(personneObservableList);
                tableView_P.setVisible(true);
            }
            else if(!Text_Nom.getText().isEmpty() ) {
                List<Personne> l = ps.getBynom(Text_Nom.getText());

                List<Personne> l2 = new ArrayList<>();
                for (Personne p : l){
                    if (p.getPrenom().equals(Text_Prenom.getText())){
                        l2.add(p);
                    }
                }
                ObservableList<Personne> personneObservableList = FXCollections.observableList(l2);
                C_ID_P.setCellValueFactory(new PropertyValueFactory<>("id"));
                c_Email_P.setCellValueFactory(new PropertyValueFactory<>("email"));
                C_Phone_P.setCellValueFactory(new PropertyValueFactory<>("phone"));
                C_Nom_P.setCellValueFactory(new PropertyValueFactory<>("nom"));
                C_Prenom_P.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                C_Adresse_P.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse().toString()));
                tableView_P.setItems(personneObservableList);
                tableView_P.setVisible(true);
            }
        }
    }

}
