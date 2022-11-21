package com.example.java_project.Controller;


import com.example.java_project.Main;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


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

        buttonRight.setOnAction(this::changeImage);
        buttonLeft.setOnAction(this::changeImageBack);
        gliderButtonStyle(buttonRight);
        gliderButtonStyle(buttonLeft);

        Button[] buttonList = {button_Advertisement, button_message, button_logOut, button_customerService,
        button_FindCar, button_profile, button_registerCar};
        for (Button buttons : buttonList)
                buttonStyle(buttons);
    }

    int counterR = 0;
    TranslateTransition trans = new TranslateTransition();

    public void changeImage(ActionEvent event) {
        trans.setDuration(Duration.seconds(0.4));
        if (counterR == 0 && carImage3.getX() == 0) {
            trans.setNode(carImage3);
            trans.setByX(375);
            trans.play();
            counterR++;
        }
        else if (counterR == 1 && carImage2.getX() == 0) {
            trans.setNode(carImage2);
            trans.setByX(375);
            trans.play();
            counterR++;
        }
        else {
            counterR = 2;
        }
    }

    public void changeImageBack(ActionEvent event) {
        trans.setDuration(Duration.seconds(0.4));
        if (counterR == 1 && carImage3.getX() == 0.0) {
            trans.setNode(carImage3);
            trans.setByX(-375);
            trans.play();
            counterR--;
        }
        else if (counterR == 2 && carImage2.getX() == 0.0) {
            trans.setNode(carImage2);
            trans.setByX(-375);
            trans.play();
            counterR--;
        }
        else {
            counterR = 0;
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
        button.setOnMouseEntered(e -> button.setStyle("-fx-opacity: 1; -fx-background-radius: 20px;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-opacity: 0.6; -fx-background-radius: 20px;"));


    }

    public void buttonStyle(Button button) {
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #c9b502;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color:  #f1c232;"));

    }
}