package org.gieback;

import com.sun.net.httpserver.HttpServer;
import jakarta.ws.rs.core.UriBuilder;
import org.gieback.DAO.ContactDao;
import org.gieback.DAO.IContactDao;
import org.gieback.Service.ContactService;
import org.gieback.Service.IContactService;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;


public class App extends ResourceConfig {
    public App() {
        packages("org.gieback");
    }

    public static void main(String[] args) {

        URI baseUri = UriBuilder.fromUri("http://localhost/").port(5000).build();
        ResourceConfig config = new ResourceConfig().packages("org.gieback");
        config.register(JacksonJsonProvider.class);
        HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);
        System.out.println("server launched Successfully ");


        //   List<Contact> listClients = new ArrayList<>();
        IContactService dao = new ContactService();



      System.out.println(dao);
}


    }



