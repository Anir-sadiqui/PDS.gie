package org.giefront;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.gieback.Entity.Contact;
import org.gieback.Entity.Entreprise;
import org.gieback.Entity.Personne;
import org.giefront.Service.AdresseService;
import org.giefront.Service.ContactService;
import org.giefront.Service.EntrepriseService;
import org.giefront.Service.PersonneService;

public class ContactController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Contact> contactss=c.getAll();
        for (Contact c:contactss){
            if(c.getClass().equals("Entreprise")){
                ObservableList<Entreprise> contactObservableList= FXCollections.observableList(e.getAll());
                C_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                C_email.setCellValueFactory(new PropertyValueFactory<>("email"));
                C_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
//                C_N.setCellValueFactory(new PropertyValueFactory<>("nom"));
//
//                C_p.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                C_FJ.setCellValueFactory(new PropertyValueFactory<>("formeJuridique"));
                C_RS.setCellValueFactory(new PropertyValueFactory<>("raisonSocial"));
                C_id_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse_id"));
                C_TableContact.setItems(contactObservableList);

            }
            else{
                ObservableList<Personne> contactObservableList= FXCollections.observableList(p.getAll());
                C_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                C_email.setCellValueFactory(new PropertyValueFactory<>("email"));
                C_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
                C_N.setCellValueFactory(new PropertyValueFactory<>("nom"));

                C_p.setCellValueFactory(new PropertyValueFactory<>("prenom"));
//                C_FJ.setCellValueFactory(new PropertyValueFactory<>("formeJuridique"));
//                C_RS.setCellValueFactory(new PropertyValueFactory<>("raisonSocial"));
                C_id_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse_id"));
                C_TableContact.setItems(contactObservableList);

            }
        }






    }
    EntrepriseService e=new EntrepriseService();
    PersonneService p=new PersonneService();
    ContactService c=new ContactService();


        @FXML
        private TableColumn C_FJ;

        @FXML
        private TableColumn C_N;

        @FXML
        private TableColumn C_RS;

        @FXML
        private TableView C_TableContact;

        @FXML
        private TableColumn C_email;

        @FXML
        private TableColumn C_id;

        @FXML
        private TableColumn C_p;

        @FXML
        private TableColumn C_phone;
        @FXML
       private TableColumn C_id_adresse;




}
