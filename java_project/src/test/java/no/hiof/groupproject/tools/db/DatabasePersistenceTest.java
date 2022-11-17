package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.interfaces.AvailableWithinExistsInDb;
import no.hiof.groupproject.models.*;
import no.hiof.groupproject.models.payment_methods.CreditDebit;
import no.hiof.groupproject.models.payment_methods.GooglePay;
import no.hiof.groupproject.models.payment_methods.Paypal;
import no.hiof.groupproject.models.payment_methods.Vipps;
import no.hiof.groupproject.models.vehicle_types.Car;
import no.hiof.groupproject.models.vehicle_types.Vehicle;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

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

        roa.addNewPeriod(LocalDate.parse("2030-01-01"), LocalDate.parse("2031-01-01"));

        assertTrue(roa.availableWithinExistsInDb(LocalDate.parse("2030-01-01"), LocalDate.parse("2031-01-01")));
        assertTrue(AvailableWithinExistsInDb.existsInDb(roa,LocalDate.parse("2030-01-01"), LocalDate.parse("2031-01-01") ));
    }

    @Test
    void assertsRentOutAdDateCreatedIsCorrect() {
       //first created this entry on 2022-11-011
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

        RentOutAd roa2 = ((RentOutAd)RetrieveAdvertisementDB.retrieveFromId(roa.getId()));

        assertEquals(LocalDate.parse("2022-11-11"), roa2.getDateCreated());
    }

    @Test
    void assertsRentOutAdDateChangedIsUpdated() {
        //first created this test database on 2022-11-09
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

        roa.addNewPeriod(LocalDate.parse("2032-01-01"), LocalDate.parse("2033-01-01"));

        RentOutAd roa2 = ((RentOutAd)RetrieveAdvertisementDB.retrieveFromId(roa.getId()));

        assertEquals(LocalDate.now(), roa2.getDateLastChanged());
    }

    @Test
    void assertsBookingsCanBeSerialised() {
        Vehicle car = new Car("22334455", "aston martin", "db5", "petrol",
                "manual", 1963, 2, 400);

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
                BigDecimal.valueOf(500), BigDecimal.valueOf(25), "Halden"
        );

        roa.addNewPeriod(LocalDate.parse("2028-01-01"), LocalDate.parse("2029-01-01"));
        roa.addNewPeriod(LocalDate.parse("2030-01-01"), LocalDate.parse("2031-01-01"));

        LocalDate bookingStart = LocalDate.parse("2028-02-01");
        LocalDate bookingEnd = LocalDate.parse("2028-03-01");
        Paypal paypal = new Paypal("rubber@hotmail.no", "greatscott");

        roa.addBooking(new Booking(
                renter,
                owner,
                bookingStart,
                bookingEnd,
                paypal,
                roa.getVehicle()));

        Booking booking = RetrieveBookingDB.retrieve(
                renter.getId() + "." + bookingStart + "." + owner.getId());

        assertNotNull(booking);
    }

    @Test
    void assertsBookingsThatClashWillNotBeSerialised() {
        Vehicle car = new Car("22334455", "aston martin", "db5", "petrol",
                "manual", 1963, 2, 400);

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
                BigDecimal.valueOf(500), BigDecimal.valueOf(25), "Halden"
        );

        roa.addNewPeriod(LocalDate.parse("2028-01-01"), LocalDate.parse("2029-01-01"));
        roa.addNewPeriod(LocalDate.parse("2030-01-01"), LocalDate.parse("2031-01-01"));

        LocalDate bookingStart = LocalDate.parse("2028-02-01");
        LocalDate bookingEnd = LocalDate.parse("2028-03-01");

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

        bookingStart = LocalDate.parse("2028-02-11");
        bookingEnd = LocalDate.parse("2028-03-11");

        roa.addBooking(new Booking(
                renter,
                owner,
                bookingStart,
                bookingEnd,
                paypal,
                roa.getVehicle()));

        ;

        LocalDate finalBookingStart = bookingStart;

        assertThrows(NullPointerException.class, () -> booking.set(RetrieveBookingDB.retrieve(
                renter.getId() + "." + finalBookingStart + "." + owner.getId())));
    }

    @Test
    void assertsBookingsWithDateBeforePresentWillNotBeSerialised() {
        Vehicle car = new Car("22334455", "aston martin", "db5", "petrol",
                "manual", 1963, 2, 400);

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
                BigDecimal.valueOf(500), BigDecimal.valueOf(25), "Halden"
        );

        roa.addNewPeriod(LocalDate.parse("2028-01-01"), LocalDate.parse("2029-01-01"));
        roa.addNewPeriod(LocalDate.parse("2030-01-01"), LocalDate.parse("2031-01-01"));

        //2001 is in the past :')
        LocalDate bookingStart = LocalDate.parse("2001-02-01");
        LocalDate bookingEnd = LocalDate.parse("2001-03-01");

        Paypal paypal = new Paypal("rubber@hotmail.no", "greatscott");

        roa.addBooking(new Booking(
                renter,
                owner,
                bookingStart,
                bookingEnd,
                paypal,
                roa.getVehicle()));


        final Booking[] booking = new Booking[1];
        assertThrows(NullPointerException.class, () -> booking[0] = RetrieveBookingDB.retrieve(
                renter.getId() + "." + bookingStart + "." + owner.getId()));
    }

    @Test
    void assertsPaymentsCanBeSerialised() {

       Vipps vipps = new Vipps("12341234", "1234");
       Paypal paypal = new Paypal("ex@amp.le", "password");
       GooglePay googlePay = new GooglePay("exa@mpl.e", "pswrd");
       CreditDebit creditDebit = new CreditDebit("1234123412341234", "777", 12, 2030);

       Vipps vipps1 = ((Vipps)RetrievePaymentDB.retrieve(vipps.getId()));
       Paypal paypal1 = ((Paypal)RetrievePaymentDB.retrieve(paypal.getId()));
       GooglePay googlePay1 = ((GooglePay)RetrievePaymentDB.retrieve(googlePay.getId()));
       CreditDebit creditDebit1 = ((CreditDebit)RetrievePaymentDB.retrieve(creditDebit.getId()));

       assertNotNull(vipps1);
       assertNotNull(paypal1);
       assertNotNull(googlePay1);
       assertNotNull(creditDebit1);
    }

    @Test
    void assertsLicenseCanBeSerialised() {

        License license = new License("98 38 123456 1", LocalDate.parse("2008-05-12"),
                "Norway");

        assertTrue(license.existsInDb());
    }

    @Test
    void assertsInsertAndDeleteFromDB() {

        InsertDB.insert("payments", "paymentType, tlfnr", "'vipps', 96286479");

        String sql = "SELECT COUNT(*) AS amount FROM payments WHERE tlfnr = 96286479";

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

        DeleteFromDB.delete("payments", "tlfnr", "96286479");

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
    void assertsAllVehiclesCanBeDeserialised() {

        assertFalse(RetrieveVehiclesDB.retrieveAllVehiclesAndAdvertisementsId().isEmpty());
        assertFalse(RetrieveVehiclesDB.retrieveAllVehiclesId().isEmpty());
        assertFalse(RetrieveVehiclesDB.retrieveAllVehiclesObject().isEmpty());
        assertFalse(RetrieveVehiclesDB.retrieveAllVehiclesAndAdvertisementsObject().isEmpty());
    }

    @Test
    void assertsRatingsInsertCorrectlySerialises() {
        User user = new User("rating", "receiver", "1777", "ratingking",
                "12341234123", "rate@me.com", "12341234",
                new License("98 44 123456 1", LocalDate.parse("2008-02-11"),
                        "Norway"));
        User user2 = new User("rating", "giver", "1777", "ratinglover",
                "12341234123", "rate@you.com", "12341234",
                new License("98 44 123456 1", LocalDate.parse("2008-02-11"),
                        "Norway"));

        InsertRatingDB.insert(user, user2, 3);

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

    }

    @Test
    void assertsCreditDebitInsertCorrectlySerialises() {


        CreditDebit cd = new CreditDebit("6479084575441564", "991", 4, 2031);

        String sql = "SELECT COUNT(*) AS amount FROM payments WHERE cardNumber = " + cd.getCard_number();

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
    void assertsPaypalInsertCorrectlySerialises() {


        Paypal pp = new Paypal("jorn@hoel.no", "magicboi");

        String sql = "SELECT COUNT(*) AS amount FROM payments WHERE email = \'" + pp.getEmail() + "\'";

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
    void assertsGooglePayInsertCorrectlySerialises() {


        GooglePay gp = new GooglePay("abba@bb.aa", "asgfdl");

        String sql = "SELECT COUNT(*) AS amount FROM payments WHERE email = \'" + gp.getEmail() + "\'";

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
    void assertsVippsInsertCorrectlySerialises() {


        Vipps vi = new Vipps("01547426", "5367");

        String sql = "SELECT COUNT(*) AS amount FROM payments WHERE tlfnr = \'" + vi.getTlfnr() + "\'";

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
    void assertsRetrieveAdvertisementsByIdCorrectlyDeserialises() {
        ArrayList<Integer> list = new ArrayList<>();
        assertTrue(list.isEmpty());

        list = RetrieveAdvertisementsDB.retrieveAdvertisementsIdFromUserId(13);

        assertFalse(list.isEmpty());
    }

    @Test
    void assertsLicenseCanBeCorrectlySerialisedFromInsert() {
        InsertLicenseDB.insert(new License("98 37 123456 1", LocalDate.parse("2020-05-05"), "Norway"));

        String sql = "SELECT COUNT(*) AS amount FROM licenses WHERE licenseNumber = '98 37 123456 1'";

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
    void assertsLicenseCanBeCorrectlyDeerialisedFromLicenseNr() {
        InsertLicenseDB.insert(new License("98 37 123456 1", LocalDate.parse("2020-05-05"), "Norway"));

        License l = null;
        assertNull(l);

        l = RetrieveLicenseDB.retrieveFromLicenseNr("98 37 123456 1");
        assertNotNull(l);
    }

    @Test
    void assertsLicenseCanBeCorrectlyDeerialisedFromId() {

        User user = new User("ronny", "pickering", "1777", "catsanddogs",
                "12341234123", "another@test.no", "12341234",
                new License("98 45 123456 1", LocalDate.parse("2008-05-12"),
                        "Norway"));

        License l = null;
        assertNull(l);

        l = RetrieveLicenseDB.retrieveFromId(user.getId());
        assertEquals(user.getdLicense().getLicenseNumber(), l.getLicenseNumber());
    }

    @Test
    void assertsVehicleCanBeDeserialisedFromRegNo() {

        Vehicle car = new Car("12341234", "audi", "tt", "petrol",
                "automatic", 2013, 5, 1500);

        Vehicle carTest = null;
        assertNull(carTest);

        carTest = RetrieveVehicleDB.retrieveFromRegNo(car.getRegNo());

        assertEquals(car.getRegNo(), carTest.getRegNo());
    }

}