package org.gieback.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Entity
@Data
public class Achat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "purchase_date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate purchaseDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id")
    private Contact supplier;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "achat", fetch = FetchType.LAZY)
    private List<AchatDetail> details ;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Commande")
    private  Commande c;



    public Achat() {}

    public Achat(Contact supplier, List<AchatDetail> details, Commande c ) {
        this.purchaseDate = LocalDate.now();
        this.supplier = supplier;
        this.details=details;
        this.c=c;
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

