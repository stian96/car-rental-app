package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.interfaces.AvailableWithinExistsInDb;
import no.hiof.groupproject.models.advertisements.Advertisement;
import no.hiof.groupproject.models.advertisements.RentOutAd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

/*
A class used to serialise an Advertisement into a database for permanent storage.
 */
public class InsertAdvertisementDB implements AvailableWithinExistsInDb {

    public static void insert(Advertisement advertisement) {

        String sql = "INSERT INTO advertisements (user_fk, dateCreated, dateLastChanged, vehicle_fk, cur, " +
                "dailyCharge, chargePerTwentyKm, town, fylke, postnr, land, advertisementSubclass) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {
            str.setInt(1, advertisement.getUser().getId());
            //output example: "2022-11-01"
            str.setString(2, advertisement.getDateCreated().toString());
            str.setString(3, advertisement.getDateLastChanged().toString());
            str.setString(12, advertisement.getAdvertisementSubclass());
            if (Objects.equals(advertisement.getAdvertisementSubclass(), "rentoutad")) {
                str.setInt(4, ((RentOutAd) advertisement).getVehicle().getId());
                str.setString(5, ((RentOutAd) advertisement).getCur().toString());
                str.setString(6, ((RentOutAd) advertisement).getDailyCharge().toString());
                str.setString(7, ((RentOutAd) advertisement).getChargePerTwentyKm().toString());
                str.setString(8, ((RentOutAd) advertisement).getTown());
                str.setString(9, ((RentOutAd) advertisement).getFylke());
                str.setString(10, ((RentOutAd) advertisement).getPostnr());
                str.setString(11, ((RentOutAd) advertisement).getLand());
                //loops through all confirmed bookings and saves them
                /*
                if (((RentOutAd) advertisement).getConfirmedBookings() != null) {
                    InsertBookingDB.insert(((RentOutAd) advertisement).getConfirmedBookings());
                }
                 */


                //serialises any availableWithin periods that aren't already stored in the database
                //InsertAvailableWithinDB.insert(advertisement, ((RentOutAd) advertisement).getAvailableWithin());

            }
            str.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    /*public static void insertWithPriorConnection(Advertisement advertisement, Connection conn) throws SQLException {

        String sql = "INSERT INTO advertisements (user_fk, dateCreated, dateLastChanged, vehicle_fk, cur, " +
                "dailyCharge, chargePerTwentyKm, town, fylke, postnr, land, advertisementSubclass) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";


        PreparedStatement str = conn.prepareStatement(sql);
        str.setInt(1, advertisement.getUser().getId());
        //output example: "2022-11-01"
        str.setString(2, advertisement.getDateCreated().toString());
        str.setString(3, advertisement.getDateLastChanged().toString());
        str.setString(12, advertisement.getAdvertisementSubclass());
        if (Objects.equals(advertisement.getAdvertisementSubclass(), "rentoutad")) {
            str.setInt(4, ((RentOutAd) advertisement).getVehicle().getId());
            str.setString(5, ((RentOutAd) advertisement).getCur().toString());
            str.setString(6, ((RentOutAd) advertisement).getDailyCharge().toString());
            str.setString(7, ((RentOutAd) advertisement).getChargePerTwentyKm().toString());
            str.setString(8, ((RentOutAd) advertisement).getTown());
            str.setString(9, ((RentOutAd) advertisement).getFylke());
            str.setString(10, ((RentOutAd) advertisement).getPostnr());
            str.setString(11, ((RentOutAd) advertisement).getLand());
            //loops through all confirmed bookings and saves them
            *//*
            if (((RentOutAd) advertisement).getConfirmedBookings() != null) {
                InsertBookingDB.insert(((RentOutAd) advertisement).getConfirmedBookings());
            }
             *//*


            //serialises any availableWithin periods that aren't already stored in the database
            //InsertAvailableWithinDB.insert(advertisement, ((RentOutAd) advertisement).getAvailableWithin());

        }
        str.executeUpdate();

    }*/



}