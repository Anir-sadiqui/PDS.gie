package org.gieback;
import com.sun.net.httpserver.HttpServer;
import jakarta.ws.rs.core.UriBuilder;
import org.gieback.Entity.Contact;
import org.gieback.Service.ContactService;
import org.gieback.Service.IContactService;

import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import java.net.*;

public class App extends ResourceConfig {
    public App() {
        packages("ma.uiass.eia.pds");
    }

    public static void main(String[] args) {

        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        ResourceConfig config = new ResourceConfig().packages("ma.uiass.eia.pds");
        config.register(JacksonJsonProvider.class);
        HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);
        System.out.println("server launched Successfully ");

        IContactService dao = new ContactService();

        Contact c1= new Contact("ikram","kkkk","1233","ikram@",78);
        dao.ajouter(c1);
         System.out.println(dao.getAll());
    }
}