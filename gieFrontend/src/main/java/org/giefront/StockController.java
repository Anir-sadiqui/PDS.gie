package org.giefront;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.giefront.DTO.Category;
import org.giefront.DTO.EtatStock;
import org.giefront.DTO.Product;
import org.giefront.Service.ProductService;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class StockController implements Initializable {

    @FXML
    private Button refresh;
    @FXML
    private  Button modify;
    @FXML
    private Button delete;
    @FXML
    private TextArea DetailsC;
    @FXML
    private ImageView IconC;
    @FXML
    private Button Add;

    @FXML
    private Button Bttn_Fetch;

    @FXML
    private TableView<Product> C_TableProduct = new TableView<>();

    @FXML
    private TableColumn<Product, Category> CategoryColumn= new TableColumn<>();

    @FXML
    private ChoiceBox<String> ChoiceBox_PD;

    @FXML
    private ChoiceBox<String> ChoiceBox_SC;


    @FXML
    private TableColumn<Product, Integer> IdColumn = new TableColumn<>();

    @FXML
    private TableColumn<Product, Double> Prix_unitaireColumn = new TableColumn<>();

    @FXML
    private TableColumn<Product, Integer> QuantiteColumn = new TableColumn<>();

    @FXML
    private Label Text_Field_C;

    @FXML
    private Label Text_Field_PD;

    @FXML
    private Label Text_Field_S;

    @FXML
    private TextField Text_Field_Search;



    @FXML
    private TableColumn<Product, String> nameColumn = new TableColumn<>();

    private ProductService productService = new ProductService();
    public static Product product;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        C_TableProduct.setRowFactory(tv -> {
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 1) {
                    Product clickedRow = row.getItem();
                    showAction(clickedRow);
                    product=row.getItem();
                    IconC.setImage(new Image(row.getItem().getImagePath()));

                }
            });
            return row;
        });
        getAll();
        remplirType();

}

    private void showAction(Product clickedRow) {
        IconC.setVisible(true);
        DetailsC.setVisible(true);
        DetailsC.setEditable(false);
        DetailsC.setText(clickedRow.getName() + "\n" + clickedRow.getDescription());
    }

    private void getAll() {
       remplirTAb(FXCollections.observableList(productService.getAll()));
    }
    private void remplirType() {
        ObservableList<String> options = FXCollections.observableArrayList();
        ObservableList<String> options2 = FXCollections.observableArrayList();
        for (Category c : Category.values()){
            options.add(c.name());
        }
        for (EtatStock c : EtatStock.values()){
            options2.add(c.name());
        }
        ChoiceBox_SC.setItems(options);
        ChoiceBox_PD.setItems(options2);
    }

    @FXML
    private void Fetch_OnAction(){
        if (ChoiceBox_SC.getValue() != null){
            ObservableList<Product> products =FXCollections.observableList(productService.getbyCat(ChoiceBox_SC.getValue()));
            if (Text_Field_Search.getText()== null && ChoiceBox_PD.getValue() == null){
                remplirTAb(products);
            }
            else if (!Text_Field_Search.getText().isEmpty() && ChoiceBox_PD.getValue() == null) {
                List<Product> p2 = new ArrayList<>();
                for (Product p : products){
                    if (p.getName().equals(Text_Field_Search.getText())){
                        p2.add(p);
                    }
                }
                remplirTAb(FXCollections.observableList(p2));
            }
            else if (Text_Field_Search.getText().isEmpty() && ChoiceBox_PD.getValue() != null) {
                ObservableList<Product> products2 =FXCollections.observableList(productService.isAvailable(ChoiceBox_PD.getValue()));
                List<Product> p2 = new ArrayList<>();
                for (Product p : products2){
                    if (p.getCategory().name().equals(ChoiceBox_SC.getValue())){
                        p2.add(p);
                    }
                }
                remplirTAb(FXCollections.observableList(p2));
            }
            else {
                ObservableList<Product> products2 =FXCollections.observableList(productService.isAvailable(ChoiceBox_PD.getValue()));
                List<Product> p2 = new ArrayList<>();
                for (Product p : products2){
                    if (p.getCategory().name().equals(ChoiceBox_SC.getValue()) && p.getName().equals(Text_Field_Search.getText())){
                        p2.add(p);
                    }
                }
                remplirTAb(FXCollections.observableList(p2));
            }
        }

        else if (ChoiceBox_SC.getValue() == null) {
            if (!Text_Field_Search.getText().isEmpty() && ChoiceBox_PD.getValue() == null){
                ObservableList<Product> products =FXCollections.observableList(Collections.singletonList(productService.getbyname(Text_Field_Search.getText())));
                remplirTAb(products);
            }
            else if (!Text_Field_Search.getText().isEmpty() && ChoiceBox_PD.getValue() != null) {
                ObservableList<Product> products2 =FXCollections.observableList(productService.isAvailable(ChoiceBox_PD.getValue()));
                List<Product> p2 = new ArrayList<>();
                for (Product p : products2){
                    if (p.getName().equals(Text_Field_Search.getText())){
                        p2.add(p);
                    }
                }
                remplirTAb(FXCollections.observableList(p2));
            }
            else if (Text_Field_Search.getText().isEmpty() && ChoiceBox_PD.getValue() != null) {
                ObservableList<Product> products2 =FXCollections.observableList(productService.isAvailable(ChoiceBox_PD.getValue()));
                remplirTAb(products2);
            }
            else {
                getAll();
            }
        }

    }

    @FXML
    private void OnDelete() {
        Product selectedProduct = C_TableProduct.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            // Create the confirmation dialog
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Are you sure you want to delete this product?");
            alert.setContentText("Product: " + selectedProduct.getName());

            // Button options in the dialog
            ButtonType buttonTypeYes = new ButtonType("Yes");
            ButtonType buttonTypeNo = new ButtonType("No", ButtonType.CANCEL.getButtonData());

            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            // Wait for the user's response
            alert.showAndWait().ifPresent(type -> {
                if (type == buttonTypeYes) {
                    productService.deleteProduct(Math.toIntExact(selectedProduct.getId()));
                    C_TableProduct.getItems().remove(selectedProduct);
                    showMessage("Deletion successful");
                } else {
                    showMessage("Deletion cancelled");
                }
            });
        } else {
            showMessage("No product selected");
        }
    }


    private void showMessage(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public void OnBtnPClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/Interfaces/Product.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void remplirTAb(ObservableList<Product> products){
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        CategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        QuantiteColumn.setCellValueFactory(new PropertyValueFactory<>("q"));
        Prix_unitaireColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        C_TableProduct.setItems(products);
    }

    public void OnmodiFy(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/Interfaces/ProductModif.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onref(ActionEvent event) {
        getAll();
        Text_Field_Search.setText(null);
        ChoiceBox_SC.setValue(null);
        ChoiceBox_PD.setValue(null);
    }
}

