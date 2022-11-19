package com.example.java_project;

import com.example.java_project.Controller.LogInController;
import com.example.java_project.Controller.PaymentPageController;
import com.example.java_project.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import no.hiof.groupproject.models.Booking;
import no.hiof.groupproject.models.advertisements.RentOutAd;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.payment_methods.Payment;
import no.hiof.groupproject.models.vehicles.four_wheeled_vehicles.Car;
import no.hiof.groupproject.models.vehicles.Vehicle;
import no.hiof.groupproject.tools.db.InsertBookingDB;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static java.time.temporal.ChronoUnit.DAYS;

public class BookingController implements Initializable {
    /** Things to fix here: The payment must be added before booking button pressed**/

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
    Vehicle vehicle;
    int vehicleId;
    Payment pay;

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
    this.vehicle= roa.getVehicle();

    label_model.setText(roa.getVehicle().getManufacturer());
    label_vehicleModel.setText(roa.getVehicle().getModel());


    }
    public void adFromDate(LocalDate localDate){
    fromDate = localDate;
    book_from.setText(String.valueOf(fromDate));
    }

    public void adToDate(LocalDate localDate){
    toDate = localDate;
    book_to.setText(String.valueOf(toDate));
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
        m.changeScene("FilterCar.fxml");
    }

    public void btn_book(ActionEvent event)throws IOException {

       User owner = this.owner;
        User renter  = LogInController.user;
        LocalDate fromDate = this.fromDate;
        LocalDate toDate = this.toDate;
        Vehicle vehicle = this.vehicle;
        Payment payment = PaymentPageController.payment;
        Booking book = new Booking(owner,renter,fromDate,toDate,payment,vehicle);
        try{
            if(!book.existsInDb()){
                Booking b = InsertBookingDB.insert(book);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paymentToggleGroups = new ToggleGroup();
        this.rb_paypal.setToggleGroup(paymentToggleGroups);






    }}

