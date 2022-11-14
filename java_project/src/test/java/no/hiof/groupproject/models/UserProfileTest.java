package no.hiof.groupproject.models;

import no.hiof.groupproject.tools.db.ConnectDB;
import no.hiof.groupproject.tools.db.RetrieveUserDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserProfileTest {

    @BeforeEach
    void initialiseDatabasePath() {
        ConnectDB.setDb("jdbc:sqlite:sqlite/db/testable.db");
    }
    @AfterEach
    void rewindDatabasePath() {
        ConnectDB.setDb("jdbc:sqlite:sqlite/db/test.db");
    }


    License license = new License("98 45 123456 1", LocalDate.parse("2008-05-12"),
            "Norway");

    User user = new User("sam", "davies", "1111", "hunter2",
            "12341234123", "sam@sam.no", "12341234", license);

    UserProfile userProfile = new UserProfile(user);
    private HashMap<User, Integer> ratings = new HashMap<>();
/*
    License license = new License("98 45 123456 1", LocalDate.parse("2008-05-12"),
            "Norway");

    User user =
            new User("Jillian","Andes","1526","jack1",
            "10333922","gigi@gmail.com","6256728272", license);

 */


    User user1 = new User("Jillian","Andes","1526","jack1",
            "10333922","gigi@gmail.com","6256728272", license);
    User user2 = new User("Jillian","Andes","1526","jack1",
            "10333922","gigi@gmail.com","6256728272", license);
    User user3 = new User("Jillian","Andes","1526","jack1",
            "10333922","gigi@gmail.com","6256728272", license);

    @Test
    public void newRatingIsAddedAndAvgIsCalculated(){
        userProfile.addNewRating(user2,3);
        userProfile.addNewRating(user1,3);
        userProfile.addNewRating(user3,3);


        double val = userProfile.calculateAverageRating();
        assertEquals(3.0, val);

    }




}