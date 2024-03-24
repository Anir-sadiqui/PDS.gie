package org.gieback.Service;

import org.gieback.DAO.IEntrepriseDao;
import org.gieback.DAO.IPersonneDao;
import org.gieback.DAO.PersonneDao;
import org.gieback.Entity.Entreprise;
import org.gieback.Entity.Personne;

import java.util.List;

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
}
