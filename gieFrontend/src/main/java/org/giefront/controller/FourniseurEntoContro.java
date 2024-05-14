package org.giefront.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.giefront.DTO.ContactType;
import org.giefront.DTO.Entreprise;
import org.giefront.DTO.Personne;
import org.giefront.Service.FournisseurEntroService;
import org.giefront.Service.FournisseurPersoService;
import org.giefront.TestFront;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FourniseurEntoContro {

    private Stage stage ;
    private Scene scene ;
    private FXMLLoader fxmlLoader ;

    public void switchToFornEntro(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(TestFront.class.getResource("/org/giefront/fournisseurPerso.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow() ;
        scene = new Scene(fxmlLoader.load()) ;
        stage.setScene(scene);
        stage.show();

    }

    public void switchToFornPerso(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(TestFront.class.getResource("/org/giefront/fournisseurEntro.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow() ;
        scene = new Scene(fxmlLoader.load()) ;
        stage.setScene(scene);
        stage.show();
    }
////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private TextField villeE ;

    @FXML
    private TextField quartierE ;

    @FXML
    private TextField numE ;

    @FXML
    private Button addressE;

////////////////////////////////////////////////////////////////////////////////////
@FXML
private TableView<Entreprise> fournisseurTable;

    @FXML
    private TableColumn<Entreprise, Long> colId = new TableColumn<>("ID");


    @FXML
    private TableColumn<Entreprise, String> colNom = new TableColumn<>("NOM") ;

    @FXML
    private TableColumn<Entreprise, String> colPrenom = new TableColumn<>("PRENOM");

    @FXML
    private TableColumn<Entreprise, String> colPhone = new TableColumn<>("PHONE");

    @FXML
    private TableColumn<Entreprise, String> colEmail = new TableColumn<>("EMAIL");

    private FournisseurEntroService fournisseurService = new FournisseurEntroService();

    public FourniseurEntoContro() {
        this.fournisseurService = new FournisseurEntroService() ;
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

        List<Entreprise> list = new ArrayList<>() ;
        list.add(new Entreprise() );
        fournisseurTable.setItems(FXCollections.observableArrayList(list));
    }


    @FXML
    public List<Entreprise> fetchFournisseurs(ActionEvent event) {

        TableColumn<Entreprise , Long> colId = new TableColumn<>("ID") ;
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Entreprise , Long> colNom = new TableColumn<>("NOM") ;
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Entreprise , Long> colPrenom = new TableColumn<>("PRENOM") ;
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        TableColumn<Entreprise , Long> colPhone = new TableColumn<>("PHONE") ;
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Entreprise , Long> colEmail = new TableColumn<>("EMAIL") ;
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        fournisseurTable.getColumns().setAll(colId , colNom , colPrenom , colPhone , colEmail) ;



        // Fetch data from the service
        List<Entreprise> fournisseurs = fournisseurService.getAll(ContactType.FOURNISSEUR);

        // Create an ObservableList to set to the TableView
        ObservableList<Entreprise> fournisseurList = FXCollections.observableArrayList(fournisseurs);

        // Set the data to the TableView
        fournisseurTable.setItems(fournisseurList);
        return fournisseurList;
    }



    // Method to show address fields when "Address" button is clicked
    @FXML
    private void showAddressFields() {
        quartierE.setVisible(true);
        villeE.setVisible(true);
        numE.setVisible(true);
    }





}
