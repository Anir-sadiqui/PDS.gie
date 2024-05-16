package org.giefront.controller;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    public Button AchatBtn; // Modifier la visibilité en public

    @FXML
    public Button CRM_BTTN;

    @FXML
    public Button ClientBtn;

    @FXML
    public Button FrBtn;

    @FXML
    public Button StockBtn;

    @FXML
    public Button VenteBtn;

    @FXML
    public Button closeBtn;

    @FXML
    public BorderPane dashboardBorderPane;

    @FXML
    public AnchorPane mainAnchor;

    @FXML
    public Button maximizeBtn;

    @FXML
    public Button minimizeBtn;

    @FXML
    public Label username;

    @FXML
    public void ClientBtnClick(ActionEvent event) {

    }

    @FXML
    public void FRBtnClick(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/giefront/fournisseurEntro.fxml"));
            Parent content = loader.load();

            // Ajoute le contenu du bouton CRM à votre interface principale
            dashboardBorderPane.setCenter(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   @FXML
    public void close () {
      System.exit(0);
   }

   @FXML
   public void maximize (ActionEvent event){
      Stage stage = (Stage) dashboardBorderPane.getScene().getWindow();
      Boolean maximized = stage.isMaximized();
      stage.setMaximized(!maximized);
   }

   @FXML
   public void minimize (ActionEvent event){
      Stage stage = (Stage) mainAnchor.getScene().getWindow();
      stage.setIconified(true);
   }

   @FXML
   public void onAchatBtnClick (ActionEvent event){

   }

    @FXML
    public void onCRMBtnClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/giefront/ContactEntreprise.fxml"));
            Parent content = loader.load();

            // Ajoute le contenu du bouton CRM à votre interface principale
            dashboardBorderPane.setCenter(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
   public void onStockButtonclick(ActionEvent event){
      try {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Stock.fxml"));
         AnchorPane root = fxmlLoader.load(); // Charger la racine en tant qu'AnchorPane
         Stage stage = new Stage();
         stage.setScene(new Scene(root));
         stage.show();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }


   @FXML
   public void onVenteBtnClick (ActionEvent event){

   }

   @Override
   public void initialize(URL url, ResourceBundle resourceBundle) {

   }
}
