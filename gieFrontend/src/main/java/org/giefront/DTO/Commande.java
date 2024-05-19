package org.giefront.DTO;



import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class Commande {

    private Long id;


    private List<Achat> achats;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate purchaseDate;


    private EtatCommande e;


    @JsonCreator
    public Commande( List<Achat> achats) {
        this.achats = achats;
        this.purchaseDate=LocalDate.now();
        this.e=EtatCommande.Initialise;
    }
    public Commande(){}


}
