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
A class used to serialise a TreeMap of dates available to book into a database for permanent storage.
 */
public class InsertAvailableWithinDB {

    public static void insert(Advertisement advertisement, TreeMap<LocalDate, LocalDate> availableWithin) {

        String sql = "INSERT INTO availableWithin (availableWithin_id_fk, dateFrom, dateTo)" +
                "VALUES(?,?,?)";

        for (Map.Entry<LocalDate, LocalDate> set : ((RentOutAd) advertisement).getAvailableWithin().entrySet()) {
            try (Connection conn = ConnectDB.connect();
                 PreparedStatement str = conn.prepareStatement(sql)) {


                if (!AvailableWithinExistsInDb.existsInDb(advertisement, set)) {
                    str.setInt(1, advertisement.getId());
                    str.setString(2, set.getKey().toString());
                    str.setString(3, set.getValue().toString());
                    str.executeUpdate();
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}