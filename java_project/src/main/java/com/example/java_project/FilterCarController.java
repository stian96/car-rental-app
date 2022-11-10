package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class FilterCarController {

    @FXML
    private RadioButton radioButton_manual;
    @FXML
    private RadioButton radioButton_automatic;
    @FXML
    private RadioButton radioButton_electric;
    @FXML
    private RadioButton radioButton_Gass;
    @FXML
    private RadioButton radioButton_Diesel;
    @FXML
    private ChoiceBox choiceBox_manufacturer;
    @FXML
    private Spinner spinner_seats;
    @FXML
    private TextField tf_yearModel;
    @FXML
    private TextField tf_gps;
    @FXML
    private TextField tf_priceClass;
    @FXML
    private Button button_exit;
    @FXML
    private Button button_search;

    public void btExit(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("ToGoCar.fxml");
    }
    public void btSearch(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("ToGoCar.fxml");
    }



}
