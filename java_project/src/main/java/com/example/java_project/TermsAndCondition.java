package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class TermsAndCondition implements Initializable {
    @FXML private TextArea textArea;
    @FXML private CheckBox checkBox, notAgreeCheckBox;
    @FXML private AnchorPane scenePane;
    Stage stage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        checkBox.setOnAction(this::checkBoxIsTicked);

        notAgreeCheckBox.setOnAction(this::notAgreeCheckBoxTicked);

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

    public void checkBoxIsTicked(ActionEvent event) {
        Main m = new Main();
        try {
            if (checkBox.isSelected()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Your booking is confirmed! ");
                alert.show();
                stage = (Stage) scenePane.getScene().getWindow();
                stage.close();

                m.changeScene("ToGoCar.fxml");
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void notAgreeCheckBoxTicked(ActionEvent event) {
        Main m = new Main();
        try{
        if(notAgreeCheckBox.isSelected()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("There are no available ads with that criteria.");
            alert.setResizable(false);
            alert.setContentText("Press Ok to search again or cancel to return to main page");
            Optional<ButtonType> result = alert.showAndWait();


            if(result.get() == ButtonType.OK){
                stage = (Stage) scenePane.getScene().getWindow();
                stage.close();
                m.changeScene("ToGoCar.fxml");}

        }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

