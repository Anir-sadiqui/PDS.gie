package org.gieback.DAO;
import org.gieback.Entity.Achat;
import org.gieback.Entity.Contact;

import java.util.Date;
import java.util.List;

public interface IAchatDao {
    List<Achat> getAll();
    void add(Achat achat);
    void deleteById(int id);
    void modifier(int id, Date newDate, Contact newFournisseur);
    List<Achat> chercherParDate(Date date);
    List<Achat> chercherParFournisseur(Contact f);
    List<Achat> chercherParId(int id);
}