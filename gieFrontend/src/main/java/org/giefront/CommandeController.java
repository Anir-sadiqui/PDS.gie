package org.giefront;

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
import javafx.scene.layout.AnchorPane;
import org.giefront.DTO.*;
import org.giefront.DTO.EtatCommande;
import org.giefront.Service.CommandeService;
import org.giefront.Service.EntrepriseService;
import org.giefront.Service.PersonneService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



public class CommandeController implements Initializable{

    public static Commande c;
    @FXML
    private TextArea Details;
    @FXML
    private DatePicker Calendrier;
    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private ChoiceBox CB_four;
    @FXML
    private TableView Tab;
    @FXML
    private TableColumn C_id;
    @FXML
    private TableColumn C_date;
    @FXML
    private TableColumn C_Etat;

    private CommandeService cs = new CommandeService();
    Commande cmd = new Commande();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Tab.setRowFactory(tv -> {
            TableRow<Commande> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 1) {
                    Commande clickedRow = row.getItem();
                    showDetails(clickedRow);
                    cmd=row.getItem();
                }
            });
            return row;
        });
        getAll();
        Details.setEditable(false);
        loadE();

    }

    private void showDetails(Commande clickedRow) {
        for (Achat a : clickedRow.getAchats()) {
            String s = "Achat :" + " " + a.getDetails().getProduct().getName() + " :" + a.getDetails().getQuantity();

            Details.setText("Commande effectue le :" + " " + clickedRow.getPurchaseDate() + "\n"
                    + "Contenant :" + "\n" + s);
        }
    }



    public void onAfch(ActionEvent event) {
        if( CB_four.getValue() != null){
            ObservableList<Commande> commandes =FXCollections.observableList(cs.getByEtat((String) CB_four.getValue()));
            if (Calendrier.getValue()==null){
                remlirTab(commandes);
            }
            else {
                List<Commande> commandes1 = cs.getByDate(Calendrier.getValue());
                for (Commande c : commandes1){
                   if(c.getE()!=CB_four.getValue()){
                       commandes1.remove(c);
                   }
                }
                remlirTab(FXCollections.observableList(commandes1));
            }
        }
        else {
            if (Calendrier.getValue()!=null){
                ObservableList<Commande> commandes = FXCollections.observableList(cs.getByDate(Calendrier.getValue()));
                remlirTab(commandes);
            }
            else {
                getAll();
            }
        }
    }

    public void onAjout(ActionEvent event) {
        List<Achat> achats = new ArrayList<>();
        Commande c = new Commande(achats);
        cs.addCom(c);
        showMessage("Your command has been initialised. You can add purchases by modifying it  ");
    }

    public void onAnnuler(ActionEvent event) throws IOException {
        Commande cmd = (Commande) Tab.getSelectionModel().getSelectedItem();
        if (cmd.getE()== EtatCommande.Initialised || cmd.getE()== EtatCommande.In_Preparation){
            cs.deleteComm(Math.toIntExact(cmd.getId()));
            showMessage("This Command has been canceled");
        }
        else showMessage("Impossible to cancel this command");
    }

    public void OnMod(ActionEvent event) {
        c= (Commande) Tab.getSelectionModel().getSelectedItem();
        if (c.getE()==EtatCommande.Initialised || c.getE()==EtatCommande.In_Preparation) {
            try {
                FXMLLoader f = new FXMLLoader();
                f.setLocation(getClass().getResource("/org/Interfaces/achat interface.fxml"));
                Node n = f.load();
                mainAnchor.getChildren().setAll(n);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            showMessage("Impossible to modify this command");
        }
    }

    public void onVal(ActionEvent event)  throws IOException {
        Commande c = (Commande) Tab.getSelectionModel().getSelectedItem();
        if(c.getE().equals(EtatCommande.Initialised) || c.getE().equals(EtatCommande.In_Preparation)) {
            cs.validerComm(Math.toIntExact(c.getId()));
            showMessage("The command has been validated");
        }
        else  {
            showMessage("Impossible to validate this command");
        }
    }


    public void onRef(ActionEvent event) {
        getAll();
        CB_four.setValue(null);
        Calendrier.setValue(null);
    }

    private void getAll(){
        remlirTab(FXCollections.observableList(cs.getAll()));
    }
    private void remlirTab(ObservableList<Commande> commandes){
        C_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        C_date.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
        C_Etat.setCellValueFactory(new PropertyValueFactory<>("e"));
        Tab.setItems(commandes);
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void loadE() {
        List<String> e = new ArrayList<>();
        for (EtatCommande etat : EtatCommande.values()){
            e.add(etat.name());
        }
        CB_four.getItems().addAll(e);

    }



}

