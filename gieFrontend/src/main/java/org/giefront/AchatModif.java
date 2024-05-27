package org.giefront;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.giefront.DTO.*;
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
    private  ChoiceBox CB_F  ;
    @FXML
    private  ChoiceBox CB_D  ;
    @FXML
    private TextField Text_Field_Q;
    @FXML
    private TextField Text_Field_N;
    Achat a = AchatController.a;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CB_F.setValue(a.getSupplier());
        CB_D.setValue(a.getDetails().getProduct().getDescription());
        Text_Field_N.setText(a.getDetails().getProduct().getName());
        Text_Field_Q.setText(String.valueOf(a.getDetails().getQuantity()));
        Text_Field_N.setEditable(false);
        loadEntreprises();
        loadPersonnes();
        initTypeF();
//        desc();
    }

    public int extractID(String text) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);

        return Integer.parseInt(matcher.group());
    }



    public void OnBtnPClick(ActionEvent event) throws IOException {
        Map<String, String> attributs = new HashMap<>();
        if (CB_D.getValue() != null){
            attributs.put("Product",(String) CB_D.getValue());
        }
        if (CB_F.getValue() != null) {
            int id = extractID((String) CB_F.getValue());
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
            CB_F.getItems().addAll(personneNames);
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
