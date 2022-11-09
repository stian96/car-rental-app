package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.Advertisement;
import no.hiof.groupproject.models.vehicle_types.Vehicle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    @Test
    void assertsMainDbExists() {
        File f = new File("sqlite/db/test.db");
        assertTrue(f.exists());
    }
    @Test
    void assertsTestableDbExists() {
        File f = new File("sqlite/db/testable.db");
        assertTrue(f.exists());
    }

    @Test
    void assertsDefaultDatabaseLocationIsTestDotDb() {

        assertEquals("jdbc:sqlite:sqlite/db/test.db", ConnectDB.getDb());
    }

    @Test
    void assertsTablesExistInDatabase() {

        String sql = "SELECT * FROM sqlite_master GROUP BY tbl_name";

        boolean ans = true;
        // all table names in the database
        String[] tblNames = {"advertisements", "availableWithin", "bookings", "licenses", "messages", "payments",
                "ratings", "userProfiles", "users", "vehicles", "sqlite_master", "sqlite_sequence"};
        try (Connection conn = ConnectDB.connect();
             Statement str = conn.createStatement();
             ResultSet rs = str.executeQuery(sql)) {

            //checks to see if all tables exist in database
            while (rs.next()) {
                String table = rs.getString("tbl_name");
                for (String s : tblNames) {
                    if (!Arrays.asList(tblNames).contains(table)) {
                        ans = false;
                        break;
                    }
                    if (!ans) {
                        break;
                    }
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        assertTrue(ans);
    }

    @Test
    void assertsTablesExistInTestDatabase() {

        String sql = "SELECT * FROM sqlite_master GROUP BY tbl_name";

        boolean ans = true;
        // all table names in the database
        String[] tblNames = {"advertisements", "availableWithin", "bookings", "licenses", "messages", "payments",
                "ratings", "userProfiles", "users", "vehicles", "sqlite_master", "sqlite_sequence"};
        try (Connection conn = ConnectSpecifiedDB.connect("jdbc:sqlite:sqlite/db/testable.db");
             Statement str = conn.createStatement();
             ResultSet rs = str.executeQuery(sql)) {

            //checks to see if all tables exist in database
            while (rs.next()) {
                String table = rs.getString("tbl_name");
                for (String s : tblNames) {
                    if (!Arrays.asList(tblNames).contains(table)) {
                        ans = false;
                        break;
                    }
                    if (!ans) {
                        break;
                    }
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        assertTrue(ans);
    }

    @Test
    void assertsContrapositiveTableExistenceLogic() {

        String sql = "SELECT * FROM sqlite_master GROUP BY tbl_name";

        boolean ans = true;
        // tblNames contains tables that aren't supposed to exist in the database
        String[] tblNames = {"wrong", "table", "array"};
        try (Connection conn = ConnectSpecifiedDB.connect("jdbc:sqlite:sqlite/db/testable.db");
             Statement str = conn.createStatement();
             ResultSet rs = str.executeQuery(sql)) {

            //checks to see if all tables exist in database
            while (rs.next()) {
                String table = rs.getString("tbl_name");
                for (String s : tblNames) {
                    if (!Arrays.asList(tblNames).contains(table)) {
                        ans = false;
                        break;
                    }
                    if (!ans) {
                        break;
                    }
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        assertFalse(ans);
    }

    @Test
    void assertsDatabaseLocationCanBeAltered() {

        ConnectDB.setDb("jdbc:sqlite:sqlite/db/test.db");
        assertEquals("jdbc:sqlite:sqlite/db/test.db", ConnectDB.getDb());

        ConnectDB.setDb("jdbc:sqlite:sqlite/db/testable.db");
        assertEquals("jdbc:sqlite:sqlite/db/testable.db", ConnectDB.getDb());

        ConnectDB.setDb("jdbc:sqlite:sqlite/db/test.db");
        assertEquals("jdbc:sqlite:sqlite/db/test.db", ConnectDB.getDb());
    }

}