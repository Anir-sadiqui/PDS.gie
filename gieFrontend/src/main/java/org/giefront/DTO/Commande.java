package org.giefront.DTO;



import java.time.LocalDate;
import java.util.List;

public class Commande {

    private Long id;


    private List<Achat> achats;


    private LocalDate purchaseDate;


    private EtatCommande e;

    public Commande(Long id, List<Achat> achats, LocalDate purchaseDate, EtatCommande e) {
        this.id = id;
        this.achats = achats;
        this.purchaseDate = purchaseDate;
        this.e = e;
    }

    public Long getId() {
        return id;
    }

    public List<Achat> getAchats() {
        return achats;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public EtatCommande getE() {
        return e;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAchats(List<Achat> achats) {
        this.achats = achats;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setE(EtatCommande e) {
        this.e = e;
    }
}
