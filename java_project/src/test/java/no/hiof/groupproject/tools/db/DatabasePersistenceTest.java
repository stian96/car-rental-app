package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.License;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.UserProfile;
import org.junit.jupiter.api.*;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DatabasePersistenceTest {

   @BeforeEach
    void initialiseDatabasePath() {
       ConnectDB.setDb("jdbc:sqlite:sqlite/db/testable.db");
   }
    @AfterEach
    void rewindDatabasePath() {
        ConnectDB.setDb("jdbc:sqlite:sqlite/db/test.db");
    }

    @Test
    void assertsUserClassIsSerialised() {
        User user = new User("testing@this.email", "breadismyfavouritefood");
        User user2 = RetrieveUserDB.retrieveFromEmail(user.getEmail());

        assertEquals(user.getEmail(), user2.getEmail());
        assertEquals(user.getPassword(), user2.getPassword());
    }

    @Test
    void assertsUserClassBasicIsSerialised() {
        User user = new User("test2@this.email", "butterismyfavouritefood");
        assertNull(user.getFirstName());

        User user2 = RetrieveUserDB.retrieveFromEmail(user.getEmail());
        assertNull(user2.getFirstName());
    }


    @Test
    void assertsUserClassCanBeUpdatedAndReserialised() {
        User user = new User("another@test.no", "catsanddogs");
        User user2 = RetrieveUserDB.retrieveFromEmail("another@test.no");

        User user3 = new User("ronny", "pickering", "1777", user.getPassword(),
                "12341234123", user.getEmail(), "12341234",
                new License("98 45 123456 1", LocalDate.parse("2008-05-12"),
                "Norway"));

        user2 = RetrieveUserDB.retrieveFromEmail(user.getEmail());

        //here the first name is "ronny" because the user was updated and reserialised
        assertEquals(user2.getFirstName(), "ronny");

    }

    @Test
    void assertsUserProfileIsSerialisedWhenUserClassIsCreated() {
        User user = new User("engelbert", "humperdinck", "1777", "biscuitlover",
                "11112222333", "egg@king.no", "12341234",
                new License("98 46 123456 1", LocalDate.parse("2008-07-11"),
                        "Norway"));
        int userId = user.getId();

        UserProfile up = RetrieveUserProfileDB.retrieve(userId);
        assertNotNull(up);

    }

    @Test
    void assertsUserBasicDoesNotCreateUserProfile() {
        User user = new User("basic@user.no", "ihavenoname");

        String sql = "SELECT COUNT(*) AS amount FROM userProfiles WHERE user_fk = " + user.getId();

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

        assertFalse(ans);
    }

    @Test
    void assertsUserCreatesUserProfile() {



        User user = new User("peter", "pepper", "1777", "pigletcollector",
                "12341234123", "pete@pepp.er", "12341234",
                new License("98 42 123456 1", LocalDate.parse("2011-11-22"),
                        "Norway"));

        String sql = "SELECT COUNT(*) AS amount FROM userProfiles WHERE user_fk = " + user.getId();

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
    }


    @Test
    void assertsUserUpgradeCreatesUserProfile() {
        User user = new User("basicbeforeupgrade@user.no", "ihavenoname");

        String sql = "SELECT COUNT(*) AS amount FROM userProfiles WHERE user_fk = " + user.getId();

        User user2 = new User("lorry", "dovid", "1777", user.getPassword(),
                "12341234123", user.getEmail(), "12341234",
                new License("98 44 123456 1", LocalDate.parse("2008-02-11"),
                        "Norway"));

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
    }

    @Test
    void assertsUserCanBeEdited() {
        User user = new User("change", "me", "1777", "change",
                "12341234123", "hunky@dory.zs", "12341234",
                new License("98 44 123456 1", LocalDate.parse("2008-02-11"),
                        "Norway"));

        User user2 = RetrieveUserDB.retrieveFromEmail(user.getEmail());
        assertEquals(user2.getFirstName(), user.getFirstName());

        user = new User("iam", "changed", "1777", "change",
                "12341234123", "hunky@dory.zs", "12341234",
                new License("98 44 123456 1", LocalDate.parse("2008-02-11"),
                        "Norway"));

        user2 = RetrieveUserDB.retrieveFromEmail(user.getEmail());
        assertEquals(user2.getFirstName(), user.getFirstName());
    }

    @Test
    void assertsRatingsCanBeSaved() {

    }

    @Test
    void assertsRatingsCanBeUpdated() {

    }

    @Test
    void assertsAverageRatingsCanBeCalculated() {

    }

    @Test
    void assertsAverageRatingsCanBeUpdated() {

    }

    @Test
    void assertsVehiclesCanBeSaved() {

    }

    @Test
    void assertsLicensesCanBeSaved() {

    }

    @Test
    void assertsRentOutAdCanBeSaved() {

    }

    @Test
    void assertsAvailableWithinPeriodCanBeSaved() {

    }

    @Test
    void assertsRentOutAdDateCreatedIsCorrect() {

    }

    @Test
    void assertsRentOutAdDateChangedIsUpdated() {

    }

    @Test
    void assertsBookingsCanBeSaved() {

    }

    @Test
    void assertsBookingsThatClashWillNotBeSaved() {

    }

    @Test
    void assertsBookingsWithDateBeforePresentWillNotBeSaved() {

    }

    @Test
    void assertsPaymentsCanBeSaved() {

    }

}