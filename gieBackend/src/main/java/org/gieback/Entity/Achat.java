package org.gieback.Entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Achat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Contact supplier;

    @OneToOne
    @JoinColumn(name = "Details_achats")
    private AchatDetail details ;

    @ManyToOne
    @JoinColumn(name = "Commande")
    private  Commande c;



    public Achat() {}

    public Achat( AchatDetail details, Commande c, Contact supplier ) {
        this.details=details;
        this.c=c;
        this.supplier=supplier;
    }

    @Override
    public String toString() {
        return "Id: " + id + '\n' +
                "Details : " + "\n" + details.toString();
    }


}

