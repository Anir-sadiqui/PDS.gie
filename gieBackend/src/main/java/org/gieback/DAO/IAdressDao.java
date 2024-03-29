package org.gieback.DAO;

import org.gieback.Entity.Adresse;
import org.gieback.Entity.Entreprise;

import java.util.List;
import java.util.Map;

public interface IAdressDao {
    List<Adresse> getAll();

    void add(Adresse e);
    Adresse getById(int id);
    List<Adresse> getByVille(String ville);
    List<Adresse> getByQuartier(String quartier , String ville);
    void modifierAdresse (int id , Map<String, String> attributs);
    List<Adresse> sortByVille (String ordre);
    void deleteById (int id);


}
