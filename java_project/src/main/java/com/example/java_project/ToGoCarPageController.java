package com.example.java_project;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.IOException;
import java.net.URL;
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
    private Button button_messages;
    @FXML
    private Button button_customerService;
    @FXML
    private Button button_logOut;
    @FXML
    private TextField tf_TownName;
    @FXML
    private DatePicker start_DatePicker;
    @FXML
    private DatePicker return_DatePicker;
    @FXML
    private Label noAvailableCarWarning;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // noAvailableCarWarning.setText("Hello: " + LogInController.user.getFirstName() + " " + LogInController.user.getLastName());
        button_Advertisement.setOnAction(this::carAdvertisement);
        button_registerCar.setOnAction(this::userRegisterCar);
        button_profile.setOnAction(this::userProfile);
        // button_messages.setOnAction(this::message_menu);
        button_FindCar.setOnAction(this::findACar);
        button_customerService.setOnAction(this::customerService);
        button_logOut.setOnAction(this::userLogOut);

    }


    public void userRegisterCar(ActionEvent event) {
        Main m = new Main();
        try {
            m.changeScene("RegistrationCar.fxml");
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
          m.changeScene("Profile.fxml");
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
}