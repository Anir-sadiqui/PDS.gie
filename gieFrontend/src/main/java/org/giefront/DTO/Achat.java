package org.giefront.DTO;


import java.time.LocalDate;

public class Achat {
    private Long id;


    private LocalDate purchaseDate;


    private Contact supplier;


    private AchatDetail details ;


    private Commande c;


    public Achat(Long id, LocalDate purchaseDate, Contact supplier, AchatDetail details, Commande c) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.supplier = supplier;
        this.details = details;
        this.c = c;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public Contact getSupplier() {
        return supplier;
    }

    public AchatDetail getDetails() {
        return details;
    }

    public Commande getC() {
        return c;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setSupplier(Contact supplier) {
        this.supplier = supplier;
    }

    public void setDetails(AchatDetail details) {
        this.details = details;
    }

    public void setC(Commande c) {
        this.c = c;
    }
}
