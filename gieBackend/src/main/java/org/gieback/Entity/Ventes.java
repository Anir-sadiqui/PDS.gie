package org.gieback.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;



@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Ventes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Contact supplier;

    @OneToOne
    @JoinColumn(name = "Details_achats")
    private AchatDetail details;

    @ManyToOne
    @JoinColumn(name = "CommandV")
    private CommandV c;


    public Ventes() {
    }

    public Ventes(AchatDetail details, CommandV c, Contact supplier) {
        this.details = details;
        this.c = c;
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Id: " + id + '\n' +
                "Details : " + "\n" + details.toString();
    }
}
