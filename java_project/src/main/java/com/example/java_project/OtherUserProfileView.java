
package com.example.java_project;


import com.example.java_project.Controller.LogInController;
import com.example.java_project.FindACarToRent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.UserProfile;
import no.hiof.groupproject.models.advertisements.RentOutAd;
import no.hiof.groupproject.tools.db.RetrieveAverageRatingDB;
import no.hiof.groupproject.tools.db.RetrieveUserDB;

import java.net.URL;
import java.util.ResourceBundle;

public class OtherUserProfileView implements Initializable {
    private  User user;
    UserProfile userprofile;
    String avg ;

    @FXML private Label userName,userAverageRate, shit;
    @FXML private TextField rateTxtField;
    @FXML private Button addRateButton;
    @FXML private ListView<RentOutAd> listView;
    private RentOutAd sAd;

    public void fillData(RentOutAd ad){
        sAd = ad;
        user = ad.getUser();
       // shit.setText(ad.getUser().getFirstName());
        userprofile = getUserProfile(user);
        avg = RetrieveAverageRatingDB.retrieve(userprofile);
        userAverageRate.setText(String.format("%s", avg));
        userName.setText(userprofile.getUser().getFirstName() + " " + user.getLastName());

    }
    @FXML
    public void addRating(ActionEvent actionEvent){
        User user = LogInController.user;
        int rate = Integer.parseInt(rateTxtField.getText());

        userprofile.addNewRating(user,rate);
        addRateButton.setText("Added");


    }

    public UserProfile getUserProfile(User user){
        return new UserProfile(user);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       //;
        addRateButton.setOnAction(this::addRating);
        listView.setItems(FindACarToRent.adObservableList);
    }








}