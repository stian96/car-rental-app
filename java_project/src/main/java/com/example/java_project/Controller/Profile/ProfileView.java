package com.example.java_project.Controller.Profile;

import com.example.java_project.Controller.LogInController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.UserProfile;
import no.hiof.groupproject.tools.db.RetrieveAverageRatingDB;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileView implements Initializable {

    UserProfile u = new UserProfile(LogInController.user);
    String avgRating = RetrieveAverageRatingDB.retrieve(u);


    @FXML
    private ListView<?> advertisementList;

    @FXML
    private Button editProfileButton;

    @FXML
    private Button mainPageButton;

    @FXML
    private Label ratingLabel;

    @FXML
    private Label userNameLabel;

    @FXML
    void backMainPage(ActionEvent event) {

    }

    @FXML
    void editProfile(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserProfile userProfile = new UserProfile(LogInController.user);
        userNameLabel.setText("Hello " + userProfile.getUser().getFirstName() +
                " " + userProfile.getUser().getLastName() + "!");
        ratingLabel.setText(String.format("%s", avgRating));

    }
}
