package com.example.java_project;

import com.example.java_project.BookingController;
import com.example.java_project.Controller.Profile.OtherUserProfileView;
import com.example.java_project.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.*;

import no.hiof.groupproject.models.advertisements.Advertisement;
import no.hiof.groupproject.models.advertisements.RentOutAd;
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

    private ListView<Advertisement> vehicleListView = new ListView<>();//change the name later

    @FXML
    private TextField tf_townName;
    @FXML
    private DatePicker fromDatePicker, toDatePicker;

    @FXML
    private Button searchButton, bookButton,selectAd ;
    @FXML
    private Button button_mainMenu;
    @FXML
    private RadioButton radioButton_manual,radioButton_automatic,
            radioButton_electric,radioButton_Hybrid,radioButton_Diesel;
    @FXML
    private Slider priceSlider ;
    @FXML
    private Label priceLabel;
    @FXML
    private Button bt_viewOwner;
    private double dailyPrice;
    private ToggleGroup transmissionToggleGroups, engineToggleGroup;
    private BigDecimal dailyChargebd;

    public static ObservableList<Advertisement> adObservableList = FXCollections.observableArrayList();
    public static RentOutAd roa ;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchButton.setOnAction(this::searchButtonPushed);
        selectAd.setOnAction(this::changeSceneToDetailedAdView);
        bookButton.setOnAction(this::changeSceneToBooking);
        bt_viewOwner.setOnAction(this::viewOwner);

        transmissionToggleGroups = new ToggleGroup();
        this.radioButton_manual.setToggleGroup(transmissionToggleGroups);
        this.radioButton_automatic.setToggleGroup(transmissionToggleGroups);

        engineToggleGroup = new ToggleGroup();
        this.radioButton_Diesel.setToggleGroup(engineToggleGroup);
        this.radioButton_electric.setToggleGroup(engineToggleGroup);
        this.radioButton_automatic.setToggleGroup(engineToggleGroup);

        button_mainMenu.setOnAction(this::goToMainMenu);;
        addStyle(button_mainMenu);
        addStyle(selectAd);
        addStyle(bookButton);
        addStyle(bt_viewOwner);
        


        priceSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                dailyPrice = (int) priceSlider.getValue();
                dailyChargebd = BigDecimal.valueOf(priceSlider.getValue());

                priceLabel.setText(String.valueOf(dailyPrice));

            }
        });
    }



    ArrayList<RentOutAd> adArrayList = new ArrayList<>();
    ArrayList<RentOutAd> adsAvailInThoseDates = new ArrayList<>();
    public void searchButtonPushed(ActionEvent event){

        String town = tf_townName.getText();
        ArrayList<Integer> filteredAds = FilterAdvertisement.filterToArrayListAdvertisementId(null, null, null,
                town, null, null, null,
                null,null, null);
        if(getFromDate() == null || getToDate()== null){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Select Date");
            alert.show();}
        else{
                try{
                    for(int i : filteredAds){
                        RentOutAd ad = (RentOutAd) RetrieveAdvertisementDB.retrieveFromId(i);
                        adArrayList.add(ad);}
                    if(!adArrayList.isEmpty()){
                        for(RentOutAd rentOutAd: adArrayList){
                            if(rentOutAd.checkIfDateIsAvailable(getFromDate(),getToDate())){
                                adsAvailInThoseDates.add(rentOutAd);
                                roa = adsAvailInThoseDates.get(0);
                                populateListView();
                                }
                            else if(adsAvailInThoseDates.isEmpty()){
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setContentText("No dates available");
                                    alert.show();}}}
                    else{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("No cars available at this locations");
                            alert.show();}
            }
                catch (Exception e){
                    throw new RuntimeException(e);
            }}}

    public String getGearType(){
        String gearType = null;
        if(radioButton_automatic.isSelected()){
            gearType = "automatic";
        }else if(radioButton_manual.isSelected()){
            gearType = "manual";
        }
        return gearType;
    }
    public String getEngineType(){
        String engineType = null;
        if(radioButton_Hybrid.isSelected()){
            engineType = "hybrid";

        }
        if(radioButton_Diesel.isSelected()){
            engineType = "petrol";
        }
        if(radioButton_electric.isSelected()){
            engineType = "electric";
        }
        return engineType;
    }


    public void addFilters(ActionEvent actionEvent){
        ArrayList<Integer> filteredAds = FilterAdvertisement.filterToArrayListAdvertisementId(getGearType(), getEngineType(), null,
                tf_townName.getText(), null, null, null,
                null,null, null);
        try {
            for(int i :filteredAds){
                RentOutAd ad = (RentOutAd) RetrieveAdvertisementDB.retrieveFromId(i);
                afterOtherFiltersAdded.add(ad);}
            if(!afterOtherFiltersAdded.isEmpty()){
                clearListViewAndRepopulate();


            }else {Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No dates available");
                alert.show();}}
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




    //Method to populatetheVehicleListView. Gets ad id from the Filter based on the
    public void populateListView(){
        adObservableList.addAll(adsAvailInThoseDates);
        System.out.println("lets" + adObservableList);
        vehicleListView.getItems().addAll(adObservableList);

    }

    ArrayList<RentOutAd> afterOtherFiltersAdded =new ArrayList<>();
    public void clearListViewAndRepopulate(){
        adObservableList.removeAll(adsAvailInThoseDates);
        adObservableList.addAll(afterOtherFiltersAdded);
        System.out.println("filter" + adObservableList);
        vehicleListView.getItems().addAll(adObservableList);

    }

    public LocalDate getFromDate(){
        return fromDatePicker.getValue();
    }
    //returns the startDate picked using the date picker
    public LocalDate getToDate(){
        return toDatePicker.getValue();
    }


    //Changes scene to view details of the Ad

    public void changeSceneToDetailedAdView(ActionEvent event){

        try{
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

    } catch (IOException e) {
            throw new RuntimeException(e);
        }}
        public void manualChecked(){

        }
    public void changeSceneToBooking(ActionEvent event) {
        try{
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

    } catch (IOException e) {
            throw new RuntimeException(e);
        }}


        public void viewOwner(ActionEvent  event)  {
        try{
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

    } catch (IOException e) {
            throw new RuntimeException(e);
        }}

            public void goToMainMenu(ActionEvent event) {
        Main main = new Main();
        try {
            main.changeScene("ToGoCar.fxml");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    public void addStyle(Button button) {
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color:  #f1c232; -fx-text-fill: white;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #f1c232; -fx-text-fill: black"));
    }
}
