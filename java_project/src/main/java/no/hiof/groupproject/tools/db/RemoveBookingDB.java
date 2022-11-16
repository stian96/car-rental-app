package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.Booking;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.payment_methods.Payment;
import no.hiof.groupproject.models.vehicle_types.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

//Returns a specific TreeMap of all valid rental periods in the database based on a specific User

public class RemoveBookingDB {

    public static void remove(String strId) {

        String sql = "DELETE FROM bookings " +
                "WHERE bookings_id = \'" + strId + "\'";


        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            str.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeWithPriorConnection(String strId, Connection conn) throws SQLException {

        String sql = "DELETE FROM bookings " +
                "WHERE bookings_id = \'" + strId + "\'";

        PreparedStatement str = conn.prepareStatement(sql);

        ResultSet queryResult = str.executeQuery();

    }

}