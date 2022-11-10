package com.example.java_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import no.hiof.groupproject.models.Advertisement;
import no.hiof.groupproject.tools.filters.FilterAdvertisement;

import java.io.IOException;
import java.util.ArrayList;

public class ProfileController {

    @FXML
    private Button button_backMainPage;
    @FXML
    private Button button_EditProfile;
    @FXML Label label_UserName; // this will be connected to the firstname column in user database, when the user loggged in it will show their name.

    @FXML
    ListView<Advertisement> advertisementList;

    ObservableList<Advertisement> observableList = FXCollections.observableArrayList();


    private void loadAds(){
        ;
    }



    public void EditProfile(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("UpdateProfile.fxml");
    }

    public void BackMainPage(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("ToGoCar.fxml");

    }



}
