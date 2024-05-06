package org.gieback.Service;
import org.gieback.Entity.Achat;
import org.gieback.Entity.Contact;
import java.util.Date;
import java.util.List;

public interface IAchatService {
    List<Achat> getAllPurchases();
    void addPurchase(Achat achat);
    void deletePurchaseById(int id);
    void modifyPurchase(int id, Date newDate, Contact newFournisseur);
    List<Achat> searchPurchasesByDate(Date date);
    List<Achat> searchPurchasesById(int id);
    List<Achat> searchPurchasesBySupplier(String fournisseurNom);
}