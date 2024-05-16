package org.gieback.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Achat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Contact supplier;

    @OneToMany(mappedBy = "achat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AchatDetail> details;

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
