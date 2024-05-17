package org.giefront.DTO;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Data
public class Commande implements Serializable {

    private long id;
    private List<Achat> achats;
    private LocalDate purchaseDate;
    private EtatCommande e;

    public Commande(){}

    public Commande(List<Achat> achats) {
        this.achats = achats;
        this.purchaseDate = LocalDate.now();
        this.e=EtatCommande.Initialise;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public List<Achat> getAchats() {
        return achats;
    }

    public void setAchats(List<Achat> achats) {
        this.achats = achats;
    }

    public EtatCommande getE() {
        return e;
    }

    public void setE(EtatCommande e) {
        this.e = e;
    }
}
