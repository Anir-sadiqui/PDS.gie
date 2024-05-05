package org.gieback.Controller;
import jakarta.ws.rs.core.Response;
import org.gieback.Entity.Adresse;
import org.gieback.Entity.ContactType;
import org.gieback.Entity.Entreprise;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import org.gieback.Entity.Personne;
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
    @PATCH
    @Path("ModifierEntreprise/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifier(@PathParam("id") int id, Map<String, String> attributs) {
        entrepriseService.modifier(id, attributs);
        return Response.noContent().build();
    }

    @DELETE
    @Path("DeleteEntreprise/{id}")
    public Response deleteByid(@PathParam("id") int id) {
        entrepriseService.deleteByid(id);
        return Response.noContent().build();
    }

    @GET
    @Path("sortById/{ordre}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Entreprise> sortById(@PathParam("ordre") String ordre) {
        return entrepriseService.sortById(ordre);
    }
    @GET
    @Path("sortByRs/{ordre}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Entreprise> sortByVille(@PathParam("ordre") String ordre) {
        return entrepriseService.sortByRs(ordre);
    }

    @GET
    @Path("getByRs/{Rs}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEnterpriseByRs(@PathParam("Rs") String Rs) {
        Entreprise entreprise = entrepriseService.getEnterpriseByRs(Rs);
        if (entreprise != null) {
            return Response.ok(entreprise).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @GET
    @Path("getByFj/{Fj}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Entreprise> getByFj(@PathParam("Fj") String Fj) {
        return entrepriseService.getByFj(Fj);
    }

    @GET
    @Path("/getByemail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Entreprise getByEmail(@PathParam("email") String email) {
        return entrepriseService.getByEmail(email);
    }


    @GET
    @Path("GetByType/{t}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Entreprise> getByType(@PathParam("t") ContactType type) {
        return entrepriseService.getByType(type);
    }

    @PATCH
    @Path("/addType/{id}/{t}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addType(@PathParam("id") String id, @PathParam("t") ContactType type ){
        entrepriseService.addType(id, type);
        return Response.noContent().build();
    }
    @PATCH
    @Path("/addType/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addType(@PathParam("id") String id ){
        entrepriseService.DeleteType(id);
        return Response.noContent().build();
    }

    @GET
    @Path("getTypeByRs/{Rs}/{t}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTypeByRs(@PathParam("Rs") String Rs, @PathParam("t") ContactType type) {
        Entreprise entreprise = entrepriseService.getTypeByRs(Rs, type);
        if (entreprise != null) {
            return Response.ok(entreprise).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @GET
    @Path("getTypeByFj/{Fj}/{t}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Entreprise> getTypeByFj(@PathParam("Fj") String Fj, @PathParam("t") ContactType type) {
        return entrepriseService.getTypeByFj(Fj, type);
    }



}


