package org.gieback.DAO;

import org.gieback.Entity.Contact;
import org.gieback.Entity.ContactType;
import org.gieback.Entity.Entreprise;
import org.gieback.Entity.Personne;

import java.util.List;
import java.util.Map;

public interface IEntrepriseDao {
    List<Entreprise> getAll();
    void add(Entreprise e);
    Entreprise getById(int id);
    void modifier(int id, Map<String, String> attributs);
    void deleteByid (int id);
    List<Entreprise> sortById (String ordre);
    List<Entreprise> sortByRs (String ordre);
    Entreprise getByRs(String raisonSocial);
    List<Entreprise> getByFj(String Fj);
    Entreprise getByEmail(String email);
    List<Entreprise> getByType(ContactType type);
    void addType (String id, ContactType type);
    void DeleteType (String id );
    Entreprise getTypeByRs(String raisonSocial , ContactType type);
    List<Entreprise> getTypeByFj(String Fj, ContactType type);




}
