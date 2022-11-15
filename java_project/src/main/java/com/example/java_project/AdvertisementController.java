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

    @FXML
    private ChoiceBox<String> advertisementBox;
    @FXML
    private Label noAvailableCarWarning;
    @FXML
    private RadioButton rd_Nok;
    @FXML
    private RadioButton rd_Euro;
    @FXML
    private TextField tf_dailyCharge;
    @FXML
    private TextField tf_KMpHour;
    @FXML
    private DatePicker dp_To;
    @FXML
    private DatePicker dp_From;
    @FXML
    private Button button_SearchAds;
    @FXML
    private Button button_Exit;

    @FXML
    private Button button_Save;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<Integer, String> advertisementInfo = getAdvertisementInfo();

        for (Map.Entry<Integer, String> set : advertisementInfo.entrySet()) {
            advertisementBox.getItems().add(set.getValue());
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
