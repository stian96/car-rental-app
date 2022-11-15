package no.hiof.groupproject.tools.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteMessagesFromDB {

    public void delete(String table, String col, String message) {

        //choose a table, then a column, then a value. All rows with that value will then be deleted
        //eg DELETE FROM people WHERE message = "your message"
        String sql = "DELETE FROM " + table + " WHERE " + col + " = ?";

        try (Connection conn = ConnectDB.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            //sets the row value
            pstmt.setString(1, message);
            //deletes the stated rows
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
