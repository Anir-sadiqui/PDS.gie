package org.gieback.Controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.gieback.Entity.Ventes;
import org.gieback.Service.VentesServices;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/Ventes")
public class VentesController {
    VentesServices vs = new VentesServices();

    @POST
    @Path("/ajouter")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPurchase(Ventes Ventes) {
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/Supprimer/{id}")
    public Response deletePurchaseById(@PathParam("id") int id) {
        vs.deleteById(id);
        return Response.noContent().build();
    }

    @PATCH
    @Path("/modifier/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifyPurchase(@PathParam("id") String id, Map<String, String> attributs) {
        vs.modifier(id, attributs);
        return Response.noContent().build();
    }


    @GET
    @Path("/getByComm/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ventes> getByComm(@PathParam("id") int id) {
        List<Ventes> Ventes = vs.getByCommande(id);
        return Ventes;
    }

    @GET
    @Path("/chercherParFournisseur/{idf}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ventes> searchPurchasesBySupplier(@PathParam("idf") int idf) {
        List<Ventes> Ventes = vs.chercherParFournisseur(idf);
        return Ventes;
    }

}
