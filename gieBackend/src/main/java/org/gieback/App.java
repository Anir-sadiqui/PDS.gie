package org.gieback;
import com.sun.net.httpserver.HttpServer;
import jakarta.ws.rs.core.UriBuilder;


import org.gieback.Entity.Adresse;
import org.gieback.Entity.Contact;
import org.gieback.Entity.Entreprise;
import org.gieback.Entity.Personne;
import org.gieback.Service.EntrepriseService;
import org.gieback.Service.PersonneService;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

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


//        Adresse adresse1 = new Adresse();
//        adresse1.setQuartier("123 Rue de la Paix");
//        adresse1.setVille("Rabat");
//        adresse1.setNumero("N1");
//
//        Adresse adresse2 = new Adresse();
//        adresse2.setQuartier("456 Avenue des Champs-Élysées");
//        adresse2.setVille("Rabat");
//        adresse2.setNumero("N2");
//
//        Entreprise entreprise1 = new Entreprise("1234567890", "entreprise1@gmail.com",adresse1,"Entreprise 1","SARL");
//
//        Entreprise entreprise2 = new Entreprise("0987654321", "entreprise2@gmail.com",adresse2,"Entreprise 2","SA");
//
//        Personne personne1 = new Personne("0987654321", "ikrame@gmail.com",adresse1, "hami", "ikrame");
//
//        Personne personne2 = new Personne("0987654321", "anir@gmail.com", adresse1,"sadiqui", "anir");
//
//        EntrepriseService entrepriseService = new EntrepriseService();
//        entrepriseService.addEnterprise(entreprise1);
//        entrepriseService.addEnterprise(entreprise2);
//
//        PersonneService personneService = new PersonneService();
//        personneService.addPersonne(personne1);
//        personneService.addPersonne(personne2);
//
//
//        List<Entreprise> entreprises = entrepriseService.getAllEnterprises();
//        List<Personne> personnes = personneService.getAllPersonnes();



    }
}