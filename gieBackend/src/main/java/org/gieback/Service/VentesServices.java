package org.gieback.Service;

import org.gieback.DAO.VentesDao;
import org.gieback.Entity.CommandV;
import org.gieback.Entity.Ventes;

import java.util.List;
import java.util.Map;

public class VentesServices implements IVentesService {

    VentesDao vdao = new VentesDao();

    @Override
    public List<Ventes> getAll(CommandV c) {
        return vdao.getAll(c);
    }

    @Override
    public void add(Ventes Ventes) {
        vdao.add(Ventes);
    }

    @Override
    public void deleteById(int id) {
        vdao.deleteById(id);
    }

    @Override
    public void modifier(String id, Map<String, String> attributs) {
        vdao.modifier(id, attributs);
    }

    @Override
    public List<Ventes> chercherParFournisseur(int idf) {
        return vdao.chercherParFournisseur(idf);
    }

    @Override
    public List<Ventes> getByCommande(int idC) {
        return vdao.getByCommande(idC);
    }
}
