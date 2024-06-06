package org.giefront;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class TestFront extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Object root = FXMLLoader.load(getClass().getResource("/org/Interfaces/CommandV.fxml"));
        Scene scene = new Scene((Parent) root, 1300, 1000);
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}