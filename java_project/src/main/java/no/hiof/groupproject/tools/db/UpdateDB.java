package no.hiof.groupproject.tools.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
Updates rows in a database.
Create this class and then call the update() method along with the following arguments:
    UpdateDB db = new UpdateDB();
    db.update(String table, String colFrom, String valFrom, String colTo, String valTo);
The code is -LOOSELY BASED- on the code from https://www.sqlitetutorial.net/sqlite-java/update/ taken at 13:00
on the 14/10/2022.
@author sqlitetutorial.net
 */
public class UpdateDB {

    public void update(String table, String colFrom, String valFrom, String colTo, String valTo) {

        //choose a table, then a column, then a value. All rows with that value will then be deleted
        // eg UPDATE people SET name = 'sam' WHERE name = 'samuel'
        String sql = "UPDATE " + table + " SET " + colFrom + " = ? WHERE "  + colTo + " = " + " ?";

        try (Connection conn = ConnectDB.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            //sets the row value
            pstmt.setString(1, valFrom);
            pstmt.setString(2, valTo);
            //updates the stated rows
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}