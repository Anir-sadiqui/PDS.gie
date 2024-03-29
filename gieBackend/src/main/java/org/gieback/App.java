package org.gieback;
import com.sun.net.httpserver.HttpServer;
import jakarta.ws.rs.core.UriBuilder;


import org.gieback.Entity.Adresse;

import org.gieback.Entity.Entreprise;
import org.gieback.Entity.Personne;
import org.gieback.Service.AdresseService;
import org.gieback.Service.EntrepriseService;
import org.gieback.Service.PersonneService;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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


        Adresse adresse1 = new Adresse();
        adresse1.setQuartier("123 Avenue Allal ELfassi");
        adresse1.setVille("Rabat");
        adresse1.setNumero("N1");

        Adresse adresse2 = new Adresse();
        adresse2.setQuartier("456 Avenue Agdal");
        adresse2.setVille("Rabat");
        adresse2.setNumero("N2");


        Adresse adresse3 = new Adresse();
        adresse3.setQuartier("123 Rue de la Paix");
        adresse3.setVille("Paris");
        adresse3.setNumero("N1");


        Adresse adresse4 = new Adresse();
        adresse1.setQuartier("123 Avenue france");
        adresse1.setVille("Paris");
        adresse1.setNumero("N2");

        Entreprise entreprise1 = new Entreprise("1234567890", "entreprise1@gmail.com",adresse1,"Entreprise 1","SARL");

        Entreprise entreprise2 = new Entreprise("0987654321", "entreprise2@gmail.com",adresse2,"Entreprise 2","SA");

        Personne personne1 = new Personne("0987654321", "ikrame@gmail.com",adresse3, "elhami", "ikrame");

        Personne personne2 = new Personne("0987654321", "anir@gmail.com", adresse4,"sadiqui", "anir");
//
//       EntrepriseService entrepriseService = new EntrepriseService();
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

        AdresseService a = new AdresseService();
        PersonneService p= new PersonneService();
        EntrepriseService e = new EntrepriseService();
//        p.addPersonne(personne1);
//        p.addPersonne(personne2);
//        e.addEnterprise(entreprise1);
//        e.addEnterprise(entreprise2);
       Map <String, String> attributs = new HashMap<>();
        //attributs.put("ville","Tanger");
        attributs.put("email","said@gmail.com");
        p.modifier(2,attributs);
       // a.addAdresse(adresse1);
        //a.modifierAdresse(1,attributs);





  }
}