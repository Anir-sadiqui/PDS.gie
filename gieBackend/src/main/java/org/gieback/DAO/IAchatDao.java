package org.gieback.DAO;
import org.gieback.Entity.Achat;
import org.gieback.Entity.AchatDetail;
import org.gieback.Entity.Commande;
import org.gieback.Entity.Contact;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IAchatDao {
    List<Achat> getAll(Commande c);
    void add(Achat achat);
    void deleteById(int id);
    void modifier(String id, Map<String, String> attributs);
    List<Achat> chercherParFournisseur(int idf);
    List<Achat> getByCommande (int idC);

}