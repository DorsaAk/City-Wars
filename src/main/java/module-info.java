module org.example.finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;

    exports view;
    exports Controller;
    opens Controller to javafx.fxml;
    opens view to javafx.fxml;
    opens Model to javafx.fxml;
    exports Model;

    opens org.example.finalproject to javafx.fxml;
    exports org.example.finalproject;
}