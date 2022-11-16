package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import no.hiof.groupproject.models.RentOutAd;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.vehicle_types.Vehicle;
import no.hiof.groupproject.tools.db.RetrieveVehicleDB;

import java.math.BigDecimal;
import java.net.URL;

import java.util.ResourceBundle;


public class AdvertisementController implements Initializable {

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
    private void buttonClick(ActionEvent event) {
        Vehicle vehicle = RetrieveVehicleDB.retrieveFromRegNo(regNumberField.getText());
        User user = LogInController.user;

        BigDecimal dailyCharge = BigDecimal.valueOf(Long.parseLong(dailyChargeField.getText()));
        BigDecimal perTwentyKm = BigDecimal.valueOf(Long.parseLong(chargePerTwentyField.getText()));
        String town = townField.getText();


        RentOutAd add = new RentOutAd(user, vehicle, dailyCharge, perTwentyKm, town);
        add.addNewPeriod(availableFrom.getValue(), availableTo.getValue());

        registerButton.setText("Success!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registerButton.setOnAction(this::buttonClick);

    }

}
