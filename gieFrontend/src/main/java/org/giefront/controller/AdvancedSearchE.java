package org.giefront.controller;

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
import org.giefront.DTO.Entreprise;
import org.giefront.Service.EntrepriseService;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class AdvancedSearchE implements Initializable {

    public static Entreprise entr;
    @FXML
    private Button Search;

    @FXML
    private TextField Text_FJ;

    @FXML
    private TextField Text_RS;

    @FXML
    private TextField Text_id;

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
    public TableView<Entreprise> tableView_E;

    @FXML
    private Button supp;

    @FXML
    private ImageView IconeC;
    @FXML
    private TextArea detailsC;
    @FXML
    private Button mod;
    @FXML
    private Button adresse;
    @FXML
    private TextArea adresseDetails;

    EntrepriseService e = new EntrepriseService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableView_E.setVisible(false);
        supp.setVisible(false);
        mod.setVisible(false);
        detailsC.setVisible(false);
        IconeC.setVisible(false);
        adresse.setVisible(false);
        adresseDetails.setVisible(false);
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

    private void showEDetails(Entreprise clickedRow) {
        IconeC.setVisible(true);
        IconeC.setImage(new Image("https://cdn-icons-png.flaticon.com/512/5343/5343441.png"));
        detailsC.setVisible(true);
        detailsC.setEditable(false);
        detailsC.setText("Raison social: " + clickedRow.getRaisonSocial() + '\n' + "Email: " + clickedRow.getEmail() + '\n' + "Num de tel: " + clickedRow.getPhone() + '\n' + "Forme Juridique: " + clickedRow.getFormeJuridique());
        adresse.setVisible(true);
        mod.setVisible(true);
        supp.setVisible(true);
    }

    public void onDelete(ActionEvent event) throws IOException {
        Entreprise selectedEntreprise = tableView_E.getSelectionModel().getSelectedItem();
        if (selectedEntreprise != null) {
            e.deleteEntro((long) Math.toIntExact(selectedEntreprise.getId()));
            tableView_E.getItems().remove(selectedEntreprise);
            showAlert("Suppression reussie");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void onAdress(ActionEvent event) {
        Entreprise selectedEntreprise = tableView_E.getSelectionModel().getSelectedItem();
        if (selectedEntreprise != null) {
            adresseDetails.setText("Numero: " + selectedEntreprise.getAdresse().getNumero() + '\n' + "Quartier: " + selectedEntreprise.getAdresse().getQuartier() + '\n' + "Ville: " + selectedEntreprise.getAdresse().getVille());
        }
    }

    public void onModify(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("EntrepriseModification.fxml"));
            Node node = fxmlLoader.load();
            mainAnchor.getChildren().setAll(node);
            Entreprise selectedEntr = tableView_E.getSelectionModel().getSelectedItem();
            if (selectedEntr != null) {
                entr = selectedEntr;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

//    public void onSearch(ActionEvent event) throws IOException {
//        if (!Text_id.getText().isEmpty()){
//            ObservableList<Entreprise> entrObservableList = FXCollections.observableList(Collections.singletonList(e.getById(Integer.parseInt(Text_id.getText()))));
//            C_ID_E.setCellValueFactory(new PropertyValueFactory<>("id"));
//            C_Email_E.setCellValueFactory(new PropertyValueFactory<>("email"));
//            C_Phone_E.setCellValueFactory(new PropertyValueFactory<>("phone"));
//            C_FJ_E.setCellValueFactory(new PropertyValueFactory<>("formeJuridique"));
//            C_RS_E.setCellValueFactory(new PropertyValueFactory<>("raisonSocial"));
//            C_Adresse_E.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse().toString()));
//            tableView_E.setItems(entrObservableList);
//            tableView_E.setVisible(true);
//        }
//        else if(!Text_RS.getText().isEmpty() && Text_id.getText().isEmpty()){
//            ObservableList<Entreprise> entrObservableList = FXCollections.observableList(Collections.singletonList(e.getByRs(Text_RS.getText())));
//            C_ID_E.setCellValueFactory(new PropertyValueFactory<>("id"));
//            C_Email_E.setCellValueFactory(new PropertyValueFactory<>("email"));
//            C_Phone_E.setCellValueFactory(new PropertyValueFactory<>("phone"));
//            C_FJ_E.setCellValueFactory(new PropertyValueFactory<>("formeJuridique"));
//            C_RS_E.setCellValueFactory(new PropertyValueFactory<>("raisonSocial"));
//            C_Adresse_E.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse().toString()));
//            tableView_E.setItems(entrObservableList);
//            tableView_E.setVisible(true);
//        }
//        else if (!Text_FJ.getText().isEmpty() && Text_id.getText().isEmpty() && Text_RS.getText().isEmpty()){
//            ObservableList<Entreprise> entrepriseObservableList = FXCollections.observableList(e.getByFj(Text_FJ.getText()));
//            C_ID_E.setCellValueFactory(new PropertyValueFactory<>("id"));
//            C_Email_E.setCellValueFactory(new PropertyValueFactory<>("email"));
//            C_Phone_E.setCellValueFactory(new PropertyValueFactory<>("phone"));
//            C_FJ_E.setCellValueFactory(new PropertyValueFactory<>("formeJuridique"));
//            C_RS_E.setCellValueFactory(new PropertyValueFactory<>("raisonSocial"));
//            C_Adresse_E.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse().toString()));
//            tableView_E.setItems(entrepriseObservableList);
//            tableView_E.setVisible(true);
//        }
//        else if (Text_RS.getText().isEmpty() && Text_id.getText().isEmpty() && Text_RS.getText().isEmpty()){
//            showAlert("Veuillez indiquer un champ de recherche");
//        }
//    }
}
