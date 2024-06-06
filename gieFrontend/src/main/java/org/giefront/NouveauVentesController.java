package org.giefront;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.giefront.DTO.*;
import org.giefront.DTO.EtatVentes;
import org.giefront.Service.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NouveauVentesController implements Initializable {


    @FXML
    private ImageView img;
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
    VentesService as = new VentesService();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        remplirCat();
    }



    @FXML
    void ajouter(ActionEvent event) throws IOException {
        if (FournisseurComboBox.getValue() == null ||
                CategorieComboBox.getValue() == null ||
                txtQuantite.getText().isEmpty() ||
                CB_N.getValue()== null){
            showAlert("Please fill all fields!");
            return;
        }
        ProductService productService = new ProductService();
        Ventes na = new Ventes();
        na.setC(CommandVController.c);
        int id = extractID(FournisseurComboBox.getValue());
        if (EntrepriseRadioButton.isSelected()){
            EntrepriseService es = new EntrepriseService();
            na.setSupplier(es.getById(id));
        }
        else {
            PersonneService es = new PersonneService();
            na.setSupplier(es.getById(id));
        }
        AchatDetail d = new AchatDetail();
        d.setQuantity(Integer.parseInt(txtQuantite.getText()));
        d.setProduct(productService.getbyname((String) CB_N.getValue()));
        d.setTotalPrice((d.getQuantity()*d.getProduct().getPrix()));
        na.setDetails(d);
        CommandVService cs = new CommandVService();
        List<CommandV> CommandVs = cs.getByEtat(EtatVentes.In_Preparation.name());
        List<Ventes> Ventes = new ArrayList<>();
        for (CommandV c : CommandVs){
            Ventes.addAll(as.getByComm(Math.toIntExact(c.getId())));
        }
        List<AchatDetail> details = new ArrayList<>();
        for (Ventes a : Ventes){
            details.add(a.getDetails());
        }
        List<Product> products = new ArrayList<>();
        for (AchatDetail ad : details){
            products.add(ad.getProduct());
        }
        if (!products.contains(productService.getbyname((String) CB_N.getValue()))) {
            as.ajouter(na);
        }
        else {
            showAlert("This product has already been purchased.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public int extractID(String text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Input text is null or empty.");
        }

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        } else {
            throw new IllegalArgumentException("No numeric value found in the input text: " + text);
        }
    }





    private void loadEntreprises() {
        EntrepriseService ps = new EntrepriseService();
        List<String> personneNames = new ArrayList<>();
        for (Entreprise p : ps.getAll()){
            if (p.getContactType()== ContactType.CLIENT){
                personneNames.add(p.getRaisonSocial() + " " +p.getId());
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
            if (p.getContactType()== ContactType.CLIENT){
                personneNames.add(p.getNom() + " " + p.getPrenom() + " " +p.getId() );
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

    public void nameP(ActionEvent event) {
        String cat =(CategorieComboBox.getValue());
        if (cat != null){
            ProductService ps = new ProductService();
            for (Product p : ps.getbyCat(cat)){
                CB_N.getItems().add(p.getName() );
            }
        }
    }

    public void loadP(ActionEvent event) {
        FournisseurComboBox.getItems().removeAll(FournisseurComboBox.getItems());
        loadPersonnes();
    }

    public void loadE(ActionEvent event) {
        FournisseurComboBox.getItems().removeAll(FournisseurComboBox.getItems());
        loadEntreprises();
    }

    public void onRet(ActionEvent event) {
        try {
            FXMLLoader f = new FXMLLoader();
            f.setLocation(getClass().getResource("/org/Interfaces/Ventes interface.fxml"));
            Node n = f.load();
            mainAnchor.getChildren().setAll(n);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void imgLoad(ActionEvent event) {
        String name = (String) CB_N.getValue();
        if (name != null){
            ProductService ps = new ProductService();
            img.setImage(new Image(ps.getbyname(name).getImagePath()));
        }

    }
}
