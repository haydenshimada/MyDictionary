module com.example.dictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires freetts;
    requires java.sql;


    opens com.example.dictionary to javafx.fxml;
    exports com.example.dictionary;
    exports com.example.dictionary.API;
    opens com.example.dictionary.API to javafx.fxml;
}