package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.License;
import no.hiof.groupproject.models.RentOutAd;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.UserProfile;
import no.hiof.groupproject.models.vehicle_types.Car;
import no.hiof.groupproject.models.vehicle_types.Vehicle;
import org.junit.jupiter.api.*;

import java.io.File;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;

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

        assertTrue(user.existsInDb());
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
    void assertsRatingsCanBeSerialised() {
        User user = new User("rate", "me", "1777", "ratingking",
                "12341234123", "rate@me.no", "12341234",
                new License("98 44 123456 1", LocalDate.parse("2008-02-11"),
                        "Norway"));
        User user2 = new User("rate", "you", "1777", "ratinglover",
                "12341234123", "rate@you.no", "12341234",
                new License("98 44 123456 1", LocalDate.parse("2008-02-11"),
                        "Norway"));

        UserProfile up = RetrieveUserProfileDB.retrieveFromEmail(user.getEmail());

        up.addNewRating(user2, 5);

        HashMap<User, Integer> ratings = RetrieveRatingDB.retrieve(up);

        String sql = "SELECT COUNT(*) AS amount FROM ratings WHERE user = " + user.getId() +
                " AND userGivingRating = " + user2.getId();

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

        assertFalse(up.getRatings().isEmpty());

        assertFalse(ratings.isEmpty());
    }

    @Test
    void assertsRatingsCanBeUpdated() {
        User user = new User("rate", "me", "1777", "ratingking",
                "12341234123", "rate@me.no", "12341234",
                new License("98 44 123456 1", LocalDate.parse("2008-02-11"),
                        "Norway"));
        User user2 = new User("rate", "you", "1777", "ratinglover",
                "12341234123", "rate@you.no", "12341234",
                new License("98 44 123456 1", LocalDate.parse("2008-02-11"),
                        "Norway"));

        UserProfile up = RetrieveUserProfileDB.retrieveFromEmail(user.getEmail());

        up.addNewRating(user2, 5);

        String sql = "SELECT rating FROM ratings WHERE user = " + user.getId() +
                " AND userGivingRating = " + user2.getId();

        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();

            assertEquals(5, queryResult.getInt("rating"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        up.addNewRating(user2, 3);

        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();

            assertEquals(3, queryResult.getInt("rating"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void assertsAverageRatingsCanBeCalculated() {
        User user = new User("rate", "me", "1777", "ratingking",
                "12341234123", "rate@me.no", "12341234",
                new License("98 44 123456 1", LocalDate.parse("2008-02-11"),
                        "Norway"));
        User user2 = new User("rate", "you", "1777", "ratinglover",
                "12341234123", "rate@you.no", "12341234",
                new License("98 44 123456 1", LocalDate.parse("2008-02-11"),
                        "Norway"));
        User user3 = new User("rating", "twice", "1777", "divisionlover",
                "12341234123", "rate@youtoo.no", "12341234",
                new License("98 44 123456 1", LocalDate.parse("2008-02-11"),
                        "Norway"));

        UserProfile up = RetrieveUserProfileDB.retrieveFromEmail(user.getEmail());

        int ratingFromUser2 = 5;
        int ratingFromUser3 = 3;
        double averageRating = ((ratingFromUser2 + ratingFromUser3) / 2);

        up = RetrieveUserProfileDB.retrieveFromEmail(user.getEmail());

        up.addNewRating(user2, ratingFromUser2);
        up.addNewRating(user3, ratingFromUser3);

        assertEquals(4, averageRating);
        assertEquals(averageRating, up.getAverageRating());
        assertEquals(averageRating, Double.parseDouble(RetrieveAverageRatingDB.retrieve(up)));
    }

    @Test
    void assertsAverageRatingsCanBeUpdated() {
        User user = new User("rate", "you", "1777", "ratinglover",
                "12341234123", "rate@you.no", "12341234",
                new License("98 44 123456 1", LocalDate.parse("2008-02-11"),
                        "Norway"));
        User user2 = new User("rate", "me", "1777", "ratingking",
                "12341234123", "rate@me.no", "12341234",
                new License("98 44 123456 1", LocalDate.parse("2008-02-11"),
                        "Norway"));

        User user3 = new User("rating", "twice", "1777", "divisionlover",
                "12341234123", "rate@youtoo.no", "12341234",
                new License("98 44 123456 1", LocalDate.parse("2008-02-11"),
                        "Norway"));

        UserProfile up = RetrieveUserProfileDB.retrieveFromEmail(user.getEmail());

        int ratingFromUser2 = 5;
        int ratingFromUser3 = 3;
        double averageRating = ((ratingFromUser2 + ratingFromUser3) / 2);

        up = RetrieveUserProfileDB.retrieveFromEmail(user.getEmail());

        up.addNewRating(user2, ratingFromUser2);
        up.addNewRating(user3, ratingFromUser3);

        assertEquals(4, averageRating);
        assertEquals(averageRating, up.getAverageRating());
        assertEquals(averageRating, Double.parseDouble(RetrieveAverageRatingDB.retrieve(up)));

        ratingFromUser3 = 1;
        up.addNewRating(user3, ratingFromUser3);
        averageRating = ((ratingFromUser2 + ratingFromUser3) / 2);

        assertEquals(3, averageRating);
        assertEquals(averageRating, up.getAverageRating());
        assertEquals(averageRating, Double.parseDouble(RetrieveAverageRatingDB.retrieve(up)));
    }

    @Test
    void assertsVehiclesCanBeSerialised() {
        Vehicle car = new Car("12341234", "audi", "tt", "petrol",
                "automatic", 2013, 5, 1500);

        assertTrue(car.existsInDb());
    }

    @Test
    void assertsLicensesCanBeSerialised() {
        License license = new License("98 43 123456 1", LocalDate.parse("2008-05-12"),
                "Norway");

        assertTrue(license.existsInDb());
    }

    @Test
    void assertsRentOutAdCanBeSerialised() {
        Vehicle car = new Car("12341234", "audi", "tt", "petrol",
                "automatic", 2013, 5, 1500);

        License license = new License("98 43 123456 1", LocalDate.parse("2008-05-12"),
                "Norway");

        User user = new User("john", "squiglet", "1777", "mmmcars",
                "12341234123", "rentmycar@car.no", "12341234",
                license);

        RentOutAd roa = new RentOutAd(
                user,
                car,
                BigDecimal.valueOf(200), BigDecimal.valueOf(10), "Sarpsborg"
        );

        assertTrue(roa.existsInDb());
    }

    @Test
    void assertsAvailableWithinPeriodCanBeSerialised() {
        /*Vehicle car = new Car("12341234", "audi", "tt", "petrol",
                "automatic", 2013, 5, 1500);

        License license = new License("98 43 123456 1", LocalDate.parse("2008-05-12"),
                "Norway");

        User user = new User("john", "squiglet", "1777", "mmmcars",
                "12341234123", "rentmycar@car.no", "12341234",
                license);

        RentOutAd roa = new RentOutAd(
                user,
                car,
                BigDecimal.valueOf(200), BigDecimal.valueOf(10), "Sarpsborg"
        );

        assertTrue(roa.existsInDb());

         */
    }

    @Test
    void assertsRentOutAdDateCreatedIsCorrect() {

    }

    @Test
    void assertsRentOutAdDateChangedIsUpdated() {

    }

    @Test
    void assertsBookingsCanBeSerialised() {

    }

    @Test
    void assertsBookingsThatClashWillNotBeSerialised() {

    }

    @Test
    void assertsBookingsWithDateBeforePresentWillNotBeSerialised() {

    }

    @Test
    void assertsPaymentsCanBeSerialised() {

    }

}