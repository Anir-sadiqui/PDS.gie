package org.gieback.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Inventory implements Serializable {
    @OneToMany
    @JoinColumn(name = "inventory_id")
    private List<Achat> Achats;

    @OneToMany
    @JoinColumn(name = "inventory_id")
    private List<Product> products;

    @OneToMany
    @JoinColumn(name = "inventory_id")
    private List<Category> categories;

    @OneToMany
    @JoinColumn(name = "inventory_id")
    private List<Contact> contacts;

    public Inventory() {}

    public Inventory(List<Achat> Achats, List<Product> products, List<Category> categories, List<Contact> contacts) {
        this.Achats = Achats;
        this.products = products;
        this.categories = categories;
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Achats: " + Achats.size() + '\n' +
                "Products: " + products.size() + '\n' +
                "Categories: " + categories.size() + '\n' +
                "Contacts: " + contacts.size() + '\n';
    }
}
