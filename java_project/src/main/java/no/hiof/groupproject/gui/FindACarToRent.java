package no.hiof.groupproject.gui;


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
import javafx.scene.layout.FlowPane;
import javafx.stage.*;

import no.hiof.groupproject.models.UserProfile;
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

    private  ListView<Advertisement> vehicleListView = new ListView<>();//change the name later

    @FXML
    private TextField tf_townName;
    @FXML
    private DatePicker fromDatePicker, toDatePicker;

    @FXML
    private Button searchButton, bookButton,selectAd ;
    @FXML
    private Button button_mainMenu;

    @FXML
    private Slider priceSlider ;
    @FXML
    private Label priceLabel;
    @FXML
    private Button bt_viewOwner;
    private double dailyPrice;

    @FXML
    private ChoiceBox<String>transmissionChoiceBox, engineChoiceBox, manuChoiceBox;
    @FXML
    private ChoiceBox<Integer> numSeatChoiceBox,
            yearChoiceBox;
    @FXML
    private FlowPane scenePane;
    Stage stage;
    private String[] gearTypeArr= {"automatic", "manual"};
    private String[] engineTypeArr={  "hybrid", "petrol","electric"};

    private String[] manuArray={  "bmw", "audi", "nissan", "honda", "aston Martin"};
    private  Integer[] numSeatArray = {2,3,4,5,6,7};
    private Integer[] yearsArray = {1963,1993, 1994,1999,2013};

    private BigDecimal dailyCharge;

    public static ObservableList<RentOutAd> adObservableList = FXCollections.observableArrayList();
    ArrayList<RentOutAd> adArrayList = new ArrayList<>();
    public static RentOutAd roa  ;
    String gearType, engineType, manuType = null;
    Integer numSeat, yearModel = null;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchButton.setOnAction(this::searchButtonPushed);
        selectAd.setOnAction(this::changeSceneToDetailedAdView);
        bookButton.setOnAction(this::changeSceneToBooking);
        bt_viewOwner.setOnAction(this::viewOwner);

        transmissionChoiceBox.getItems().addAll(gearTypeArr);
        transmissionChoiceBox.setOnAction(this::gearTypeChanged);

        engineChoiceBox.getItems().addAll(engineTypeArr);
        engineChoiceBox.setOnAction(this::getEngineType);

        manuChoiceBox.getItems().addAll(manuArray);
        manuChoiceBox.setOnAction(this::getManuType);

        numSeatChoiceBox.getItems().addAll(numSeatArray);
        numSeatChoiceBox.setOnAction(this::getNumSeats);

        yearChoiceBox.getItems().addAll(yearsArray);
        yearChoiceBox.setOnAction(this::getYearModel);




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
    public void getYearModel(ActionEvent event){
        yearModel = yearChoiceBox.getValue();
    }

    public LocalDate getFromDate(){
        return fromDatePicker.getValue();
    }
    public LocalDate getToDate(){
        return toDatePicker.getValue();
    }

    public void searchButtonPushed(ActionEvent event){

        String town = tf_townName.getText();
        ArrayList<Integer> filteredAds = FilterAdvertisement.filterToArrayListAdvertisementId(gearType, engineType,
                manuType, town, null, null, null,
                numSeat,null, null);
        for(int i : filteredAds){
            if(!filteredAds.isEmpty()){
                RentOutAd ad = (RentOutAd) RetrieveAdvertisementDB.retrieveFromId(i);
                adArrayList.add(ad);
                }}

        try{
            if(!adArrayList.isEmpty()){
                adObservableList.addAll(adArrayList);
                vehicleListView.getItems().addAll(adObservableList);

            }else{ Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                alert.setHeaderText("There are no available ads with that criteria.");
                alert.setResizable(false);
                alert.setContentText("Press Ok to search again or cancel to return to main page");
                Optional<ButtonType> result = alert.showAndWait();


                if(result.get() == ButtonType.OK){
                    stage = (Stage) scenePane.getScene().getWindow();
                    stage.close();
                    reload();
                }
                //oke button is pressed
                else if(result.get() == ButtonType.CANCEL){
                    stage = (Stage) scenePane.getScene().getWindow();
                    stage.close();
                }

            }}
        catch (Exception e){
            throw new RuntimeException(e);
        }}

    public void reload(){

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FilterCar.fxml"));
            Parent pane = loader.load();

            Scene scene = new Scene(pane);

            Stage window = new Stage();

            window.setScene(scene);
            window.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }}

    public void changeSceneToBooking(ActionEvent event) {
        checkDate(getFromDate(),getToDate());
    }





    //Changes scene to view details of the Ad

    public void changeSceneToDetailedAdView(ActionEvent event){

        try{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DetailedAdView.fxml"));
        Parent pane = loader.load();

        Scene scene = new Scene(pane);


        DetailedAdViewController controller = loader.getController();
        controller.fillData((RentOutAd) vehicleListView.getSelectionModel().getSelectedItem());



        Stage window = new Stage();

        window.setScene(scene);
        window.show();

    } catch (IOException e) {
            throw new RuntimeException(e);
        }}

    public void checkDate(LocalDate date1, LocalDate date2){
        RentOutAd ad = (RentOutAd) vehicleListView.getSelectionModel().getSelectedItem();

        if (!ad.checkIfDateIsAvailable(date1, date2)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setHeaderText("Oops! Looks like this has been booked already!");
            alert.setResizable(false);
            alert.setContentText("Press Ok to search again or cancel to return to main page");
            Optional<ButtonType> result = alert.showAndWait();


            if(result.get() == ButtonType.OK){
                stage = (Stage) scenePane.getScene().getWindow();
                stage.close();
                reload();
            }

            else if(result.get() == ButtonType.CANCEL){
                stage = (Stage) scenePane.getScene().getWindow();
                stage.close();
            }}

        else{
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setHeaderText("If you proceed to booking your search results will be lost");
            alert.setResizable(false);
            alert.setContentText("Press Ok to proceed to booking ");
            Optional<ButtonType> result = alert.showAndWait();


            if(result.get() == ButtonType.OK){
                stage = (Stage) scenePane.getScene().getWindow();
                stage.close();
                changeScene();
        }}


        }

    public RentOutAd getRoa(){
        RentOutAd ad =  (RentOutAd) vehicleListView.getSelectionModel().getSelectedItem();
        return ad;
    }
    public void changeScene(){
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
        }
    }

 public static UserProfile userProfile;
        public void viewOwner(ActionEvent  event)   {
         try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("ugh.fxml"));
                Parent pane = loader.load();

                Scene scene = new Scene(pane);


                OtherUserProfileView controller = loader.getController();
                controller.fillData((RentOutAd) vehicleListView.getSelectionModel().getSelectedItem());



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
