package org.gieback.Controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.gieback.Entity.Achat;
import org.gieback.Service.AchatService;
import org.gieback.Service.IAchatService;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.gieback.Entity.Contact;

@Path("/achat")
public class AchatController {
    IAchatService achatService = new AchatService();

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Achat> getAllPurchases() {
        return achatService.getAllPurchases();
    }

    @POST
    @Path("/ajouter")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPurchase(Achat achat) {
        System.out.println(achat);
        achatService.addPurchase(achat);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/Supprimer/{id}")
    public Response deletePurchaseById(@PathParam("id") int id) {
        achatService.deletePurchaseById(id);
        return Response.noContent().build();
    }

    @PATCH
    @Path("/modifier/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifyPurchase(@PathParam("id") String id, Map<String, Integer> attributs) {
        achatService.modifier(id, attributs);
        return Response.noContent().build();
    }

    @GET
    @Path("/chercherParDate")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Achat> searchPurchasesByDate(@QueryParam("date") Date date) {
        return achatService.searchPurchasesByDate(date);
    }

    @GET
    @Path("/chercherParId")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Achat> searchPurchasesById(@QueryParam("id") int id) {
        return achatService.searchPurchasesById(id);
    }

    @GET
    @Path("/chercherParFournisseur")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Achat> searchPurchasesBySupplier(Contact f) {
        return achatService.searchPurchasesBySupplier(f);
    }
}
