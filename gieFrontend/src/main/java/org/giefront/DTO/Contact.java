package org.giefront.DTO;

import lombok.Data;

import java.io.Serializable;


@Data

public class Contact implements Serializable {
    private Long id;

    private String phone;

    private String email;

    private Adresse adresse;

    public Contact(String phone, String email, Adresse adresse) {
        this.phone = phone;
        this.email = email;
        this.adresse = adresse;
    }
    public Contact(){

    }


}
