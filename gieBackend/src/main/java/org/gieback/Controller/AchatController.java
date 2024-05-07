package org.gieback.Controller;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.gieback.Entity.Achat;
import java.util.Date;
import java.util.List;

import org.gieback.Entity.Contact;
import org.gieback.Service.AchatService;
import org.gieback.Service.IAchatService;


@Path("/achat")
public class AchatController{
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
    public Response modifyPurchase(@PathParam("id") int id, Achat modifiedAchat) {
        achatService.modifyPurchase(id, modifiedAchat.getPurchaseDate(), modifiedAchat.getSupplier());
        return Response.noContent().build();
    }

    @GET
    @Path("/chercherParDate/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Achat> searchPurchasesByDate(@PathParam("date") Date date) {
        return achatService.searchPurchasesByDate(date);
    }

    @GET
    @Path("/chercherParId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Achat> searchPurchasesById(@PathParam("id") int id) {
        return achatService.searchPurchasesById(id);
    }

    @GET
    @Path("/chercherParFournisseur")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Achat> searchPurchasesBySupplier(Contact f) {
        return achatService.searchPurchasesBySupplier(f);
    }


}



