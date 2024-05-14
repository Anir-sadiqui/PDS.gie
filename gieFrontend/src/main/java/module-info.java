module org.giefront {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires com.fasterxml.jackson.databind;
    requires static lombok;



    opens org.giefront to javafx.fxml;
    exports org.giefront;
    exports org.giefront.DTO to com.fasterxml.jackson.databind;
    requires javafx.base;
    requires de.jensd.fx.glyphs.fontawesome;
    opens org.giefront.DTO to javafx.base;
}