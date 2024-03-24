package org.gieback.DAO;

import org.gieback.Entity.Entreprise;
import org.gieback.Entity.Personne;

import java.util.List;

public interface IEntrepriseDao {

    List<Entreprise> getAll();

    void add(Entreprise e);
    Entreprise getById(int id);
}
