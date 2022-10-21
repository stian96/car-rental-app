package no.hiof.groupproject.tools.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
Deletes rows from a database.
Create this class and then call the delete() method along with the following arguments:
    DeleteFromDB db = new DeleteFromDB();
    db.delete(String table, String col, int val);
The code is based on the code from https://www.sqlitetutorial.net/sqlite-java/delete/ taken at 13:00
on the 14/10/2022.
@author sqlitetutorial.net
 */
public class DeleteFromDB {

    public void delete(String table, String col, int val) {

        //choose a table, then a column, then a value. All rows with that value will then be deleted
        //eg DELETE FROM people WHERE people_pk = 5
        String sql = "DELETE FROM " + table + " WHERE " + col + " = ?";

        try (Connection conn = ConnectDB.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            //sets the row value
            pstmt.setInt(1, val);
            //deletes the stated rows
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}