package com.example.java_project;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.sql.Date;
import java.time.LocalDate;
import java.util.TreeMap;

public class ToGoCarPageController {

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
    private ListView<Vehicle> vehicleList;


/*    @FXML
    private void FindBookCar() throws IOException {
        if(start_DatePicker.getValue() == null || return_DatePicker.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a date!");
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




    }

 */
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

    public void FindBookCar(ActionEvent event) throws IOException{

        String town = tf_TownName.getText().trim().toLowerCase();

        RentOutAd ad = (RentOutAd) RetrieveAdvertisementDB.retrieveFromTown(tf_TownName.getText());
        if(ad != null && !ad.getTown().equals(town)) {
            noAvailableCarWarning.setText("doesnt");
        }else{noAvailableCarWarning.setText("exist");



        }}
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