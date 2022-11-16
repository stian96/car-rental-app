package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.Advertisement;
import no.hiof.groupproject.models.Booking;
import no.hiof.groupproject.models.RentOutAd;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.vehicle_types.Vehicle;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Objects;
import java.util.TreeMap;

/*
Returns a specific Advertisement in the database based on the Advertisement id
 */
public class RetrieveAdvertisementDB {

    public static Advertisement retrieveFromId(int id) {

        String sql = "SELECT * FROM advertisements WHERE advertisements_id = " + id;

        RentOutAd advertisement = null;

        try (Connection conn = ConnectDB.connectReadOnly();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            User user = RetrieveUserDB.retrieveFromId(queryResult.getInt("user_fk"));
            LocalDate dateCreated = LocalDate.parse(queryResult.getString("dateCreated"));
            LocalDate dateLastChanged = LocalDate.parse(queryResult.getString("dateLastChanged"));
            String subClass = queryResult.getString("advertisementSubclass");

            //the following code creates a RentOutAd subclass
            if (Objects.equals(subClass, "rentoutad")) {
                Vehicle vehicle = RetrieveVehicleDB.retrieveFromId(queryResult.getInt("vehicle_fk"));
                Currency cur = Currency.getInstance(queryResult.getString("cur"));
                BigDecimal dailyCharge =
                        BigDecimal.valueOf(Double.parseDouble(queryResult.getString("dailyCharge")));
                BigDecimal chargePerTwentyKm =
                        BigDecimal.valueOf(Double.parseDouble(queryResult.getString("chargePerTwentyKm")));
                //only town is required because the Location makes use of an API to generate the fylke, postnr and land
                String town = queryResult.getString("town");

                advertisement = new RentOutAd(user, vehicle, dailyCharge, chargePerTwentyKm, town);

                TreeMap<LocalDate, LocalDate> availableWithin = RetrieveAvailableWithinDB.retrieve(advertisement);
                ArrayList<Booking> confirmedBookings = RetrieveBookingsDB.retrieve(advertisement);

                advertisement.setDateCreated(dateCreated);
                advertisement.setDateLastChanged(dateLastChanged);
                advertisement.setAvailableWithin(availableWithin);
                advertisement.setConfirmedBookings(confirmedBookings);
            }

            return advertisement;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return advertisement;
    }

    /*public static Advertisement retrieveFromIdWithPriorConnection(int id, Connection conn) throws SQLException {

        String sql = "SELECT * FROM advertisements WHERE advertisements_id = " + id;

        RentOutAd advertisement = null;


        PreparedStatement str = conn.prepareStatement(sql);

        ResultSet queryResult = str.executeQuery();
        User user = RetrieveUserDB.retrieveFromId(queryResult.getInt("user_fk"));
        LocalDate dateCreated = LocalDate.parse(queryResult.getString("dateCreated"));
        LocalDate dateLastChanged = LocalDate.parse(queryResult.getString("dateLastChanged"));
        String subClass = queryResult.getString("advertisementSubclass");

        //the following code creates a RentOutAd subclass
        if (Objects.equals(subClass, "rentoutad")) {
            Vehicle vehicle = RetrieveVehicleDB.retrieveFromId(queryResult.getInt("vehicle_fk"));
            Currency cur = Currency.getInstance(queryResult.getString("cur"));
            BigDecimal dailyCharge =
                    BigDecimal.valueOf(Double.parseDouble(queryResult.getString("dailyCharge")));
            BigDecimal chargePerTwentyKm =
                    BigDecimal.valueOf(Double.parseDouble(queryResult.getString("chargePerTwentyKm")));
            //only town is required because the Location makes use of an API to generate the fylke, postnr and land
            String town = queryResult.getString("town");

            advertisement = new RentOutAd(user, vehicle, dailyCharge, chargePerTwentyKm, town);

            TreeMap<LocalDate, LocalDate> availableWithin = RetrieveAvailableWithinDB.retrieve(advertisement);
            ArrayList<Booking> confirmedBookings = RetrieveBookingsDB.retrieve(advertisement);

            advertisement.setDateCreated(dateCreated);
            advertisement.setDateLastChanged(dateLastChanged);
            advertisement.setAvailableWithin(availableWithin);
            advertisement.setConfirmedBookings(confirmedBookings);
        }

        return advertisement;
    }*/

}