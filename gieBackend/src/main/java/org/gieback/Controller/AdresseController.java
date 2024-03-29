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
import java.util.Map;

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
    @GET
    @Path("/getByVille/{ville}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Adresse> getByVille(@PathParam("ville") String ville) {
        return adresseService.getByVille(ville);
    }

    @GET
    @Path("/getByQuartier/{quartier}/{ville}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Adresse> getByQuartier(@PathParam("quartier") String quartier, @PathParam("ville") String ville) {
        return adresseService.getByQuartier(quartier, ville);
    }
    @GET
    @Path("/sortByVille")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Adresse> sortByVille(@QueryParam("ordre") String ordre) {
        return adresseService.sortByVille(ordre);
    }

    @DELETE
    @Path("/DeleteById{id}")
    public Response deleteById(@PathParam("id") int id) {
        adresseService.deleteById(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    @PUT
    @Path("/ModifierAdresse{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifierAdresse(@PathParam("id") int id, Map<String, String> attributs) {
        adresseService.modifierAdresse(id, attributs);
        return Response.noContent().build();

    }

}
