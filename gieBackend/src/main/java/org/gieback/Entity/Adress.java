package org.gieback.Entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

@Entity (name="Adress")
public class Adress implements Serializable {

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;
        @Column (name="numero")
        private int numero;
        @Column (name="rue")
        private String rue;
        @Column (name="quartier")
        private String quartier;
        @Column(name="ville")
        private String ville;
    @ManyToMany(mappedBy = "adresses")
    private List<Contact> contacts;





    }
