package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class ProfileController {

    @FXML
    private Button button_backMainPage;
    @FXML
    private Button button_EditProfile;
    @FXML Label label_UserName; // this will be connected to the firstname column in user database, when the user loggged in it will show their name.

    public void EditProfile(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("UpdateProfile.fxml");
    }

    public void BackMainPage(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("ToGoCar.fxml");

    }



}
