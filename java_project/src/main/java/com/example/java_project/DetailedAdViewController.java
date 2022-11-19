package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import no.hiof.groupproject.models.advertisements.RentOutAd;
import no.hiof.groupproject.models.vehicles.four_wheeled_vehicles.Car;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DetailedAdViewController implements Initializable {
    private RentOutAd selectedAd;
    private Car vehicle;

    private LocalDate fromDate, toDate;
    private String stage = null;

    //labels with fx-Id's
    @FXML
    private Label selectedOwner, selectedMan, selectedModel, selectedModelYear, selectedGearType,
            selectedFuelType,
            selectedSeatingCapacity,
            selectedTowingCapacity;


    @FXML
    private Button button_back;

    // action Listen


    @FXML
    private Label vehicleNameLabel, selectedFromDate;
    @FXML
    private TextField tf_modelName;

    public void fillData(RentOutAd roa) {
        selectedAd = roa;
        vehicle = (Car) roa.getVehicle();
        selectedOwner.setText(roa.getUser().getFirstName());
        selectedMan.setText(roa.getVehicle().getManufacturer());
        selectedModel.setText(roa.getVehicle().getModel());
        selectedModelYear.setText(String.valueOf(roa.getVehicle().getModelYear()));
        selectedGearType.setText(roa.getVehicle().getGearType());
        selectedFuelType.setText(roa.getVehicle().getEngineType());
        selectedSeatingCapacity.setText(String.valueOf(vehicle.getSeatingCapacity()));
        selectedTowingCapacity.setText(String.valueOf(vehicle.getTowingCapacity()));
    }


    public void changeSceneToFilterCar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FilterCar.fxml"));
        Parent pane = loader.load();

        Scene scene = new Scene(pane);

        //access the controller and call a method


        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}