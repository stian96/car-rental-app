package no.hiof.groupproject.tools;

import no.hiof.groupproject.models.*;
import no.hiof.groupproject.models.advertisements.Advertisement;
import no.hiof.groupproject.models.advertisements.RentOutAd;
import no.hiof.groupproject.models.vehicles.four_wheeled_vehicles.Car;
import no.hiof.groupproject.models.vehicles.Vehicle;
import no.hiof.groupproject.tools.db.ConnectDB;
import no.hiof.groupproject.tools.db.RetrieveUserProfileDB;
import no.hiof.groupproject.tools.filters.FilterAdvertisement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class GDPRInformationTest {

    @BeforeEach
    void initialiseDatabasePath() {
        ConnectDB.setDb("jdbc:sqlite:sqlite/db/testable.db");
    }
    @AfterEach
    void rewindDatabasePath() {
        ConnectDB.setDb("jdbc:sqlite:sqlite/db/test.db");
    }



    @Test
    void assertsGDPRInformationCanBeRetrievedFromTheDatabase() {

        User user = new User("jeff", "goldbum", "1777", "gfdgd",
                "12341234123", "dsfsd@hgh.no", "12341234",
                new License("98 41 123456 1", LocalDate.parse("2014-11-12"),
                        "Norway"));

        UserProfile up = RetrieveUserProfileDB.retrieve(user.getId());
        GDPRInformation printOut = new GDPRInformation(user.getId());
        assertFalse(printOut.getArrayOfPrintOut().isEmpty());
    }

    @Test
    void assertsGDPRInformationIsCorrectlyCompiled() {

        User user = new User("jeff", "goldbum", "1777", "gfdgd",
                "12341234123", "dsfsd@hgh.no", "12341234",
                new License("98 41 123456 1", LocalDate.parse("2014-11-12"),
                        "Norway"));

        UserProfile up = RetrieveUserProfileDB.retrieve(user.getId());
        GDPRInformation printOut = new GDPRInformation(user.getId());
        ArrayList<String> arrayToPrint = printOut.getArrayOfPrintOut();
        assertEquals("\n*** Personal Information ***\n", arrayToPrint.get(0));
        assertEquals("*        Full name: " + user.getFirstName() + " " + user.getLastName()
                , arrayToPrint.get(1));
    }

    @Test
    void assertsNonexistingUserCannotRetrieveGDPRInformationFromTheDatabase() {

        assertThrows(NullPointerException.class, () -> new GDPRInformation(125436346));

    }
}