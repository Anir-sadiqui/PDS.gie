package org.gieback.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Achat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "purchase_date")
    private Date purchaseDate;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Contact supplier;

    @JsonIgnore
    @OneToMany(mappedBy = "purchase")
    private List<AchatDetail> details;

    public Achat() {}

    public Achat(Long id, Date purchaseDate) {
        this.id = id;
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return "Id: " + id + '\n' +
                "Purchase Date: " + purchaseDate + '\n';
    }
}

