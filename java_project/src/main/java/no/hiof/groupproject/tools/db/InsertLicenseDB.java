package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.tools.License;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
A class used to serialise a Booking into a database for permanent storage.
 */
public class InsertLicenseDB {

    public static void insert(License dLicense) {

        String sql = "INSERT INTO licenses (licenseNumber, dateOfIssue, countryOfIssue)" +
                "VALUES(?,?,?)";

        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {
            str.setString(1, dLicense.getLicenseNumber());
            str.setString(2, dLicense.getDateOfIssue().toString());
            str.setString(3, dLicense.getCountryOfIssue());
            str.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}