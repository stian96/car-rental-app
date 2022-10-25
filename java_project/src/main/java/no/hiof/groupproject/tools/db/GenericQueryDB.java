package no.hiof.groupproject.tools.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
Used to execute a single query when nothing else works
Example:
    GenericQueryDB.query("INSERT INTO people(name,age) VALUES('jesper', 14)");
 */
public class GenericQueryDB {

    public static void query(String query) {

        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(query)) {
            str.executeUpdate();
            //conn.close();   //not necessary - try catch closes the resource after being used
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}