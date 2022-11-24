module no.hiof.groupproject {
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





    opens no.hiof.groupproject.gui to javafx.fxml;
    exports no.hiof.groupproject.gui;
    exports no.hiof.groupproject.gui.Controller;
    opens no.hiof.groupproject.gui.Controller to javafx.fxml;
    exports no.hiof.groupproject.gui.Other;
    opens no.hiof.groupproject.gui.Other to javafx.fxml;
    exports no.hiof.groupproject.gui.Controller.Profile;
    opens no.hiof.groupproject.gui.Controller.Profile to javafx.fxml;

}