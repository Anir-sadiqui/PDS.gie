package org.giefront.DTO;


import lombok.Data;

import java.time.LocalDate;

@Data
public class Achat {
    private Long id;


    private LocalDate purchaseDate;


    private String supplier;


    private AchatDetail details ;


    private Commande c;

    public Achat(){}


    public Achat( AchatDetail details, Commande c) {
        this.purchaseDate = LocalDate.now();
        this.details = details;
        this.c = c;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public AchatDetail getDetails() {
        return details;
    }

    public void setDetails(AchatDetail details) {
        this.details = details;
    }

    public Commande getC() {
        return c;
    }

    public void setC(Commande c) {
        this.c = c;
    }
}
