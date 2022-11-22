package com.example.java_project;

import com.example.java_project.Controller.LogInController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.UserProfile;
import no.hiof.groupproject.models.advertisements.Advertisement;
import no.hiof.groupproject.tools.db.RetrieveAdvertisementsDB;
import no.hiof.groupproject.tools.db.RetrieveAverageRatingDB;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProfileView implements Initializable {

    UserProfile u = new UserProfile(LogInController.user);
    String avgRating = RetrieveAverageRatingDB.retrieve(u);


    @FXML
    private ListView<Advertisement> advertisementList;

    @FXML
    private Button editProfileButton;

    @FXML
    private Button mainPageButton;

    @FXML
    private Label ratingLabel, noAdLabel;

    @FXML
    private Label userNameLabel;
    ObservableList<Advertisement> observableList = FXCollections.observableArrayList();
    ArrayList<Advertisement> adsInArrayList = RetrieveAdvertisementsDB.retrieveAdvertisementsObjectFromUserId(LogInController.user.getId());
    public ObservableList<Advertisement> getAd(){
        ObservableList<Advertisement> ads = FXCollections.observableArrayList();

        if(!adsInArrayList.isEmpty()){
            ads.addAll(adsInArrayList);

        }else {
            noAdLabel.setText("You have no ads yet");
        }
        return ads;



    }
    public String getAvgRating(){
        String avgRating = RetrieveAverageRatingDB.retrieve(u);
        if(avgRating == null){
            return "No ratings yet!";

        }else {return avgRating;}
    }
    @FXML
    void backMainPage(ActionEvent event) {
        Main m = new Main();
        try {
            m.changeScene("ToGoCar.fxml");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    @FXML
    void editProfile(ActionEvent event) {
        Main m = new Main();
        try {
            m.changeScene("UpdateProfile.fxml");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserProfile userProfile = new UserProfile(LogInController.user);
        userNameLabel.setText("Hello " + userProfile.getUser().getFirstName() +
                " " + userProfile.getUser().getLastName() + "!");
        ratingLabel.setText(String.format("%s", getAvgRating()));
        advertisementList.getItems().addAll(getAd());

    }
}
