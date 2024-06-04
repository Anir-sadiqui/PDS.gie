package org.gieback.DTO;

import lombok.Data;
import org.gieback.Entity.AchatDetail;
import org.gieback.Entity.Contact;

@Data
public class AchatDTO {


    private Long id;


    private String purchaseDate;


    private Contact supplier;


    private AchatDetail details ;


    private CommandeDTO c;

}
