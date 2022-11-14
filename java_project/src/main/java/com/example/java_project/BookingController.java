package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import no.hiof.groupproject.models.RentOutAd;
import no.hiof.groupproject.models.vehicle_types.Vehicle;

import java.io.IOException;
import java.time.LocalDate;

public class BookingController {
    private RentOutAd ad;
    private Vehicle v;
    LocalDate fromDate, toDate;

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
    private RadioButton rb_card,rb_paypal,rb_Vipps,rb_googlePay;

    @FXML
    private Button button_back;
    @FXML
    private Button button_book;

// radio buttons has action listener.  radioCard, radioPaypal, radioVipps, radioGoogle
//temporary
    public void fillData(RentOutAd roa) {
    ad = roa;
    label_model.setText(roa.getVehicle().getManufacturer().toUpperCase());
    label_vehicleModel.setText(roa.getVehicle().getModel());

    }
    public void adFromDate(LocalDate localDate){
    fromDate = localDate;
    book_from.setText(String.valueOf(fromDate));
    }

    public void adToDate(LocalDate localDate){
    fromDate = localDate;
    book_from.setText(String.valueOf(fromDate));
    }
    public void radioCard(ActionEvent event){

    }
    public void radioPayPal(ActionEvent event){

    }
    public void radioGoogle(ActionEvent event){

    }
    public void radioVipps(ActionEvent event){

    }



    public void btn_back(ActionEvent event)throws IOException {
        Main m = new Main();
        m.changeScene("Advertisement.fxml");
    }

    public void btn_book(ActionEvent event)throws IOException {
        Main m = new Main();
        m.changeScene("Advertisement.fxml");
    }


}
