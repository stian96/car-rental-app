package com.example.java_project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import no.hiof.groupproject.models.Advertisement;
import no.hiof.groupproject.models.RentOutAd;
import no.hiof.groupproject.models.vehicle_types.Vehicle;

import java.time.LocalDate;

public class DetailedAdViewController {
    private RentOutAd selectedAd;
    private LocalDate fromDate;


   @FXML private Label vehicleNameLabel, selectedFromDate;
   @FXML private TextField tf_modelName;

   public void fillData(RentOutAd roa){
       selectedAd = roa;
       tf_modelName.setText(roa.getVehicle().getModel());
   }

   public void adFromdate(LocalDate localDate){
       fromDate = localDate;
       selectedFromDate.setText(String.valueOf(fromDate));


   }

}
