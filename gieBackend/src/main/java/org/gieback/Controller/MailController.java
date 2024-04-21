package org.gieback.Controller;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.gieback.DTO.EmailDetails;
import org.gieback.Service.MailService;

@Path("/mail")
public class MailController {
    MailService ms = new MailService();


    @POST
    @Path("Notifier")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendEmail(EmailDetails details) {
        ms.sendSimpleEmail(details.getTo(), details.getSubject(), details.getMessage());
        return Response.ok().build();
    }
}
