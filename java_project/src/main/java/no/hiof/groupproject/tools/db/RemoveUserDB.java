package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.Advertisement;

import java.sql.*;
import java.util.ArrayList;

//Returns a specific TreeMap of all valid rental periods in the database based on a specific User

public class RemoveUserDB {

    public static void remove(int id) {

        String sql = "DELETE FROM users WHERE users_id = " + id;

        //foreign keys are automatically turned off on connection to database
        //here I turn them on again so that ON DELETE CASCADE works properly
        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql);
             Statement str2 = conn.createStatement()) {

            str2.execute("PRAGMA foreign_keys = ON;");
            str.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
/*
    public static void removeWithPriorConnection(int id, Connection conn) throws SQLException {

        String sql = "DELETE FROM users WHERE users_id = " + id;


        PreparedStatement str = conn.prepareStatement(sql);
        Statement str2 = conn.createStatement();

        str2.execute("PRAGMA foreign_keys = ON;");
        str.executeUpdate();

    }*/

}