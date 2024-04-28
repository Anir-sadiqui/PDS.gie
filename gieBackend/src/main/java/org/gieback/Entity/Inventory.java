package org.gieback.Entity;

import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Inventory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the primary key
    private Long id;

    @OneToMany
    @JoinColumn(name = "inventory_id")
    private List<Achat> achats; // Corrected variable naming

    @OneToMany
    @JoinColumn(name = "inventory_id")
    private List<Product> products;

    @OneToMany
    @JoinColumn(name = "inventory_id")
    private List<Contact> contacts;

    public Inventory() {}

    public Inventory(List<Achat> achats, List<Product> products, List<Contact> contacts) {
        this.achats = achats;
        this.products = products;
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Achats: " + achats.size() + '\n' +
                "Products: " + products.size() + '\n' +
                "Contacts: " + contacts.size() + '\n';
    }
}
