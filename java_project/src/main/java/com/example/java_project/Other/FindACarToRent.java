package com.example.java_project.Other;

import com.example.java_project.Controller.BookingController;
import com.example.java_project.Controller.DetailedAdViewController;
import com.example.java_project.Controller.Profile.OtherUserProfileView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.*;
import no.hiof.groupproject.models.Advertisement;
import no.hiof.groupproject.models.RentOutAd;
import no.hiof.groupproject.models.vehicle_types.Vehicle;
import no.hiof.groupproject.tools.db.RetrieveAdvertisementDB;
import no.hiof.groupproject.tools.filters.FilterAdvertisement;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class FindACarToRent implements Initializable  {
    //method to get the number of days for booking
    public static long countDaysBetween(LocalDate dateFrom, LocalDate dateTo){
        return DAYS.between(dateFrom,dateTo);
    }
    @FXML
    private TableView<Vehicle> tableViewVehicle;
    @FXML
    //private ListView<Vehicle> vehicleListView = new ListView<>();
    private ListView<Advertisement> vehicleListView = new ListView<>();//change the name later
   //rivate ListView<HashMap> vehicleListView = new ListView<>();
    @FXML
    private TextField tf_townName;
    @FXML
    private DatePicker fromDatePicker, toDatePicker;

    @FXML
    private Button searchButton, bookButton,selectAd ;
    @FXML
    private RadioButton radioButton_manual,radioButton_automatic,
            radioButton_electric,radioButton_Hybrid,radioButton_Diesel;
    @FXML
    private Slider priceSlider ;
    @FXML
    private Label priceLabel;
    private double dailyPrice;
    private ToggleGroup transmissionToggleGroups, engineToggleGroup;
    private BigDecimal dailyChargebd;
   // ObservableList<Vehicle> vehicleObservableList = FXCollections.observableArrayList();
    ObservableList<Advertisement> adObservableList = FXCollections.observableArrayList();

    ArrayList<Integer> filteredAds = FilterAdvertisement.filterToArrayListAdvertisementId(null, null, null,
            null, null, null, null,
            null,null, null);


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


    public void addFilters(){

    }
    public static RentOutAd roa;

    //Method to populatetheVehicleListView. Gets ad id from the Filter based on the
    public void populateListView(){
        RentOutAd ad = (RentOutAd) RetrieveAdvertisementDB.retrieveFromId(getAdId());
        roa =ad;

        //Vehicle v = RetrieveVehicleDB.retrieveFromId(roa.getVehicle().getId());
        adObservableList.addAll(ad);
        System.out.println(adObservableList);
        vehicleListView.getItems().addAll(adObservableList);




    }
    public void radioButtonChanged() {

        if(this.transmissionToggleGroups.getSelectedToggle().equals(this.radioButton_manual)){



        }


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
            alert.setContentText("Select a date");
            alert.show();
            status = "error";}
        else {
            try {
                RentOutAd roa = (RentOutAd) RetrieveAdvertisementDB.retrieveFromId(getAdId());
                if(!roa.checkIfDateIsAvailable(getFromDate(),getToDate())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("No cars are available for those dates");
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
    //Changes scene to view details of the Ad
    public void changeSceneToDetailedAdView(ActionEvent event) throws IOException, IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DetailedAdView.fxml"));
        Parent pane = loader.load();

        Scene scene = new Scene(pane);

        //access the controller and call a method
        DetailedAdViewController controller = loader.getController();
        controller.fillData((RentOutAd) vehicleListView.getSelectionModel().getSelectedItem());


        //This line gets the Stage information
        Stage window = new Stage();

        window.setScene(scene);
        window.show();


    }





    public void manualChecked(){

    }


    public void changeSceneToBooking(ActionEvent event) throws IOException, IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Booking.fxml"));
        Parent pane = loader.load();

        Scene scene = new Scene(pane);

        //access the controller and call a method
        BookingController booking = loader.getController();
        booking.fillData((RentOutAd)vehicleListView.getSelectionModel().getSelectedItem());
        booking.adFromDate(getFromDate());
        booking.adToDate(getToDate());
        booking.fillAmount(BigDecimal.valueOf(countDaysBetween(getFromDate(),getToDate())));


        //This line gets the Stage information
        Stage window = new Stage();

        window.setScene(scene);
        window.show();
    }

    public void viewOwner(ActionEvent  event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("OtherUserProfileView.fxml"));
        Parent pane = loader.load();

        Scene scene = new Scene(pane);

        //access the controller and call a method
        OtherUserProfileView p = new OtherUserProfileView();
       // p.fillData((RentOutAd)vehicleListView.getSelectionModel().getSelectedItem());


        //This line gets the Stage information
        Stage window = new Stage();

        window.setScene(scene);
        window.show();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
         transmissionToggleGroups = new ToggleGroup();
        this.radioButton_manual.setToggleGroup(transmissionToggleGroups);
        this.radioButton_automatic.setToggleGroup(transmissionToggleGroups);

        engineToggleGroup = new ToggleGroup();
        this.radioButton_Diesel.setToggleGroup(engineToggleGroup);
        this.radioButton_electric.setToggleGroup(engineToggleGroup);
        this.radioButton_automatic.setToggleGroup(engineToggleGroup);




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
