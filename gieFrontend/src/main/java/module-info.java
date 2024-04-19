module org.giefront {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires static lombok;
    requires com.fasterxml.jackson.databind;



    opens org.giefront to javafx.fxml;
    exports org.giefront;
}