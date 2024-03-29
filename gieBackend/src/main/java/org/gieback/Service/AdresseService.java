package org.gieback.Service;

import org.gieback.DAO.AdressDao;
import org.gieback.DAO.EntrepriseDao;
import org.gieback.DAO.IAdressDao;
import org.gieback.DAO.IEntrepriseDao;
import org.gieback.Entity.Adresse;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<Adresse> getByVille(String ville) {
        return adresseDao.getByVille(ville);
    }

    @Override
    public List<Adresse> getByQuartier(String quartier, String ville) {
        return adresseDao.getByQuartier(quartier,ville);
    }

    @Override
    public void modifierAdresse(int id, Map<String, String> attributs) {
        adresseDao.modifierAdresse(id,attributs);

    }

    @Override
    public List<Adresse> sortByVille(String ordre) {
        return adresseDao.sortByVille(ordre);
    }

    @Override
    public void deleteById(int id) {
        adresseDao.deleteById(id);

    }
}
