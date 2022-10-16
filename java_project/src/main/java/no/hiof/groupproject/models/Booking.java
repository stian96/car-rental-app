package no.hiof.groupproject.models;

import no.hiof.groupproject.models.payment_methods.Payment;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

/*
A class used to hold data relating to a specific singular booking. This class is used within the RentOutAd
class.
 */

public class Booking {

    private String strId;
    //the person renting the vehicle
    private User renter;
    //the start and end date of the rental period
    private LocalDate bookedFrom;
    private LocalDate bookedTo;
    //the period of time the renter has booked the car for
    private Period bookedWithin;
    //the payment method connected to the booking
    private Payment payment;

    public Booking(String strId, User renter, LocalDate bookedFrom, LocalDate bookedTo, Payment payment) {
        //creates a unique id based on the format of <renter id>.<date booking begins>
        //note that it does not include vehicle owner - the final id is updated in the RentOutAd class
        //in the format of <renter id>.<date booking begins>.<vehicle owner id>
        //when added to a confirmedBookings array
        this.strId = renter.getId() + "." + bookedFrom.toString();

        this.renter = renter;
        this.bookedFrom = bookedFrom;
        this.bookedTo = bookedTo;
        this.bookedWithin = Period.between(bookedFrom, bookedTo);
        this.payment = payment;
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
