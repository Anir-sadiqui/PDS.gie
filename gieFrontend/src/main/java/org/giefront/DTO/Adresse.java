package org.giefront.DTO;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data

public class Adresse implements Serializable {

    private Long adresse_id;

    private String numero;

    private String quartier;

    private String ville;


    public Adresse(String numero, String quartier, String ville) {
        this.numero = numero;
        this.quartier = quartier;
        this.ville = ville;
    }

    public Adresse() {

    }

    @Override
    public String toString() {
        return   " " + adresse_id;
    }
}
