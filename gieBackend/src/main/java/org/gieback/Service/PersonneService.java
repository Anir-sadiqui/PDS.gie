package org.gieback.Service;

import org.gieback.DAO.IEntrepriseDao;
import org.gieback.DAO.IPersonneDao;
import org.gieback.DAO.PersonneDao;
import org.gieback.Entity.Entreprise;
import org.gieback.Entity.Personne;

import java.util.List;
import java.util.Map;

public class PersonneService implements IPersonneService{
    IPersonneDao personneeDao=new PersonneDao();



    @Override
    public List<Personne> getAllPersonnes() {
        return personneeDao.getAll();
    }

    @Override
    public void addPersonne(Personne p) {
        personneeDao.add(p);
    }

    @Override
    public Personne getPersonneById(int id) {
        return personneeDao.getById(id);
    }

    @Override
    public List<Personne> getByNom(String n) {
        return personneeDao.getByNom(n);
    }

    @Override
    public List<Personne> getByPrenom(String p) {
        return personneeDao.getByPrenom(p);
    }

    @Override
    public List<Personne> sortByNom(String ordre) {
        return personneeDao.sortByNom(ordre);
    }

    @Override
    public List<Personne> sortById(String ordre) {
        return personneeDao.sortById(ordre);
    }

    @Override
    public void modifier(String id, Map<String, String> attributs) {
        personneeDao.modifier(id,attributs);

    }

    @Override
    public void deleteById(int id) {
        personneeDao.deleteById(id);

    }
}
