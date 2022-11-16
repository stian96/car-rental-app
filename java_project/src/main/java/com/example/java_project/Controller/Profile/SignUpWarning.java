package com.example.java_project.Controller.Profile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import no.hiof.groupproject.models.License;

import java.time.LocalDate;
import java.util.Optional;

public class SignUpWarning {
    @FXML
    private Button createProfile, goToMainPage;


    public void goToMainPage(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("You must have set up your profile to fully experince the sericses!");
        alert.setContentText("Press ok to cancel");
        alert.showAndWait();




    }
}


