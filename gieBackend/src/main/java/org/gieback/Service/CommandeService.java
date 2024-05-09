package org.gieback.Service;

import org.gieback.DAO.CommandeDao;
import org.gieback.Entity.Achat;
import org.gieback.Entity.Commande;
import org.gieback.Entity.EtatCommande;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class CommandeService implements ICommandeService{
    CommandeDao cdao =new CommandeDao();

    @Override
    public List<Achat> getAllachats(int id) {
        return cdao.getAllachats(id);
    }

    @Override
    public List<Commande> getComByDate(LocalDate d) {
        return cdao.getComByDate(d);
    }

    @Override
    public void validerComm(int id) {
        cdao.validerComm(id);

    }

    @Override
    public List<Commande> getByEtat(EtatCommande e) {
        return cdao.getByEtat(e);
    }

    @Override
    public void deleteComm(int id) {
        cdao.deleteComm(id);
    }

    @Override
    public void addComm(Commande c) {
        cdao.addComm(c);
    }
}
