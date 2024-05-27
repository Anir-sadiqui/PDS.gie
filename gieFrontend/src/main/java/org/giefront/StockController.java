package org.giefront;

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
import javafx.scene.control.skin.TableCellSkin;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.giefront.DTO.Category;
import org.giefront.DTO.Entreprise;
import org.giefront.DTO.Product;

import org.giefront.Service.IProductService;
import org.giefront.Service.ProductService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class StockController implements Initializable {

    @FXML
    private Button Add;

    @FXML
    private Button Bttn_Fetch;

    @FXML
    private TableView<Product> C_TableProduct = new TableView<>();

    @FXML
    private TableColumn<Product, Category> CategoryColumn= new TableColumn<>();

    @FXML
    private ChoiceBox<Product> ChoiceBox_PD;

    @FXML
    private ChoiceBox<Category> ChoiceBox_SC;

    @FXML
    private TableColumn<Product, String> DescrpColumn = new TableColumn<>();

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
    private TableColumn<Product, Button> buttonColumn = new TableColumn<>();

    @FXML
    private TableColumn<Product, String> nameColumn = new TableColumn<>();

    private IProductService productService = new ProductService();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        C_TableProduct.setRowFactory(tv -> {
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 1) {
                    Product clickedRow = row.getItem();
                    showAction(clickedRow);

                }
            });
            return row;
        });




}

    private void showAction(Product clickedRow) {

    }
    }

