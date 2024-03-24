package org.gieback.Service;

import org.gieback.DAO.AdressDao;
import org.gieback.DAO.EntrepriseDao;
import org.gieback.DAO.IAdressDao;
import org.gieback.DAO.IEntrepriseDao;
import org.gieback.Entity.Adresse;

import java.util.List;

public class AdresseService implements IAdresseService{
    IAdressDao adresseDao=new AdressDao();
    @Override
    public List<Adresse> getAllAdresses() {
        return adresseDao.getAll();
    }
    @Override
    public void addAdresse(Adresse a) {
        adresseDao.add(a);

    }
    @Override
    public Adresse getAdresseById(int id) {
        return adresseDao.getById(id);
    }
}
