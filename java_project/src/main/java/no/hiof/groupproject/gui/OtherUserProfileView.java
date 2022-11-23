
package no.hiof.groupproject.gui;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import no.hiof.groupproject.gui.Controller.LogInController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.UserProfile;
import no.hiof.groupproject.models.advertisements.Advertisement;
import no.hiof.groupproject.models.advertisements.RentOutAd;
import no.hiof.groupproject.tools.db.RetrieveAdvertisementsDB;
import no.hiof.groupproject.tools.db.RetrieveAverageRatingDB;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OtherUserProfileView implements Initializable {
    private  User user;
    UserProfile userprofile;
    String avg ;

    @FXML private Label userName,userAverageRate, noAds;
    @FXML private TextField rateTxtField;
    @FXML private Button addRateButton,backButton;
    @FXML private ListView<RentOutAd> listView;
    @FXML private AnchorPane scenePane;
    Stage stage;
    private RentOutAd sAd;
    ObservableList<RentOutAd> rad = FXCollections.observableArrayList();


    public void fillData(RentOutAd ad){
        sAd = ad;
        user = ad.getUser();
       // shit.setText(ad.getUser().getFirstName());
        userprofile = getUserProfile(user);

        avg = RetrieveAverageRatingDB.retrieve(userprofile);
        userAverageRate.setText(String.format("%s", getAvg(avg)));

        userName.setText(userprofile.getUser().getFirstName() + " " + user.getLastName());

        rad.addAll(ad);
        listView.setItems(rad);

    }
    @FXML
    public void addRating(ActionEvent actionEvent){
        User user = LogInController.user;
        int rate = Integer.parseInt(rateTxtField.getText());

        userprofile.addNewRating(user,rate);
        addRateButton.setText("Added");


    }
    public String getAvg(String average){
         String avg = average;
         if(average == null){    return "No ratings yet!";

         }else {return avg;}
    }


    public void backToPreviousPage(ActionEvent even){
        stage = (Stage) scenePane.getScene().getWindow();
        stage.close();

    }

    public UserProfile getUserProfile(User user){
        return new UserProfile(user);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        addRateButton.setOnAction(this::addRating);
        backButton.setOnAction(this::backToPreviousPage);



        buttonStyle();
    }

    public void buttonStyle() {
        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color: #c9b502;"));
        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color:  #f1c232;"));}


}








