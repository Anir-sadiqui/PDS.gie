package org.gieback.Service;

import org.gieback.Entity.CommandV;
import org.gieback.Entity.Ventes;

import java.time.LocalDate;
import java.util.List;

public interface ICommandVServices {
    List<Ventes> getAllVentes(int id);
    List<CommandV> getComByDate(LocalDate d);
    void validerComm(int id);
    List<CommandV> getByEtat (String e);
    void deleteComm(int id);
    void addComm(CommandV c);
    List<CommandV> getAllCom();
    void pendComm(int id);
}
