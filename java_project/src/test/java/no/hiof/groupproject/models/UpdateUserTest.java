package no.hiof.groupproject.models;

import no.hiof.groupproject.models.payment_methods.Paypal;
import no.hiof.groupproject.models.vehicle_types.Car;
import no.hiof.groupproject.models.vehicle_types.Vehicle;
import no.hiof.groupproject.tools.db.ConnectDB;
import no.hiof.groupproject.tools.db.RetrieveBookingDB;
import no.hiof.groupproject.tools.db.RetrieveUserDB;
import no.hiof.groupproject.tools.db.UpdateDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class UpdateUserTest {

    @BeforeEach
    void initialiseDatabasePath() {
        ConnectDB.setDb("jdbc:sqlite:sqlite/db/testable.db");
    }
    @AfterEach
    void rewindDatabasePath() {
        ConnectDB.setDb("jdbc:sqlite:sqlite/db/test.db");
    }

    @Test
    void assertsUserPasswordCanBeChanged() {

        License license = new License("98 40 123456 1", LocalDate.parse("2016-05-12"),
                "Norway");

        User user = new User("geoff", "shreeves", "1777", "cheersgeoff",
                "12341234123", "doyouknow@yourenot.playing", "12341234",
                license);

        assertTrue(user.existsInDb());

        String password = "cheersgeoff";
        UpdateDB.update("users", "email", user.getEmail(), "password", password);
        user.setPassword(password);

        User user2 = RetrieveUserDB.retrieveFromEmail("doyouknow@yourenot.playing");

        assertEquals(password, user.getPassword());

        String newPassword = "thankyougeoff";
        UpdateDB.update("users", "email", user2.getEmail(), "password", newPassword);
        user.setPassword(newPassword);

        User user3 = RetrieveUserDB.retrieveFromEmail("doyouknow@yourenot.playing");

        assertEquals(newPassword, user3.getPassword());

    }

    @Test
    void assertsUserFirstNameCanBeChanged() {

        License license = new License("98 40 123456 1", LocalDate.parse("2016-05-12"),
                "Norway");

        User user = new User("geoff", "shreeves", "1777", "cheersgeoff",
                "12341234123", "doyouknow@yourenot.playing", "12341234",
                license);

        assertTrue(user.existsInDb());

        String firstName = "geoff";
        UpdateDB.update("users", "email", user.getEmail(), "firstName", firstName);
        user.setFirstName(firstName);

        User user2 = RetrieveUserDB.retrieveFromEmail("doyouknow@yourenot.playing");

        assertEquals(firstName, user.getFirstName());

        String newFirstName = "tony";
        UpdateDB.update("users", "email", user2.getEmail(), "firstName", newFirstName);
        user.setFirstName(newFirstName);

        User user3 = RetrieveUserDB.retrieveFromEmail("doyouknow@yourenot.playing");

        assertEquals(newFirstName, user3.getFirstName());

    }

    @Test
    void assertsUserLastNameCanBeChanged() {

        License license = new License("98 40 123456 1", LocalDate.parse("2016-05-12"),
                "Norway");

        User user = new User("geoff", "shreeves", "1777", "cheersgeoff",
                "12341234123", "doyouknow@yourenot.playing", "12341234",
                license);

        assertTrue(user.existsInDb());

        String lastName = "shreeves";
        UpdateDB.update("users", "email", user.getEmail(), "lastName", lastName);
        user.setLastName(lastName);

        User user2 = RetrieveUserDB.retrieveFromEmail("doyouknow@yourenot.playing");

        assertEquals(lastName, user.getLastName());

        String newLastName = "bolognese";
        UpdateDB.update("users", "email", user2.getEmail(), "lastName", newLastName);
        user.setLastName(newLastName);

        User user3 = RetrieveUserDB.retrieveFromEmail("doyouknow@yourenot.playing");

        assertEquals(newLastName, user3.getLastName());

    }

    @Test
    void assertsUserPostNrCanBeChanged() {

        License license = new License("98 40 123456 1", LocalDate.parse("2016-05-12"),
                "Norway");

        User user = new User("geoff", "shreeves", "1777", "cheersgeoff",
                "12341234123", "doyouknow@yourenot.playing", "12341234",
                license);

        assertTrue(user.existsInDb());

        String postNr = "1777";
        UpdateDB.update("users", "email", user.getEmail(), "postNr", postNr);
        user.setLastName(postNr);

        User user2 = RetrieveUserDB.retrieveFromEmail("doyouknow@yourenot.playing");

        assertEquals(postNr, user.getPostNr());

        String newPostNr = "1778";
        UpdateDB.update("users", "email", user2.getEmail(), "postNr", newPostNr);
        user.setLastName(newPostNr);

        User user3 = RetrieveUserDB.retrieveFromEmail("doyouknow@yourenot.playing");

        assertEquals(newPostNr, user3.getPostNr());

    }

    @Test
    void assertsUserTlfNrCanBeChanged() {

        License license = new License("98 40 123456 1", LocalDate.parse("2016-05-12"),
                "Norway");

        User user = new User("geoff", "shreeves", "1777", "cheersgeoff",
                "12341234123", "doyouknow@yourenot.playing", "12341234",
                license);

        assertTrue(user.existsInDb());

        String tlfNr = "12341234";
        UpdateDB.update("users", "email", user.getEmail(), "tlfNr", tlfNr);
        user.setLastName(tlfNr);

        User user2 = RetrieveUserDB.retrieveFromEmail("doyouknow@yourenot.playing");

        assertEquals(tlfNr, user.getTlfNr());

        String newTlfNr = "12344321";
        UpdateDB.update("users", "email", user2.getEmail(), "tlfNr", newTlfNr);
        user.setLastName(newTlfNr);

        User user3 = RetrieveUserDB.retrieveFromEmail("doyouknow@yourenot.playing");

        assertEquals(newTlfNr, user3.getTlfNr());

    }

    @Test
    void assertsUserBankAccountNrCanBeChanged() {

        License license = new License("98 40 123456 1", LocalDate.parse("2016-05-12"),
                "Norway");

        User user = new User("geoff", "shreeves", "1777", "cheersgeoff",
                "12341234123", "doyouknow@yourenot.playing", "12341234",
                license);

        assertTrue(user.existsInDb());

        String bankAccountNr = "12341234123";
        UpdateDB.update("users", "email", user.getEmail(), "bankAccountNr", bankAccountNr);
        user.setLastName(bankAccountNr);

        User user2 = RetrieveUserDB.retrieveFromEmail("doyouknow@yourenot.playing");

        assertEquals(bankAccountNr, user.getBankAccountNr());

        String newBankAccountNr = "12341234321";
        UpdateDB.update("users", "email", user2.getEmail(), "bankAccountNr", newBankAccountNr);
        user.setLastName(newBankAccountNr);

        User user3 = RetrieveUserDB.retrieveFromEmail("doyouknow@yourenot.playing");

        assertEquals(newBankAccountNr, user3.getBankAccountNr());

    }

    @Test
    void assertsUserLicenseCanBeChanged() {

        License license = new License("98 40 123456 1", LocalDate.parse("2016-05-12"),
                "Norway");

        User user = new User("geoff", "shreeves", "1777", "cheersgeoff",
                "12341234123", "doyouknow@yourenot.playing", "12341234",
                license);

        assertTrue(user.existsInDb());

        UpdateDB.update("users", "email", user.getEmail(), "license", license.getLicenseNumber());
        user.setdLicense(license);

        User user2 = RetrieveUserDB.retrieveFromEmail("doyouknow@yourenot.playing");

        assertEquals(license.getLicenseNumber(), user.getdLicense().getLicenseNumber());

        License newLicense = new License("98 47 123456 1", LocalDate.parse("2016-06-12"),
                "Norway");

        UpdateDB.update("users", "email", user2.getEmail(), "license", newLicense.getLicenseNumber());
        user.setdLicense(newLicense);

        User user3 = RetrieveUserDB.retrieveFromEmail("doyouknow@yourenot.playing");

        System.out.println(user3.getdLicense().getLicenseNumber());
        assertEquals(newLicense.getLicenseNumber(), user3.getdLicense().getLicenseNumber());

    }

}