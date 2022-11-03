package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.UserProfile;

import java.sql.*;
import java.util.HashMap;

/*
Returns a specific User in the database based on either the id or email of the User, both of which are unique values
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