package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.Advertisement;
import no.hiof.groupproject.models.Booking;
import no.hiof.groupproject.models.RentOutAd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

/*
A class used to serialise a Booking into a database for permanent storage.
 */
public class InsertBookingDB {

    public static void insert(Booking booking) {

        String sql = "INSERT INTO bookings (renter_fk, owner_fk, bookedFrom, bookedTo, payment_fk)" +
                "VALUES(?,?,?,?,?)";

        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            //saves the payment used for the specific booking
            if (!booking.getPayment().existsInDb()) {
                InsertPaymentDB.insert(booking.getPayment());
            }


            str.setInt(1, booking.getRenter().getId());
            str.setInt(2, booking.getOwner().getId());
            str.setString(3, booking.getBookedFrom().toString());
            str.setString(4, booking.getBookedTo().toString());
            str.setInt(5, booking.getPayment().getId());
            str.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}