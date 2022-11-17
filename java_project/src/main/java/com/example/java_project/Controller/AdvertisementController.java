package com.example.java_project.Controller;

import com.example.java_project.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import no.hiof.groupproject.models.advertisements.RentOutAd;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.vehicles.Vehicle;
import no.hiof.groupproject.tools.db.RetrieveVehicleDB;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;

import java.util.ResourceBundle;


public class AdvertisementController implements Initializable {
    @FXML
    private Button menuButton;
    @FXML
    private TextField regNumberField;

    @FXML
    private TextField dailyChargeField;
    @FXML
    private DatePicker availableFrom;
    @FXML
    private DatePicker availableTo;
    @FXML
    private TextField townField;
    @FXML
    private TextField chargePerTwentyField;
    @FXML
    private Button registerButton;

    @FXML
    private Label error1;
    @FXML
    private Label error2;
    @FXML
    private Label error3;
    @FXML
    private Label error4;
    @FXML
    private Label error5;
    @FXML
    private Label error6;
    @FXML
    private Label errorLabel;

    @FXML
    private void buttonClick(ActionEvent event) {
        String regNumber = regNumberField.getText();
        String dailyCharge = dailyChargeField.getText();
        String perTwentyKm = chargePerTwentyField.getText();
        String town = townField.getText();

        if (regNumber.equals("") || dailyCharge.equals("") || perTwentyKm.equals("") || town.equals(""))
            showErrorLabels();

        else {
            createAdvertisement(regNumber, dailyCharge, perTwentyKm, town);
            registerButton.setText("Success!");
        }
}

    public void createAdvertisement(String regNo, String dailyCharge, String per20, String city) {
        Vehicle vehicle = RetrieveVehicleDB.retrieveFromRegNo(regNo);


        User user = LogInController.user;

        BigDecimal dailyC = BigDecimal.valueOf(Long.parseLong(dailyCharge));
        BigDecimal perTwentyKm = BigDecimal.valueOf(Long.parseLong(per20));

        RentOutAd add = new RentOutAd(user, vehicle, dailyC, perTwentyKm, city);
        add.addNewPeriod(availableFrom.getValue(), availableTo.getValue());

    }

    public void showErrorLabels() {
        Label[] labelList = {error1, error2, error3, error4, error5, error6, errorLabel};
        for (Label element : labelList)
                element.setVisible(true);
    }

    @FXML
    private void menuButtonClick(ActionEvent event) {
        Main main = new Main();
        try {
            main.changeScene("ToGoCar.fxml");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registerButton.setOnAction(this::buttonClick);
        menuButton.setOnAction(this::menuButtonClick);

        menuButton.setOnMouseEntered(e -> menuButton.setStyle("-fx-background-color:  #f1c232; -fx-text-fill: white;"));
        menuButton.setOnMouseExited(e -> menuButton.setStyle("-fx-background-color: #f1c232; -fx-text-fill: black"));
    }

}
