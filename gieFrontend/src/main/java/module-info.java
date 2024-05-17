module org.giefront {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires com.fasterxml.jackson.databind;
    requires static lombok;


    // Use 'opens' to allow reflection-based access for FXML
    opens org.giefront.controller to javafx.fxml;
    exports org.giefront;
    exports org.giefront.DTO to com.fasterxml.jackson.databind;
    requires javafx.base;
    requires com.fasterxml.jackson.datatype.jsr310;
    opens org.giefront.DTO to javafx.base;
    exports org.giefront.controller;
}