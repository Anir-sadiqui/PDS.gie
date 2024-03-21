package org.gieback.Entity;

import lombok.Data;

import java.io.Serializable;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Data
@Entity
@DiscriminatorValue("Personne")

public class Personne extends Contactt implements Serializable {


        @Column (name ="nom")
        String nom;
        @Column(name="prenom")
        String prenom;

        public Personne() {
            super();
        }
    }





