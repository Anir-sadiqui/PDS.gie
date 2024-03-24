package org.gieback.Service;

import org.gieback.DAO.EntrepriseDao;
import org.gieback.DAO.IEntrepriseDao;
import org.gieback.Entity.Entreprise;

import java.util.List;

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
}
