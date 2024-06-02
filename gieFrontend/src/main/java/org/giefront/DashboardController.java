package org.giefront;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    public Button AchatBtn;

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
    public void ClientBtnClick(ActionEvent event) {
        loadUI("/org/Interfaces/Commande.fxml");
    }

    @FXML
    public void FRBtnClick(ActionEvent event) {
        loadUI("/org/Interfaces/fournisseurPerso.fxml");
    }

    @FXML
    public void onCRMBtnClick(ActionEvent event) {
        loadUI("/org/Interfaces/MainInterface.fxml");
    }

    @FXML
    public void onStockButtonclick(ActionEvent event) {
        loadUI("/org/Interfaces/Stock.fxml");
    }

    @FXML
    public void onVenteBtnClick(ActionEvent event) {
        // Add functionality if needed
    }

    @FXML
    public void onAchatBtnClick(ActionEvent event) {
        loadUI("/org/Interfaces/achat interface.fxml");
    }

    private void loadUI(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent content = loader.load();
            dashboardBorderPane.setCenter(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialization logic if needed
    }

    @FXML
    public void close() {
        System.exit(0);
    }

    @FXML
    public void maximize(ActionEvent event) {
        Stage stage = (Stage) dashboardBorderPane.getScene().getWindow();
        Boolean maximized = stage.isMaximized();
        stage.setMaximized(!maximized);
    }

    @FXML
    public void minimize(ActionEvent event) {
        Stage stage = (Stage) mainAnchor.getScene().getWindow();
        stage.setIconified(true);
    }
}
