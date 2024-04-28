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

    public Long getAdresse_id() {
        return adresse_id;
    }

    public void setAdresse_id(Long adresse_id) {
        this.adresse_id = adresse_id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}

