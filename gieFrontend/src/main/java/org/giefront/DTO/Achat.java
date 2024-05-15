package org.giefront.DTO;

import java.io.Serializable;
import java.time.LocalDate;

public class Achat implements Serializable {

    private long id;
    private LocalDate purchaseDate;
    private Contact supplier;



}
