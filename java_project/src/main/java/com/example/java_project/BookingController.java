package com.example.java_project;

import com.example.java_project.Controller.LogInController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import no.hiof.groupproject.models.Booking;
import no.hiof.groupproject.models.advertisements.RentOutAd;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.payment_methods.GooglePay;
import no.hiof.groupproject.models.payment_methods.Payment;
import no.hiof.groupproject.models.payment_methods.Paypal;
import no.hiof.groupproject.models.vehicles.four_wheeled_vehicles.Car;
import no.hiof.groupproject.models.vehicles.Vehicle;
import no.hiof.groupproject.tools.db.InsertBookingDB;
import no.hiof.groupproject.tools.db.RetrievePaymentDB;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.time.temporal.ChronoUnit.DAYS;

public class BookingController implements Initializable {
    /**
     * Things to fix here: The payment must be added before booking button pressed
     **/

    public static long
    countDaysBetween(LocalDate dateFrom, LocalDate dateTo) {
        return DAYS.between(dateFrom, dateTo);
    }

    private RentOutAd ad;
    private Car v;
    LocalDate fromDate, toDate;
    @FXML
    private AnchorPane scenePane;
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

    int id;

    User owner;
    Vehicle vehicle;
    int vehicleId;
    static Payment pay;



    @FXML
    private Button button_back;
    @FXML
    private Button button_book;
    @FXML
    private ChoiceBox<String> paymentMethodChoiceBox;

    Stage stage;

    public String[] paymentMethodsArray = {"card", "paypal", "vipps", "googlepay"};

    public void fillData(RentOutAd roa) {
        ad = roa;
        this.id = roa.getAutoIncrementId();
        this.vehicle = roa.getVehicle();
        this.owner = roa.getUser();


        label_model.setText(roa.getVehicle().getManufacturer());
        label_vehicleModel.setText(roa.getVehicle().getModel());


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        paymentMethodChoiceBox.getItems().addAll(paymentMethodsArray);
        paymentMethodChoiceBox.setOnAction(this::choosePaymentMethod);



    }

    public void choosePaymentMethod(ActionEvent event) {


        if (Objects.equals(paymentMethodChoiceBox.getValue(), "paypal")) {

            changeToPayPalScene();


        } else if (Objects.equals(paymentMethodChoiceBox.getValue(), "googlepay")) {
            changeToGooglePayScene();

        }else if(Objects.equals(paymentMethodChoiceBox.getValue(), "googlepay")){

        }
    }



    public void changeToPayPalScene() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("PaymentPage.fxml"));
            Parent pane = loader.load();

            Scene scene = new Scene(pane);

            Stage window = new Stage();

            window.setScene(scene);
            window.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void changeToGooglePayScene() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("GooglePay.fxml"));
            Parent pane = loader.load();

            Scene scene = new Scene(pane);

            Stage window = new Stage();

            window.setScene(scene);
            window.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void changeToVippsScene() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("VippsPayment.fxml"));
            Parent pane = loader.load();

            Scene scene = new Scene(pane);

            Stage window = new Stage();

            window.setScene(scene);
            window.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void adFromDate(LocalDate localDate) {
        fromDate = localDate;
        book_from.setText(String.valueOf(fromDate));
    }

    public void adToDate(LocalDate localDate) {
        toDate = localDate;
        book_to.setText(String.valueOf(toDate));
    }

    public void fillAmount(BigDecimal s) {
        RentOutAd roa = ad;
        Car c = (Car) ad.getVehicle();
        BigDecimal b = ad.getDailyCharge();


        total_Amount.setText(String.valueOf(s.multiply(b)));

    }


    public void btn_back(ActionEvent event) throws IOException {

    }

    public void getAgreement() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("TermsAndCondition.fxml"));
            Parent pane = loader.load();

            Scene scene = new Scene(pane);

            Stage window = new Stage();

            window.setScene(scene);
            window.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void btn_book(ActionEvent event) throws IOException {

        User owner = this.owner;
        User renter  = LogInController.user;
        LocalDate fromDate = this.fromDate;
        LocalDate toDate = this.toDate;
        Vehicle vehicle = this.vehicle;

        Booking book = new Booking(renter,owner,fromDate,toDate,pay,vehicle);
            try {
                if (!book.existsInDb()) {
                    InsertBookingDB.insert(book);
                    getAgreement();
                    stage = (Stage) scenePane.getScene().getWindow();
                    stage.close();

                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Your booking is confirmed ");
                    alert.show();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }


    }


