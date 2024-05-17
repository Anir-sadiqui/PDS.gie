package org.gieback.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Achat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id")
    private Contact supplier;

    @OneToMany(mappedBy = "achat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AchatDetail> details;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate purchaseDate;


    // Constructors, getters, and setters...

    public Achat() {
        // Default constructor
    }

    public Achat(Contact supplier, List<AchatDetail> details, LocalDate purchaseDate) {
        this.supplier = supplier;
        this.details = details;
        this.purchaseDate = purchaseDate;
        if (details != null) {
            for (AchatDetail detail : details) {
                detail.setAchat(this);
            }
        }
    }

    // Getters and Setters...

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

    public List<AchatDetail> getDetails() {
        return details;
    }

    public void setDetails(List<AchatDetail> details) {
        this.details = details;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
