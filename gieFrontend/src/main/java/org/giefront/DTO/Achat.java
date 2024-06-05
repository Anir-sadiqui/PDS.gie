package org.giefront.DTO;


import lombok.Data;

import java.time.LocalDate;

@Data
public class Achat {
    private Long id;


    private Contact supplier;


    private AchatDetail details ;


    private Commande c;

    public Achat(){}


    public Achat( AchatDetail details, Commande c, Contact supplier ) {
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
                ", supplier=" + supplier +
                ", details=" + details +
                ", c=" + c +
                '}';
    }
}
