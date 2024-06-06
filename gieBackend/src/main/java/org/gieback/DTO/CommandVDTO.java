package org.gieback.DTO;


import lombok.Data;
import org.gieback.Entity.Achat;
import org.gieback.Entity.EtatCommande;
import org.gieback.Entity.EtatVentes;
import org.gieback.Entity.Ventes;

import java.util.List;

@Data
public class CommandVDTO {
    private Long id;
    private List<Ventes> ventes;

    private String purchaseDate;

    private EtatVentes e;

}
