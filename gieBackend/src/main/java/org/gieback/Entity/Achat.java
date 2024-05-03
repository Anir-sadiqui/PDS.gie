package org.gieback.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
//import javax.persistence.*;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    public Achat(Date purchaseDate, Contact supplier) {
        this.purchaseDate = purchaseDate;
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Id: " + id + '\n' +
                "Purchase Date: " + purchaseDate + '\n';
    }
}

