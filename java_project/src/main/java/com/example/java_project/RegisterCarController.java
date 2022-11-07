package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterCarController {

    @FXML
    private TextField tf_regNumber;
    @FXML
    private TextField tf_manufacturer;
    @FXML
    private TextField tf_model;
    @FXML
    private TextField tf_EngineType;
    @FXML
    private TextField tf_GearType;
    @FXML
    private TextField tf_ModelYear;
    @FXML
    private TextField tf_SeatingCapacity;
    @FXML
    private TextField tf_TowingCapacity;
    @FXML
    private Button button_Register;
    @FXML
    private Label RegisterPrompt;

    public void RegisterCar(ActionEvent event) throws IOException {
        Main m = new Main();
            m.changeScene("ToGoCar.fxml");

    }


}
