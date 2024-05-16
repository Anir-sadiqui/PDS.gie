package org.giefront;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.giefront.DTO.Category;
import org.giefront.DTO.EtatStock;
import org.giefront.DTO.Personne;
import org.giefront.DTO.Product;
import org.giefront.Service.IProductService;
import org.giefront.Service.ProductService;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class StockController implements Initializable {

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
        getAll();
        remplirType();



}

    private void showAction(Product clickedRow) {
        IconC.setVisible(true);
//        IconC.setImage(new Image(clickedRow.getImagePath()));
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
        if (!Text_Field_S.getText().isEmpty()){
            if (!ChoiceBox_SC.getValue().isEmpty() && ChoiceBox_PD.getValue().isEmpty()) {
                List<Product> p = productService.getbyCat(Category.valueOf(ChoiceBox_SC.getValue()));
                for (Product produit : p ){
                    if (!Objects.equals(produit.getName(), Text_Field_S.getText())){
                        p.remove(produit);
                    }
                }
                remplirTAb(FXCollections.observableList(p));
            }
            remplirTAb(FXCollections.observableList(productService.getbyname(Text_Field_S.getText())));
        }
        else {
            if (!ChoiceBox_SC.getValue().isEmpty() && ChoiceBox_PD.getValue().isEmpty()){
                remplirTAb(FXCollections.observableList(productService.getbyCat(Category.valueOf(ChoiceBox_SC.getValue()))));
            } else if (ChoiceBox_SC.getValue().isEmpty() && !ChoiceBox_PD.getValue().isEmpty()) {
                if (ChoiceBox_SC.getValue()==EtatStock.Epuise.name()){
                    remplirTAb(FXCollections.observableList(productService.);
                }
                remplirTAb(FXCollections.observableList(p));
            }

        }

    }
    @FXML
    private void OnDelete(){
        Product selectedPerson =  C_TableProduct.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            productService.deleteProduct(Math.toIntExact(selectedPerson.getId()));
            C_TableProduct.getItems().remove(selectedPerson);
            showMessage("Suppression reussie");
        }
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void OnBtnPClick(ActionEvent event) {
    }

    public void remplirTAb(ObservableList<Product> products){
        IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        CategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        QuantiteColumn.setCellValueFactory(new PropertyValueFactory<>("q"));
        Prix_unitaireColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        C_TableProduct.setItems(products);
    }
}

