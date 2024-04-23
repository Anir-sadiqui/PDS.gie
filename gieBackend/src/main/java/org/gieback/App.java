package org.gieback;
import com.sun.net.httpserver.HttpServer;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.core.UriBuilder;


import org.gieback.Entity.*;

import org.gieback.HibernateUtility.HibernateUtil;
import org.gieback.Service.AdresseService;
import org.gieback.Service.EntrepriseService;
import org.gieback.Service.PersonneService;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import java.net.*;
import java.util.*;


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
//        adresse1.setQuartier("123 Avenue Allal ELfassi");
//        adresse1.setVille("Rabat");
//        adresse1.setNumero("N1");
//
        Adresse adresse2 = new Adresse();
        adresse2.setQuartier("456 Avenue Agdal");
        adresse2.setVille("Rabat");
        adresse2.setNumero("N2");
//
//
//        Adresse adresse3 = new Adresse();
//        adresse3.setQuartier("123 Rue de la Paix");
//        adresse3.setVille("Paris");
//        adresse3.setNumero("N1");
//
//
//        Adresse adresse4 = new Adresse();
//        adresse1.setQuartier("123 Avenue france");
//        adresse1.setVille("Paris");
//        adresse1.setNumero("N2");
//
//       Entreprise entreprise1 = new Entreprise("1234567890", "entreprise1@gmail.com",adresse1,"Entreprise 1","Apple");
//
//        Entreprise entreprise2 = new Entreprise("0987654321", "anirsadiqui2@gmail.com",adresse2,"Entreprise 2","SA");

//
//        entrepriseService.addEnterprise(entreprise2);
//
//        PersonneService personneService = new PersonneService();
//        personneService.addPersonne(personne1);
//        personneService.addPersonne(personne2);
//
//
//        List<Entreprise> entreprises = entrepriseService.getAllEnterprises();
//        List<Personne> personnes = personneService.getAllPersonnes();

//        AdresseService a = new AdresseService();
//        PersonneService p= new PersonneService();
//        EntrepriseService e = new EntrepriseService();
//        p.addPersonne(personne1);
//        p.addPersonne(personne2);
//        e.addEnterprise(entreprise1);

//       Map <String, String> attributs = new HashMap<>();
//        attributs.put("Nom","Said");
//        attributs.put("email","said@gmail.com");
//        p.modifier("103",attributs);
  //      p.modifier(2,attributs);3
//        a.addAdresse(adresse1);
   //     a.modifierAdresse(2,attributs);
//        System.out.println(a.sortByVille("desc"));
//        System.out.println(entreprise2);
//        System.out.println(entreprise2.getAdresse());
//        EntrepriseService e = new EntrepriseService();
//        System.out.println(e.getByEmail("anirsadiqui2@gmail.com"));
//        e.addEnterprise(entreprise2);


        EntityManager entityManager = HibernateUtil.getEntityManger();

        try {
            // Start a transaction
            entityManager.getTransaction().begin();

            // Create some Addresses
            Adresse addr1 = new Adresse("123", "Main St", "Cityville");
            Adresse addr2 = new Adresse("456", "Second St", "Townsville");

            // Create Contacts
            Personne person1 = new Personne("1234567890", "person1@example.com", addr1, "John", "Doe");
            Personne person2 = new Personne("0987654321", "person2@example.com", addr2, "Jane", "Doe");

            Entreprise company1 = new Entreprise("1112223333", "company1@example.com", addr1, "LLC", "ABC Corp.");
            Entreprise company2 = new Entreprise("4445556666", "company2@example.com", addr2, "Inc.", "XYZ Corp.");

            // Persist Addresses and Contacts
            entityManager.persist(addr1);
            entityManager.persist(addr2);
            entityManager.persist(person1);
            entityManager.persist(person2);
            entityManager.persist(company1);
            entityManager.persist(company2);

            // Create some Products
            Product product1 = new Product("Phone", "A smartphone", Product.Category.MOBILE_DEVICES);
            Product product2 = new Product("Laptop", "A lightweight laptop", Product.Category.COMPUTERS);

            entityManager.persist(product1);
            entityManager.persist(product2);

            // Create a Purchase (Achat) with a Supplier
            Achat achat1 = new Achat(new Date(), company1);
            AchatDetail detail1 = new AchatDetail();
            AchatDetail detail2 = new AchatDetail();

            // Persist the Achat to get a valid ID
            entityManager.persist(achat1);

            // Create AchatDetails with a correct reference to Achat
            //AchatDetail detail1 = new AchatDetail(null, achat1, product1, 10, 500.0);
            //AchatDetail detail2 = new AchatDetail(null, achat1, product2, 5, 1200.0);

            // Persist AchatDetails and associate them with Achat
            //achat1.setDetails(new ArrayList<>(List.of(detail1, detail2)));
            Set<AchatDetail> detailSet = new HashSet<>(List.of(detail1, detail2));


            entityManager.persist(detail1);
            entityManager.persist(detail2);

            // Create Inventory with the related entities
            Inventory inventory = new Inventory(new ArrayList<>(List.of(achat1)), new ArrayList<>(List.of(product1, product2)), new ArrayList<>(List.of(company1)));

            // Persist the Inventory
            entityManager.persist(inventory);

            // Commit the transaction
            entityManager.getTransaction().commit();

            System.out.println("Data created successfully!");
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback(); // Rollback transaction in case of error
            }
            System.err.println("Error during persistence: " + e.getMessage());
        } finally {
            entityManager.close(); // Ensure the EntityManager is closed
        }


  }
}