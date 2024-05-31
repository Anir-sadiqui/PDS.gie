package org.giefront;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import org.giefront.DTO.ContactType;
import org.giefront.DTO.Entreprise;
import org.giefront.DTO.Personne;
import org.giefront.Service.EntrepriseService;
import org.giefront.Service.PersonneService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FEex implements Initializable {
    @FXML
    private ChoiceBox CB_F;
    private EntrepriseService ps = new EntrepriseService();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPersonnes();

    }

    public void OnBtnPClick(ActionEvent event) throws IOException {
        int id = extractID((String) CB_F.getValue());
        ps.addType(id,ContactType.FOURNISSEUR);
    }
    private void loadPersonnes() {
        List<String> personneNames = new ArrayList<>();
        for (Entreprise p : ps.getAll()){
            if (p.getContactType() != ContactType.FOURNISSEUR){
                personneNames.add(p.getRaisonSocial() +  " " +p.getId() );
            }
        }
        CB_F.getItems().addAll(personneNames);
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

}
