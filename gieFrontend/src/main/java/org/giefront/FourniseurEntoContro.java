package org.giefront;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FourniseurEntoContro {

    private Stage stage ;
    private Scene scene ;
    private FXMLLoader fxmlLoader ;

    public void switchToFornEntro(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(TestFront.class.getResource("fournisseur.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow() ;
        scene = new Scene(fxmlLoader.load()) ;
        stage.setScene(scene);
        stage.show();

    }

    public void switchToFornPerso(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(TestFront.class.getResource("fournisseurEntro.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow() ;
        scene = new Scene(fxmlLoader.load()) ;
        stage.setScene(scene);
        stage.show();
    }
}
