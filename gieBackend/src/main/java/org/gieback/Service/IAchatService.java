package org.gieback.Service;
import org.gieback.Entity.Achat;
import org.gieback.Entity.AchatDetail;
import org.gieback.Entity.Contact;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IAchatService {
    List<Achat> getAllPurchases();
    void addPurchase(Achat achat);
    void deletePurchaseById(int id);
    void modifier(String id, Map<String, Integer> attributs);
    List<Achat> searchPurchasesByDate(Date date);
    List<Achat> searchPurchasesById(int id);
    List<Achat> searchPurchasesBySupplier(Contact f);
}