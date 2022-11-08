package no.hiof.groupproject.interfaces;

import no.hiof.groupproject.models.Advertisement;
import no.hiof.groupproject.tools.db.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

//static interface to help with serialisation of TreeMap serialisation, and to avoid saving duplicates
public interface AvailableWithinExistsInDb {

    static boolean existsInDb(Advertisement advertisement, LocalDate dateFrom, LocalDate dateTo) {
        String sql = "SELECT COUNT(*) AS amount FROM availableWithin WHERE availableWithin_id_fk = " +
                advertisement.getUser().getId() + " AND dateFrom = \'" + dateFrom.toString() +
                "\' AND dateTo = \'" + dateTo.toString() + "\'";

        boolean ans = false;
        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            if (queryResult.getInt("amount") > 0) {
                ans = true;
            }
            return ans;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
