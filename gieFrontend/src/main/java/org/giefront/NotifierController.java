package org.giefront;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.giefront.Service.EntrepriseService;
import org.giefront.Service.MailService;
import org.giefront.Service.PersonneService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NotifierController implements Initializable {
    @FXML
    private TextField objet;
    PersonneService p = new PersonneService();
    EntrepriseService e = new EntrepriseService();
    MailService ms = new MailService();


    @FXML
    private ChoiceBox typeChoiceBox;
    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private TextField Email;
    @FXML
    private TextArea Msg;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        remplirType();

    }

    private void remplirType() {
        ObservableList<String> options = FXCollections.observableArrayList();
        options.add("Entreprise");
        options.add("Personne");
        typeChoiceBox.setItems(options);
    }

    public void OnReturn(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Messagerie.fxml"));
        Node node = null;
        try {
            node = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainAnchor.getChildren().setAll(node);

    }

    public void OnSend(ActionEvent event) {
        String selectedType = (String) typeChoiceBox.getValue();
        switch (selectedType) {
            case "Personne":
                if (!Email.getText().isEmpty() && !Msg.getText().isEmpty()) {
                    if (p.getByEmail(Email.getText()) != null) {
                        String to = Email.getText();
                        String s = objet.getText();
                        String m = Msg.getText();
                        try {
                            ms.Notifier(to, s, m);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        showMessage("Email incorrecte");
                    }
                } else {
                    showMessage("Veuillez Remplir les champs");
                }
            case "Entreprise" :
                if (!Email.getText().isEmpty() && !Msg.getText().isEmpty()) {
                    if (e.getByEmail(Email.getText()) != null) {
                        String to = Email.getText();
                        String s = objet.getText();
                        String m = Msg.getText();
                        try {
                            ms.Notifier(to, s, m);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        showMessage("Email incorrecte");
                    }
                } else {
                    showMessage("Veuillez Remplir les champs");
                }


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
