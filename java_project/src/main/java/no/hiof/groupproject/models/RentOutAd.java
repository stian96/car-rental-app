package no.hiof.groupproject.models;

import javafx.beans.property.SimpleObjectProperty;
import no.hiof.groupproject.interfaces.AvailableWithinExistsInDb;
import no.hiof.groupproject.interfaces.GetAutoIncrementId;
import no.hiof.groupproject.models.vehicle_types.Vehicle;
import no.hiof.groupproject.tools.db.*;
import no.hiof.groupproject.tools.geocode.Location;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        //this.id = count;
        //increments the id by 1
        //count++;

        this.vehicle = vehicle;
        this.cur = Currency.getInstance("NOK");
        this.dailyCharge = dailyCharge;
        this.chargePerTwentyKm = chargePerTwentyKm;
        this.setAdvertisementSubclass("rentoutad");
        availableWithin = new TreeMap<>();
        confirmedBookings = new ArrayList<>();
        try {
            this.location = new Location(city);
            by = this.location.getBy();
            fylke = this.location.getFylke();
            postnr = this.location.getPostNr();
            land = this.location.getLand();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!existsInDb()) {
            serialise();
            updateDateLastChanged();
        }


        this.setId(getAutoIncrementId());

    }


    //function to set a new period of time that the vehicle is available within
    public void addNewPeriod(LocalDate dateFrom, LocalDate dateTo) {
        availableWithin.put(dateFrom, dateTo);
        //saves the period in the database table 'availableWithin'
        if (!availableWithinExistsInDb(dateFrom, dateTo)) {
            InsertAvailableWithinDB.insert(this, dateFrom, dateTo);
            updateDateLastChanged();
        }

    }

    public boolean checkIfDateIsAvailable(LocalDate from, LocalDate to) {
        boolean dateAvailable = false;
        boolean dateDoesNotClash = false;

        for (Map.Entry<LocalDate, LocalDate> set : availableWithin.entrySet()) {
            //as long as the booking is within the available dates then the code can proceed
            if (from.isAfter(set.getKey()) && to.isBefore(set.getValue())) {
                dateAvailable = true;
            }
        }

        if (dateAvailable) {
            //creating a sorted map of bookings
            TreeMap<LocalDate, LocalDate> sortedBookings = new TreeMap<>();
            TreeMap<LocalDate, LocalDate> dateIsFree = new TreeMap<>();


            //initialising variables
            LocalDate dateTo = null;
            LocalDate dateFrom = null;

            /*
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

             */

            /*        ***************************************************************************************
                  if a booking exists from the 10th to the 12th, then another booking can only be valid if it is
                  AFTER the 12th, but not ON the 12th
                      ***************************************************************************************
            basically does the same thing as the commented out block of code above
            just had to alter behaviour of the code based on iff ArrayList was:
            -empty, -contained a single object, -2 objects, -3 or more
            important otherwise NullPointerException is given in cases where sortedBookings.higherEntry(set.getKey())
            didn't exist
             */
            if (!confirmedBookings.isEmpty()) {
                for (Booking book : confirmedBookings) {
                    //sorts bookings based on date
                    dateTo = book.getBookedTo();
                    dateFrom = book.getBookedFrom();
                    sortedBookings.put(dateFrom, dateTo);
                }
                if (confirmedBookings.size() > 2) {

                    for (Map.Entry<LocalDate, LocalDate> set : sortedBookings.entrySet()) {
                        if (sortedBookings.higherEntry(set.getKey()) == null) {
                            dateIsFree.put(set.getValue(), LocalDate.parse("2099-01-01"));
                        } else {
                            Map.Entry<LocalDate, LocalDate> next = sortedBookings.higherEntry(set.getKey());
                            dateIsFree.put(set.getValue(), next.getKey());
                        }
                    }

                } else if (confirmedBookings.size() == 2) {
                    LocalDate firstVal = null;
                    LocalDate secondKey = null;
                    LocalDate secondVal = null;
                    int count = 1;

                    for (Map.Entry<LocalDate, LocalDate> set : sortedBookings.entrySet()) {
                        if (count == 1) {
                            firstVal = set.getValue();
                            count++;
                        } else if (count == 2) {
                            secondKey = set.getKey();
                            secondVal = set.getValue();
                        }
                    }

                    dateIsFree.put(firstVal, secondKey);
                    dateIsFree.put(secondVal, LocalDate.parse("2099-01-01"));
                } else if (confirmedBookings.size() == 1) {
                    for (Map.Entry<LocalDate, LocalDate> set : sortedBookings.entrySet()) {
                        Map.Entry<LocalDate, LocalDate> next = sortedBookings.higherEntry(set.getKey());
                        Map.Entry<LocalDate, LocalDate> prev = sortedBookings.lowerEntry(set.getKey());
                        dateIsFree.put(set.getValue(), LocalDate.parse("2099-01-01"));
                    }
                }
            } else {
                //if the arrayList is empty then we can assume that there are no date clashes
                dateDoesNotClash = true;

            }

            //sets a flag if the booking does not clash with another booking
            for (Map.Entry<LocalDate, LocalDate> set : dateIsFree.entrySet()) {
                if (from.isAfter(set.getKey()) && to.isBefore(set.getValue())) {
                    dateDoesNotClash = true;
                }
            }
        }

        return dateDoesNotClash;


    }

    //function to add a new booking, ensuring the date is available, and no other booking happens at the same time
    public void addBooking(Booking booking) {

        //if and ONLY IF the booking date is within dates available AND does not clash with other booking dates
        //then the booking will be added to a booking array
        if (checkIfDateIsAvailable(booking.getBookedFrom(), booking.getBookedTo())) {
            //creates a booking in the format of <renter id>.<date booking begins>.<vehicle owner id>
            //42.2024-12-24.26
            confirmedBookings.add(booking);
            //serialises booking
            if (!booking.existsInDb()) {
                InsertBookingDB.insert(booking);
                updateDateLastChanged();
            }
        }
    }

    //function used to remove rental periods that are now expired
    public void refreshPeriods() {
        availableWithin.entrySet().removeIf(entry -> LocalDate.now().isAfter(entry.getValue()));
    }

    @Override
    public boolean existsInDb() {
        String sql = "SELECT COUNT(*) AS amount FROM advertisements WHERE user_fk = " + this.getUser().getId() +
                " AND vehicle_fk = " + this.vehicle.getId();

        boolean ans = false;
        try (Connection conn = ConnectDB.connectReadOnly();
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

    public boolean availableWithinExistsInDb(LocalDate dateFrom, LocalDate dateTo) {
        String sql = "SELECT COUNT(*) AS amount FROM availableWithin WHERE availableWithin_id_fk = " +
                this.getId() + " AND dateFrom = \'" + dateFrom.toString() +
                "\' AND dateTo = \'" + dateTo.toString() + "\'";

        boolean ans = false;
        try (Connection conn = ConnectDB.connectReadOnly();
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
    public int getAutoIncrementId() {
        String sql = "SELECT * FROM advertisements WHERE user_fk = " + this.getUser().getId() +
                " AND vehicle_fk = " + this.vehicle.getId();

        int i = 0;
        try (Connection conn = ConnectDB.connectReadOnly();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            i = queryResult.getInt("advertisements_id");
            return i;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i;
    }

    //test to remove a specific booking based on its id
    public void removeBooking(String strId) {

        Booking bookingToRemove = null;

        for (Booking booking : confirmedBookings) {
            if (Objects.equals(booking.getStrId(), strId)) {
                bookingToRemove = booking;
            }
        }
        if (bookingToRemove != null) {
            confirmedBookings.remove(bookingToRemove);
            RemoveBookingDB.remove(strId);
        }

    }

    public String getTown() {
        return by;
    }

    public void setTown(String by) {
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

    @Override
    public String toString() {
        return "Owner:" + getUser().getFirstName() + " |manufacturer: " + vehicle.getManufacturer() + " |Model: " + vehicle.getModel() +
                " |Daily charge: " + dailyCharge
                ;
    }
}


