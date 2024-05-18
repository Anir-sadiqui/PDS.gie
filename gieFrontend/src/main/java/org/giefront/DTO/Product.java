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

        public Product(String name, String description, Category category, int q,double prix, String imagePath ) {
            this.name = name;
            this.description = description;
            this.category = category;
            this.q=q;
            this.prix=prix;
            this.imagePath=imagePath;
        }


    }






