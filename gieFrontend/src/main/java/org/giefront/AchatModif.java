package org.giefront;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.giefront.DTO.*;
import org.giefront.Service.AchatService;
import org.giefront.Service.EntrepriseService;
import org.giefront.Service.PersonneService;
import org.giefront.Service.ProductService;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AchatModif implements Initializable {


    @FXML
    private RadioButton EntrepriseRadioButton;
    @FXML
    private RadioButton PersonneRadioButton;
    @FXML
    private  ChoiceBox <String> CB_F  ;
    @FXML
    private  ChoiceBox CB_D  ;
    @FXML
    private TextField Text_Field_Q;
    @FXML
    private TextField Text_Field_N;
    Achat a = AchatController.a;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (a.getSupplier() instanceof Personne){
            CB_F.getItems().setAll(((Personne) a.getSupplier()).getNom() + " " + ((Personne) a.getSupplier()).getPrenom()+ " " +a.getSupplier().getId());
        }
        if (a.getSupplier() instanceof  Entreprise){
            CB_F.getItems().setAll(((Entreprise) a.getSupplier()).getRaisonSocial()+ " " +a.getSupplier().getId());
        }
        Text_Field_N.setText(a.getDetails().getProduct().getName());
        Text_Field_Q.setText(String.valueOf(a.getDetails().getQuantity()));
        Text_Field_N.setEditable(false);
        loadEntreprises();
        loadPersonnes();
        initTypeF();
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



    public void OnBtnPClick(ActionEvent event) throws IOException {
        Map<String, String> attributs = new HashMap<>();
        AchatService as = new AchatService();
        if (CB_F.getValue() != null) {
            int id = extractID(CB_F.getValue());
            if (EntrepriseRadioButton.isSelected()) {
                EntrepriseService es = new EntrepriseService();
                attributs.put("FournisseurE", String.valueOf(id));

            } else {
                PersonneService es = new PersonneService();
                attributs.put("FournisseurP", String.valueOf(id));
            }
        }
        if (!Text_Field_Q.getText().isEmpty()){
            attributs.put("Quantite",Text_Field_Q.getText());
        }
        as.modifier(String.valueOf(a.getId()),attributs);
        AchatController ac = new AchatController();
        as.getByComm(Math.toIntExact(ac.c.getId()));
    }



    private void loadEntreprises() {
        EntrepriseService ps = new EntrepriseService();
        List<String> personneNames = new ArrayList<>();
        for (Entreprise p : ps.getAll()){
            if (p.getContactType()== ContactType.FOURNISSEUR){
                personneNames.add(p.getRaisonSocial() + " " +p.getId());
            }
        }
        if (EntrepriseRadioButton.isSelected()) {
            CB_F.getItems().addAll(personneNames);
        }

    }


    private void loadPersonnes() {
        PersonneService ps = new PersonneService();
        List<String> personneNames = new ArrayList<>();
        for (Personne p : ps.getAll()){
            if (p.getContactType()== ContactType.FOURNISSEUR){
                personneNames.add(p.getNom() + " " + p.getPrenom() + " " +p.getId() );
            }
        }
        if (PersonneRadioButton.isSelected()) {
            CB_F.getItems().addAll(personneNames);
        }
    }

    private void initTypeF(){
        if (a.getSupplier() instanceof Personne){
            PersonneRadioButton.setSelected(true);
        } else if (a.getSupplier() instanceof  Entreprise) {
            EntrepriseRadioButton.setSelected(true);
        }
    }


}
