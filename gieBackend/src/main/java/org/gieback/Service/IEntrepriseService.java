package org.gieback.Service;

import org.gieback.Entity.Entreprise;

import java.util.List;

public interface IEntrepriseService {
    List<Entreprise> getAllEnterprises();
    void addEnterprise(Entreprise entreprise);
    Entreprise getEnterpriseById(int id);
}
