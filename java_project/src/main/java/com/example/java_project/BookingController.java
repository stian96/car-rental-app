package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.io.IOException;

public class BookingController {

    @FXML
    private Label label_model;
    @FXML
    private Label label_vehicleModel;
    @FXML
    private Label book_from;
    @FXML
    private Label book_to;
    @FXML
    private Label total_Amount;
    @FXML
    private RadioButton rb_card;
    @FXML
    private RadioButton rb_paypal;
    @FXML
    private RadioButton rb_Vipps;
    @FXML
    private RadioButton rb_googlePay;
    @FXML
    private Button button_back;
    @FXML
    private Button button_book;

// radio buttons has action listener.  radioCard, radioPaypal, radioVipps, radioGoogle
//temporary
    public void btn_back(ActionEvent event)throws IOException {
        Main m = new Main();
        m.changeScene("Advertisement.fxml");
    }

    public void btn_book(ActionEvent event)throws IOException {
        Main m = new Main();
        m.changeScene("Advertisement.fxml");
    }


}
