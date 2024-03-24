package org.gieback.DAO;

import org.gieback.Entity.Adresse;
import org.gieback.Entity.Entreprise;

import java.util.List;

public interface IAdressDao {
    List<Adresse> getAll();

    void add(Adresse e);
    Adresse getById(int id);
}
