package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.License;
import no.hiof.groupproject.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/*
Returns a specific UserProfile in the database based on the User id
 */
public class RetrieveUserDB {

    public static User retrieveFromId(int id) {

        String sql = "SELECT * FROM users LEFT JOIN licenses ON license=licenseNumber WHERE users_id = " + id;


        User returnedUser = null;

        try (Connection conn = ConnectDB.connectReadOnly();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();

            String email = queryResult.getString("email");
            String password = queryResult.getString("password");

            returnedUser = new User(email, password);

            if (queryResult.getString("firstName") != null) {
                String firstName = queryResult.getString("firstName");
                returnedUser.setFirstName(firstName);
            }
            if (queryResult.getString("lastName") != null) {
                String lastName = queryResult.getString("lastName");
                returnedUser.setLastName(lastName);
            }
            if (queryResult.getString("postNr") != null) {
                String postNr = queryResult.getString("postNr");
                returnedUser.setPostNr(postNr);
            }
            if (queryResult.getString("bankAccountNr") != null) {
                String bankAccountNr = queryResult.getString("bankAccountNr");
                returnedUser.setBankAccountNr(bankAccountNr);
            }
            if (queryResult.getString("tlfNr") != null) {
                String tlfNr = queryResult.getString("tlfNr");
                returnedUser.setTlfNr(tlfNr);
            }
            if (queryResult.getString("license") != null) {
                String licenseNumber = queryResult.getString("license");
                String dateOfIssue = queryResult.getString("dateOfIssue");
                String countryOfIssue = queryResult.getString("countryOfIssue");

                returnedUser.setdLicense(new License(licenseNumber, LocalDate.parse(dateOfIssue), countryOfIssue));
            }

            returnedUser.setId(id);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return returnedUser;
    }

    public static User retrieveFromEmail(String email) {

        String sql = "SELECT * FROM users LEFT JOIN licenses ON license=licenseNumber WHERE email = \'" + email + "\'";

        User returnedUser = null;

        try (Connection conn = ConnectDB.connectReadOnly();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            int idNumber = queryResult.getInt("users_id");
            String password = queryResult.getString("password");

            returnedUser = new User(email, password);

            if (queryResult.getString("firstName") != null) {
                String firstName = queryResult.getString("firstName");
                returnedUser.setFirstName(firstName);
            }
            if (queryResult.getString("lastName") != null) {
                String lastName = queryResult.getString("lastName");
                returnedUser.setLastName(lastName);
            }
            if (queryResult.getString("postNr") != null) {
                String postNr = queryResult.getString("postNr");
                returnedUser.setPostNr(postNr);
            }
            if (queryResult.getString("bankAccountNr") != null) {
                String bankAccountNr = queryResult.getString("bankAccountNr");
                returnedUser.setBankAccountNr(bankAccountNr);
            }
            if (queryResult.getString("tlfNr") != null) {
                String tlfNr = queryResult.getString("tlfNr");
                returnedUser.setTlfNr(tlfNr);
            }
            if (queryResult.getString("license") != null) {
                String licenseNumber = queryResult.getString("license");
                String dateOfIssue = queryResult.getString("dateOfIssue");
                String countryOfIssue = queryResult.getString("countryOfIssue");

                License dLicense = new License(licenseNumber, LocalDate.parse(dateOfIssue), countryOfIssue);
                returnedUser.setdLicense(dLicense);
            }
            returnedUser.setId(idNumber);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return returnedUser;
    }
/*
    public static User retrieveFromIdWithPriorConnection(int id, Connection conn) throws SQLException {
        String sql = "SELECT * FROM users LEFT JOIN licenses ON license=licenseNumber WHERE users_id = " + id;
        User returnedUser = null;
        PreparedStatement str = conn.prepareStatement(sql);
        ResultSet queryResult = str.executeQuery();
        String email = queryResult.getString("email");
        String password = queryResult.getString("password");
        returnedUser = new User(email, password);
        if (queryResult.getString("firstName") != null) {
            String firstName = queryResult.getString("firstName");
            returnedUser.setFirstName(firstName);
        }
        if (queryResult.getString("lastName") != null) {
            String lastName = queryResult.getString("lastName");
            returnedUser.setLastName(lastName);
        }
        if (queryResult.getString("postNr") != null) {
            String postNr = queryResult.getString("postNr");
            returnedUser.setPostNr(postNr);
        }
        if (queryResult.getString("bankAccountNr") != null) {
            String bankAccountNr = queryResult.getString("bankAccountNr");
            returnedUser.setBankAccountNr(bankAccountNr);
        }
        if (queryResult.getString("tlfNr") != null) {
            String tlfNr = queryResult.getString("tlfNr");
            returnedUser.setTlfNr(tlfNr);
        }
        if (queryResult.getString("license") != null) {
            String licenseNumber = queryResult.getString("license");
            String dateOfIssue = queryResult.getString("dateOfIssue");
            String countryOfIssue = queryResult.getString("countryOfIssue");
            License dLicense = new License(licenseNumber, LocalDate.parse(dateOfIssue), countryOfIssue);
            returnedUser.setdLicense(dLicense);
        }
        returnedUser.setId(id);
        return returnedUser;
    }
    public static User retrieveFromEmailWithPriorConnection(String email, Connection conn) throws SQLException {
        String sql = "SELECT * FROM users LEFT JOIN licenses ON license=licenseNumber WHERE email = \'" + email + "\'";
        User returnedUser = null;
        PreparedStatement str = conn.prepareStatement(sql);
        ResultSet queryResult = str.executeQuery();
        int idNumber = queryResult.getInt("users_id");
        String password = queryResult.getString("password");
        returnedUser = new User(email, password);
        if (queryResult.getString("firstName") != null) {
            String firstName = queryResult.getString("firstName");
            returnedUser.setFirstName(firstName);
        }
        if (queryResult.getString("lastName") != null) {
            String lastName = queryResult.getString("lastName");
            returnedUser.setLastName(lastName);
        }
        if (queryResult.getString("postNr") != null) {
            String postNr = queryResult.getString("postNr");
            returnedUser.setPostNr(postNr);
        }
        if (queryResult.getString("bankAccountNr") != null) {
            String bankAccountNr = queryResult.getString("bankAccountNr");
            returnedUser.setBankAccountNr(bankAccountNr);
        }
        if (queryResult.getString("tlfNr") != null) {
            String tlfNr = queryResult.getString("tlfNr");
            returnedUser.setTlfNr(tlfNr);
        }
        if (queryResult.getString("license") != null) {
            String licenseNumber = queryResult.getString("license");
            String dateOfIssue = queryResult.getString("dateOfIssue");
            String countryOfIssue = queryResult.getString("countryOfIssue");
            License dLicense = new License(licenseNumber, LocalDate.parse(dateOfIssue), countryOfIssue);
            returnedUser.setdLicense(dLicense);
        }
        returnedUser.setId(idNumber);
        return returnedUser;
    }*/
}