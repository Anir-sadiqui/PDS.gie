package org.gieback.Controller;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.gieback.Entity.Achat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.gieback.Entity.Commande;
import org.gieback.Entity.Contact;
import org.gieback.Service.AchatService;
import org.gieback.Service.IAchatService;


@Path("/achat")
public class AchatController{
    IAchatService achatService = new AchatService();

//    @GET
//    @Path("/getAll")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public List<Achat> getAllPurchases(Commande c ) {
//        return achatService.getAll(c);
//    }

    @POST
    @Path("/ajouter")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPurchase(Achat achat) {
        achatService.add(achat);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/Supprimer/{id}")
    public Response deletePurchaseById(@PathParam("id") int id) {
        achatService.deleteById(id);
        return Response.noContent().build();
    }

    @PATCH
    @Path("/modifier/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifyPurchase(@PathParam("id") String id, Map<String, String> attributs) {
        achatService.modifier(id, attributs);
        return Response.noContent().build();
    }

    @GET
    @Path("/chercherParDate")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Achat> searchPurchasesByDate( @QueryParam("date") String date) {
        LocalDate localDate = LocalDate.parse(date);
        return achatService.chercherParDate(localDate);
    }

    @GET
    @Path("/getByComm/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Achat> getByComm(@PathParam("id") int id) {
        return achatService.getByCommande(id);
    }

    @GET
    @Path("/chercherParFournisseur/{idf}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Achat> searchPurchasesBySupplier(@PathParam("idf") int idf) {
        return achatService.chercherParFournisseur(idf);
    }


}



