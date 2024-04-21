package org.gieback.Controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.gieback.Entity.Adresse;
import org.gieback.Entity.Contact;
import org.gieback.Entity.Entreprise;
import org.gieback.Entity.Personne;
import org.gieback.Service.EntrepriseService;
import org.gieback.Service.IEntrepriseService;
import org.gieback.Service.IPersonneService;
import org.gieback.Service.PersonneService;

import java.util.ArrayList;
import java.util.List;

@Path("/contact")

public class ContactController {
    IEntrepriseService entrepriseService=new EntrepriseService();
    IPersonneService personneService=new PersonneService();
    @GET
    @Path("/getAll")

    @Produces(MediaType.APPLICATION_JSON)
    public List<Contact> getAllContacts() {
        List<Contact> contacts=new ArrayList<>();
        List<Personne> personnes=personneService.getAllPersonnes();
        List<Entreprise> entreprises=entrepriseService.getAllEnterprises();
        contacts.addAll(personnes);
        contacts.addAll(entreprises);
        return contacts;
    }



}
