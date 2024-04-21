package org.gieback.Service;

import org.gieback.Entity.Compte;

public interface ICompteService {

    void add(Compte c);
    Boolean verifyMdp (String mdp, String email);
    Compte getByEmail(String email);
}
