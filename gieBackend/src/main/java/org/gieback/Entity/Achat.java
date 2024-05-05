package org.gieback.Entity;


import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Achat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "purchase_date")
    private Date purchaseDate;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Contact supplier;

    @OneToOne
    @JoinColumn(name = "Details_achats")
    private AchatDetail details ;



    public Achat() {}

    public Achat(Date purchaseDate, Contact supplier, AchatDetail details ) {
        this.purchaseDate = purchaseDate;
        this.supplier = supplier;
        this.details=details;
    }

    @Override
    public String toString() {
        return "Id: " + id + '\n' +
                "Purchase Date: " + purchaseDate + '\n';
    }
}

