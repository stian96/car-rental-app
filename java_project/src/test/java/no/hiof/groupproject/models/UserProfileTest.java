package no.hiof.groupproject.models;

import no.hiof.groupproject.tools.VerifyLicense;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class UserProfileTest {
    UserProfile userProfile = new UserProfile();
    private HashMap<User, Integer> ratings = new HashMap<>();

    VerifyLicense license = new VerifyLicense("98 45 123456 1", LocalDate.parse("2008-05-12"),
            "Norway");

    User user = new User("Jillian","Andes","1526","jack1",
            "10333922","gigi@gmail.com","6256728272", license);
    User user1 = new User("Jillian","Andes","1526","jack1",
            "10333922","gigi@gmail.com","6256728272", license);
    User user2 = new User("Jillian","Andes","1526","jack1",
            "10333922","gigi@gmail.com","6256728272", license);

    @Test
    public void check_calculate_average_works(){
        ratings.put(user,1);
        ratings.put(user1,5);
        ratings.put(user2,3);
        double val = userProfile.calculateAverageRating(ratings);
        assertEquals(3.0, val);

    }
    @Test
    public void check_add_rating_works(){
        ratings.put(user,1);
        ratings.put(user1,5);

        double val = userProfile.addNewRating(user2,3);
        assertEquals(3.0, val);
    }



}