package org.gieback.Service;
import org.gieback.DAO.AchatDao;
import org.gieback.DAO.IAchatDao;
import org.gieback.Entity.Achat;
import org.gieback.Entity.AchatDetail;
import org.gieback.Entity.Commande;
import org.gieback.Entity.Contact;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AchatService implements IAchatService {
    IAchatDao achatDao = new AchatDao();


    @Override
    public List<Achat> getAll(Commande c) {
        return achatDao.getAll(c);
    }

    @Override
    public void add(Achat achat) {
        achatDao.add(achat);
    }

    @Override
    public void deleteById(int id) {
        achatDao.deleteById(id);
    }

    @Override
    public void modifier(String id, Map<String, String> attributs) {
        achatDao.modifier(id,attributs);
    }

    @Override
    public List<Achat> chercherParDate(LocalDate date) {
        return achatDao.chercherParDate(date);
    }

    @Override
    public List<Achat> chercherParFournisseur(int idf) {
        return achatDao.chercherParFournisseur(idf);
    }

    @Override
    public List<Achat> getByCommande(int idC) {
        return achatDao.getByCommande(idC);
    }
}