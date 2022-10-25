package no.hiof.groupproject.models;

import no.hiof.groupproject.models.vehicle_types.Vehicle;
import no.hiof.groupproject.tools.geocode.Location;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

/*
A subclass of Advertisement designed to be used for users wanting to rent out their car.
RentOutAd has two different costs - first is a charge for each day the vehicle is rented, second is an additional
charge for every 20 kilometers driven during the rental period.
An example of initialisation is:
    RentOutAd roa = new RentOutAd(
                    new User("Sam", "Davies", "1111", "hunter2", "123412341234", "sam@sam.no", "12345678"),
                    new Car("12341234", "audi", "tt", "petrol",
                    "automatic", 2013, 5, 1500, "a nice one"),
                    BigDecimal.valueOf(200), BigDecimal.valueOf(10), "Sarpsborg"
                    );
 */

public class RentOutAd extends Advertisement {

    //auto-incremental id
    private static int count = 1;
    private int id;

    private Vehicle vehicle;
    //currency in norwegian kroner (NOK) set during initialisation
    private Currency cur;
    //BigDecimal used for currencies - BigDecimal.valueOf(<long> or <double>)
    private BigDecimal dailyCharge;
    private BigDecimal chargePerTwentyKm;
    //treemap of the time that the vehicle is available to be rented within
    //sorted array from oldest -> newest date based on the key
    private TreeMap<LocalDate, LocalDate> availableWithin;
    //array of confirmed bookings connected to a singular advertisement
    private ArrayList<Booking> confirmedBookings;
    //where the vehicle is located - the location is based on a string of a city eg "Sarpsborg" or "Bergen"
    private Location location;
    //strings based on Location so that the API is only queried on initialisation.
    //handy if API is temporarily unavailable
    private String by, fylke, postnr, land;

    public RentOutAd(User user, Vehicle vehicle,
                     BigDecimal dailyCharge, BigDecimal chargePerTwentyKm, String city) {
        super(user);

        this.id = count;
        //increments the id by 1
        count++;

        this.vehicle = vehicle;
        this.cur = Currency.getInstance("NOK");
        this.dailyCharge = dailyCharge;
        this.chargePerTwentyKm = chargePerTwentyKm;
        try {
            this.location = new Location(city);
            by = this.location.getBy();
            fylke = this.location.getFylke();
            postnr = this.location.getPostNr();
            land = this.location.getLand();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //function to set a new period of time that the vehicle is available within
    public void addNewPeriod(LocalDate dateFrom, LocalDate dateTo) {
        availableWithin.put(dateFrom, dateTo);
        updateDateLastChanged();
    }

    //function to add a new booking, ensuring the date is available, and no other booking happens at the same time
    public void addBooking(Booking booking) {

        boolean dateAvailable = false;
        boolean dateDoesNotClash = false;

        for (Map.Entry<LocalDate, LocalDate> set : availableWithin.entrySet()) {
            //as long as the booking is within the available dates then the code can proceed
            if (booking.getBookedFrom().isAfter(set.getKey()) && booking.getBookedTo().isBefore(set.getValue())) {
                dateAvailable = true;
            }
        }

        if (dateAvailable) {
            //creating a sorted map of bookings
            Map<LocalDate, LocalDate> sortedBookings = new TreeMap<>();

            //initialising variables
            LocalDate dateTo = null;
            LocalDate dateFrom = null;

            //flags for the following for loop
            boolean iteration = true;
            boolean turn = false;

            //TLDR - establishes periods between bookings when nobody is booking the vehicle.

            //on first iteration the booking's last date is set to a variable then the turn flag is set to true
            //    on second iteration the booking's first date is set to a variable, turn flag is set to false,
            //    then a set is put into the TreeMap, and the turn flag is set to true.
            //        on the third iteration the loop is repeated
            for (Booking book : confirmedBookings) {
                if (iteration) {
                    dateTo = book.getBookedTo();
                    iteration = false;
                } else {
                    dateFrom = book.getBookedFrom();
                    iteration = true;
                }
                if (turn && dateTo != null && dateFrom != null) {
                    sortedBookings.put(dateTo, dateFrom);
                    turn = false;
                } else {
                    turn = true;
                }
            }
            //sets a flag if the booking does not clash with another booking
            for (Map.Entry<LocalDate, LocalDate> set : sortedBookings.entrySet()) {
                if (booking.getBookedFrom().isAfter(set.getKey()) && booking.getBookedTo().isAfter(set.getValue())) {
                    dateDoesNotClash = true;
                }
            }
        }

        //if and ONLY IF the booking date is within dates available AND does not clash with other booking dates
        //then the booking will be added to a booking array
        if (dateDoesNotClash) {
            //creates a booking in the format of <renter id>.<date booking begins>.<vehicle owner id>
            //42.2024-12-24.26
            booking.setStrId(booking.getStrId() + "." + this.getUser().getId());
            confirmedBookings.add(booking);
            updateDateLastChanged();
        }
    }

    //function used to remove rental periods that are now expired
    public void refreshPeriods() {
        availableWithin.entrySet().removeIf(entry -> LocalDate.now().isAfter(entry.getValue()));
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getFylke() {
        return fylke;
    }

    public void setFylke(String fylke) {
        this.fylke = fylke;
    }

    public String getPostnr() {
        return postnr;
    }

    public void setPostnr(String postnr) {
        this.postnr = postnr;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Currency getCur() {
        return cur;
    }

    public void setCur(Currency cur) {
        this.cur = cur;
    }

    public BigDecimal getDailyCharge() {
        return dailyCharge;
    }

    public void setDailyCharge(BigDecimal dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public BigDecimal getChargePerTwentyKm() {
        return chargePerTwentyKm;
    }

    public void setChargePerTwentyKm(BigDecimal chargePerTwentyKm) {
        this.chargePerTwentyKm = chargePerTwentyKm;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Currency getNok() {
        return cur;
    }

    public void setNok(Currency cur) {
        this.cur = cur;
    }

    public TreeMap<LocalDate, LocalDate> getAvailableWithin() {
        return availableWithin;
    }

    public void setAvailableWithin(TreeMap<LocalDate, LocalDate> availableWithin) {
        this.availableWithin = availableWithin;
    }

    public ArrayList<Booking> getConfirmedBookings() {
        return confirmedBookings;
    }

    public void setConfirmedBookings(ArrayList<Booking> confirmedBookings) {
        this.confirmedBookings = confirmedBookings;
    }
}


