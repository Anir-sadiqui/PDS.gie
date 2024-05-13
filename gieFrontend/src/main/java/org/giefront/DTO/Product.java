package org.giefront.DTO;



public class Product {

    private Long id;


    private String name;


    private String description;


    private Category category;


    private int q;

    private double prix;

    public Product(Long id, String name, String description, Category category, int q, double prix) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.q = q;
        this.prix = prix;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public int getQ() {
        return q;
    }

    public double getPrix() {
        return prix;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setQ(int q) {
        this.q = q;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
