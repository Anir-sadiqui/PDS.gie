package org.giefront;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import org.giefront.DTO.*;
import org.giefront.Service.AchatService;
import org.giefront.Service.EntrepriseService;
import org.giefront.Service.PersonneService;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class NouveauAchatController implements Initializable {


    @FXML
    private TextField desc;
    @FXML
    private TextField Nom;

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



    @FXML
    void Fournisseur(ActionEvent event) {
        if (EntrepriseRadioButton.isSelected()) {
            loadEntreprises(); // Load entreprise data if entreprise radio button is selected
        } else if (PersonneRadioButton.isSelected()) {
            loadPersonnes(); // Load personne data if personne radio button is selected
        }
    }

    @FXML
    void Product(ActionEvent event) {

    }

    @FXML
    void retour(ActionEvent event) {

    }
    @FXML
    void selectEntreprise(ActionEvent event) {
        loadEntreprises();
    }

    @FXML
    void selectPersonne(ActionEvent event) {
        loadPersonnes();
    }

    @FXML
    void Back(ActionEvent event) {

    }
    @FXML
    void Nom(ActionEvent event) {

    }







    @FXML
    void ajouter(ActionEvent event) {
        if (FournisseurComboBox.getValue() == null ||
                CategorieComboBox.getValue() == null ||
                txtQuantite.getText().isEmpty() ||
                desc.getText().isEmpty() ||
                Nom.getText().isEmpty()) {
            showAlert("Please fill all fields!");
            return;
        }
        Achat na = new Achat();
        AchatDetail d = new AchatDetail();
        Product p = new Product();
        p.setCategory(Category.valueOf(CategorieComboBox.getValue()));
        p.setName(Nom.getText());
        p.setDescription(desc.getText());
        d.setProduct(p);
        d.setAchat(AchatController.a);
        na.setC(CommandeController.c);
        na.setDetails(d);
        na.setSupplier(FournisseurComboBox.getValue());
        as.ajouter(na);


    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categorie();
    }




    private void loadEntreprises() {
        EntrepriseService ps = new EntrepriseService();
        List<String> personneNames = new ArrayList<>();
        for (Entreprise p : ps.getAll()){
            if (p.getContactType()== ContactType.FOURNISSEUR){
                personneNames.add(p.getRaisonSocial() );
                int id = Math.toIntExact(p.getId());
            }
        }
        FournisseurComboBox.getItems().addAll(personneNames);

    }


    private void loadPersonnes() {
        PersonneService ps = new PersonneService();
        List<String> personneNames = new ArrayList<>();
       for (Personne p : ps.getAll()){
           if (p.getContactType()== ContactType.FOURNISSEUR){
               personneNames.add(p.getNom() + " " + p.getPrenom());
               int id = Math.toIntExact(p.getId());
           }
       }
        FournisseurComboBox.getItems().addAll(personneNames);
    }


    @FXML
    void categorie() {
        List<String> categoryNames = Arrays.stream(Category.values())
                .map(Category::name)
                .collect(Collectors.toList());
        CategorieComboBox.getItems().addAll(categoryNames);
    }

    public void Quantite(ActionEvent event) {
    }
}