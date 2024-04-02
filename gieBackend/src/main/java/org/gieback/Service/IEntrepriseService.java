package org.gieback.Service;

import org.gieback.Entity.Entreprise;

import java.util.List;
import java.util.Map;

public interface IEntrepriseService {
    List<Entreprise> getAllEnterprises();
    void addEnterprise(Entreprise entreprise);
    Entreprise getEnterpriseById(int id);
    void modifier(int id, Map<String, String> attributs);
    void deleteByid (int id);
    List<Entreprise> sortById (String ordre);
    List<Entreprise> sortByRs (String ordre);

    Entreprise getEnterpriseByRs(String rs);
}
