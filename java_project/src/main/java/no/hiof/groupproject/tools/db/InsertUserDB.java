package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
A class used to serialise a User into a database for permanent storage.
 */
public class InsertUserDB {

    public static void insert(User user) {

        String sql = "INSERT INTO users (firstName, lastName, postNr, password, bankAccountNr, email, tlfNr) " +
                "VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {
            str.setString(1, user.getFirstName());
            str.setString(2, user.getLastName());
            str.setString(3, user.getPostNr());
            str.setString(4, user.getPassword());
            str.setString(5, user.getBankAccountNr());
            str.setString(6, user.getEmail());
            str.setString(7, user.getTlfNr());
            str.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}