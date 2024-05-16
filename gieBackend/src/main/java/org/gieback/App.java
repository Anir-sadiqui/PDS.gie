package org.gieback;
import com.sun.net.httpserver.HttpServer;
import jakarta.ws.rs.core.UriBuilder;
import org.gieback.Entity.*;
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



        // Assuming there are existing instances of Product and Contact
        Product product1 = new Product("Product 1", "Description 1", Category.ELECTRONICS, 10.0);
        Product product2 = new Product("Product 2", "Description 2", Category.ELECTRONICS, 20.0);
        Product product3 = new Product("Product 3", "Description 3", Category.CLOTHING, 30.0);
        Product product4 = new Product("Product 4", "Description 4", Category.CLOTHING, 40.0);
        Product product5 = new Product("Product 5", "Description 5", Category.FOOD, 50.0);

        AchatDetail detail1 = new AchatDetail(null, product1, 5);
        AchatDetail detail2 = new AchatDetail(null, product2, 10);
        AchatDetail detail3 = new AchatDetail(null, product3, 15);
        AchatDetail detail4 = new AchatDetail(null, product4, 20);
        AchatDetail detail5 = new AchatDetail(null, product5, 25);

        // These variables should be replaced with actual instances of Contact
        Contact supplier = new Contact();

        Achat achat1 = new Achat(supplier, detail1, null);
        Achat achat2 = new Achat(supplier, detail2, null);
        Achat achat3 = new Achat(supplier, detail3, null);
        Achat achat4 = new Achat(supplier, detail4, null);
        Achat achat5 = new Achat(supplier, detail5, null);


        achatService.addPurchase(achat1);
        achatService.addPurchase(achat2);
        achatService.addPurchase(achat3);
        achatService.addPurchase(achat4);
        achatService.addPurchase(achat5);








//      Adresse adresse1 = new Adresse();
//      adresse1.setQuartier("123 Avenue Allal ELfassi");
//      adresse1.setVille("Rabat");
//       adresse1.setNumero("N1");

//        Adresse adresse2 = new Adresse();
//       adresse2.setQuartier("456 Avenue Agdal");
//       adresse2.setVille("Rabat");
//       adresse2.setNumero("N2");
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
//       Entreprise entreprise1 = new Entreprise("1234567890", "entreprise1@gmail.com",adresse2,"Entreprise 1","Apple", ContactType.FOURNISSEUR);
//        Personne p1 = new Personne("1234567890", "entreprise1@gmail.com",adresse2,"Ayoub","mah", ContactType.FOURNISSEUR);
//        Entreprise entreprise2 = new Entreprise("0987654321", "anirsadiqui2@gmail.com",adresse2,"Entreprise 2","SA");
//
//
//        EntrepriseService entrepriseService = new EntrepriseService();
//        entrepriseService.addEnterprise(entreprise1);
//
//        PersonneService personneService = new PersonneService();
//        personneService.addPersonne(p1);
//        personneService.addPersonne(personne2);
//
//
//        List<Entreprise> entreprises = entrepriseService.getAllEnterprises();
//        List<Personne> personnes = personneService.getAllPersonnes();
//
//        AdresseService a = new AdresseService();
//        PersonneService p= new PersonneService();
//        EntrepriseService e = new EntrepriseService();
//        p.addPersonne(personne1);
//        p.addPersonne(personne2);
//        e.addEnterprise(entreprise1);
//
//       Map<String, String> attributs = new HashMap<>();
//        attributs.put("Nom","Said");
//       attributs.put("email","said@gmail.com");
//        p.modifier("103",attributs);
//        p.modifier("2",attributs);
//       a.addAdresse(adresse1);
//        a.modifierAdresse(2,attributs);
//        System.out.println(a.sortByVille("desc"));
//        System.out.println(entreprise2);
//        System.out.println(entreprise2.getAdresse());
//        EntrepriseService e = new EntrepriseService();
//        System.out.println(e.getByEmail("anirsadiqui2@gmail.com"));
//        e.addEnterprise(entreprise2);
//       PersonneService fs = new PersonneService();
//       fs.DeleteType(String.valueOf(52));




  }
}