package org.gieback.Entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Contact implements Serializable{
//    public Contact(String phone, String email) {
//        this.phone = phone;
//        this.email = email;
//    }

    public Contact(String phone, String email, Adresse adresse) {
        this.phone = phone;
        this.email = email;
        this.adresse = adresse;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "phone")
    private String phone;

    @Column(name = "email")

    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;

    public Contact() {

    }

    // Getters and setters
}