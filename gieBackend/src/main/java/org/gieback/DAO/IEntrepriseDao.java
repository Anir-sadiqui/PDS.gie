package org.gieback.DAO;

import org.gieback.Entity.Contact;
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


}
