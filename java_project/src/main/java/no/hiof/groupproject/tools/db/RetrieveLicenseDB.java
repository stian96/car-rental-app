package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.License;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/*
Returns a specific License in the database based on either the id of the User or licenseNumber, both of which are unique

 */
public class RetrieveLicenseDB {

    public static License retrieveFromLicenseNr(String licenseNumber) {

        String sql = "SELECT * FROM licenses WHERE licenseNumber = \'" + licenseNumber + "\'";

        License dLicense = null;
        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            String dateOfIssue = queryResult.getString("dateOfIssue");
            String countryOfIssue = queryResult.getString("countryOfIssue");

            dLicense = new License(licenseNumber, LocalDate.parse(dateOfIssue), countryOfIssue);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dLicense;
    }


    public static License retrieveFromId(int id) {

        String sql = "SELECT * FROM users INNER JOIN licenses ON license=licenseNumber WHERE users_id = " + id;

        License dLicense = null;
        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            String licenseNumber = queryResult.getString("licenseNumber");
            String dateOfIssue = queryResult.getString("dateOfIssue");
            String countryOfIssue = queryResult.getString("countryOfIssue");

            dLicense = new License(licenseNumber, LocalDate.parse(dateOfIssue), countryOfIssue);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dLicense;
    }

}