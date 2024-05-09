package org.giefront;
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
   private Button AchatBtn;

   @FXML
   private Button CRM_BTTN;

   @FXML
   private Button ClientBtn;

   @FXML
   private Button FrBtn;

   @FXML
   private Button StockBtn;

   @FXML
   private Button VenteBtn;

   @FXML
   private Button closeBtn;

   @FXML
   private BorderPane dashboardBorderPane;

   @FXML
   private AnchorPane mainAnchor;

   @FXML
   private Button maximizeBtn;

   @FXML
   private Button minimizeBtn;

   @FXML
   private Label username;

   @FXML
   void ClientBtnClick(ActionEvent event) {

   }

   @FXML
   void FRBtnClick(ActionEvent event) {
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fournisseur.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
         } catch (IOException e) {
            e.printStackTrace();
         }

      }

   @FXML
   void close () {
      System.exit(0);
   }

   @FXML
   void maximize (ActionEvent event){
      Stage stage = (Stage) dashboardBorderPane.getScene().getWindow();
      Boolean maximized = stage.isMaximized();
      stage.setMaximized(!maximized);
   }

   @FXML
   void minimize (ActionEvent event){
      Stage stage = (Stage) mainAnchor.getScene().getWindow();
      stage.setIconified(true);
   }

   @FXML
   void onAchatBtnClick (ActionEvent event){

   }

   @FXML
   void onCRMBtnClick (ActionEvent event){
      FXMLLoader fxmlLoader = new FXMLLoader(TestFront.class.getResource("CrmEntro.fxml"));
      try {
         dashboardBorderPane.setCenter(fxmlLoader.load());

      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   @FXML
   void onStockButtonclick(ActionEvent event){

         try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Stock.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
         } catch (IOException e) {
            e.printStackTrace();
         }

      }






   @FXML
   void onVenteBtnClick (ActionEvent event){

   }

   @Override
   public void initialize(URL url, ResourceBundle resourceBundle) {

   }
}
