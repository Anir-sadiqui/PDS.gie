package org.giefront;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.giefront.DTO.Ventes;
import org.giefront.DTO.ContactType;
import org.giefront.DTO.Entreprise;
import org.giefront.DTO.Personne;
import org.giefront.Service.VentesService;
import org.giefront.Service.EntrepriseService;
import org.giefront.Service.PersonneService;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VentesModif implements Initializable {


    @FXML
    private RadioButton EntrepriseRadioButton;
    @FXML
    private RadioButton PersonneRadioButton;
    @FXML
    private ChoiceBox<String> CB_F;
    @FXML
    private ChoiceBox CB_D;
    @FXML
    private TextField Text_Field_Q;
    @FXML
    private TextField Text_Field_N;
    Ventes a = VentesController.a;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (a.getSupplier() instanceof Personne) {
            CB_F.getItems().setAll(((Personne) a.getSupplier()).getNom() + " " + ((Personne) a.getSupplier()).getPrenom() + " " + a.getSupplier().getId());
        }
        if (a.getSupplier() instanceof Entreprise) {
            CB_F.getItems().setAll(((Entreprise) a.getSupplier()).getRaisonSocial() + " " + a.getSupplier().getId());
        }
        Text_Field_N.setText(a.getDetails().getProduct().getName());
        Text_Field_Q.setText(String.valueOf(a.getDetails().getQuantity()));
        Text_Field_N.setEditable(false);
        remplType();
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
        VentesService as = new VentesService();
        if (CB_F.getValue() != null) {
            int id = extractID(CB_F.getValue());
            if (EntrepriseRadioButton.isSelected()) {
                EntrepriseService es = new EntrepriseService();
                attributs.put("ClientE", String.valueOf(id));

            } else {
                PersonneService es = new PersonneService();
                attributs.put("ClientP", String.valueOf(id));
            }
        }
        if (!Text_Field_Q.getText().isEmpty()) {
            attributs.put("Quantite", Text_Field_Q.getText());
        }
        as.modifier(String.valueOf(a.getId()), attributs);
        VentesController ac = new VentesController();
        as.getByComm(Math.toIntExact(ac.c.getId()));
    }

    private void remplType() {
        if (EntrepriseRadioButton.isSelected()) {
            loadEntreprises();
        }
        if (PersonneRadioButton.isSelected()) {
            loadPersonnes();
        }
    }


    @FXML
    private void loadEntreprises() {
        EntrepriseService ps = new EntrepriseService();
        List<String> personneNames = new ArrayList<>();
        for (Entreprise p : ps.getAll()) {
            if (p.getContactType() == ContactType.CLIENT) {
                personneNames.add(p.getRaisonSocial() + " " + p.getId());
            }
        }
        CB_F.getItems().removeAll(CB_F.getItems());
        CB_F.getItems().addAll(personneNames);


    }


    @FXML
    private void loadPersonnes() {
        PersonneService ps = new PersonneService();
        List<String> personneNames = new ArrayList<>();
        for (Personne p : ps.getAll()) {
            if (p.getContactType() == ContactType.CLIENT) {
                personneNames.add(p.getNom() + " " + p.getPrenom() + " " + p.getId());
            }
        }
        CB_F.getItems().removeAll(CB_F.getItems());
        CB_F.getItems().addAll(personneNames);
    }

    private void initTypeF() {
        if (a.getSupplier() instanceof Personne) {
            PersonneRadioButton.setSelected(true);
        } else if (a.getSupplier() instanceof Entreprise) {
            EntrepriseRadioButton.setSelected(true);
        }
    }

}
