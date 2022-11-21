package com.example.java_project;

import com.example.java_project.BookingController;
import com.example.java_project.Controller.Profile.OtherUserProfileView;
import com.example.java_project.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
    @FXML
    private ToggleGroup transmissionToggleGroups, engineToggleGroup;
    @FXML
    private ChoiceBox<String>transmissionChoiceBox, engineChoiceBox, manuChoiceBox;
    @FXML
    private ChoiceBox<Integer> numSeatChoiceBox;
    private String[] gearTypeArr= {"automatic", "manual"};
    private String[] engineTypeArr={  "hybrid", "petrol","electric"};

    private String[] manuArray={  "bmw", "audi", "nissan", "honda", "aston Martin"};
    private  Integer[] numSeatArray = {2,3,4,5,6,7};

    private BigDecimal dailyCharge;

    public static ObservableList<Advertisement> adObservableList = FXCollections.observableArrayList();
    public static RentOutAd roa ;
    String gearType, engineType, manuType = null;
    Integer numSeat = null;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchButton.setOnAction(this::searchButtonPushed);
        selectAd.setOnAction(this::changeSceneToDetailedAdView);
        bookButton.setOnAction(this::changeSceneToBooking);
        bt_viewOwner.setOnAction(this::viewOwner);

        //yearChoiceBox.getItems().addAll("1950","1999");
        //yearChoiceBox.setValue("1999");

        transmissionChoiceBox.getItems().addAll(gearTypeArr);
        transmissionChoiceBox.setOnAction(this::gearTypeChanged);

        engineChoiceBox.getItems().addAll(engineTypeArr);
        engineChoiceBox.setOnAction(this::getEngineType);

        manuChoiceBox.getItems().addAll(manuArray);
        manuChoiceBox.setOnAction(this::getManuType);

        numSeatChoiceBox.getItems().addAll(numSeatArray);
        numSeatChoiceBox.setOnAction(this::getNumSeats);









        button_mainMenu.setOnAction(this::goToMainMenu);;
        addStyle(button_mainMenu);
        addStyle(selectAd);
        addStyle(bookButton);
        addStyle(bt_viewOwner);
        


        priceSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                dailyPrice = (int) priceSlider.getValue();
                dailyCharge = BigDecimal.valueOf(priceSlider.getValue());

                priceLabel.setText(String.valueOf(dailyPrice));

            }
        });
    }




    ArrayList<RentOutAd> adArrayList = new ArrayList<>();
    ArrayList<RentOutAd> adsAvailInThoseDates = new ArrayList<>();
    public void searchButtonPushed(ActionEvent event){

        String town = tf_townName.getText();
        ArrayList<Integer> filteredAds = FilterAdvertisement.filterToArrayListAdvertisementId(gearType, engineType,
                manuType, town, null, null, null,
                numSeat,null, null);

                try{
                    for(int i : filteredAds){
                        RentOutAd ad = (RentOutAd) RetrieveAdvertisementDB.retrieveFromId(i);
                        adArrayList.add(ad);
                    roa = ad;}
                populateListView();}

                catch (Exception e){
                    throw new RuntimeException(e);
            }}

    @FXML
    private void gearTypeChanged(ActionEvent event){

       gearType = transmissionChoiceBox.getValue();

    }


    public void getEngineType(ActionEvent event){

        engineType = engineChoiceBox.getValue();

    }

    public void getManuType(ActionEvent event){
        manuType = manuChoiceBox.getValue();
    }

    public void getNumSeats(ActionEvent event){
        numSeat = numSeatChoiceBox.getValue();
    }

    public void addFilters(ActionEvent actionEvent){
        ArrayList<Integer> filteredAds = FilterAdvertisement.filterToArrayListAdvertisementId(gearType, engineType, null,
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
        adObservableList.addAll(adArrayList);
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
