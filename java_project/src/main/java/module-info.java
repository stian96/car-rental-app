module com.example.java_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;








    opens com.example.java_project to javafx.fxml;
    exports no.hiof.groupproject;

}