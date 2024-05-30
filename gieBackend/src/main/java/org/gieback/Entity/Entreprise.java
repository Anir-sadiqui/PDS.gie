package org.gieback.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@JsonTypeName("entreprise")
public class Entreprise extends Contact  implements Serializable {
    @Column(name = "formeJuridique")

    private String formeJuridique;
    @Column(name = "raisonSocial")

    private String raisonSocial;

//    public Entreprise(String phone, String email, Adresse adresse,String formeJuridique, String raisonSocial , ContactType contactType) {
//        super(phone, email,adresse , contactType );
//        this.formeJuridique = formeJuridique;
//        this.raisonSocial = raisonSocial;
//    }
    public Entreprise(String phone, String email, Adresse adresse,String formeJuridique, String raisonSocial ){
        super(phone, email,adresse );
        this.formeJuridique = formeJuridique;
        this.raisonSocial = raisonSocial;
    }

    public Entreprise() {

    }
    @JsonCreator
    public Entreprise(
            @JsonProperty("phone") String phone,
            @JsonProperty("email") String email,
            @JsonProperty("adresse") Adresse adresse,
            @JsonProperty("formeJuridique") String formeJuridique,
            @JsonProperty("raisonSocial") String raisonSocial,
            @JsonProperty("contactType") ContactType contactType
    ) {
        super(phone, email, adresse, contactType);
        this.formeJuridique = formeJuridique;
        this.raisonSocial = raisonSocial;
    }

    @Override
    public String toString() {
        return "Raison Social: " + raisonSocial + ", Type: " + getContactType() + "\n" +
                "Phone: " + getPhone() + "\n" +
                "Email: " + getEmail() + "\n" +
                "Forme Juridique: " + formeJuridique;
    }

}