package no.hiof.groupproject.models;

import no.hiof.groupproject.interfaces.ExistsInDb;
import no.hiof.groupproject.interfaces.Serialise;
import no.hiof.groupproject.models.payment_methods.Payment;
import no.hiof.groupproject.tools.db.ConnectDB;
import no.hiof.groupproject.tools.db.InsertBookingDB;
import no.hiof.groupproject.tools.db.InsertVehicleDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

/*
A class used to hold data relating to a specific singular booking. This class is used within the RentOutAd
class.
 */

public class Booking implements ExistsInDb, Serialise {

    private String strId;
    //the person renting the vehicle
    private User renter;
    //the person owning the car
    private User owner;
    //the start and end date of the rental period
    private LocalDate bookedFrom;
    private LocalDate bookedTo;
    //the period of time the renter has booked the car for
    private Period bookedWithin;
    //the payment method connected to the booking
    private Payment payment;

    public Booking(User renter, User owner, LocalDate bookedFrom, LocalDate bookedTo, Payment payment) {
        //creates a booking in the format of <renter id>.<date booking begins>.<vehicle owner id>
        //42.2024-12-24.26
        //the final id is added in the RentOutAd class
        //in the format of <renter id>.<date booking begins>.<vehicle owner id>
        //when added to a confirmedBookings array
        this.strId = renter.getId() + "." + bookedFrom.toString() + "." + owner.getId();

        this.renter = renter;
        this.owner = owner;
        this.bookedFrom = bookedFrom;
        this.bookedTo = bookedTo;
        this.bookedWithin = Period.between(bookedFrom, bookedTo);
        this.payment = payment;

        if (!existsInDb()) {
            serialise();
        }

    }

    @Override
    public boolean existsInDb() {
        String sql = "SELECT COUNT(*) AS amount FROM bookings WHERE bookings_id = \'" + strId + "\'";

        boolean ans = false;
        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            if (queryResult.getInt("amount") > 0) {
                ans = true;
            }
            return ans;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public void serialise() {
        InsertBookingDB.insert(this);
    }


    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getStrId() {
        return strId;
    }

    public void setStrId(String strId) {
        this.strId = strId;
    }

    public LocalDate getBookedFrom() {
        return bookedFrom;
    }

    public void setBookedFrom(LocalDate bookedFrom) {
        this.bookedFrom = bookedFrom;
    }

    public LocalDate getBookedTo() {
        return bookedTo;
    }

    public void setBookedTo(LocalDate bookedTo) {
        this.bookedTo = bookedTo;
    }

    public User getRenter() {
        return renter;
    }

    public void setRenter(User renter) {
        this.renter = renter;
    }

    public Period getBookedWithin() {
        return bookedWithin;
    }

    public void setBookedWithin(Period bookedWithin) {
        this.bookedWithin = bookedWithin;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
