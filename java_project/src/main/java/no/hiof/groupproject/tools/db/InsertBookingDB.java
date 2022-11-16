package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.Booking;
import no.hiof.groupproject.models.payment_methods.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

/*
A class used to serialise a Booking into a database for permanent storage.
 */
public class InsertBookingDB {

    public static Booking insert(Booking booking) {

        String sql = "INSERT INTO bookings (bookings_id, renter_fk, owner_fk, bookedFrom, bookedTo, payment_fk, vehicle_fk)" +
                "VALUES(?,?,?,?,?,?,?)";


        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {
            str.setString(1, booking.getStrId());
            str.setInt(2, booking.getRenter().getId());
            str.setInt(3, booking.getOwner().getId());
            str.setString(4, booking.getBookedFrom().toString());
            str.setString(5, booking.getBookedTo().toString());
            str.setInt(7, booking.getVehicle().getId());
            Payment payment = booking.getPayment();
            //NOT REDUNDANT - causes getId() to have a default value of 0 otherwise
            if (Objects.equals(booking.getPayment().getPaymentType(), "creditdebit")) {
                str.setInt(6, ((CreditDebit) payment).getId());
            } else if (Objects.equals(booking.getPayment().getPaymentType(), "googlepay")) {
                str.setInt(6, ((GooglePay) payment).getId());
            } else if (Objects.equals(booking.getPayment().getPaymentType(), "paypal")) {
                str.setInt(6, ((Paypal) payment).getId());
            } else if (Objects.equals(booking.getPayment().getPaymentType(), "vipps")) {
                str.setInt(6, ((Vipps) payment).getId());
            }
            str.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return booking;
    }

    /*public static void insertWithPriorConnection(Booking booking, Connection conn) throws SQLException {

        String sql = "INSERT INTO bookings (bookings_id, renter_fk, owner_fk, bookedFrom, bookedTo, payment_fk, vehicle_fk)" +
                "VALUES(?,?,?,?,?,?,?)";


        PreparedStatement str = conn.prepareStatement(sql);
        str.setString(1, booking.getStrId());
        str.setInt(2, booking.getRenter().getId());
        str.setInt(3, booking.getOwner().getId());
        str.setString(4, booking.getBookedFrom().toString());
        str.setString(5, booking.getBookedTo().toString());
        str.setInt(7, booking.getVehicle().getId());
        Payment payment = booking.getPayment();
        //NOT REDUNDANT - causes getId() to have a default value of 0 otherwise
        if (Objects.equals(booking.getPayment().getPaymentType(), "creditdebit")) {
            str.setInt(6, ((CreditDebit) payment).getId());
        } else if (Objects.equals(booking.getPayment().getPaymentType(), "googlepay")) {
            str.setInt(6, ((GooglePay) payment).getId());
        } else if (Objects.equals(booking.getPayment().getPaymentType(), "paypal")) {
            str.setInt(6, ((Paypal) payment).getId());
        } else if (Objects.equals(booking.getPayment().getPaymentType(), "vipps")) {
            str.setInt(6, ((Vipps) payment).getId());
        }
        str.executeUpdate();


    }*/
}