module org.giefront {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires com.fasterxml.jackson.databind;
    requires gieBackend;


    opens org.giefront to javafx.fxml;
    exports org.giefront;
}