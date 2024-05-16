package org.giefront.DTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Product implements Serializable {
        private Long id;

        private String name;

        private String description;

        private Category category;

        private int q;

        private double prix;
        private String imagePath;


        public Product() {}

        public Product(String name, String description, Category category, int q,double prix ) {
            this.name = name;
            this.description = description;
            this.category = category;
            this.q=q;
            this.prix=prix;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setPrix(double prix) {
            this.prix = prix;
        }

        public void setQ(int q) {
            this.q = q;
        }

        public void setId(Long id) {
            this.id = id;
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

        public int getIdProduct() {
            return 0;
        }
    }






