package org.gieback.Entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity

@Inheritance(strategy = InheritanceType.SINGLE_TABLE )

@DiscriminatorColumn(name="type")
@Table(name="contact")

public abstract class  Contactt implements Serializable {

                @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

 @Column(name = "id")
    protected int id;
 @Column(name = "email")
    protected String email;
 @Column(name = "phone")
    protected String phone;
  @Column(name = "fax")
    protected String fax;

    @ManyToMany
    @JoinTable(
            name = "contact_adress",
            joinColumns = @JoinColumn(name = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "adress_id")
    )
    private List<Adress> adresses;
                public Contactt(String email, String phone, String fax) {

                    this.email = email;
                    this.fax = fax;
                    this.phone = phone;
                }

                public Contactt() {

                }

    @Override
    public String toString() {
        return "Contactt{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", adresses=" + adresses +
                '}';
    }
}


