package org.giefront;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.giefront.Service.FournisseurPersoService;

import java.io.IOException;
import java.util.List;

public class FournisseurPersoController  {

    public static FournisseurPersoDTO fournisseurPersoDTO ;

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
    private TableView<FournisseurPersoDTO> fournisseurTable;

    @FXML
    private TableColumn<FournisseurPersoDTO, String> colNom;

    @FXML
    private TableColumn<FournisseurPersoDTO, String> colPrenom;

    @FXML
    private TableColumn<FournisseurPersoDTO, String> colPhone;

    @FXML
    private TableColumn<FournisseurPersoDTO, String> colEmail;

    private FournisseurPersoService fournisseurService = new FournisseurPersoService();



    @FXML
    public void initialize() {
        // Initialize the TableColumn properties
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    @FXML
    public List<FournisseurPersoDTO> fetchFournisseurs(ActionEvent event) {
        // Fetch data from the service
        List<FournisseurPersoDTO> fournisseurs = fournisseurService.getAll();

        // Create an ObservableList to set to the TableView
        ObservableList<FournisseurPersoDTO> fournisseurList = FXCollections.observableArrayList(fournisseurs);

        // Set the data to the TableView
        fournisseurTable.setItems(fournisseurList);
        return fournisseurList;
    }


/////////////////////////////////////////////////////////////////////////////////////

    private Stage stage ;
    private Scene scene ;
    private FXMLLoader fxmlLoader ;

    public void switchToFornEntro(ActionEvent event) throws IOException  {

        Parent root  = FXMLLoader.load(getClass().getResource("fournisseurPerso.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow() ;
        scene = new Scene(root) ;
        stage.setScene(scene);
        stage.show();

    }

    public void switchToFornPerso(ActionEvent event) throws IOException {

        Parent root  = FXMLLoader.load(getClass().getResource("fournisseurEntro.fxml"));

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
