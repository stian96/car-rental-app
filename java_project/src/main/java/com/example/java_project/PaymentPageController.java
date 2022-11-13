package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PaymentPageController {

    @FXML
    private TextField tf_EmailAdd;
    @FXML
    private TextField tf_Password;
    @FXML
    private Button button_addPayment;

    public void btn_AddPayment(ActionEvent event)throws IOException {
        Main m = new Main();
        m.changeScene("ToGoCar.fxml");
    }
}
