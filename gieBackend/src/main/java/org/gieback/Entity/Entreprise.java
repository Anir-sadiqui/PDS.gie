package org.gieback.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import java.io.Serializable;
@Data

@Entity
@DiscriminatorValue("Entreprise")
public class Entreprise extends Contactt implements Serializable{

        @Column(name="raisonSociale")
        private String raisonSociale;
        @Column(name="formeJuridique")
        private String formeJuridique;
        @Id
        private Long id;


        public Entreprise( String email,String phone,String fax,String raisonSociale,String formeJuridique ) {
            super(email,phone,fax);
            this.raisonSociale=raisonSociale;
            this.formeJuridique=formeJuridique;

        }

        public Entreprise() {
            super();
        }





    }



