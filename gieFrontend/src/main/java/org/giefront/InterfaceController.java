package org.giefront;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class InterfaceController implements Initializable {

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
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("createContact.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            dashboardBorderPane.setCenter(anchorPane);

        } catch (IOException e) {
            throw new RuntimeException(e);
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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AfficherContact.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            dashboardBorderPane.setCenter(anchorPane);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void onUpdateBtnClick(ActionEvent event) {

    }


}
