package org.gieback;

import com.sun.net.httpserver.HttpServer;
import jakarta.ws.rs.core.UriBuilder;
import org.gieback.Entity.*;
import org.gieback.Service.AchatService;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;
import java.util.Arrays;

public class App extends ResourceConfig {

    public App() {
        packages("org.gieback");
    }

    public static void main(String[] args) {

        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        ResourceConfig config = new ResourceConfig().packages("org.gieback");
        config.register(JacksonJsonProvider.class);
        HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);
        System.out.println("server launched Successfully ");

        // Create products
        Product product1 = new Product("Product 1", "Description 1", Category.ELECTRONICS, 10.0);
        Product product2 = new Product("Product 2", "Description 2", Category.ELECTRONICS, 20.0);
        Product product3 = new Product("Product 3", "Description 3", Category.CLOTHING, 30.0);
        Product product4 = new Product("Product 4", "Description 4", Category.CLOTHING, 40.0);
        Product product5 = new Product("Product 5", "Description 5", Category.FOOD, 50.0);

        // Create achat details
        AchatDetail detail1 = new AchatDetail(null, product1, 5);
        AchatDetail detail2 = new AchatDetail(null, product2, 10);
        AchatDetail detail3 = new AchatDetail(null, product3, 15);
        AchatDetail detail4 = new AchatDetail(null, product4, 20);
        AchatDetail detail5 = new AchatDetail(null, product5, 25);

        // Create a supplier
        Contact supplier = new Contact();

        // Create achats
        Achat achat1 = new Achat(supplier, Arrays.asList(detail1), null);
        Achat achat2 = new Achat(supplier, Arrays.asList(detail2), null);
        Achat achat3 = new Achat(supplier, Arrays.asList(detail3), null);
        Achat achat4 = new Achat(supplier, Arrays.asList(detail4), null);
        Achat achat5 = new Achat(supplier, Arrays.asList(detail5), null);

        // Save achats
        AchatService achatService = new AchatService();
        achatService.addPurchase(achat1);
        achatService.addPurchase(achat2);
        achatService.addPurchase(achat3);
        achatService.addPurchase(achat4);
        achatService.addPurchase(achat5);
    }
}
