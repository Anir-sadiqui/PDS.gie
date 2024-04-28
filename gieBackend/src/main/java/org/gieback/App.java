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
        System.out.println("Server launched successfully!");

        EntityManager entityManager = HibernateUtil.getEntityManger();

        try {
            // Start a transaction
            entityManager.getTransaction().begin();

            // Create some Addresses
            Adresse addr1 = new Adresse("000", "Oled  khadour", "OUtat El Haj");
            Adresse addr2 = new Adresse("111", "SIDI Ali", "Misour");

            // Create Contacts with ContactType
            Personne person1 = new Personne("1234567890", "person1@example.com", addr1, "John", "Doe", ContactType.CLIENT); // As a client
            Personne person2 = new Personne("0987654321", "person2@example.com", addr2, "Jane", "Doe", ContactType.CLIENT); // As a client

            Entreprise company1 = new Entreprise("1112223333", "company1@example.com", addr1, "LLC", "ABC Corp.", ContactType.FOURNISSEUR); // As a supplier
            Entreprise company2 = new Entreprise("4445556666", "company2@example.com", addr2, "Inc.", "XYZ Corp.", ContactType.FOURNISSEUR); // As a supplier

            // Create Contacts with ContactType
            Personne person3 = new Personne("07653122", "yesgo@example.com", addr1, "omar", "mahmod", ContactType.FOURNISSEUR); //
            Personne person4 = new Personne("098871656253", "mohamed6@example.com", addr2, "jihan", "abderahim", ContactType.FOURNISSEUR); // As a supplier

            Entreprise company5 = new Entreprise("+9999999999", "amazon@example.com", addr1, "LLKIC", "natio Corp.", ContactType.CLIENT); // As a client
            Entreprise company6 = new Entreprise("+88888888888", "google@example.com", addr2, "INTZ.", "mpooe Corp.", ContactType.CLIENT); //


            // Persist Addresses and Contacts
            entityManager.persist(addr1);
            entityManager.persist(addr2);
            entityManager.persist(person1);
            entityManager.persist(person2);
            entityManager.persist(company1);
            entityManager.persist(company2);

            entityManager.persist(person3);
            entityManager.persist(person4);
            entityManager.persist(company5);
            entityManager.persist(company6);

            // Commit the transaction
            entityManager.getTransaction().commit();

            System.out.println("Data created successfully!");
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback(); // Rollback in case of error
            }
            System.err.println("Error during persistence: " + e.getMessage());
        } finally {
            entityManager.close(); // Ensure the EntityManager is closed
        }
    }
}