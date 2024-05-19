package org.giefront;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.giefront.DTO.Adresse;
import org.giefront.DTO.ContactType;
import org.giefront.DTO.Personne;
import org.giefront.Service.FournisseurPersoService ;
import org.giefront.Service.PersonneService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FournisseurPersoController  {

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;


    @FXML
    private TextField ville ;

    @FXML
    private TextField quartier ;

    @FXML
    private TextField num ;

    @FXML
    private Button address;


    //////////////////////////////////////////////////////////////////////////////////
    @FXML
    private TableView<Personne> fournisseurTable;

    @FXML
    private TableColumn<Personne, Long> colId = new TableColumn<>("ID");


    @FXML
    private TableColumn<Personne, String> colNom = new TableColumn<>("NOM") ;

    @FXML
    private TableColumn<Personne, String> colPrenom = new TableColumn<>("PRENOM");

    @FXML
    private TableColumn<Personne, String> colPhone = new TableColumn<>("PHONE");

    @FXML
    private TableColumn<Personne, String> colEmail = new TableColumn<>("EMAIL");

    private FournisseurPersoService fournisseurService = new FournisseurPersoService();

    public FournisseurPersoController() {
        this.fournisseurService = new FournisseurPersoService() ;
    }

    @FXML
    public void initialize() {
        // Initialize the TableColumn properties
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        fournisseurTable.getColumns().setAll(colId, colNom, colPrenom, colPhone, colEmail);

        List<Personne> list = new ArrayList<>() ;
        list.add(new Personne() );
        fournisseurTable.setItems(FXCollections.observableArrayList(list));
    }


    @FXML
    public List<Personne> fetchFournisseurs(ActionEvent event) {

        TableColumn<Personne , Long> colId = new TableColumn<>("ID") ;
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Personne , Long> colNom = new TableColumn<>("NOM") ;
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Personne , Long> colPrenom = new TableColumn<>("PRENOM") ;
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        TableColumn<Personne , Long> colPhone = new TableColumn<>("PHONE") ;
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Personne , Long> colEmail = new TableColumn<>("EMAIL") ;
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        fournisseurTable.getColumns().setAll(colId , colNom , colPrenom , colPhone , colEmail) ;



        // Fetch data from the service
        List<Personne> fournisseurs = fournisseurService.getAll(ContactType.FOURNISSEUR);

        // Create an ObservableList to set to the TableView
        ObservableList<Personne> fournisseurList = FXCollections.observableArrayList(fournisseurs);

        // Set the data to the TableView
        fournisseurTable.setItems(fournisseurList);
        return fournisseurList;
    }


    @FXML
    void OnBtnPClick(ActionEvent event) {
        if (areFieldsFilled()) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Êtes-vous sûr de vouloir ajouter ce fournisseur ?");
            confirmationAlert.setContentText("Cliquez sur OK pour confirmer.");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                PersonneService personneService = new PersonneService();
                Personne personne = new Personne();
                personne.setEmail(email.getText());
                personne.setNom(nom.getText());
                personne.setPrenom(prenom.getText());
                personne.setPhone(phone.getText());
                Adresse a = new Adresse();
                a.setVille(ville.getText());
                a.setNumero(num.getText());
                a.setQuartier(quartier.getText());
                personne.setAdresse(a);
                personne.setContactType(ContactType.FOURNISSEUR); // Set ContactType to FOURNISSEUR
                System.out.println(personne);

                personneService.add(personne);

                System.out.println("Fournisseur ajouté avec succès!");
            } else {
                System.out.println("L'ajout du fournisseur a été annulé.");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs incomplets");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
        }
    }

    private boolean areFieldsFilled() {
        return !email.getText().isEmpty() &&
                !nom.getText().isEmpty() &&
                !ville.getText().isEmpty() &&
                !quartier.getText().isEmpty() &&
                !num.getText().isEmpty() &&
                !prenom.getText().isEmpty() &&
                !phone.getText().isEmpty();
    }





/////////////////////////////////////////////////////////////////////////////////////

    private Stage stage ;
    private Scene scene ;
    private FXMLLoader fxmlLoader ;

//    public void switchToFornEntro(ActionEvent event) throws IOException  {
//
//        Parent root  = FXMLLoader.load(getClass().getResource("/org/giefront/fournisseurEntro.fxml"));
//        stage = (Stage) ((Node)event.getSource()).getScene().getWindow() ;
//        scene = new Scene(root) ;
//        stage.setScene(scene);
//        stage.show();
//
//    }

    public void switchToFornPerso(ActionEvent event) throws IOException {

        Parent root  = FXMLLoader.load(getClass().getResource("/org/Interfaces/fournisseurEntro.fxml"));

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow() ;
        scene = new Scene(root) ;
        stage.setScene(scene);
        stage.show();
    }

    // Method to show address fields when "Address" button is clicked
    @FXML
    private void showAddressFields() {
        quartier.setVisible(true);
        ville.setVisible(true);
        num.setVisible(true);
    }



}
