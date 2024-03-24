package org.gieback.Controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.gieback.Entity.Adresse;
import org.gieback.Entity.Entreprise;
import org.gieback.Service.AdresseService;
import org.gieback.Service.EntrepriseService;
import org.gieback.Service.IAdresseService;
import org.gieback.Service.IEntrepriseService;

import java.util.List;
@Path("/adresse")

public class AdresseController {

    IAdresseService adresseService=new AdresseService();
    @GET
    @Path("/getAll")

    @Produces(MediaType.APPLICATION_JSON)
    public List<Adresse> getAllAdresses() {
        return adresseService.getAllAdresses();
    }

    @GET
    @Path("getById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEnterpriseById(@PathParam("id") int id) {
        Adresse a = adresseService.getAdresseById(id);
        if (a != null) {
            return Response.ok(a).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/add")

    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEnterprise(Adresse a) {
        adresseService.addAdresse(a);
        return Response.status(Response.Status.CREATED).build();
    }
}
