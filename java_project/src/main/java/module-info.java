module com.example.java_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;


    opens com.example.java_project to javafx.fxml;
    exports com.example.java_project;
}