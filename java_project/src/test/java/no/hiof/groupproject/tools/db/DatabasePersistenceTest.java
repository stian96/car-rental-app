package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.License;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.UserProfile;
import org.junit.jupiter.api.*;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        User user2 = RetrieveUserDB.retrieveFromEmail("testing@this.email");

        assertEquals(user.getEmail(), user2.getEmail());
        assertEquals(user.getPassword(), user2.getPassword());
    }

    @Test
    void assertsUserClassBasicIsSerialised() {
        User user = new User("test2@this.email", "butterismyfavouritefood");
        assertNull(user.getFirstName());

        User user2 = RetrieveUserDB.retrieveFromEmail("test2@this.email");
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

        user2 = RetrieveUserDB.retrieveFromEmail("another@test.no");

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

    }

    @Test
    void assertsUserCreatesUserProfile() {

    }

    @Test
    void assertsUserUpgradeCreatesUserProfile() {

    }

}