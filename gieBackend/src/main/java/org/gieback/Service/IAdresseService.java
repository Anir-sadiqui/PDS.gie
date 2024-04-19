package org.gieback.Service;

import org.gieback.Entity.Adresse;
import org.gieback.Entity.Personne;

import java.util.List;
import java.util.Map;

public interface IAdresseService {
    List<Adresse> getAllAdresses();
    void addAdresse(Adresse a);
    Adresse getAdresseById(int id);
    List<Adresse> getByVille(String ville);
    List<Adresse> getByQuartier(String quartier , String ville);
    void modifierAdresse (int id , Map<String, String> attributs);
    List<Adresse> sortByVille (String ordre);
    void deleteById (int id);
}
