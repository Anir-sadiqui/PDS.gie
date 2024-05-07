package org.gieback.Controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.gieback.Entity.Entreprise;
import org.gieback.Entity.Product;
import org.gieback.Service.EntrepriseService;
import org.gieback.Service.IEntrepriseService;
import org.gieback.Service.IProductService;
import org.gieback.Service.ProductService;

import java.util.Map;

public class ProductController {

    IProductService productService =new ProductService();


    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Product p) {
        productService.add(p);
        return Response.status(Response.Status.CREATED).build();
    }

    @PATCH
    @Path("Modify/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modify(@PathParam("id") int id, Map<String, String> attributs) {
        productService.modify(id, attributs);
        return Response.noContent().build();
    }


}
