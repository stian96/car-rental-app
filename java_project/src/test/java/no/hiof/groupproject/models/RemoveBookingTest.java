package no.hiof.groupproject.models;

import no.hiof.groupproject.models.payment_methods.Paypal;
import no.hiof.groupproject.models.vehicle_types.Car;
import no.hiof.groupproject.models.vehicle_types.Vehicle;
import no.hiof.groupproject.tools.db.ConnectDB;
import no.hiof.groupproject.tools.db.RetrieveBookingDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class RemoveBookingTest {

    @BeforeEach
    void initialiseDatabasePath() {
        ConnectDB.setDb("jdbc:sqlite:sqlite/db/testable.db");
    }
    @AfterEach
    void rewindDatabasePath() {
        ConnectDB.setDb("jdbc:sqlite:sqlite/db/test.db");
    }

    @Test
    void assertsBookingIsRemovedFromArrayListAndDatabase() {

        Vehicle car = new Car("65843489", "hyundai", "kona", "electric",
                "automatic", 2020, 5, 1600);

        License license = new License("98 41 123456 1", LocalDate.parse("2012-04-11"),
                "Norway");

        User owner = new User("harry", "potter", "1777", "chocolatefrogs",
                "12341234123", "inviscloak@no.where", "12341234",
                license);

        User renter = new User("speed", "fiend", "1777", "greatscott",
                "12341234123", "rubber@hotmail.no", "12341234",
                license);

        RentOutAd roa = new RentOutAd(
                owner,
                car,
                BigDecimal.valueOf(300), BigDecimal.valueOf(20), "Halden"
        );

        roa.addNewPeriod(LocalDate.parse("2028-01-01"), LocalDate.parse("2029-01-01"));
        roa.addNewPeriod(LocalDate.parse("2030-01-01"), LocalDate.parse("2031-01-01"));

        LocalDate bookingStart = LocalDate.parse("2028-03-04");
        LocalDate bookingEnd = LocalDate.parse("2028-03-08");
        Paypal paypal = new Paypal("rubber@hotmail.no", "greatscott");

        roa.addBooking(new Booking(
                renter,
                owner,
                bookingStart,
                bookingEnd,
                paypal,
                roa.getVehicle()));

        AtomicReference<Booking> booking = new AtomicReference<>(RetrieveBookingDB.retrieve(
                renter.getId() + "." + bookingStart + "." + owner.getId()));

        assertNotNull(booking);

        roa.removeBooking(booking.get().getStrId());

        assertThrows(NullPointerException.class, () -> booking.set(RetrieveBookingDB.retrieve(
                renter.getId() + "." + bookingStart + "." + owner.getId())));

        assertTrue(roa.getConfirmedBookings().isEmpty());


    }

}