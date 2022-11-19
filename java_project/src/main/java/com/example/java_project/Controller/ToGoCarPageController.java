package com.example.java_project.Controller;


import com.example.java_project.Main;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private Button button_message;
    @FXML
    private Button button_customerService;
    @FXML
    private Button button_logOut;
    @FXML
    private ImageView carImage;
    @FXML
    private ImageView carImage2;
    @FXML
    private ImageView carImage3;
    @FXML
    private Button buttonLeft;
    @FXML
    private Button buttonRight;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_Advertisement.setOnAction(this::carAdvertisement);
        button_registerCar.setOnAction(this::userRegisterCar);
        button_profile.setOnAction(this::userProfile);
        button_message.setOnAction(this::message_menu);
        button_FindCar.setOnAction(this::findACar);
        button_customerService.setOnAction(this::customerService);
        button_logOut.setOnAction(this::userLogOut);
        buttonStyle();

        buttonRight.setOnAction(this::changeImage);
        buttonLeft.setOnAction(this::changeImageBack);
        gliderButtonStyle(buttonRight);
        gliderButtonStyle(buttonLeft);
    }

    int counter = 0;

    public void changeImage(ActionEvent event) {
        if (counter <= 1) {
            if (counter == 0) {
                carImage3.setVisible(false);
            }
            else if (counter == 1) {
                carImage2.setVisible(false);
            }
            counter++;
        }
        else {
            counter--;
        }

    }

    public void changeImageBack(ActionEvent event) {
        if (counter >= 0) {
            if (counter == 1) {
                carImage2.setVisible(true);
            }
            else if (counter == 0) {
                carImage3.setVisible(true);
            }
            counter--;
        }
        else {
            counter++;
        }

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
          m.changeScene("UpdateProfile.fxml");
        } catch (IOException ioException) {
          System.out.println(ioException.getMessage());
        }
    }

  public void message_menu(ActionEvent event) {
        Main m = new Main();
        try {
          m.changeScene("Message.fxml");
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

    public void gliderButtonStyle(Button button) {
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #e6e6e6;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color:  white;"));

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