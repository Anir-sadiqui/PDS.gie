module org.giefront {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.giefront to javafx.fxml;
    exports org.giefront;
}