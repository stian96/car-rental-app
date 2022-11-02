package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.User;
import no.hiof.groupproject.tools.VerifyLicense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/*
Returns a specific User in the database based on either the id or email of the User, both of which are unique values
 */
public class RetrieveLicenseDB {

    /*
    public static VerifyLicense retrieveFromId(int id) {

        String sql = "SELECT * FROM licenses WHERE user_fk = " + id;

        VerifyLicense dLicense = null;
        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            String licenseNumber = queryResult.getString("licenseNumber");
            String dateOfIssue = queryResult.getString("dateOfIssue");
            String countryOfIssue = queryResult.getString("countryOfIssue");

            dLicense = new VerifyLicense(licenseNumber, LocalDate.parse(dateOfIssue), countryOfIssue);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dLicense;
    }

     */

    public static VerifyLicense retrieveFromLicenseNr(String licenseNumber) {

        String sql = "SELECT * FROM licenses WHERE licenseNumber = " + licenseNumber;

        VerifyLicense dLicense = null;
        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            String dateOfIssue = queryResult.getString("dateOfIssue");
            String countryOfIssue = queryResult.getString("countryOfIssue");
            int ownerId = queryResult.getInt("users_fk");

            dLicense = new VerifyLicense(licenseNumber, LocalDate.parse(dateOfIssue), countryOfIssue);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dLicense;
    }
}