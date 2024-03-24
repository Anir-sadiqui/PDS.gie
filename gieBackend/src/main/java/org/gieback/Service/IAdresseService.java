package org.gieback.Service;

import org.gieback.Entity.Adresse;
import org.gieback.Entity.Personne;

import java.util.List;

public interface IAdresseService {
    List<Adresse> getAllAdresses();
    void addAdresse(Adresse a);
    Adresse getAdresseById(int id);
}
