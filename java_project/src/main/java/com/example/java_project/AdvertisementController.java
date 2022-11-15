package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import no.hiof.groupproject.tools.db.ConnectDB;
import no.hiof.groupproject.tools.filters.FilterAdvertisement;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AdvertisementController implements Initializable {


    public TextField regNumberField;
    public Label regNumberLabel;
    public TextField dailyChargeField;
    public Label dailyChargeLabel;
    public RadioButton nokRadioButton;
    public RadioButton euroRadioButton;
    public Label currencyLabel;
    public DatePicker availableFrom;
    public DatePicker availableTo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<Integer, String> advertisementInfo = getAdvertisementInfo();

        for (Map.Entry<Integer, String> set : advertisementInfo.entrySet()) {
        }

    }

    public void searchAdvertisement(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("ToGoCar.fxml");
    }

    public void ExitAds(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("ToGoCar.fxml");
    }


    public HashMap<Integer, String> getAdvertisementInfo() {
        HashMap<Integer, String> addMap = new HashMap<>();
        ArrayList<Integer> thingWithInts = FilterAdvertisement.filterToArrayListAdvertisementId(null,
                null, null, null, null, null,
                null, null, null, null);

        for (int i : thingWithInts) {
            String sql = "SELECT * FROM advertisements " +
                    "INNER JOIN vehicles ON vehicle_fk = vehicles_id " +
                    "WHERE advertisements_id = " + i;

            String string = null;
            try (Connection conn = ConnectDB.connect();
                 PreparedStatement str = conn.prepareStatement(sql)) {

                ResultSet queryResult = str.executeQuery();

                string = queryResult.getString("modelYear") + " " +
                        queryResult.getString("manufacturer") + " " +
                        queryResult.getString("model") + " - " +
                        queryResult.getString("town");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            addMap.put(i, string);
        }
        return addMap;
    }

}
