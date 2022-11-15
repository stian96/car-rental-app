package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import no.hiof.groupproject.models.RentOutAd;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.vehicle_types.Car;
import no.hiof.groupproject.models.vehicle_types.Vehicle;
import no.hiof.groupproject.tools.db.RetrieveAdvertisementDB;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static java.time.temporal.ChronoUnit.DAYS;

public class BookingController implements Initializable {
    public static long
    countDaysBetween(LocalDate dateFrom, LocalDate dateTo){
        return DAYS.between(dateFrom,dateTo);
    }
    private RentOutAd ad;
    private Car v;
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
    private ToggleGroup paymentToggleGroups;
    int id;

    User owner, renter;
    int vehicleId;

    LocalDate getFromDate, getToDate;
    @FXML
    private Button button_back;
    @FXML
    private Button button_book;

// radio buttons has action listener.  radioCard, radioPaypal, radioVipps, radioGoogle
//temporary
    public void fillData(RentOutAd roa) {
    ad = roa;
    this.id = roa.getAutoIncrementId();
    this.owner = roa.getUser();
    this.vehicleId = roa.getVehicle().getId();

    label_model.setText(roa.getVehicle().getManufacturer());
    label_vehicleModel.setText(roa.getVehicle().getModel());


    }
    public void adFromDate(LocalDate localDate){
    fromDate = localDate;
    book_from.setText(String.valueOf(fromDate));
    }

    public void adToDate(LocalDate localDate){
    fromDate = localDate;
    book_to.setText(String.valueOf(fromDate));
    }

    public void fillAmount(BigDecimal s){
        RentOutAd roa = ad;
        Car c = (Car) ad.getVehicle();
        BigDecimal b = ad.getDailyCharge();


       total_Amount.setText(String.valueOf(s.multiply(b)));

    }



    public void radioCard(ActionEvent event){

    }
    public void radioPayPal(ActionEvent event) throws IOException {
        Main m = new Main();
        if(this.paymentToggleGroups.getSelectedToggle().equals(this.rb_paypal)){
            m.changeScene("PaymentPage.fxml");
        }


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
        //RentOutAd roa = (RentOutAd) RetrieveAdvertisementDB.retrieveFromId(id);


        System.out.println(owner.getFirstName());

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paymentToggleGroups = new ToggleGroup();
        this.rb_paypal.setToggleGroup(paymentToggleGroups);



    }
}
