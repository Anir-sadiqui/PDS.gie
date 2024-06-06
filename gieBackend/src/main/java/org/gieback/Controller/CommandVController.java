package org.gieback.Controller;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.gieback.DTO.CommandVDTO;
import org.gieback.Entity.Ventes;
import org.gieback.Entity.CommandV;
import org.gieback.Service.CommandVServices;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Path("/CommandV")
public class CommandVController {
    
    CommandVServices cs = new CommandVServices();

    @GET
    @Path("/Ventes/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ventes> getAllVentess(@PathParam("id") int id) {
        List<Ventes> Ventess = cs.getAllVentes(id);
        return Ventess;
    }



    @GET
    @Path("/CommandVByDate")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CommandVDTO> getByDate(@QueryParam("date") String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<CommandV> CommandVs = cs.getComByDate(localDate);
        List<CommandVDTO> CommandVDTOs = CommandVs.stream().map(this::toDTO).collect(Collectors.toList());
        return CommandVDTOs;
    }

    @PATCH
    @Path("/validerComm/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void validerComm(@PathParam("id") int id) {
        cs.validerComm(id);
    }

    @GET
    @Path("/CommandVByEtat/{e}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CommandVDTO> getByEtat(@PathParam("e") String e) {
        List<CommandV> CommandVs = cs.getByEtat(e);
        List<CommandVDTO> CommandVDTOs = CommandVs.stream().map(this::toDTO).collect(Collectors.toList());
        return CommandVDTOs;
    }

    @DELETE
    @Path("/deleteComm/{id}")
    public void deleteComm(@PathParam("id") int id) {
        cs.deleteComm(id);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addComm(CommandVDTO c) {
        cs.addComm(toEntity(c));
        return Response.status(Response.Status.CREATED).build();
    }

    public CommandV toEntity(CommandVDTO dto) {
        CommandV CommandV = new CommandV();
        CommandV.setId(dto.getId());
        CommandV.setVentes(dto.getVentes());
        CommandV.setPurchaseDate(LocalDate.parse(dto.getPurchaseDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        CommandV.setE(dto.getE());
        return CommandV;
    }

    public CommandVDTO toDTO(CommandV CommandV) {
        CommandVDTO dto = new CommandVDTO();
        dto.setId(CommandV.getId());
        dto.setVentes(CommandV.getVentes());
        dto.setPurchaseDate(CommandV.getPurchaseDate().toString());
        dto.setE(CommandV.getE());
        return dto;
    }

    @GET
    @Path("/CommandVs")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CommandVDTO> getAllCom() {
        List<CommandV> CommandVs = cs.getAllCom();
        List<CommandVDTO> CommandVDTOs = CommandVs.stream().map(this::toDTO).collect(Collectors.toList());
        return CommandVDTOs;
    }
}
