package org.giefront;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class DashboardController implements Initializable {

    @FXML
    private Button CreateBtn;

    @FXML
    private Button DeleteBtn;

    @FXML
    private Button SearchBtn;

    @FXML
    private Button ShowBtn;

    @FXML
    private Button UpdateBtn;

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




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void close() {
            System.exit(0);
        }


    @FXML
    void maximize() {

            Stage stage = (Stage) dashboardBorderPane.getScene().getWindow();
            Boolean maximized = stage.isMaximized();
            stage.setMaximized(!maximized);
        }


    @FXML
    void minimize() {
        Stage stage = (Stage) mainAnchor.getScene().getWindow();
            stage.setIconified(true);
        }



    @FXML
    void onCreateBtnClick(ActionEvent event) {
        try {
            // Charger le fichier FXML pour la scène de création
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateContact.fxml"));
            // Créer une nouvelle scène avec le contenu du fichier FXML
            Parent root = loader.load();
            // Obtenir la fenêtre actuelle (Stage)
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Changer la scène actuelle par la nouvelle scène
            stage.setScene(new Scene(root));
            // Afficher la nouvelle scène
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void onDeleteBtnClick(ActionEvent event) {

    }

    @FXML
    void onSearchButtonclick(ActionEvent event) {

    }

    @FXML
    void onShowBtnClick(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("AfficherContact.fxml"));
        try {
            dashboardBorderPane.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void onUpdateBtnClick(ActionEvent event) {

    }


}
