package com.example.java_project.Controller.Profile;

import com.example.java_project.Controller.LogInController;
import com.example.java_project.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import no.hiof.groupproject.models.Advertisement;
import no.hiof.groupproject.models.RentOutAd;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.UserProfile;
import no.hiof.groupproject.tools.db.RetrieveAdvertisementDB;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {

    @FXML
    private Button button_backMainPage;
    @FXML
    private Button button_EditProfile;
    @FXML
    private Label ratingLabel;
    @FXML private Label label_UserName; // this will be connected to the firstname column in user database, when the user loggged in it will show their name.

    @FXML
    ListView<Advertisement> advertisementList;

    ObservableList<Advertisement> observableList = FXCollections.observableArrayList();

    Advertisement ads;

    double rating;

    UserProfile userProfile1;
    User logIn = LogInController.user;
    User user;
    private RentOutAd ad = (RentOutAd) RetrieveAdvertisementDB.retrieveFromId(LogInController.user.getId());
    //RetrieveUserProfileDB.retrieve(LogInController.user.getId());
   /*
    private ObservableList<Advertisement> getAds(){
        ObservableList<Advertisement> ads = FXCollections.observableArrayList();


        ads.addAll(RetrieveAdvertisementDB.retrieveFromId(LogInController.user.getId()));
        System.out.println(ads);
        advertisementList.getItems().addAll(ads);

    return ads;



    }

    */

    private ObservableList<Advertisement> getAd(){
        ObservableList<Advertisement> ads = FXCollections.observableArrayList();
        Advertisement ad = RetrieveAdvertisementDB.retrieveFromId(LogInController.user.getId());
        if(!((RentOutAd)ad).existsInDb()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("No ads");
            alert.show();
        }else{
            ads.addAll(ad);
            System.out.println(ads);
            advertisementList.getItems().addAll(ads);}

            return ads;



        }
/*

    public void fillData(RentOutAd roa){
        ad = roa;
        this.user = roa.getUser();
        this.userProfile1 = RetrieveUserProfileDB.retrieve(user.getId());
        label_UserName.setText(roa.getUser().getFirstName());
        //this.userProfile1 = RetrieveUserProfileDB.retrieve(roa.getUser().getId());
        //ratingLabel.setText(roa.getUser().getFirstName());
        //System.out.println(userProfile);

        if(roa.getUser().equals(logIn)){
            label_UserName.setText(logIn.getFirstName());
        }else{
            label_UserName.setText(roa.getUser().getFirstName() + " " + ad.getUser().getLastName());}







        }

 */



    public void EditProfile(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("UpdateProfile.fxml");
    }

    public void BackMainPage(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("ToGoCar.fxml");

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       label_UserName.setText(LogInController.user.getFirstName() + " " + LogInController.user.getLastName());
    }
}
