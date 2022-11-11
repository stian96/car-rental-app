package com.example.java_project;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.*;
import no.hiof.groupproject.models.Advertisement;
import no.hiof.groupproject.models.RentOutAd;
import no.hiof.groupproject.models.vehicle_types.Vehicle;
import no.hiof.groupproject.tools.db.RetrieveAdvertisementDB;
import no.hiof.groupproject.tools.db.RetrieveAvailableWithinDB;
import no.hiof.groupproject.tools.filters.FilterAdvertisement;
import no.hiof.groupproject.tools.geocode.Location;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class ToGoCarPageController  {

    @FXML
    private Button button_registerCar;
    @FXML
    private Button button_FindCar;
    @FXML
    private Button button_Advertisement;
    @FXML
    private Button button_profile;
    @FXML
    private Button button_messages;
    @FXML
    private Button button_customerService;
    @FXML
    private Button button_logOut;
    @FXML
    private TextField tf_TownName;
    @FXML
    private DatePicker start_DatePicker;
    @FXML
    private DatePicker return_DatePicker;
    @FXML
    private Label noAvailableCarWarning;
   @FXML
    private ListView<Integer> vehicleList = new ListView<>() ;



    ObservableList<Integer> observableList = FXCollections.observableArrayList();


    public void FilterTown(){
        String town = tf_TownName.getText().trim().toLowerCase();
        FilterAdvertisement.filterToArrayListAdvertisement(null, null, null,
                town, null, null, null,
                null,null, null);

    }

    public void getStartDate(ActionEvent event){
        LocalDate fromDate = start_DatePicker.getValue();
        //String myFormattedDate = fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        noAvailableCarWarning.setText(String.valueOf(fromDate));
    }

    public void getReturnDate(ActionEvent event){
        LocalDate toDate = return_DatePicker.getValue();
        //String myFormattedDate = fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        noAvailableCarWarning.setText(String.valueOf(toDate));
    }

    public void initialize() {


    }

    //prints the ad id into the listview
    public void loadAds(){





                observableList.addAll(getAdId());
            System.out.println(observableList);
                vehicleList.getItems().addAll(observableList);

        }

    public Integer getAdId(){
        String town = tf_TownName.getText().trim().toLowerCase();

        for(Integer id : FilterAdvertisement.filterToArrayListAdvertisementId(null, null, null,
                town, null, null, null,
                null,null, null)){
            return id;
        }


    return getAdId();}






    public void FindBookCar(ActionEvent event) throws IOException{



        loadAds();
        dateSelection();


        }


   @FXML
    private void dateSelection(){
        if(start_DatePicker.getValue() == null || return_DatePicker.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(start_DatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            alert.show();
            return;
        }
        if(start_DatePicker.getValue().isBefore(LocalDate.now()) || return_DatePicker.getValue().isBefore(LocalDate.now())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select an actual date!");
            alert.show();
            return;
        }
        if(return_DatePicker.getValue().isBefore(start_DatePicker.getValue())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("End date is before start date!");
            alert.show();
            return;
        }

        LocalDate fromDate = start_DatePicker.getValue();

        LocalDate toDate = return_DatePicker.getValue();


       TreeMap<LocalDate, LocalDate> availableDates = new TreeMap<>();
       availableDates.put(fromDate,toDate);
       RentOutAd roa = (RentOutAd) RetrieveAdvertisementDB.retrieveFromId(getAdId());
       TreeMap<LocalDate, LocalDate> s = RetrieveAvailableWithinDB.retrieve(roa);
       roa.checkIfDateIsAvailable(fromDate,toDate);

       if(roa.availableWithinExistsInDb(fromDate,toDate)){

           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setContentText("not available");
           alert.show();

       }




    }





    /*

    public void FindBookCar(ActionEvent event) throws IOException{
        Main m = new Main();
        if(event.getSource() == button_FindCar){
            if(getDateFrom(event).equals("Error")){
                try {
                    m.changeScene("FilterCar.fxml");
                } catch (IOException e){
                    System.out.println(e.getMessage());

                }
            }
        }

    }

    public String getDateFrom(ActionEvent event) throws IOException{
        String status = "success";
        LocalDate fromDate = start_DatePicker.getValue();
        if(event.getSource() == start_DatePicker ){
            ;
        }


        return status;
    }

     */

/*
    public String findACar() throws IOException {
        String status = "success";
        String town = tf_TownName.getText().trim();

        RentOutAd ad = (RentOutAd) RetrieveAdvertisementDB.retrieveFromTown(tf_TownName.getText());
        if(ad != null && ad.getTown().equals(town)){
            setNoAvailableCarWarning("does not exist");

            status = "Error";


        }

        return status;
    }

 */



    private int ConvertIntoNumeric(String xVal)
    {
        try
        {
            return Integer.parseInt(xVal);
        }
        catch(Exception ex)
        {
            return 0;
        }
    }


        public void setNoAvailableCarWarning(String text){
            noAvailableCarWarning.setText(text);
        }

        public void userRegisterCar(ActionEvent event) throws IOException {
            Main m = new Main();
            m.changeScene("RegisterACar.fxml");
        }
        public void carAdvertisement(ActionEvent event)throws IOException{
            Main m = new Main();
            m.changeScene("Advertisement.fxml");
        }


        public void userProfile(ActionEvent event) throws IOException{
            Main m = new Main();
            m.changeScene("Profile.fxml");
        }

        public void message_menu(ActionEvent event) throws IOException{
            Main m = new Main();
            m.changeScene("LogIn.fxml");
        }
        public void customerService(ActionEvent event) throws IOException{
            Main m = new Main();
            m.changeScene("LogIn.fxml");
        }
        public void userLogOut(ActionEvent event) throws IOException{
            Main m = new Main();
            m.changeScene("LogIn.fxml");
        }



/*

    public void FindBookCar(ActionEvent event) throws IOException{
        Main m = new Main();
        if(event.getSource() == button_FindCar){
            if(findACar().equals("Error")){
                try {
                    m.changeScene("FilterCar.fxml");
                } catch (IOException e){
                    System.out.println(e.getMessage());

                }
            }
        }

    }

    public String findACar() throws IOException {
        String status = "success";
        String town = tf_TownName.getText().trim();

        RentOutAd ad = (RentOutAd) RetrieveAdvertisementDB.retrieveFromId(ConvertIntoNumeric(town));
        if(!ad.existsInDb()){
            setNoAvailableCarWarning("does not exist");

            status = "success";


        }else{status = "Error";}

        return status;
    }
    private int ConvertIntoNumeric(String xVal)
    {
        try
        {
            return Integer.parseInt(xVal);
        }
        catch(Exception ex)
        {
            return 0;
        }
    }

    Vehicle[] cars = FilterAdvertisement.filterToArrayListVehicle(null, null, null,
                tf_TownName.getText(), null,
                null, null, null, null, null).toArray(new Vehicle[0]);

        vehicleList.setItems(FXCollections.observableArrayList(cars));
        vehicleList.setCellFactory(lv -> {
            ListCell<Vehicle> cell = new ListCell<>();

            ContextMenu contextMenu = new ContextMenu();

            MenuItem viewItem = new MenuItem();
            viewItem.textProperty().bind(Bindings.format("View photos"));
            viewItem.setOnAction(event -> {
                Vehicle item = cell.getItem();

            });
            contextMenu.getItems().add(viewItem);

            cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
                if (isNowEmpty) {
                    cell.setContextMenu(null);
                } else {
                    cell.textProperty().bind(cell.itemProperty().asString());
                    cell.setContextMenu(contextMenu);
                }
            });
            return cell;
        });

 */




}