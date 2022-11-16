module com.example.java_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.sql;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    //serialisation dependencies
    /*
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires org.hibernate.orm.community.dialects;
    requires jakarta.xml.bind;
    requires com.fasterxml.classmate;

     */





    opens com.example.java_project to javafx.fxml;
    exports com.example.java_project;
    exports com.example.java_project.Controller;
    opens com.example.java_project.Controller to javafx.fxml;
    exports com.example.java_project.DefaultApp;
    opens com.example.java_project.DefaultApp to javafx.fxml;
    exports com.example.java_project.Other;
    opens com.example.java_project.Other to javafx.fxml;
    exports com.example.java_project.Controller.Profile;
    opens com.example.java_project.Controller.Profile to javafx.fxml;

}