package org.giefront.DTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.List;

public class Achat {
    private Long id;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate purchaseDate;

    private Contact supplier;


    private List<AchatDetail> details ;


    private Commande c;


    public Achat(Long id, LocalDate purchaseDate, Contact supplier, List<AchatDetail> details, Commande c) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.supplier = supplier;
        this.details = details;
        this.c = c;
    }

    public Achat(Contact supplier, List<AchatDetail> details, LocalDate purchaseDate) {
        this.supplier = supplier;
        this.details = details;
        this.purchaseDate = purchaseDate;
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

    public List<AchatDetail> getDetails() {
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

    public void setDetails(List<AchatDetail> details) {
        this.details = details;
    }

    public void setC(Commande c) {
        this.c = c;
    }
    public Achat(){

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
