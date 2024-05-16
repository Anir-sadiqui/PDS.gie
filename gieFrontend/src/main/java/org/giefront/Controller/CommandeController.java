package org.giefront.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.giefront.DTO.Commande;
import org.giefront.DTO.Entreprise;
import org.giefront.Service.CommandeService;

import java.util.List;



public class CommandeController {

    private CommandeService commandService;


    @FXML
    private Button validerButton;


    @FXML
    private Button afficherCommandesButton;



    @FXML
    private Button supprimerButton;


    @FXML
    private Button ajouterButton;


    @FXML
    private TableView commandeTableView;


    @FXML
    private TableColumn dateColumn;


    @FXML
    private TableColumn etatColumn;


    @FXML
    private TableColumn totalPrixColumn;


    @FXML
    private TableColumn idColumn;


    @FXML
    private ListView achatsListView;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ChoiceBox etatChoiceBox;


    @FXML
    void valider(){

    }

    @FXML
    void annuler(){

    }

    @FXML
    void ajouer(){

    }




    @FXML
    public void setColumn(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        etatColumn.setCellValueFactory(new PropertyValueFactory<>("état"));
        totalPrixColumn.setCellValueFactory(new PropertyValueFactory<>("prix totale"));
        commandeTableView.getColumns().setAll(idColumn, dateColumn, etatColumn, totalPrixColumn);

    }

    @FXML
     List<Commande>  getall(ActionEvent action){

        List<Commande> commands = commandService.getAll();
        ObservableList<Commande> commandList = FXCollections.observableArrayList(commandService);


    }















}
