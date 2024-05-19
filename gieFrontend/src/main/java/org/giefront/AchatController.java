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
import javafx.scene.layout.Pane;
import org.giefront.DTO.Achat;
import org.giefront.DTO.Commande;
import org.giefront.Service.AchatService;

import java.io.IOException;
import java.net.URL;
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
    AchatService as = new AchatService();

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
    }

    private void getAll() {
        remlirTab(FXCollections.observableList(as.getAll()));

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
    }

    public void onADD(ActionEvent event) {
        a= (Achat) Tab.getSelectionModel().getSelectedItem();
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
    }

    public void onDel(ActionEvent event) {
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
}
