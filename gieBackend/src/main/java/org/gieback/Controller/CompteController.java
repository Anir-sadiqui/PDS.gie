package org.gieback.Controller;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.gieback.Entity.Adresse;
import org.gieback.Entity.Compte;
import org.gieback.Service.CompteService;
import org.gieback.Service.ICompteService;

import java.util.List;

@Path("/compte")

public class CompteController {
    ICompteService cs = new CompteService();

    @POST
    @Path("/add")

    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Compte c) {
        cs.add(c);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/getByemail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Compte getByEmail(@PathParam("email") String email) {
        return cs.getByEmail(email);
    }

    @POST
    @Path("/verifyMdp/{email}/{MDP}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean verifyMdp(@PathParam("email") String email,@PathParam("MDP") String MDP) {
        return cs.verifyMdp(MDP,email);
    }


}
