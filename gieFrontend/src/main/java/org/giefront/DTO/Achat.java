package org.giefront.DTO;


import lombok.Data;

import java.time.LocalDate;

@Data
public class Achat {
    private Long id;


    private LocalDate purchaseDate;


    private String supplier;


    private AchatDetail details ;


    private Commande c;

    public Achat(){}


    public Achat( AchatDetail details, Commande c) {
        this.purchaseDate = LocalDate.now();
        this.details = details;
        this.c = c;
    }



}
