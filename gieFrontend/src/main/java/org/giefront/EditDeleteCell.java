package org.giefront;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import org.giefront.DTO.Personne;

public class EditDeleteCell extends TableCell<Personne, Void> {
    private final Button editButton = new Button("Edit");
    private final Button deleteButton = new Button("Delete");

    public EditDeleteCell(TableColumn<Personne, Void> column) {
        // Add styling if necessary
        editButton.getStyleClass().add("edit-delete-button");
        deleteButton.getStyleClass().add("edit-delete-button");

        // Add behavior when buttons are clicked
        editButton.setOnAction(event -> {
            // Get the fournisseur item for the current row
            Personne fournisseur = getTableView().getItems().get(getIndex());
            // Call your edit method with the fournisseur
            // controller.editFournisseur(fournisseur);
        });

        deleteButton.setOnAction(event -> {
            // Get the fournisseur item for the current row
            Personne fournisseur = getTableView().getItems().get(getIndex());
            // Call your delete method with the fournisseur
            // controller.deleteFournisseur(fournisseur);
        });
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