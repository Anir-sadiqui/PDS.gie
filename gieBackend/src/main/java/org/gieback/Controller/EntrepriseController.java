package org.gieback.Controller;
import jakarta.ws.rs.core.Response;
import org.gieback.Entity.Adresse;
import org.gieback.Entity.Entreprise;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import org.gieback.Service.AdresseService;
import org.gieback.Service.EntrepriseService;
import org.gieback.Service.IAdresseService;
import org.gieback.Service.IEntrepriseService;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.*;
import java.util.List;
import java.util.Map;

@Path("/entreprise")


public class EntrepriseController {
    IEntrepriseService entrepriseService=new EntrepriseService();
    IAdresseService adresseService=new AdresseService();
    @GET
    @Path("/getAll")

    @Produces(MediaType.APPLICATION_JSON)
    public List<Entreprise> getAllEnterprises() {
        return entrepriseService.getAllEnterprises();
    }

    @GET
    @Path("getById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEnterpriseById(@PathParam("id") int id) {
        Entreprise entreprise = entrepriseService.getEnterpriseById(id);
        if (entreprise != null) {
            return Response.ok(entreprise).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/add")

    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEnterprise(Entreprise entreprise) {

       entrepriseService.addEnterprise(entreprise);
        return Response.status(Response.Status.CREATED).build();
    }
    @PUT
    @Path("/ModifierEntreprise{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifier(@PathParam("id") int id, Map<String, String> attributs) {
        entrepriseService.modifier(id, attributs);
        return Response.noContent().build();
    }
    @DELETE
    @Path("/DeleteEntreprise{id}")
    public Response deleteByid(@PathParam("id") int id) {
        entrepriseService.deleteByid(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/sortById")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Entreprise> sortById(@QueryParam("ordre") String ordre) {
        return entrepriseService.sortById(ordre);
    }


}
