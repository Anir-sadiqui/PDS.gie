package org.gieback.Service;

import org.gieback.Entity.Entreprise;
import org.gieback.Entity.Personne;

import java.util.List;

public interface IPersonneService {
    List<Personne> getAllPersonnes();
    void addPersonne(Personne p);
    Personne getPersonneById(int id);
}
