package com.example.java_project;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import no.hiof.groupproject.models.RentOutAd;
import no.hiof.groupproject.models.vehicle_types.Vehicle;
import no.hiof.groupproject.tools.db.RetrieveAdvertisementDB;
import no.hiof.groupproject.tools.db.RetrieveVehicleDB;
import no.hiof.groupproject.tools.filters.FilterAdvertisement;

import java.math.BigDecimal;
import java.math.MathContext;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FilterCarController implements Initializable {
    @FXML
    private TableView<Vehicle> tableViewVehicle;
    @FXML
    private ListView<Vehicle> vehicleListView = new ListView<>();
    @FXML
    private TextField tf_townName;
    @FXML
    private DatePicker fromDatePicker, toDatePicker;

    @FXML
    private Button searchButton;
    @FXML
    private RadioButton radioButton_manual,radioButton_automatic,
            radioButton_electric,radioButton_Hybrid,radioButton_Diesel;
    @FXML
    private Slider priceSlider ;
    @FXML
    private Label priceLabel;
    private double dailyPrice;
    private BigDecimal dailyChargebd;
    ObservableList<Vehicle> vehicleObservableList = FXCollections.observableArrayList();

    public FilterCarController() {
    }

    //mathod to find the ad id using filter based on town name
    public Integer getAdId(){
        String town = tf_townName.getText().trim().toLowerCase();

        for(Integer id : FilterAdvertisement.filterToArrayListAdvertisementId(null, null, null,
                town, null, null, null,
                null,null, null)){
            return id;
        }
        return getAdId();
    }

    public ListView<Vehicle> getVehicleListView() {
        return vehicleListView;
    }

    public void setVehicleListView(ListView<Vehicle> vehicleListView) {
        this.vehicleListView = vehicleListView;
    }

    //Method to populatetheVehicleListView. Gets ad id from the Filter based on the
    public void populateListView(){
        RentOutAd roa = (RentOutAd) RetrieveAdvertisementDB.retrieveFromId(getAdId());
        Vehicle v = RetrieveVehicleDB.retrieveFromId(roa.getVehicle().getId());
        vehicleObservableList.addAll(v);
        System.out.println(vehicleObservableList);
        vehicleListView.getItems().addAll(vehicleObservableList);


    }

    public LocalDate getFromDate(){

        return fromDatePicker.getValue();
    }
    //returns the startDate picked using the date picker
    public LocalDate getToDate(){

        return toDatePicker.getValue();


    }
    public String findAnAvailableCar(){
        String status = "success";
        if(getFromDate() == null || getToDate()== null){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Select Date");
            alert.show();
            status = "error";}
        else {
            try {
                RentOutAd roa = (RentOutAd) RetrieveAdvertisementDB.retrieveFromId(getAdId());
                if(!roa.checkIfDateIsAvailable(getFromDate(),getToDate())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("No cars available for those dates");
                    alert.show();
                    status = "error";}


            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        return status;

    }
    // Once search button is pressed adds vehicle in the listview
    public void searchButtonPushed(ActionEvent event){
        if(event.getSource() == searchButton){
            if(findAnAvailableCar().equals("success")){
                populateListView();
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        priceSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                dailyPrice = (int) priceSlider.getValue();
                dailyChargebd = BigDecimal.valueOf(priceSlider.getValue());

                priceLabel.setText(String.valueOf(dailyPrice));

            }
        });





    }
}
