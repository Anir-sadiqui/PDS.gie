package org.gieback.Controller;
import jakarta.ws.rs.core.Response;
import org.gieback.Entity.Personne;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import org.gieback.Service.EntrepriseService;
import org.gieback.Service.IEntrepriseService;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.*;
import org.gieback.Service.IPersonneService;
import org.gieback.Service.PersonneService;

import java.util.List;

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

    @POST
    @Path("/add")

    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPersonne(Personne p) {
        personneService.addPersonne(p);
        return Response.status(Response.Status.CREATED).build();
    }


}
