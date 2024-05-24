package org.giefront;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import javafx.scene.layout.AnchorPane;
import org.giefront.DTO.*;
import org.giefront.Service.AchatService;
import org.giefront.Service.EntrepriseService;
import org.giefront.Service.PersonneService;
import org.giefront.Service.ProductService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class NouveauAchatController implements Initializable {


    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private ComboBox CB_N;
    @FXML
    private ComboBox CB_D  ;
    @FXML
    private TextField desc;


    @FXML
    private ComboBox<String> FournisseurComboBox;

    @FXML
    private ComboBox<String> CategorieComboBox;

    @FXML
    private RadioButton EntrepriseRadioButton;

    @FXML
    private RadioButton PersonneRadioButton;

    @FXML
    private ToggleGroup Type;

    @FXML
    private Button btnRetour;

    @FXML
    private Button btnValider;

    @FXML
    private TextField txtQuantite;
    AchatService as = new AchatService();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadPersonnes();
        loadEntreprises();
        remplirCat();
    }



    @FXML
    void ajouter(ActionEvent event) throws IOException {
        if (FournisseurComboBox.getValue() == null ||
                CategorieComboBox.getValue() == null ||
                txtQuantite.getText().isEmpty() ||
                desc.getText().isEmpty() ||
                CB_N.getValue()== null ||
                CB_D.getValue() == null ) {
            showAlert("Please fill all fields!");
            return;
        }
        Achat na = new Achat();
        AchatDetail d = new AchatDetail();
        Product p = new Product();
        p.setCategory(Category.valueOf(CategorieComboBox.getValue()));
        p.setName((String) CB_N.getValue());
        p.setDescription((String) CB_D.getValue());
        d.setProduct(p);
        d.setAchat(AchatController.a);
        d.setQuantity(Integer.parseInt(txtQuantite.getText()));
        na.setC(CommandeController.c);
        na.setDetails(d);
        int id = extractID(FournisseurComboBox.getValue());
        if (EntrepriseRadioButton.isSelected()){
            EntrepriseService es = new EntrepriseService();
            na.setSupplier(es.getById(id));
        }
        else {
            PersonneService es = new PersonneService();
            na.setSupplier(es.getById(id));
        }
        as.ajouter(na);


    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public int extractID(String text) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);

        return Integer.parseInt(matcher.group());
    }





    private void loadEntreprises() {
        EntrepriseService ps = new EntrepriseService();
        List<String> personneNames = new ArrayList<>();
        for (Entreprise p : ps.getAll()){
            if (p.getContactType()== ContactType.FOURNISSEUR){
                personneNames.add(p.getRaisonSocial() + " (" +p.getId()+")");
            }
        }
        if (EntrepriseRadioButton.isSelected()) {
            FournisseurComboBox.getItems().addAll(personneNames);
        }

    }


    private void loadPersonnes() {
        PersonneService ps = new PersonneService();
        List<String> personneNames = new ArrayList<>();
       for (Personne p : ps.getAll()){
           if (p.getContactType()== ContactType.FOURNISSEUR){
               personneNames.add(p.getNom() + " " + p.getPrenom() + " (" +p.getId()+")" );
           }
       }
       if (PersonneRadioButton.isSelected()) {
           FournisseurComboBox.getItems().addAll(personneNames);
       }
    }

    public void remplirCat(){
        for (Category cat : Category.values()){
            CategorieComboBox.getItems().add(cat.name());
        }
    }




    public void Desc(ActionEvent event) {
        String p = (String) CB_N.getValue();
        if (p != null) {
            ProductService ps = new ProductService();
            for (Product prod : ps.getbyname(p)){
                CB_D.getItems().add(prod.getDescription());
            }
        }
    }

    public void Back(ActionEvent event) {
        try {
            FXMLLoader f = new FXMLLoader();
            f.setLocation(getClass().getResource("/org/Interfaces/achat interface.fxml"));
            Node n = f.load();
            mainAnchor.getChildren().setAll(n);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void nameP(ActionEvent event) {
        String cat =(CategorieComboBox.getValue());
        if (cat != null){
            ProductService ps = new ProductService();
            for (Product p : ps.getbyCat(cat)){
                CB_N.getItems().add(p.getName());
            }
        }
    }
}
