package org.gieback.Service;
import org.gieback.DAO.AchatDao;
import org.gieback.DAO.IAchatDao;
import org.gieback.Entity.Achat;
import org.gieback.Entity.AchatDetail;
import org.gieback.Entity.Contact;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class AchatService implements IAchatService {
    IAchatDao achatDao = new AchatDao();

    @Override
    public List<Achat> getAllPurchases() {
        return achatDao.getAll();
    }

    @Override
    public void addPurchase(Achat achat) {
        achatDao.add(achat);
    }

    @Override
    public void deletePurchaseById(int id) {
        achatDao.deleteById(id);
    }

    @Override
    public void modifier(String id, Map<String, Integer> attributs) {
        achatDao.modifier(id, attributs);
    }

    @Override
    public List<Achat> searchPurchasesByDate(Date date) {
        return achatDao.chercherParDate(date);
    }

    @Override
    public List<Achat> searchPurchasesById(int id) {
        return achatDao.chercherParId(id);
    }

    @Override
    public List<Achat> searchPurchasesBySupplier(Contact f) {
        return achatDao.chercherParFournisseur(f);
    }
}