package org.gieback.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import org.gieback.Entity.Achat;
import org.gieback.Entity.Commande;
import org.gieback.Entity.EtatCommande;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Data
public class CommandeDTO {


        private Long id;


        private List<Achat> achats;


        private String purchaseDate;

        private EtatCommande e;




}


