package com.example.java_project;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


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



    public void userRegisterCar(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("RegistrationCar.fxml");}

    public void carAdvertisement(ActionEvent event)throws IOException{
        Main m = new Main();
        m.changeScene("Advertisement.fxml");}


  public void userProfile(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("Profile.fxml");
        }

  public void message_menu(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("LogIn.fxml");
        }
 public void findACar(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("FilterCar.fxml");
        }
 public void customerService(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("LogIn.fxml");
        }
 public void userLogOut(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("LogIn.fxml");
        }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // noAvailableCarWarning.setText("Hello: " + LogInController.user.getFirstName() + " " + LogInController.user.getLastName());
    }


}