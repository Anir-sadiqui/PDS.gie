package org.gieback.Controller;
import jakarta.ws.rs.core.Response;
import org.gieback.Entity.Compte;
import org.gieback.Entity.ContactType;
import org.gieback.Entity.Personne;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import org.gieback.Service.EntrepriseService;
import org.gieback.Service.IEntrepriseService;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.*;
import org.gieback.Service.IPersonneService;
import org.gieback.Service.PersonneService;
import org.hibernate.service.spi.ServiceException;

import java.util.List;
import java.util.Map;

@Path("/personne")


public class PersonneController {
    IPersonneService personneService=new PersonneService();

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Personne> getAllEnterprises() {
        return personneService.getAllPersonnes();
    }

    @GET
    @Path("getById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonneById(@PathParam("id") int id) {
        Personne personne = personneService.getPersonneById(id);
        if (personne != null) {
            return Response.ok(personne).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

//    @POST
//    @Path("/add")
//
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response addPersonne(Personne p) {
//        personneService.addPersonne(p);
//        return Response.status(Response.Status.CREATED).build();
//    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPersonne(Personne p) {
        try {
            personneService.addPersonne(p);
            return Response.status(Response.Status.CREATED).build();
        } catch (ServiceException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error adding person: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("GetBynom/{n}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Personne> getByNom(@PathParam("n") String nom) {
        return personneService.getByNom(nom);
    }

    @GET
    @Path("getPersonneByPrenom/{prenom}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Personne> getByPrenom(@PathParam("prenom") String prenom) {
        return personneService.getByPrenom(prenom);
    }

    @GET
    @Path("sortBynom/{ordre}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Personne> sortByNom(@PathParam("ordre") String ordre) {
        return personneService.sortByNom(ordre);
    }

    @GET
    @Path("sortByid/{ordre}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Personne> sortById(@PathParam("ordre") String ordre) {
        return personneService.sortById(ordre);
    }

    @PATCH
    @Path("Modifier/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifier(@PathParam("id") String id, Map<String, String> attributs) {
        personneService.modifier(id, attributs);
        return Response.noContent().build();
    }

    @DELETE
    @Path("DeletePersonne/{id}")
    public Response deleteById(@PathParam("id") int id) {
        personneService.deleteById(id);
        return Response.noContent().build();
    }
    @GET
    @Path("/getByemail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Personne getByEmail(@PathParam("email") String email) {
        return personneService.getByEmail(email);
    }

    @GET
    @Path("GetByType/{t}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Personne> getByType(@PathParam("t") ContactType type) {
        return personneService.getByType(type);
    }

    @PATCH
    @Path("/addType/{id}/{t}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addType(@PathParam("id") String id, @PathParam("t") ContactType type ){
        personneService.addType(id, type);
        return Response.noContent().build();
    }
    @PATCH
    @Path("/deleteType/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addType(@PathParam("id") String id ){
        personneService.DeleteType(id);
        return Response.noContent().build();
    }


}



