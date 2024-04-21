package org.gieback.DAO;

import org.gieback.Entity.Compte;

public interface ICompteDao {
    void add(Compte c);
    Boolean verifyMdp (String mdp, String email);
    Compte getByEmail(String email);
}
