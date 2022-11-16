package no.hiof.groupproject.models;

import no.hiof.groupproject.models.payment_methods.Paypal;
import no.hiof.groupproject.models.vehicle_types.Car;
import no.hiof.groupproject.models.vehicle_types.Vehicle;
import no.hiof.groupproject.tools.db.ConnectDB;
import no.hiof.groupproject.tools.db.RetrieveBookingDB;
import no.hiof.groupproject.tools.db.RetrieveUserDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class RemoveUserTest {

    @BeforeEach
    void initialiseDatabasePath() {
        ConnectDB.setDb("jdbc:sqlite:sqlite/db/testable.db");
    }
    @AfterEach
    void rewindDatabasePath() {
        ConnectDB.setDb("jdbc:sqlite:sqlite/db/test.db");
    }

    @Test
    void assertsUserIsRemovedFromDatabase() {

        License license = new License("98 39 123456 1", LocalDate.parse("2012-01-06"),
                "Norway");

        User user = new User("delete", "me", "1777", "peawet",
                "12341234123", "iwill@be.deleted", "12341234",
                license);

        User user2 = RetrieveUserDB.retrieveFromEmail("iwill@be.deleted");

        assertNotNull(user2);

        String sql = "SELECT COUNT(*) AS amount FROM users WHERE email = \'" + "iwill@be.deleted" + "\'";

        boolean ans = false;
        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            if (queryResult.getInt("amount") > 0) {
                ans = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        assertTrue(ans);



        user.deleteUserAndCascade();



        sql = "SELECT COUNT(*) AS amount FROM users WHERE email = \'" + "iwill@be.deleted" + "\'";

        ans = false;
        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            if (queryResult.getInt("amount") > 0) {
                ans = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        assertFalse(ans);

    }

    @Test
    void assertsUserProfileIsRemovedFromDatabaseViaCascade() {

        License license = new License("98 39 123456 1", LocalDate.parse("2012-01-06"),
                "Norway");

        User user = new User("delete", "me", "1777", "peawet",
                "12341234123", "iwill@be.deleted", "12341234",
                license);

        User user2 = RetrieveUserDB.retrieveFromEmail("iwill@be.deleted");

        assertNotNull(user2);

        String sql = "SELECT COUNT(*) AS amount FROM userProfiles INNER JOIN users ON users_id = user_fk " +
                "WHERE email = \'" + "iwill@be.deleted" + "\'";

        boolean ans = false;
        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            if (queryResult.getInt("amount") > 0) {
                ans = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        assertTrue(ans);



        user.deleteUserAndCascade();



        sql = "SELECT COUNT(*) AS amount FROM userProfiles INNER JOIN users ON users_id = user_fk " +
                "WHERE email = \'" + "iwill@be.deleted" + "\'";

        ans = false;
        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            if (queryResult.getInt("amount") > 0) {
                ans = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        assertFalse(ans);

    }

    @Test
    void assertsAdvertisementsAreDeletedOnCascade() {

        License license = new License("98 39 123456 1", LocalDate.parse("2012-01-06"),
                "Norway");

        User user = new User("delete", "me", "1777", "peawet",
                "12341234123", "iwill@be.deleted", "12341234",
                license);

        User user2 = RetrieveUserDB.retrieveFromEmail("iwill@be.deleted");

        assertNotNull(user2);

        Vehicle car = new Car("22334455", "aston martin", "db5", "petrol",
                "manual", 1963, 2, 400);

        User renter = new User("speed", "fiend", "1777", "greatscott",
                "12341234123", "rubber@hotmail.no", "12341234",
                license);

        RentOutAd roa = new RentOutAd(
                user,
                car,
                BigDecimal.valueOf(500), BigDecimal.valueOf(25), "Halden"
        );

        roa.addNewPeriod(LocalDate.parse("2028-01-01"), LocalDate.parse("2029-01-01"));
        roa.addNewPeriod(LocalDate.parse("2030-01-01"), LocalDate.parse("2031-01-01"));

        LocalDate bookingStart = LocalDate.parse("2028-02-01");
        LocalDate bookingEnd = LocalDate.parse("2028-03-01");
        Paypal paypal = new Paypal("rubber@hotmail.no", "greatscott");

        roa.addBooking(new Booking(
                renter,
                user,
                bookingStart,
                bookingEnd,
                paypal,
                roa.getVehicle()));

        Booking booking = RetrieveBookingDB.retrieve(
                renter.getId() + "." + bookingStart + "." + user.getId());

        assertNotNull(booking);

        String sql = "SELECT COUNT(*) AS amount FROM advertisements " +
                "LEFT JOIN availableWithin on advertisements_id = availableWithin_id_fk " +
                "LEFT JOIN userProfiles ON advertisements.user_fk " +
                "LEFT JOIN users ON advertisements.user_fk = users.users_id " +
                "LEFT JOIN ratings ON advertisements.user_fk = ratings.user " +
                "WHERE email = \'" + "iwill@be.deleted" + "\'";

        boolean ans = false;
        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            if (queryResult.getInt("amount") > 0) {
                ans = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        assertTrue(ans);

        sql = "SELECT COUNT(*) AS amount FROM userProfiles INNER JOIN users ON users_id = user_fk " +
                "WHERE email = \'" + "iwill@be.deleted" + "\'";

        ans = false;
        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            if (queryResult.getInt("amount") > 0) {
                ans = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        assertTrue(ans);



        user.deleteUserAndCascade();



        sql = "SELECT COUNT(*) AS amount FROM userProfiles INNER JOIN users ON users_id = user_fk " +
                "WHERE email = \'" + "iwill@be.deleted" + "\'";

        ans = false;
        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            if (queryResult.getInt("amount") > 0) {
                ans = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        assertFalse(ans);

        sql = "SELECT COUNT(*) AS amount FROM advertisements " +
                "LEFT JOIN availableWithin on advertisements_id = availableWithin_id_fk " +
                "LEFT JOIN userProfiles ON advertisements.user_fk " +
                "LEFT JOIN users ON advertisements.user_fk = users.users_id " +
                "LEFT JOIN ratings ON advertisements.user_fk = ratings.user " +
                "WHERE email = \'" + "iwill@be.deleted" + "\'";

        ans = false;
        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            if (queryResult.getInt("amount") > 0) {
                ans = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        assertFalse(ans);

    }

}