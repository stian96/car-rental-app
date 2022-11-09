package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ToGoCarPageController {

    @FXML
    private Button button_registerCar;
    @FXML
    private Button button_FindCar;
    @FXML
    private Button button_Advertisement;
    @FXML
    private Button button_profile;
    @FXML
    private Button button_messages;
    @FXML
    private Button button_customerService;
    @FXML
    private Button button_logOut;
    @FXML
    private TextField tf_TownName;
    @FXML
    private DatePicker Start_DatePicker;
    @FXML
    private DatePicker Return_DatePicker;
    @FXML
    private Label noAvailableCarWarning;



    public void userRegisterCar(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("RegisterACar.fxml");
    }
    public void carAdvertisement(ActionEvent event)throws IOException{
        Main m = new Main();
        m.changeScene("Advertisement.fxml");
    }

    public void FindBookCar(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("FilterCar.fxml");
    }

    public void userProfile(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("Profile.fxml");
    }

    public void message_menu(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("LogIn.fxml");
    }
    public void customerService(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("LogIn.fxml");
    }
    public void userLogOut(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("LogIn.fxml");
    }


}