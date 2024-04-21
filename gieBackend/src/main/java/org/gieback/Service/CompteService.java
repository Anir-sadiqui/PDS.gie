package org.gieback.Service;

import org.gieback.DAO.CompteDao;
import org.gieback.DAO.ICompteDao;
import org.gieback.Entity.Compte;

public class CompteService implements ICompteService {
    ICompteDao cd = new CompteDao();


    @Override
    public void add(Compte c) {
        cd.add(c);

    }

    @Override
    public Boolean verifyMdp(String mdp, String email) {
        return cd.verifyMdp(mdp,email);
    }

    @Override
    public Compte getByEmail(String email) {
        return cd.getByEmail(email);
    }
}
