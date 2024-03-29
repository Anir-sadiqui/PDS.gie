package org.gieback.DAO;

import org.gieback.Entity.Contact;
import org.gieback.Entity.Personne;

import java.util.List;
import java.util.Map;

public interface IPersonneDao {
     List<Personne> getAll();
     void add(Personne p);
     Personne getById(int id);
     List<Personne> getByNom(String n);
     List<Personne> getByPrenom(String p);
     List<Personne> sortByNom(String ordre);
     List<Personne> sortById (String ordre);
    void modifier(int id, Map<String, String> attributs);
    void deleteById (int id);


 }

