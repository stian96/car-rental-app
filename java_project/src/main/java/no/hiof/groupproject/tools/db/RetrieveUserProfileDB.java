package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.UserProfile;
import no.hiof.groupproject.tools.License;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/*
Returns a specific User in the database based on either the id or email of the User, both of which are unique values

public class RetrieveUserProfileDB {

    public static UserProfile retrieve(int id) {

        String sql = "SELECT * FROM users INNER JOIN licenses ON license=licenseNumber WHERE users_id = " + id;

        User returnedUser = null;

        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            String firstName = queryResult.getString("firstName");
            String lastName = queryResult.getString("lastName");
            String postNr = queryResult.getString("postNr");
            String password = queryResult.getString("password");
            String bankAccountNr = queryResult.getString("bankAccountNr");
            String email = queryResult.getString("email");
            String tlfNr = queryResult.getString("tlfNr");
            String licenseNumber = queryResult.getString("license");
            String dateOfIssue = queryResult.getString("dateOfIssue");
            String countryOfIssue = queryResult.getString("countryOfIssue");

            License dLicense = new License(licenseNumber, LocalDate.parse(dateOfIssue), countryOfIssue);

            returnedUser = new User(firstName, lastName, postNr, password, bankAccountNr, email, tlfNr, dLicense);
            returnedUser.setId(id);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return returnedUser;
    }

}
 */
