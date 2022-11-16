package com.example.java_project.Controller.Profile;

import com.example.java_project.Controller.LogInController;
import com.example.java_project.Other.FindACarToRent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.UserProfile;
import no.hiof.groupproject.tools.db.RetrieveAverageRatingDB;

import java.net.URL;
import java.util.ResourceBundle;

public class OtherUserProfileView implements Initializable {
       UserProfile userprofile = new UserProfile(FindACarToRent.roa.getUser());

        @FXML private Label userName,userAVgRate;
        @FXML private TextField rateTxtField;
        @FXML private Button addRateButton;
    @FXML
    public void addRating(ActionEvent actionEvent){
        User user = LogInController.user;
        int rate = Integer.parseInt(rateTxtField.getText());

        userprofile.addNewRating(user,rate);
        addRateButton.setText("Added");

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addRateButton.setOnAction(this::addRating);

        userName.setText(FindACarToRent.roa.getUser().getFirstName() + " " + FindACarToRent.roa.getUser().getLastName());
        userAVgRate.setText(String.valueOf(RetrieveAverageRatingDB.retrieve(userprofile)));

    }


}
