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

        public Product(String name, String description, Category category, double prix, String imagePath ) {
            this.name = name;
            this.description = description;
            this.category = category;
            this.prix=prix;
            this.imagePath=imagePath;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public int getQ() {
            return q;
        }

        public void setQ(int q) {
            this.q = q;
        }

        public double getPrix() {
            return prix;
        }

        public void setPrix(double prix) {
            this.prix = prix;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }
    }






