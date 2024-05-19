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
                    showAction(clickedRow);
                    cmd=row.getItem();
                }
            });
            return row;
        });
        getAll();
        Details.setEditable(false);
        loadEntreprises();
        loadPersonnes();
    }

    private void showAction(Commande clickedRow) {
        for (Achat a : clickedRow.getAchats()) {
            String s = "Achat :" + " " + a.getDetails().getProduct().getName() + " :" + a.getDetails().getQuantity();

            Details.setText("Commande effectue le :" + " " + clickedRow.getPurchaseDate() + "\n"
                    + "Contenant :" + "\n" + s);
        }
    }



    public void onAfch(ActionEvent event) {
    }

    public void onAjout(ActionEvent event) {
        List<Achat> achats = new ArrayList<>();
        Commande c = new Commande(achats);
        cs.addCom(c);
        showMessage("Votre commande vient d'etre initialise veuillez ajouter vos achats en modifiant votre comm");
    }

    public void onAnnuler(ActionEvent event) throws IOException {
        Commande cmd = (Commande) Tab.getSelectionModel().getSelectedItem();
        if (cmd.getE()== EtatCommande.Initialise || cmd.getE()== EtatCommande.En_Cours){
            cs.deleteComm(Math.toIntExact(cmd.getId()));
            showMessage("Votre comm a ete annulee");
        }
        else showMessage("Impossible d'annuler cette commande");
    }

    public void OnMod(ActionEvent event) {
        c= (Commande) Tab.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader f = new FXMLLoader();
            f.setLocation(getClass().getResource("/org/Interfaces/achat interface.fxml"));
            Node n = f.load();
            mainAnchor.getChildren().setAll(n);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onVal(ActionEvent event) {

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
    private void loadEntreprises() {
        EntrepriseService ps = new EntrepriseService();
        List<String> personneNames = new ArrayList<>();
        for (Entreprise p : ps.getAll()){
            if (p.getContactType()== ContactType.FOURNISSEUR){
                personneNames.add(p.getRaisonSocial() );
                int id = Math.toIntExact(p.getId());
            }
        }
        CB_four.getItems().addAll(personneNames);

    }


    private void loadPersonnes() {
        PersonneService ps = new PersonneService();
        List<String> personneNames = new ArrayList<>();
        for (Personne p : ps.getAll()){
            if (p.getContactType()== ContactType.FOURNISSEUR){
                personneNames.add(p.getNom() + " " + p.getPrenom());
                int id = Math.toIntExact(p.getId());
            }
        }
        CB_four.getItems().addAll(personneNames);
    }
}

