package org.gieback.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Compte implements Serializable {

    public Compte(String email, Long compte_id, String MDP, Contact c) {
        this.email = email;
        this.Compte_id = compte_id;
        this.contact=c;
        this.MDP=MDP;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Compte_id;

    @Column(name = "email")

    private String email;

    @Column(name = "MDP")

    private String MDP;

    @JsonIgnore
    @OneToOne(mappedBy = "compte")
    private Contact contact;

    public Compte() {

    }

}
