package org.gieback.Service;

import org.gieback.DAO.EntrepriseDao;
import org.gieback.DAO.IEntrepriseDao;
import org.gieback.Entity.Entreprise;

import java.util.List;
import java.util.Map;

public class EntrepriseService implements IEntrepriseService{
     IEntrepriseDao entrepriseDao=new EntrepriseDao();



    @Override
    public List<Entreprise> getAllEnterprises() {
        return entrepriseDao.getAll();
    }

  @Override
    public void addEnterprise(Entreprise entreprise) {
        entrepriseDao.add(entreprise);
    }

    @Override
    public Entreprise getEnterpriseById(int id) {
        return entrepriseDao.getById(id);
    }

    @Override
    public void modifier(int id, Map<String, String> attributs) {
        entrepriseDao.modifier(id, attributs);

    }

    @Override
    public void deleteByid(int id) {
        entrepriseDao.deleteByid(id);

    }

    @Override
    public List<Entreprise> sortById(String ordre) {
        return entrepriseDao.sortById(ordre);
    }
}
