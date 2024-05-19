package org.gieback;
import com.sun.net.httpserver.HttpServer;
import jakarta.ws.rs.core.UriBuilder;
import org.gieback.Entity.*;
import org.gieback.Service.*;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import java.net.*;
import java.util.ArrayList;
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
//        Product p = new Product("Iphone 11","noir 256go ",Category.CATEGORY1,18900.00);
//        ProductService ps = new ProductService();
////        ps.add(p);
//        String c = Category.CATEGORY1.name();
//        System.out.println( ps.isAvailable(EtatStock.Epuise.name()));
//        List<Achat> a = new ArrayList<>();
//        Commande c = new Commande(a);
//        CommandeService cs = new CommandeService();
//        System.out.println(cs.getAllCom());
//        cs.addComm(c);
//        cs.deleteComm(5);
//        Achat a = new Achat();
//        AchatService as = new AchatService();
//        as.addPurchase(a);




  }
}