package org.giefront;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.giefront.DTO.*;
import org.giefront.DTO.EtatCommande;
import org.giefront.Service.AchatService;
import org.giefront.Service.CommandeService;
import org.giefront.Service.EntrepriseService;
import org.giefront.Service.PersonneService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AchatController implements Initializable {

    public static Achat a;

    @FXML
    private Pane mainAnchor;
    @FXML
    private  TableColumn C_P ;
    @FXML
    private TextArea Details;
    @FXML
    private DatePicker calendrier;
    @FXML
    private TableView Tab;
    @FXML
    private TableColumn C_id;
    @FXML
    private TableColumn C_date;
    @FXML
    private TableColumn C_Prod;
    @FXML
    private TableColumn C_q;
    @FXML
    private TableColumn C_f;
    @FXML
    private ChoiceBox CB ;
    Commande c = CommandeController.c;
    AchatService as = new AchatService();
    CommandeService cs = new CommandeService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Tab.setRowFactory(tv -> {
            TableRow<Achat> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 1) {
                    Achat clickedRow = row.getItem();
                    showAction(clickedRow);
                }
            });
            return row;
        });
        getAll();
        Details.setEditable(false);
        loadEntreprises();
        loadPersonnes();
    }

    private void getAll() {
        remlirTab(FXCollections.observableList(cs.getAllAchats(Math.toIntExact(c.getId()))));

    }

    private void remlirTab(ObservableList<Achat> achats) {
        C_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        C_date.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
        C_f.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        Tab.setItems(achats);
    }

    private void showAction(Achat clickedRow) {
        Details.setText("Produit achete :" + clickedRow.getDetails().getProduct().getName() + "\n " + "Quantite :" + clickedRow.getDetails().getQuantity() + "\n" + "Prix Achat:" + clickedRow.getDetails().getTotalPrice());

    }




    public void onshow(ActionEvent event) {
        if( CB.getValue() != null){
            Contact four = (Contact) CB.getValue();
            ObservableList<Achat> achats =FXCollections.observableList(as.chercherParFournisseur(Math.toIntExact(four.getId())));
            if (calendrier.getValue()==null){
                remlirTab(achats);
            }
            else {
                List<Achat> achats1 = as.chercherParDate(calendrier.getValue());
                for (Achat a : achats1){
                    if(a.getSupplier()!=four){
                        achats1.remove(a);
                    }
                }
                remlirTab(FXCollections.observableList(achats1));
            }
        }
        else {
            if (calendrier.getValue()!=null){
                ObservableList<Achat> achats1 = FXCollections.observableList(as.chercherParDate(calendrier.getValue()));
                remlirTab(achats1);
            }
            else {
                getAll();
            }
        }
    }

    public void onADD(ActionEvent event) {
        try {
            FXMLLoader f = new FXMLLoader();
            f.setLocation(getClass().getResource("/org/Interfaces/NouveauAchat.fxml"));
            Node n = f.load();
            mainAnchor.getChildren().setAll(n);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onMod(ActionEvent event) {
        a = (Achat) Tab.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/Interfaces/AchatModif.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onDel(ActionEvent event) {
        Achat achat = (Achat) Tab.getSelectionModel().getSelectedItem();
            as.deleteById(Math.toIntExact(achat.getId()));
    }

    public void onRef(ActionEvent event) {
        getAll();
        CB.setValue(null);
        calendrier.setValue(null);
    }

    public void onRet(ActionEvent event) {
        try {
            FXMLLoader f = new FXMLLoader();
            f.setLocation(getClass().getResource("/org/Interfaces/Commande.fxml"));
            Node n = f.load();
            mainAnchor.getChildren().setAll(n);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        List<Contact> personneNames = new ArrayList<>();
        for (Entreprise p : ps.getAll()){
            if (p.getContactType()== ContactType.FOURNISSEUR){
                personneNames.add(p);
            }
        }
        CB.getItems().addAll(personneNames);

    }


    private void loadPersonnes() {
        PersonneService ps = new PersonneService();
        List<Contact> personneNames = new ArrayList<>();
        for (Personne p : ps.getAll()){
            if (p.getContactType()== ContactType.FOURNISSEUR){
                personneNames.add(p);
            }
        }
        CB.getItems().addAll(personneNames);
    }
}
