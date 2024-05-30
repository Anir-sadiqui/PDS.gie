package org.gieback.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;

    @Enumerated(EnumType.STRING)
    @Column(name = "contact_type")
    private ContactType contactType;

    public Contact() {
    }

    public Contact(String phone, String email, Adresse adresse, ContactType contactType) {
        this.phone = phone;
        this.email = email;
        this.adresse = adresse;
        this.contactType = contactType;
    }

    public Contact(String phone, String email, Adresse adresse) {
        this(phone, email, adresse, null);
    }

    @Override
    public String toString() {
        return "Phone: " + phone + ", Email: " + email + ", Type: " + contactType;
    }

}