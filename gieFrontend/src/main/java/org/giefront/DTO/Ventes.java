package org.giefront.DTO;

import lombok.Data;

@Data
public class Ventes {
    private Long id;


    private Contact supplier;


    private AchatDetail details;


    private CommandV c;

    public Ventes(Contact supplier, AchatDetail details, CommandV c) {
        this.supplier = supplier;
        this.details = details;
        this.c = c;
    }

    public Ventes() {

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

    public CommandV getC() {
        return c;
    }

    public void setC(CommandV c) {
        this.c = c;
    }
}
