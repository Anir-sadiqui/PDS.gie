package org.gieback.Service;

import org.gieback.Entity.Entreprise;
import org.gieback.Entity.Personne;

import java.util.List;
import java.util.Map;

public interface IPersonneService {
    List<Personne> getAllPersonnes();
    void addPersonne(Personne p);
    Personne getPersonneById(int id);
    List<Personne> getByNom(String n);
    List<Personne> getByPrenom(String p);
    List<Personne> sortByNom(String ordre);
    List<Personne> sortById (String ordre);
    void modifier(String  id, Map<String, String> attributs);
    void deleteById (int id);
    Personne getByEmail(String email);
}
