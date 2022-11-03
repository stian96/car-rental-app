package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.interfaces.DeserialiseUser;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.UserProfile;
import no.hiof.groupproject.tools.License;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

/*
Returns a specific User in the database based on either the id or email of the User, both of which are unique values
*/
public class RetrieveUserProfileDB {

    public static UserProfile retrieve(int id) {

        String sql = "SELECT * FROM userProfiles WHERE user_fk= " + id;

        UserProfile returnedUserProfile = null;

        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();

            User user = RetrieveUserDB.retrieveFromId(id);
            returnedUserProfile = new UserProfile(user);
            HashMap<User, Integer> ratings = RetrieveRatingDB.retrieve(returnedUserProfile);
            returnedUserProfile.setRatings(ratings);
            returnedUserProfile.setAverageRating(Double.parseDouble(RetrieveAverageRatingDB.retrieve(returnedUserProfile)));


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return returnedUserProfile;
    }

}
