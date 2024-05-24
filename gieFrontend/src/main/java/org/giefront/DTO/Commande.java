package org.giefront.DTO;



import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class Commande {

    private Long id;


    private List<Achat> achats;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate purchaseDate;


    private EtatCommande e;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Achat> getAchats() {
        return achats;
    }

    public void setAchats(List<Achat> achats) {
        this.achats = achats;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public EtatCommande getE() {
        return e;
    }

    public void setE(EtatCommande e) {
        this.e = e;
    }

    @JsonCreator
    public Commande( List<Achat> achats) {
        this.achats = achats;
        this.purchaseDate=LocalDate.now();
        this.e=EtatCommande.Initialised;
    }
    public Commande(){}


}
