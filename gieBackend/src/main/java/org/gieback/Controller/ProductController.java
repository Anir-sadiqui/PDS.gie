package org.gieback.Controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.gieback.Entity.Category;
import org.gieback.Entity.EtatStock;
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
    @Path("/isAvailable/{t}")
    @Produces (MediaType.APPLICATION_JSON)
    public List<Product> isAvailable(@PathParam("t") String t){
        return ps.isAvailable(t);
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
    public List<Product> getbyCat(@PathParam("c")String c){
        return ps.getbyCat(c);
    }

    /* add 08/05/2024 */
    @GET
    @Path("/getByName/{na}")
    @Produces (MediaType.APPLICATION_JSON)
    public List<Product> getByName(@PathParam("na")String na) {return ps.getByName(na);}


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
