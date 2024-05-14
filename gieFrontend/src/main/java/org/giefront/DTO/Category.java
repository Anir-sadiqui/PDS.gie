package org.giefront.DTO;

public enum Category {

        CATEGORY1("Category 1"),
        CATEGORY2("Category 2"),
        CATEGORY3("Category 3");

        private final String categoryName;

        Category(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getCategoryName() {
            return categoryName;
        }
    }




