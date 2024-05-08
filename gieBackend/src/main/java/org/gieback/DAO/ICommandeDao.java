package org.gieback.DAO;

import org.gieback.Entity.Achat;
import org.gieback.Entity.Commande;
import org.gieback.Entity.EtatCommande;

import java.util.Date;
import java.util.List;

public interface ICommandeDao {
    List<Achat> getAllachats(int id);
    List<Commande> getComByDate(Date d);
    void validerComm(int id);
    List<Commande> getByEtat (EtatCommande e);
   void deleteComm(int id);
   void addComm(Commande c);

}