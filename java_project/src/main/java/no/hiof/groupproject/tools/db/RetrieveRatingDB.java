package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.UserProfile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/*
Returns a specific HashMap of all ratings in the database based on a specific UserProfile
 */
public class RetrieveRatingDB {

    public static HashMap<User, Integer> retrieve(UserProfile userProfile) {

        String sql = "SELECT userGivingRating, rating FROM ratings " +
                "WHERE user = " + userProfile.getUser().getId();
        HashMap<User, Integer> ratings = new HashMap<>();

        try (Connection conn = ConnectDB.connectReadOnly();
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
/*

    public static HashMap<User, Integer> retrieveWithPriorConnection(UserProfile userProfile, Connection conn) throws SQLException {

        String sql = "SELECT userGivingRating, rating FROM ratings " +
                "WHERE user = " + userProfile.getUser().getId();
        HashMap<User, Integer> ratings = new HashMap<>();

         Statement str = conn.createStatement();
         ResultSet rs = str.executeQuery(sql);

        //loops through rows in the sql SELECT statement
        while (rs.next()) {
            ratings.put(RetrieveUserDB.retrieveFromId(rs.getInt("userGivingRating")),
                    rs.getInt("rating"));
        }
        return ratings;

    }
*/

}