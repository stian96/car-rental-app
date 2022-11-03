package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.Booking;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.vehicle_types.Vehicle;

import java.sql.*;
import java.util.ArrayList;

/*
Returns a specific TreeMap of all valid rental periods in the database based on a specific User

public class RetrieveBookingDB {

    public static Booking retrieve(Booking booking) {

        String sql = "SELECT * FROM bookings " +
                "WHERE bookings_id = \'" + booking.getStrId() + "\'";

        Booking booking = null;

        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            String vehicleSubclass = queryResult.getString("vehicleSubclass");
            return booking;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return booking;
    }

} */