package org.giefront.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FournisseurDTO {

    private Long id;
    private String contactType;  // "personne" or "entreprise"
    private String phone;
    private String email;
    private String addressId;

    // Fields specific to "Personne"
    private String nom;     // if applicable
    private String prenom;  // if applicable

    // Fields specific to "Entreprise"
    private String formeJuridique;  // if applicable
    private String raisonSocial;   // if applicable

}
