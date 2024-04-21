package org.giefront;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoginConroller {

    @FXML
    private Button MDP;
    @FXML
    private Button Inscr;
    @FXML
    private Button Connex;
    @FXML
    private TextField MDP_txt;
    @FXML
    private TextField Email_txt;
    @FXML
    private AnchorPane mainAnchor;


    public void onLogin(ActionEvent event) {
        if ("Admin@1234".equals(Email_txt.getText()) && "1234".equals(MDP_txt.getText())){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("MainInterface.fxml"));
            Node node = null;
            try {
                node = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            mainAnchor.getChildren().setAll(node);


        }

    }

    public void onInscr(ActionEvent event) {
    }

    public void onMDPoub(ActionEvent event) {
    }
}
