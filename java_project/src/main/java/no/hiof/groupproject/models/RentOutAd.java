package no.hiof.groupproject.models;

import no.hiof.groupproject.models.vehicle_types.Vehicle;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;

/*
A subclass of Advertisement designed to be used for users wanting to rent out their car.
 */

public class RentOutAd extends Advertisement {


    private Vehicle vehicle;
    private Currency nok;
    //hashmap of the time that the vehicle is available to be rented within
    private HashMap<LocalDate, LocalDate> availableWithin;
    //array of confirmed bookings connected to a singular advertisement
    private ArrayList<Booking> confirmedBookings;

    public RentOutAd(int id, User user, LocalDate dateCreated, LocalDate dateLastChanged, Vehicle vehicle,
                     Currency nok, ArrayList<Period> availableWithin, ArrayList<Booking> confirmedBookings) {
        super(id, user, dateCreated, dateLastChanged);
        this.vehicle = vehicle;
        this.nok = nok;
    }


    //function to set a new period of time that the vehicle is available within
    public void addNewPeriod(LocalDate dateFrom, LocalDate dateTo) {
        availableWithin.put(dateFrom, dateTo);
    }

    //function to set a new booking
    public void addBooking(Booking booking) {
        for (HashMap.Entry<LocalDate, LocalDate> set : availableWithin.entrySet()) {
            if (booking.getBookedFrom().isAfter(set.getKey()) && booking.getBookedTo().isBefore(set.getValue())) {
                //creates a booking in the format of <renter id>.<date booking begins>.<vehicle owner id>
                //42.2024-12-24.26
                booking.setStrId(booking.getStrId() + "." + this.getUser().getId());
                confirmedBookings.add(booking);
            }
        }

    }

    //function used to remove rental periods that are now expired
    public void refreshPeriods() {
        availableWithin.entrySet().removeIf(entry -> LocalDate.now().isAfter(entry.getValue()));
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Currency getNok() {
        return nok;
    }

    public void setNok(Currency nok) {
        this.nok = nok;
    }

    public HashMap<LocalDate, LocalDate> getAvailableWithin() {
        return availableWithin;
    }

    public void setAvailableWithin(HashMap<LocalDate, LocalDate> availableWithin) {
        this.availableWithin = availableWithin;
    }

    public ArrayList<Booking> getConfirmedBookings() {
        return confirmedBookings;
    }

    public void setConfirmedBookings(ArrayList<Booking> confirmedBookings) {
        this.confirmedBookings = confirmedBookings;
    }
}
