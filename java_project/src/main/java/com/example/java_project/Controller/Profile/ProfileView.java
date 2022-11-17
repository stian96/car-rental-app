package com.example.java_project.Controller.Profile;

import com.example.java_project.Controller.LogInController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import no.hiof.groupproject.models.UserProfile;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileView implements Initializable {


    @FXML
    private ListView<?> advertisementList;

    @FXML
    private Button button_EditProfile;

    @FXML
    private Button button_backMainPage;

    @FXML
    private Label ratingLabel;

    @FXML
    private Label userNameLabel;

    @FXML
    void BackMainPage(ActionEvent event) {

    }

    @FXML
    void EditProfile(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserProfile userProfile = new UserProfile(LogInController.user);
        userNameLabel.setText("Hello " + userProfile.getUser().getFirstName() +
                " " + userProfile.getUser().getLastName() + "!");
        ratingLabel.setText(String.format("%f", userProfile.getAverageRating()));

    }
}
