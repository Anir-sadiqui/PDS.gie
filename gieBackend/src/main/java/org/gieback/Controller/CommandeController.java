package org.gieback.Controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.gieback.Entity.Achat;
import org.gieback.Entity.Commande;
import org.gieback.Entity.Entreprise;
import org.gieback.Entity.EtatCommande;
import org.gieback.Service.CommandeService;

import java.time.LocalDate;
import java.util.List;

@Path("/Commande")
public class CommandeController {
    CommandeService cs = new CommandeService();

    @GET
    @Path("/achats/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Achat> getAllachats(@PathParam("id") int id) {
        return cs.getAllachats(id);
    }

    @GET
    @Path("/CommandeByDate")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Commande> getByDate(@QueryParam("date") String date) {
        LocalDate localDate = LocalDate.parse(date);
        return cs.getComByDate(localDate);
    }

    @PATCH
    @Path("/validerComm/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void validerComm(@PathParam("id") int id) {
        cs.validerComm(id);
    }

    @GET
    @Path("/CommandeByEtat/{e}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Commande> getByEtat(@PathParam("e") String e) {
        return cs.getByEtat(EtatCommande.valueOf(e));
    }

    @DELETE
    @Path("/deleteComm/{id}")
    public void deleteComm(@PathParam("id") int id) {
        cs.deleteComm(id);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addComm(Commande c) {
        cs.addComm(c);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/Commandes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Commande> getAllCom() {
        return cs.getAllCom();
    }
}
