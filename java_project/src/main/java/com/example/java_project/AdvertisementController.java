package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class AdvertisementController {

    @FXML
    private Label noAvailableCarWarning;
    @FXML
    private TextField tf_SearchVehicle;
    @FXML
    private RadioButton rd_Nok;
    @FXML
    private RadioButton rd_Euro;
    @FXML
    private TextField tf_dailyCharge;
    @FXML
    private TextField tf_KMpHour;
    @FXML
    private DatePicker dp_To;
    @FXML
    private DatePicker dp_From;
    @FXML
    private Button button_SearchAds;
    @FXML
    private Button button_Exit;


    public void searchAdvertisement(ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("ToGoCar.fxml");
    }

    public void ExitAds (ActionEvent event) throws IOException{
        Main m = new Main();
        m.changeScene("ToGoCar.fxml");
    }






}
