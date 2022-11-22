package com.example.java_project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TermsAndCondition implements Initializable {
    @FXML private TextArea textArea;
    @FXML private CheckBox checkBox;
    @FXML private AnchorPane scenePane;
    Stage stage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.textArea.setText("Terms Agreement\n" +
                "By accepting these terms, the user agrees to:\n" +
                "Deposit:\n" +
                "Upon booking, the deposit amount will be put on hold and charged to the renter’s account after delivery given car is returned in good condition. \n" +
                "Cleaning\n" +
                "The vehicle will be returned as it was delivered. Failure to do so will result in a fine depending on the severity. The base fine is 200NOK.\n" +
                "Fuel:\n" +
                "The vehicle will be returned with more than half a tank. Failure to do so results in 500NOK.\n" +
                "Damages:\n" +
                "Damages must be reported to the owner of the vehicle and insurance company. Damages will be compensated of the insurance company.\n");
    }

    public void checkBoxIsTicked() throws IOException {
        Main m = new Main();
        if(checkBox.isSelected()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Your booking is confirmed! ");
            alert.show();
            stage = (Stage) scenePane.getScene().getWindow();
            stage.close();

            m.changeScene("ToGoCar.fxml");}



    }}

