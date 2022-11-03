package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.UserProfile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.TreeMap;

/*
Returns a specific TreeMap of all valid rental periods in the database based on a specific User
 */
public class RetrieveAvailableWithinDB {

    public static TreeMap<LocalDate, LocalDate> retrieve(User user) {

        String sql = "SELECT dateFrom, dateTo FROM availableWithin " +
                "WHERE availableWithin_id_fk = " + user.getId();

        TreeMap<LocalDate, LocalDate> availableWithin = new TreeMap<>();

        try (Connection conn = ConnectDB.connect();
             Statement str = conn.createStatement();
             ResultSet rs = str.executeQuery(sql)) {

            //loops through rows in the sql SELECT statement
            while (rs.next()) {
                availableWithin.put(LocalDate.parse(rs.getString("dateFrom")),
                        LocalDate.parse(rs.getString("dateTo")));
            }
            return availableWithin;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return availableWithin;
    }

}