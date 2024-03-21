package org.gieback.Controller;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import org.gieback.Entity.Contact;
import org.gieback.Service.ContactService;
import org.gieback.Service.IContactService;

import java.util.List;

@Path("/contact")

public class ContactController {
    IContactService c = new ContactService();

    @GET
    @Path("/getClients")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Contact> getAll() {
        return c.getAll();
    }


    @POST
    @Path("/addContact")
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(String nom, String adresse, String telephone, String email, int code_postal) {
        Contact c =new Contact(nom,adresse,telephone,email,code_postal);
        this.c.ajouter(c);
       System.out.println(c);

    }

}