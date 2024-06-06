package org.gieback.DAO;

import org.gieback.Entity.CommandV;
import org.gieback.Entity.Ventes;
import org.gieback.Entity.Commande;

import java.util.List;
import java.util.Map;

public interface IVentesDao {
    List<Ventes> getAll(CommandV c);
    void add(Ventes Ventes);
    void deleteById(int id);
    void modifier(String id, Map<String, String> attributs);
    List<Ventes> chercherParFournisseur(int idf);
    List<Ventes> getByCommande (int idC);
}
