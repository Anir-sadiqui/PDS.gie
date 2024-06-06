package org.gieback.Service;

import org.gieback.Entity.CommandV;
import org.gieback.Entity.Ventes;

import java.util.List;
import java.util.Map;

public interface IVentesService {
    List<Ventes> getAll(CommandV c);
    void add(Ventes Ventes);
    void deleteById(int id);
    void modifier(String id, Map<String, String> attributs);
    List<Ventes> chercherParFournisseur(int idf);
    List<Ventes> getByCommande (int idC);
}
