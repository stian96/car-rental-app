package no.hiof.groupproject.models;

import no.hiof.groupproject.interfaces.AvailableWithinExistsInDb;
import no.hiof.groupproject.models.advertisements.RentOutAd;
import no.hiof.groupproject.models.payment_methods.Paypal;
import no.hiof.groupproject.models.vehicles.Vehicle;
import no.hiof.groupproject.models.vehicles.four_wheeled_vehicles.Car;
import no.hiof.groupproject.tools.db.ConnectDB;
import no.hiof.groupproject.tools.db.RetrieveBookingDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class AddBookingAndAvailableWithinTest {

    @BeforeEach
    void initialiseDatabasePath() {
        ConnectDB.setDb("jdbc:sqlite:sqlite/db/testable.db");
    }
    @AfterEach
    void rewindDatabasePath() {
        ConnectDB.setDb("jdbc:sqlite:sqlite/db/test.db");
    }

    Vehicle car = new Car("12341234", "audi", "tt", "petrol",
            "automatic", 2013, 5, 1500);

    License license = new License("98 43 123456 1", LocalDate.parse("2008-05-12"),
            "Norway");

    User owner = new User("john", "squiglet", "1777", "mmmcars",
            "12341234123", "rentmycar@car.no", "12341234",
            license);

    User renter = new User("speed", "fiend", "1777", "greatscott",
            "12341234123", "rubber@hotmail.no", "12341234",
            license);

    Paypal paypal = new Paypal("rubber@hotmail.no", "greatscott");

    RentOutAd roa = new RentOutAd(
            owner,
            car,
            BigDecimal.valueOf(200), BigDecimal.valueOf(10), "Sarpsborg"
    );

    @Test
    void assertsValidAvailableWithinIsAddedToRentOutAd() {

        LocalDate dateFrom = LocalDate.parse("2030-01-01");
        LocalDate dateTo = LocalDate.parse("2031-01-01");
        roa.addNewPeriod(dateFrom, dateTo);

        TreeMap<LocalDate, LocalDate> availableWithin = roa.getAvailableWithin();
        assertTrue(availableWithin.containsKey(dateFrom) && availableWithin.containsValue(dateTo));
    }

    @Test
    void assertsInvalidAvailableWithinIsNotAddedToRentOutAd() {

        LocalDate dateFrom = LocalDate.parse("2034-01-01");
        LocalDate dateTo = LocalDate.parse("2030-01-01");
        roa.addNewPeriod(dateFrom, dateTo);

        TreeMap<LocalDate, LocalDate> availableWithin = roa.getAvailableWithin();
        assertFalse(availableWithin.containsKey(dateFrom) && availableWithin.containsValue(dateTo));
    }

    @Test
    void assertsValidBookingIsAddedToRentOutAd() {
        LocalDate dateFrom = LocalDate.parse("2030-01-01");
        LocalDate dateTo = LocalDate.parse("2031-01-01");
        roa.addNewPeriod(dateFrom, dateTo);

        LocalDate bookingStart = LocalDate.parse("2030-07-01");
        LocalDate bookingEnd = LocalDate.parse("2030-08-01");

        Booking booking = new Booking(
                renter,
                owner,
                bookingStart,
                bookingEnd,
                paypal,
                roa.getVehicle());

        roa.addBooking(booking);

        ArrayList<Booking> bookings = roa.getConfirmedBookings();
        assertTrue(bookings.contains(booking));
    }

    @Test
    void assertsInvalidBookingIsNotAddedToRentOutAd() {
        LocalDate dateFrom = LocalDate.parse("2035-01-01");
        LocalDate dateTo = LocalDate.parse("2034-01-01");
        roa.addNewPeriod(dateFrom, dateTo);

        LocalDate bookingStart = LocalDate.parse("2030-07-01");
        LocalDate bookingEnd = LocalDate.parse("2030-08-01");

        Booking booking = new Booking(
                renter,
                owner,
                bookingStart,
                bookingEnd,
                paypal,
                roa.getVehicle());

        roa.addBooking(booking);

        ArrayList<Booking> bookings = roa.getConfirmedBookings();
        assertFalse(bookings.contains(booking));
    }

}