package org.giefront;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    // from home page to crm
    private Stage stage ;
    private Scene scene ;
    private FXMLLoader fxmlLoader ;

    public void homeToCrm(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(TestFront.class.getResource("MainInterface.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow() ;
        scene = new Scene(fxmlLoader.load()) ;
        stage.setScene(scene);
        stage.show();

    }

    public void CrmTOHome(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(TestFront.class.getResource("homePage.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow() ;
        scene = new Scene(fxmlLoader.load()) ;
        stage.setScene(scene);
        stage.show();
    }

}
