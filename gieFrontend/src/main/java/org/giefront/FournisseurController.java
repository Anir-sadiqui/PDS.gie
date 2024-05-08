package org.giefront;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class FournisseurController {

    private Stage stage ;
    private Scene scene ;
    private FXMLLoader fxmlLoader ;

    public void switchToFornEntro(ActionEvent event) throws IOException  {

        Parent root  = FXMLLoader.load(getClass().getResource("fournisseur.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow() ;
        scene = new Scene(root) ;
        stage.setScene(scene);
        stage.show();

    }

    public void switchToFornPerso(ActionEvent event) throws IOException {

        Parent root  = FXMLLoader.load(getClass().getResource("fournisseurEntro.fxml"));

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow() ;
        scene = new Scene(root) ;
        stage.setScene(scene);
        stage.show();
    }
}