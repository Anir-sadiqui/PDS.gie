package org.giefront.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.giefront.DTO.Category;
import org.giefront.DTO.Product;
import org.giefront.Service.ProductService;

public class StockController implements Initializable {


    @FXML
    private Button Add;

    @FXML
    private Button Btnn_Delete;

    @FXML
    private Button Bttn_Fetch;

    @FXML
    private Button Bttn_update;

    @FXML
    private TableColumn<Product, String> C_D;

    @FXML
    private TableColumn< Product,String> C_Name;

    @FXML
    private TableColumn<Product, Double> C_P;

    @FXML
    private TableColumn<Product, Integer> C_Q;

    @FXML
    private TableView<Product> C_TableProduct;

    @FXML
    private TableColumn<Product, Category> C_ca;

    @FXML
    private TableColumn<Product, Long> C_id;
    @FXML
    private TableColumn<Product, String> C_model;

    @FXML
    private Label Text_Field_S;

    @FXML
    private TextField Text_Field_Search;

    @FXML
    void DeleteOnAction(ActionEvent event) {

    }

    @FXML
    void Fetch_OnAction(ActionEvent event) {
        ProductService productService = new ProductService();
        ObservableList<Product> productList = FXCollections.observableArrayList(productService.getAll());

        C_TableProduct.setItems(productList);
    }

    @FXML
    void OnBtnPClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("product.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void UpdateOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

