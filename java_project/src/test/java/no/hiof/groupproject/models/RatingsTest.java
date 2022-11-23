package no.hiof.groupproject.models;

import no.hiof.groupproject.models.advertisements.RentOutAd;
import no.hiof.groupproject.models.payment_methods.Paypal;
import no.hiof.groupproject.models.vehicles.Vehicle;
import no.hiof.groupproject.models.vehicles.four_wheeled_vehicles.Car;
import no.hiof.groupproject.tools.db.ConnectDB;
import no.hiof.groupproject.tools.db.InsertRatingDB;
import no.hiof.groupproject.tools.db.RetrieveUserProfileDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RatingsTest {

    @BeforeEach
    void initialiseDatabasePath() {
        ConnectDB.setDb("jdbc:sqlite:sqlite/db/testable.db");
    }
    @AfterEach
    void rewindDatabasePath() {
        ConnectDB.setDb("jdbc:sqlite:sqlite/db/test.db");
    }


    User user = new User("rating", "receiver", "1777", "ratingking",
            "12341234123", "rate@me.co.uk", "12341234",
            new License("98 44 123456 1", LocalDate.parse("2008-02-11"),
                    "Norway"));
    User user2 = new User("rating", "giver", "1777", "ratinglover",
            "12341234123", "rate@you.co.uk", "12341234",
            new License("98 44 123456 1", LocalDate.parse("2008-02-11"),
                    "Norway"));

    @Test
    void assertsValidRatingIsAddedToUserProfile() {

        UserProfile up = RetrieveUserProfileDB.retrieve(user.getId());
        up.addNewRating(user2, 4);
        HashMap<User, Integer> ratings = up.getRatings();
        System.out.println(ratings);

        assertTrue(ratings.containsValue(4));

    }

    @Test
    void assertsValidRatingIsUpdatedToUserProfile() {

        UserProfile up = RetrieveUserProfileDB.retrieve(user.getId());


        up.addNewRating(user2, 4);
        up.addNewRating(user2, 2);

        up = RetrieveUserProfileDB.retrieve(user.getId());


        assertFalse(up.getRatings().containsValue(4));
        assertTrue(up.getRatings().containsValue(2));

    }

}