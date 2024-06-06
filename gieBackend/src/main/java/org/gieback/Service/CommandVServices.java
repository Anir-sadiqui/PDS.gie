package org.gieback.Service;

import org.gieback.DAO.CommandVDao;
import org.gieback.Entity.CommandV;
import org.gieback.Entity.Ventes;

import java.time.LocalDate;
import java.util.List;

public class CommandVServices implements ICommandVServices {
    CommandVDao cdao = new CommandVDao();
    @Override
    public List<Ventes> getAllVentes(int id) {
        return cdao.getAllVentes(id);
    }

    @Override
    public List<CommandV> getComByDate(LocalDate d) {
        return cdao.getComByDate(d);
    }

    @Override
    public void validerComm(int id) {
        cdao.validerComm(id);
    }

    @Override
    public List<CommandV> getByEtat(String e) {
        return cdao.getByEtat(e);
    }

    @Override
    public void deleteComm(int id) {
        cdao.deleteComm(id);
    }

    @Override
    public void addComm(CommandV c) {
        cdao.addComm(c);
    }

    @Override
    public List<CommandV> getAllCom() {
        return cdao.getAllCom();
    }

    @Override
    public void pendComm(int id) {
        cdao.pendComm(id);
    }
}
