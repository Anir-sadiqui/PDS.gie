package org.giefront.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.giefront.DTO.Entreprise;
import org.giefront.DTO.Personne;

import java.io.IOException;

public class EditDeleteCellEnt extends TableCell<Entreprise, Void> {

    @FXML
    private TableView<Entreprise> fournisseurTable;

    private final Button editButton = new Button("Edit");
    private final Button deleteButton = new Button("Delete");

    public EditDeleteCellEnt(TableColumn<Entreprise, Void> column) {
        // Add styling if necessary
        editButton.getStyleClass().add("edit-delete-button");
        deleteButton.getStyleClass().add("edit-delete-button");

        // Add behavior when buttons are clicked
        editButton.setOnAction(event -> {
            // Get the fournisseur item for the current row
            Entreprise fournisseur = getTableView().getItems().get(getIndex());
            switchToEditEntro(fournisseur) ;

        });

        deleteButton.setOnAction(event -> {
            // Get the fournisseur item for the current row
            Entreprise fournisseur = getTableView().getItems().get(getIndex());

        });
    }

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    @FXML
    private void switchToEditEntro(Entreprise entreprise) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/giefront/EntrepriseModification.fxml")); // Correct path to your FXML
            Parent root = fxmlLoader.load();

            // Get the controller and pass the data
            EntrepriseModifController modificationController = fxmlLoader.getController();
            modificationController.setEntroprise(entreprise);

            stage = (Stage) fournisseurTable.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {
            // Create a pane to hold the buttons
            Pane pane = new Pane(editButton, deleteButton);
            // Set the pane as the graphic of the cell
            setGraphic(pane);
        }
    }
}