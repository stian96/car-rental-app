package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import no.hiof.groupproject.models.Advertisement;
import no.hiof.groupproject.models.RentOutAd;
import no.hiof.groupproject.models.vehicle_types.Car;
import no.hiof.groupproject.tools.db.ConnectDB;
import no.hiof.groupproject.tools.filters.FilterAdvertisement;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


public class AdvertisementController implements Initializable {

    @FXML
    private TextField regNumberField;
    @FXML
    private Label regNumberLabel;
    @FXML
    private TextField dailyChargeField;
    @FXML
    private Label dailyChargeLabel;
    @FXML
    private RadioButton nokRadioButton;
    @FXML
    private RadioButton euroRadioButton;
    @FXML
    private Label currencyLabel;
    @FXML
    private DatePicker availableFrom;
    @FXML
    private DatePicker availableTo;
    @FXML
    private TextField townField;
    @FXML
    private TextField fylkeField;
    @FXML
    private TextField postNrField;
    @FXML
    private TextField chargePerTwentyField;
    @FXML
    private TextField countryField;
    @FXML
    private Button registerButton;

    @FXML
    private void buttonClick(ActionEvent event) {
        String userCar = regNumberField.getText();
        int vehicleID = getVehicleId(userCar);


    }

    public Integer getVehicleId(String regNumber) {
        int vehicleId = 0;
        String sql = "SELECT * FROM vehicles WHERE regNo = " + regNumber;

        try (Connection connection = ConnectDB.connect()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            vehicleId = rs.getInt("vehicles_id");
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return vehicleId;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registerButton.setOnAction(this::buttonClick);

    }

}
