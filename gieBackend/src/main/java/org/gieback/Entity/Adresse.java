package org.gieback.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Adresse implements Serializable {
    public Adresse(String numero, String quartier, String ville, List<Contact> contacts) {
        this.numero = numero;
        this.quartier = quartier;
        this.ville = ville;
        this.contacts = contacts;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adresse_id;

    @Column(name = "numero")

    private String numero;

    @Column(name = "quartier")
    private String quartier;
    @Column(name = "ville")
    private String ville;


    @JsonIgnore

    @OneToMany(mappedBy = "adresse")
    private List<Contact> contacts;


    public Adresse() {

    }
}
