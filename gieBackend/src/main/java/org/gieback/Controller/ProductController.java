package org.gieback.Controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.gieback.Entity.Category;
import org.gieback.Entity.Product;
import org.gieback.Service.ProductService;

import java.util.List;
import java.util.Map;

@Path("/Product")
public class ProductController {
    ProductService ps = new ProductService();


    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Product p) {
        ps.add(p);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/isAvailable/{id}")
    @Produces (MediaType.APPLICATION_JSON)
    public boolean isAvailable(@PathParam("id") int id){
        return ps.isAvailable(id);
    }


    @PATCH
    @Path("Modify/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modify(@PathParam("id") int id, Map<String, String> attributs) {
        ps.modify(id, attributs);
        return Response.noContent().build();
    }


    @GET
    @Path("/getByCat/{c}")
    @Produces (MediaType.APPLICATION_JSON)
    public List<Product> getbyCat(@PathParam("c")Category c){
        return ps.getbyCat(c);
    }
    @GET
    @Path("/getAllprod")
    @Produces (MediaType.APPLICATION_JSON)
    public List<Product> getAllProd(){
        return ps.getAllProd();
    }
    @DELETE
    @Path("/deleteProd/{id}")
    public void deleteProduct(@PathParam("id")int id){
        ps.deleteProduct(id);
    }


}