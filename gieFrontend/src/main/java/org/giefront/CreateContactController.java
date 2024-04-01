package org.giefront;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.gieback.Entity.Entreprise;
import org.gieback.Entity.Personne;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CreateContactController implements Initializable {
    @FXML
    private ChoiceBox<String> choiceBox_TypeContact;

    @FXML
    private AnchorPane Anchorepane_Contact;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        remplirChoiceBox();
        choiceBox_TypeContact.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.equals("Personne")) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("personne.fxml"));
                        Parent root = loader.load();
                        Anchorepane_Contact.getChildren().setAll(root);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (newValue.equals("Entreprise")) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("entreprise.fxml"));
                        Parent root = loader.load();
                        Anchorepane_Contact.getChildren().setAll(root);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    private void remplirChoiceBox() {
        ObservableList<String> options = FXCollections.observableArrayList();
        options.add("Entreprise");
        options.add("Personne");
        choiceBox_TypeContact.setItems(options);
    }
}
