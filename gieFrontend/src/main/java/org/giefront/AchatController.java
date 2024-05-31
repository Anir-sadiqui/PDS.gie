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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.giefront.DTO.*;
import org.giefront.DTO.EtatCommande;
import org.giefront.Service.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AchatController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private TextArea Details;
    @FXML
    private TableColumn C_f111;
    @FXML
    private TableColumn C_f11;
    @FXML
    private TableColumn C_f1;
    @FXML
    private ChoiceBox CB1 ;
    @FXML
    private ChoiceBox CB2  ;

    public static Achat a;

    @FXML
    private Pane mainAnchor;
    @FXML
    private  TableColumn C_P ;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Tab.setRowFactory(tv -> {
            TableRow<Achat> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 1) {
                    Achat clickedRow = row.getItem();
                    img.setImage(new Image(clickedRow.getDetails().getProduct().getImagePath()));
                    showAction(clickedRow);
                }
            });
            return row;
        });
        Details.setEditable(false);
        Details.setVisible(false);
        getAll();
        loadEntreprises();
        loadPersonnes();
        remplirCat();
    }
    private void showAction(Achat clickedRow) {
        Details.setText("Produit achete :" + clickedRow.getDetails().getProduct().getName() + "\n " + "Quantite :" + clickedRow.getDetails().getQuantity() + "\n" + "Prix Achat:" + clickedRow.getDetails().getTotalPrice());
        Details.setVisible(true);
    }

    private void getAll() {
        List<Achat> achats = as.getByComm(Math.toIntExact(c.getId()));
        remlirTab(FXCollections.observableList(achats));
    }

    private void remlirTab(ObservableList<Achat> achats) {
        C_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        C_f.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        Tab.setItems(achats);
    }
    public void onshow(ActionEvent event) {
        if (CB.getValue() != null) {
            Contact four = (Contact) CB.getValue();
            ObservableList<Achat> achats = FXCollections.observableList(as.getByComm(Math.toIntExact(c.getId())));
            if (CB1.getValue() == null) {
                List<Achat> achats1 = new ArrayList<>();
                for (Achat a : achats) {
                    if (a.getSupplier().equals(four)) {
                        achats1.add(a);
                    }
                }
                remlirTab(FXCollections.observableList(achats1));
            }
            else {
                if (CB2.getValue() == null) {
                    List<Achat> achats1 = new ArrayList<>();
                    for (Achat a : achats) {
                        if (a.getDetails().getProduct().getCategory().name() == CB1.getValue()) {
                            achats1.add(a);
                        }
                    }
                    remlirTab(FXCollections.observableList(achats1));
                }
                else {
                    List<Achat> achats1 = new ArrayList<>();
                    for (Achat a : achats) {
                        if (a.getDetails().getProduct().getName().equals(CB2.getValue())) {
                            achats1.add(a);
                        }
                    }
                    remlirTab(FXCollections.observableList(achats1));
                }
            }
        }
        else {
            if (CB1.getValue() != null) {
                if (CB2.getValue() == null) {
                    List<Achat> achats1 = new ArrayList<>();
                    List<Achat>achats = as.getByComm(Math.toIntExact(c.getId()));
                    for (Achat a : achats) {
                        if (a.getDetails().getProduct().getCategory().name() == CB1.getValue()) {
                            achats1.add(a);
                        }
                    }
                    remlirTab(FXCollections.observableList(achats1));
                }
                else {
                    List<Achat> achats1 = new ArrayList<>();
                    for (Achat a : as.getByComm(Math.toIntExact(c.getId()))) {
                        if (a.getDetails().getProduct().getName().equals(CB2.getValue())) {
                            achats1.add(a);
                        }
                    }
                        remlirTab(FXCollections.observableList(achats1));
                }
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
        CB2.setValue(null);
        CB1.setValue(null);
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

    public void remplirCat(){
        for (Category cat : Category.values()){
            CB1.getItems().add(cat.name());
        }
    }

    public void ajouProd(ActionEvent event) {
        String cat = (String) CB1.getValue();
        CB2.getItems().removeAll(CB2.getItems());
        if (cat != null){
            ProductService ps = new ProductService();
            for (Product p : ps.getbyCat(cat)){
                CB2.getItems().add(p.getName() );
            }
        }
    }
}
