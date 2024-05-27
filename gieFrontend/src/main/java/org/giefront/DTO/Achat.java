package org.giefront.DTO;


import lombok.Data;

import java.time.LocalDate;

@Data
public class Achat {
    private Long id;


    private LocalDate purchaseDate;


    private Contact supplier;


    private AchatDetail details ;


    private Commande c;

    public Achat(){}


    public Achat( AchatDetail details, Commande c, Contact supplier ) {
        this.purchaseDate = c.getPurchaseDate();
        this.details=details;
        this.c=c;
        this.supplier=supplier;
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


    public Contact getSupplier() {
        return supplier;
    }

    public void setSupplier(Contact supplier) {
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

    @Override
    public String toString() {
        return "Achat{" +
                "id=" + id +
                ", purchaseDate=" + purchaseDate +
                ", supplier=" + supplier +
                ", details=" + details +
                ", c=" + c +
                '}';
    }
}
