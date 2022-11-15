package com.example.java_project;

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
import javafx.scene.layout.Pane;
import javafx.stage.*;
import no.hiof.groupproject.models.Advertisement;
import no.hiof.groupproject.models.RentOutAd;
import no.hiof.groupproject.models.UserProfile;
import no.hiof.groupproject.models.vehicle_types.Vehicle;
import no.hiof.groupproject.tools.db.RetrieveAdvertisementDB;
import no.hiof.groupproject.tools.db.RetrieveVehicleDB;
import no.hiof.groupproject.tools.filters.FilterAdvertisement;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.EventObject;
import java.util.Optional;
import java.util.ResourceBundle;
import java.time.temporal.ChronoUnit;

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
    private BigDecimal dailyChargebd;
   // ObservableList<Vehicle> vehicleObservableList = FXCollections.observableArrayList();
    ObservableList<Advertisement> adObservableList = FXCollections.observableArrayList();


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

    public void setVehicleListView(ListView<Advertisement> vehicleListView) {
        this.vehicleListView = vehicleListView;
    }

    public ListView<Advertisement> getVehicleListView() {
        return vehicleListView;
    }




    //Method to populatetheVehicleListView. Gets ad id from the Filter based on the
    public void populateListView(){
        RentOutAd roa = (RentOutAd) RetrieveAdvertisementDB.retrieveFromId(getAdId());
        //Vehicle v = RetrieveVehicleDB.retrieveFromId(roa.getVehicle().getId());
        adObservableList.addAll(roa);
        System.out.println(adObservableList);
        vehicleListView.getItems().addAll(adObservableList);


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
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();


        /*



        RentOutAd RentOutAd;
        controller.fillData(RentOutAd)vehicleListView.getSelectionModel().getSelectedItem()  ;

        loader.setController(controller);
        Parent layout;
        try {
            layout = loader.load();
            Scene scene = new Scene(layout);
            // this is the popup stage
            Stage popupStage = new Stage();
            // now
            controller.setStage(popupStage);
            if (this.main != null) {
                popupStage.initOwner(main.getStage());
            }
            popupStage.initModality(Modality.WINDOW_MODAL);
            popupStage.setScene(scene);
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "There was an error trying to load the popup fxml file.").show();
        }

         */


        /*
        DetailedAdViewController controller = new DetailedAdViewController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailedAdView.fxml"));
        loader.setController(controller);
        p.getContent().add(Parent)loader.load()

         */






    }
    /*

    public void showDetails() throws IOException {


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DetailedAdView.fxml"));
        // initializing the controller
        DetailedAdViewController popupController = new DetailedAdViewController();
        popupController.fillData((RentOutAd) vehicleListView.getSelectionModel().getSelectedItem());

        //loader.setController(popupController);
        Parent layout;
        try {
            layout = loader.load();
            Scene scene = new Scene(layout);
            // this is the popup stage
            Stage popupStage = new Stage();
            // now
            popupController.setStage(popupStage);
            if(this.main!=null) {
                popupStage.initOwner(main.getStage());
            }
            popupStage.initModality(Modality.WINDOW_MODAL);
            popupStage.setScene(scene);
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "There was an error trying to load the popup fxml file.").show();
        }



}

     */


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
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public void viewOwner(ActionEvent  event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Profile.fxml"));
        Parent pane = loader.load();

        Scene scene = new Scene(pane);

        //access the controller and call a method
        UserProfileController p = new UserProfileController();
        p.fillData((RentOutAd)vehicleListView.getSelectionModel().getSelectedItem());


        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();

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
