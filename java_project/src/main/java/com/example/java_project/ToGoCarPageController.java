package com.example.java_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import no.hiof.groupproject.models.RentOutAd;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.vehicle_types.Vehicle;
import no.hiof.groupproject.tools.db.RetrieveAdvertisementDB;
import no.hiof.groupproject.tools.db.RetrieveAvailableWithinDB;
import no.hiof.groupproject.tools.db.RetrieveUserDB;
import no.hiof.groupproject.tools.db.RetrieveVehicleDB;
import no.hiof.groupproject.tools.filters.FilterAdvertisement;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.TreeMap;

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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("RegisterACar.fxml"));
        Parent pane = loader.load();

        Scene scene = new Scene(pane);

        Stage window = new Stage();

        window.setScene(scene);
        window.show();}

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
        noAvailableCarWarning.setText("Hello: " + LogInController.user.getFirstName() + " " + LogInController.user.getLastName());
    }
}