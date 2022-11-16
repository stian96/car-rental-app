package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.interfaces.AvailableWithinExistsInDb;
import no.hiof.groupproject.models.Advertisement;
import no.hiof.groupproject.models.RentOutAd;
import no.hiof.groupproject.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

/*
A class used to serialise a TreeMap of dates available to book into a database for permanent storage.
IMPORTANT: SQLite table 'availableWithin' has a multiple column constraint to prevent the same periods being added
 */
public class InsertRatingDB {

    public static void insert(User user, User userGivingRating, int rating) {

        String sql = "INSERT INTO ratings (user, userGivingRating, rating)" +
                "VALUES(?,?,?)";

        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            str.setInt(1, user.getId());
            str.setInt(2, userGivingRating.getId());
            str.setInt(3, rating);
            str.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    //used to update the rating without creating an additional row
    public static void update(User user, User userGivingRating, int rating) {

        String sql = "UPDATE ratings SET rating = " + rating +
                " WHERE userGivingRating = " + userGivingRating.getId() + " AND user = " + user.getId();

        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            str.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

   /* public static void insertWithPriorConnection(User user, User userGivingRating, int rating, Connection conn) throws SQLException {

        String sql = "INSERT INTO ratings (user, userGivingRating, rating)" +
                "VALUES(?,?,?)";

        PreparedStatement str = conn.prepareStatement(sql);

        str.setInt(1, user.getId());
        str.setInt(2, userGivingRating.getId());
        str.setInt(3, rating);
        str.executeUpdate();

    }

    //used to update the rating without creating an additional row
    public static void updateWithPriorConnection(User user, User userGivingRating, int rating, Connection conn) throws SQLException {

        String sql = "UPDATE ratings SET rating = " + rating +
                " WHERE userGivingRating = " + userGivingRating.getId() + " AND user = " + user.getId();

        PreparedStatement str = conn.prepareStatement(sql);

        str.executeUpdate();

    }*/


}