package org.giefront;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class TestFront extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader(TestFront.class.getResource("NouveauAchat.fxml"));
//        Parent root  = FXMLLoader.load(getClass().getResource("homePage.fxml"));
//        Scene scene = new Scene(root);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setHeight(600);
        stage.setWidth(900);
        stage.setResizable(true);
        stage.setTitle("Test");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}