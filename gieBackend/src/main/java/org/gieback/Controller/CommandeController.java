package org.gieback.Controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.gieback.DTO.AchatDTO;
import org.gieback.DTO.CommandeDTO;
import org.gieback.Entity.Achat;
import org.gieback.Entity.Commande;
import org.gieback.Entity.Entreprise;
import org.gieback.Entity.EtatCommande;
import org.gieback.Service.CommandeService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Path("/Commande")
public class CommandeController {
    CommandeService cs = new CommandeService();

    @GET
    @Path("/achats/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AchatDTO> getAllachats(@PathParam("id") int id) {
        List<Achat> achats = cs.getAllachats(id);
        List<AchatDTO> achatDTOs = achats.stream().map(this::toADTO).collect(Collectors.toList());
        return achatDTOs;
    }
    public AchatDTO toADTO(Achat achat) {
        AchatController ac = new AchatController();
        return ac.toADTO(achat);
    }


    @GET
    @Path("/CommandeByDate")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CommandeDTO> getByDate(@QueryParam("date") String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<Commande> commandes = cs.getComByDate(localDate);
        List<CommandeDTO> commandeDTOs = commandes.stream().map(this::toDTO).collect(Collectors.toList());
        return commandeDTOs;
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
    public List<CommandeDTO> getByEtat(@PathParam("e") String e) {
        List<Commande> commandes = cs.getByEtat(e);
        List<CommandeDTO> commandeDTOs = commandes.stream().map(this::toDTO).collect(Collectors.toList());
        return commandeDTOs;
    }

    @DELETE
    @Path("/deleteComm/{id}")
    public void deleteComm(@PathParam("id") int id) {
        cs.deleteComm(id);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addComm(CommandeDTO c) {
        cs.addComm(toEntity(c));
        return Response.status(Response.Status.CREATED).build();
    }

    public Commande toEntity(CommandeDTO dto) {
        Commande commande = new Commande();
        commande.setId(dto.getId());
        commande.setAchats(dto.getAchats());
        commande.setPurchaseDate(LocalDate.parse(dto.getPurchaseDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        commande.setE(dto.getE());
        return commande;
    }

    public CommandeDTO toDTO(Commande commande) {
        CommandeDTO dto = new CommandeDTO();
        dto.setId(commande.getId());
        dto.setAchats(commande.getAchats());
        dto.setPurchaseDate(commande.getPurchaseDate().toString());
        dto.setE(commande.getE());
        return dto;
    }

    @GET
    @Path("/Commandes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CommandeDTO> getAllCom() {
        List<Commande> commandes = cs.getAllCom();
        List<CommandeDTO> commandeDTOs = commandes.stream().map(this::toDTO).collect(Collectors.toList());
        return commandeDTOs;
    }
}
