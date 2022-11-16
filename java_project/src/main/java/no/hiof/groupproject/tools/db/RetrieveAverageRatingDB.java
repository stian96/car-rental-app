package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.UserProfile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
Returns a specific AverageRating double in the form of String from the database based on a specific UserProfile
 */
public class RetrieveAverageRatingDB {

    public static String retrieve(UserProfile userProfile) {

        String sql = "SELECT AVG(rating) as avgRating FROM ratings " +
                "WHERE user = " + userProfile.getUser().getId();

        String i = null;
        try (Connection conn = ConnectDB.connectReadOnly();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            i = queryResult.getString("avgRating");
            return i;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i;
    }

    //used to update averageRating in userProfiles table after every refresh
    public static void update(UserProfile userProfile) {

        String avgRating = retrieve(userProfile);
        if (avgRating != null) {
            String sql = "UPDATE userProfiles SET averageRating = \'" + avgRating +
                    "\' WHERE user_fk = " + userProfile.getUser().getId();

            try (Connection conn = ConnectDB.connect();
                 PreparedStatement str = conn.prepareStatement(sql)) {

                str.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

   /* public static String retrieveWithPriorConnection(UserProfile userProfile, Connection conn) throws SQLException {

        String sql = "SELECT AVG(rating) as avgRating FROM ratings " +
                "WHERE user = " + userProfile.getUser().getId();

        String i = null;
        PreparedStatement str = conn.prepareStatement(sql);

        ResultSet queryResult = str.executeQuery();
        i = queryResult.getString("avgRating");
        return i;

    }

    //used to update averageRating in userProfiles table after every refresh
    public static void updateWithPriorConnection(UserProfile userProfile, Connection conn) throws SQLException {

        String avgRating = retrieve(userProfile);
        if (avgRating != null) {
            String sql = "UPDATE userProfiles SET averageRating = \'" + avgRating +
                    "\' WHERE user_fk = " + userProfile.getUser().getId();

            PreparedStatement str = conn.prepareStatement(sql);

            str.executeUpdate();
        }
    }*/

}