package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.interfaces.AvailableWithinExistsInDb;
import no.hiof.groupproject.models.Advertisement;
import no.hiof.groupproject.models.Booking;
import no.hiof.groupproject.models.RentOutAd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

/*
A class used to serialise an ArrayWithin date available to book into a database for permanent storage.
IMPORTANT: SQLite table 'availableWithin' has a multiple column constraint to prevent the same periods being added
 */
public class InsertAvailableWithinDB {

    public static void insert(Advertisement advertisement, LocalDate dateFrom, LocalDate dateTo) {

        String sql = "INSERT INTO availableWithin (availableWithin_id_fk, dateFrom, dateTo)" +
                "VALUES(?,?,?)";

        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            str.setInt(1, advertisement.getId());
            str.setString(2, dateFrom.toString());
            str.setString(3, dateTo.toString());
            str.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}