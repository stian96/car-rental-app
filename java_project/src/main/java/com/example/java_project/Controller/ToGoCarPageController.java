package com.example.java_project.Controller;


import com.example.java_project.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import no.hiof.groupproject.models.advertisements.RentOutAd;
import no.hiof.groupproject.tools.db.RetrieveAdvertisementDB;
import no.hiof.groupproject.tools.filters.FilterAdvertisement;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class ToGoCarPageController  implements Initializable {

    @FXML
    private Button button_registerCar;
    @FXML
    private Button button_FindCar;
    @FXML
    private Button button_Advertisement;
    @FXML
    private Button button_profile;
    @FXML
    private Button button_message;
    @FXML
    private Button button_customerService;
    @FXML
    private Button button_logOut;
    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private Button searchButton;

    @FXML
    private DatePicker toDatePicker;

    @FXML
    private TextField townTxtField;


    public static RentOutAd roa;

    public Integer getAdId(){
        String town = townTxtField.getText().trim().toLowerCase();

        for(Integer id : FilterAdvertisement.filterToArrayListAdvertisementId(null, null, null,
                town, null, null, null,
                null,null, null)){
            return id;
        }
        return getAdId();
    }

    public LocalDate getFromDate(){
        return fromDatePicker.getValue();
    }
    //returns the startDate picked using the date picker
    public LocalDate getToDate(){
        return toDatePicker.getValue();
    }


    public String findAnAvailableCar(){
        String status = "success";
        if(getFromDate() == null || getToDate()== null){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Select Date");
            alert.show();
            status = "error";}
        else {
            try {
                RentOutAd ad = (RentOutAd) RetrieveAdvertisementDB.retrieveFromId(getAdId());
                roa = ad;


                if(!ad.checkIfDateIsAvailable(getFromDate(),getToDate())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("No cars available for those dates");
                    alert.show();
                    status = "error";}


            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        return status;

    }

    @FXML
    void searchAds(ActionEvent event) {
        Main m = new Main();
        if(event.getSource() == searchButton){
            if(findAnAvailableCar().equals("success")){
                try {
                    m.changeScene("FilterCar.fxml");
                } catch (IOException ioException) {
                    System.out.println(ioException.getMessage());
                }

            }
        }

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // noAvailableCarWarning.setText("Hello: " + LogInController.user.getFirstName() + " " + LogInController.user.getLastName());
        button_Advertisement.setOnAction(this::carAdvertisement);
        button_registerCar.setOnAction(this::userRegisterCar);
        button_profile.setOnAction(this::userProfile);
        button_message.setOnAction(this::message_menu);
        button_FindCar.setOnAction(this::findACar);
        button_customerService.setOnAction(this::customerService);
        button_logOut.setOnAction(this::userLogOut);
        searchButton.setOnAction(this::searchAds);
        buttonStyle();

    }

    public void userRegisterCar(ActionEvent event) {
        Main m = new Main();
        try {
            m.changeScene("RegisterACar.fxml");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    public void carAdvertisement(ActionEvent event) {
        Main m = new Main();
        try {
            m.changeScene("Advertisement.fxml");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

  public void userProfile(ActionEvent event) {
        Main m = new Main();
        try {
          m.changeScene("UserProfile.fxml");
        } catch (IOException ioException) {
          System.out.println(ioException.getMessage());
        }
    }

  public void message_menu(ActionEvent event) {
        Main m = new Main();
        try {
          m.changeScene("LogIn.fxml");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

 public void findACar(ActionEvent event) {
        Main m = new Main();
        try {
         m.changeScene("FilterCar.fxml");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
 public void customerService(ActionEvent event) {
        Main m = new Main();
        try {
         m.changeScene("Login.fxml");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
 public void userLogOut(ActionEvent event) {
        Main m = new Main();
        try {
         m.changeScene("LogIn.fxml");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    public void buttonStyle() {
        button_registerCar.setOnMouseEntered(e -> button_registerCar.setStyle("-fx-background-color: #c9b502;"));
        button_registerCar.setOnMouseExited(e -> button_registerCar.setStyle("-fx-background-color:  #f1c232;"));

        button_Advertisement.setOnMouseEntered(e -> button_Advertisement.setStyle("-fx-background-color: #c9b502;"));
        button_Advertisement.setOnMouseExited(e -> button_Advertisement.setStyle("-fx-background-color:  #f1c232;"));

        button_profile.setOnMouseEntered(e -> button_profile.setStyle("-fx-background-color: #c9b502;"));
        button_profile.setOnMouseExited(e -> button_profile.setStyle("-fx-background-color:  #f1c232;"));

        button_FindCar.setOnMouseEntered(e -> button_FindCar.setStyle("-fx-background-color: #c9b502;"));
        button_FindCar.setOnMouseExited(e -> button_FindCar.setStyle("-fx-background-color:  #f1c232;"));

        button_customerService.setOnMouseEntered(e -> button_customerService.setStyle("-fx-background-color: #c9b502;"));
        button_customerService.setOnMouseExited(e -> button_customerService.setStyle("-fx-background-color:  #f1c232;"));

        button_logOut.setOnMouseEntered(e -> button_logOut.setStyle("-fx-background-color: #c9b502;"));
        button_logOut.setOnMouseExited(e -> button_logOut.setStyle("-fx-background-color:  #f1c232;"));

        button_message.setOnMouseEntered(e -> button_message.setStyle("-fx-background-color: #c9b502;"));
        button_message.setOnMouseExited(e -> button_message.setStyle("-fx-background-color:  #f1c232;"));
    }
}