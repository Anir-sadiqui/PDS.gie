package org.gieback.DAO;

import org.gieback.Entity.Contact;
import org.gieback.Entity.ContactType;
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
    void modifier(String id, Map<String, String> attributs);
    void deleteById (int id);
    Personne getByEmail(String email);
    List<Personne> getByType (ContactType type);
    void addType (String id, ContactType type);
    void DeleteType (String id );
    List<Personne> getTypeByNom(String n , ContactType type);
    List<Personne> getTypeByPrenom(String p , ContactType type);


 }

