package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.Advertisement;
import no.hiof.groupproject.models.Booking;
import no.hiof.groupproject.models.RentOutAd;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.vehicle_types.Vehicle;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Objects;
import java.util.TreeMap;

/*
Returns an ArrayList of Advertisements or Advertisement id based off a User id.
Useful for allowing a single UserProfile to have multiple vehicles
 */
public class RetrieveAdvertisementsDB {

    public static ArrayList<Advertisement> retrieveAdvertisementObjectFromId(int id) {

        String sql = "SELECT * FROM advertisements WHERE user_fk = " + id;

        ArrayList<Advertisement> returnedAdvertisements = new ArrayList<>();

        try (Connection conn = ConnectDB.connect();
             Statement str = conn.createStatement();
             ResultSet rs = str.executeQuery(sql)) {



            //loops through rows in the sql SELECT statement
            while (rs.next()) {

                Advertisement advertisement =  RetrieveAdvertisementDB.
                        retrieveFromIdWithPriorConnection(rs.getInt("advertisements_id"), conn);
                returnedAdvertisements.add(advertisement);
            }

            return returnedAdvertisements;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return returnedAdvertisements;

    }

    public static ArrayList<Advertisement> retrieveAdvertisementObjectFromIdWithPriorConnection(int id, Connection conn) throws SQLException {

        String sql = "SELECT * FROM advertisements WHERE user_fk = " + id;

        ArrayList<Advertisement> returnedAdvertisements = new ArrayList<>();

        Statement str = conn.createStatement();
        ResultSet rs = str.executeQuery(sql);

        //loops through rows in the sql SELECT statement
        while (rs.next()) {

            Advertisement advertisement =  RetrieveAdvertisementDB.retrieveFromIdWithPriorConnection(rs.getInt("advertisements_id"), conn);
            returnedAdvertisements.add(advertisement);
        }


        return returnedAdvertisements;
    }

    public static ArrayList<Integer> retrieveAdvertisementIdFromId(int id) {

        String sql = "SELECT * FROM advertisements WHERE user_fk = " + id;

        ArrayList<Integer> returnedAdvertisements = new ArrayList<>();


        try (Connection conn = ConnectDB.connect();
             Statement str = conn.createStatement();
             ResultSet rs = str.executeQuery(sql)) {



            //loops through rows in the sql SELECT statement
            while (rs.next()) {

                int advertisementId = rs.getInt("advertisements_id");
                returnedAdvertisements.add(advertisementId);
            }

            return returnedAdvertisements;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return returnedAdvertisements;

    }

    public static ArrayList<Integer> retrieveAdvertisementIdFromIdWithPriorConnection(int id, Connection conn) throws SQLException {

        String sql = "SELECT * FROM advertisements WHERE user_fk = " + id;

        ArrayList<Integer> returnedAdvertisements = new ArrayList<>();

        Statement str = conn.createStatement();
        ResultSet rs = str.executeQuery(sql);

        //loops through rows in the sql SELECT statement
        while (rs.next()) {

            int advertisementId = rs.getInt("advertisements_id");
            returnedAdvertisements.add(advertisementId);
        }


        return returnedAdvertisements;
    }

}