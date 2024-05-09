package org.gieback.Service;

import org.gieback.Entity.Achat;
import org.gieback.Entity.Commande;
import org.gieback.Entity.EtatCommande;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ICommandeService {
    List<Achat> getAllachats(int id);
    List<Commande> getComByDate(LocalDate d);
    void validerComm(int id);
    List<Commande> getByEtat (EtatCommande e);
    void deleteComm(int id);
    void addComm(Commande c);
}
