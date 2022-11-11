package com.example.java_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import no.hiof.groupproject.models.RentOutAd;
import no.hiof.groupproject.models.vehicle_types.Vehicle;
import no.hiof.groupproject.tools.db.RetrieveAdvertisementDB;
import no.hiof.groupproject.tools.db.RetrieveVehicleDB;

import java.net.URL;
import java.util.ResourceBundle;

public class FilterCarController implements Initializable {
    @FXML
    private TableView<Vehicle> tableViewVehicle;
    @FXML
    private ListView<Vehicle> vehicleListView = new ListView<>();

    ObservableList<Vehicle> vehicleObservableList = FXCollections.observableArrayList();

    ToGoCarPageController t = new ToGoCarPageController();

    public ListView<Vehicle> getVehicleListView() {
        return vehicleListView;
    }

    public void setVehicleListView(ListView<Vehicle> vehicleListView) {
        this.vehicleListView = vehicleListView;
    }

    public void populateListView(String s){
        RentOutAd roa = (RentOutAd) RetrieveAdvertisementDB.retrieveFromId(t.getAdId());
        Vehicle v = RetrieveVehicleDB.retrieveFromId(roa.getVehicle().getId());
        vehicleObservableList.addAll(v);
        System.out.println(vehicleObservableList);
        vehicleListView.getItems().addAll(vehicleObservableList);


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ToGoCarPageController t = new ToGoCarPageController();
        t.populateListView();

    }
}
