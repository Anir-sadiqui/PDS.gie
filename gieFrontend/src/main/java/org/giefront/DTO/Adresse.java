package org.giefront.DTO;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Adresse implements Serializable {
    public Adresse(String numero, String quartier, String ville) {
        this.numero = numero;
        this.quartier = quartier;
        this.ville = ville;
    }


    private Long adresse_id;

    private String numero;

    private String quartier;

    private String ville;

    private List<Contact> contacts;


    public Adresse() {

    }

    @Override
    public String toString() {
        return "Id: " + adresse_id + '\n' +
                "Quartier: " + quartier + '\n' +
                "Ville: " + ville + '\n' ;
    }
}