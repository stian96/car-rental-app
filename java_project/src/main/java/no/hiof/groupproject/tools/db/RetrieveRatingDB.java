package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.UserProfile;

import java.sql.*;
import java.util.HashMap;

/*
Returns a specific HashMap of all ratings in the database based on a specific UserProfile
 */
public class RetrieveRatingDB {

    public static HashMap<User, Integer> retrieve(UserProfile userProfile) {

        String sql = "SELECT userGivingRating, rating FROM ratings " +
                "WHERE user = " + userProfile.getUser().getId();
        HashMap<User, Integer> ratings = new HashMap<>();

        String i = null;
        try (Connection conn = ConnectDB.connect();
             Statement str = conn.createStatement();
             ResultSet rs = str.executeQuery(sql)) {

            //loops through rows in the sql SELECT statement
            while (rs.next()) {
                ratings.put(RetrieveUserDB.retrieveFromId(rs.getInt("userGivingRating")),
                        rs.getInt("rating"));
            }
            return ratings;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ratings;
    }

}