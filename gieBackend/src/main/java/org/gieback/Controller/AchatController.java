package org.gieback.Controller;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.gieback.DTO.AchatDTO;
import org.gieback.DTO.CommandeDTO;
import org.gieback.Entity.Achat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.gieback.Entity.Commande;
import org.gieback.Entity.Contact;
import org.gieback.Service.AchatService;
import org.gieback.Service.IAchatService;


@Path("/achat")
public class AchatController{
    IAchatService achatService = new AchatService();


    @POST
    @Path("/ajouter")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPurchase(AchatDTO achat) {
        achatService.add(toAEntity(achat));
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
    @Path("/getByComm/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AchatDTO> getByComm(@PathParam("id") int id) {
        List<Achat> achats = achatService.getByCommande(id);
        List<AchatDTO> achatDTOs = achats.stream().map(this::toADTO).collect(Collectors.toList());
        return achatDTOs;
    }

    @GET
    @Path("/chercherParFournisseur/{idf}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AchatDTO> searchPurchasesBySupplier(@PathParam("idf") int idf) {
        List<Achat> achats = achatService.chercherParFournisseur(idf);
        List<AchatDTO> achatDTOs = achats.stream().map(this::toADTO).collect(Collectors.toList());
        return achatDTOs;
    }

    public Achat toAEntity(AchatDTO dto) {
        CommandeController cc = new CommandeController();
        Achat achat = new Achat();
        achat.setId(dto.getId());
        achat.setDetails(dto.getDetails());
        achat.setSupplier(dto.getSupplier());
        achat.setC(cc.toEntity(dto.getC()));
        return achat;
    }

    public AchatDTO toADTO(Achat achat) {
        AchatDTO dto = new AchatDTO();
        CommandeController cc = new CommandeController();
        dto.setId(achat.getId());
        dto.setDetails(achat.getDetails());
        dto.setSupplier(achat.getSupplier());
        dto.setC(cc.toDTO(achat.getC()));
        return dto;
    }


}



