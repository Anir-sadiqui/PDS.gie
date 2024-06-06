package org.giefront.DTO;

import java.time.LocalDate;
import java.util.List;

public class CommandV {
    private Long id;
    private List<Ventes> ventes;

    private String purchaseDate;

    private EtatVentes e;

    public CommandV(List<Ventes> ventes) {
        this.ventes = ventes;
        this.purchaseDate=String.valueOf(LocalDate.now());
        this.e=EtatVentes.In_Preparation;
    }

    public CommandV() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Ventes> getVentes() {
        return ventes;
    }

    public void setVentes(List<Ventes> ventes) {
        this.ventes = ventes;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public EtatVentes getE() {
        return e;
    }

    public void setE(EtatVentes e) {
        this.e = e;
    }
}
