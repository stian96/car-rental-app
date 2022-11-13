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

        String sql = "INSERT INTO users (firstName, lastName, postNr, password, bankAccountNr, email, tlfNr, license) " +
                "VALUES(?,?,?,?,?,?,?,?)";

        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {
            str.setString(1, user.getFirstName());
            str.setString(2, user.getLastName());
            str.setString(3, user.getPostNr());
            str.setString(4, user.getPassword());
            str.setString(5, user.getBankAccountNr());
            str.setString(6, user.getEmail());
            str.setString(7, user.getTlfNr());
            str.setString(8, user.getdLicense().getLicenseNumber());

            str.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertOnlyEmailAndPwd(User user) {

        String sql = "INSERT INTO users (email, password) " +
                "VALUES(?,?)";

        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {
            str.setString(1, user.getEmail());
            str.setString(2, user.getPassword());

            str.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateFromEmailAndPwd(User user) {

        String sql = "UPDATE users SET firstName = \'" + user.getFirstName() +
                "\', lastName = \'" + user.getLastName() +
                "\', postNr = \'" + user.getPostNr() +
                "\', bankAccountNr = \'" + user.getBankAccountNr() +
                "\', tlfNr = \'" + user.getTlfNr() +
                "\', license = \'" + user.getdLicense().getLicenseNumber() +
                "\' WHERE email = \'" + user.getEmail() + "\'";

        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            str.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void insertWithPriorConnection(User user, Connection conn) throws SQLException {

        String sql = "INSERT INTO users (firstName, lastName, postNr, password, bankAccountNr, email, tlfNr, license) " +
                "VALUES(?,?,?,?,?,?,?,?)";

        PreparedStatement str = conn.prepareStatement(sql);
        str.setString(1, user.getFirstName());
        str.setString(2, user.getLastName());
        str.setString(3, user.getPostNr());
        str.setString(4, user.getPassword());
        str.setString(5, user.getBankAccountNr());
        str.setString(6, user.getEmail());
        str.setString(7, user.getTlfNr());
        str.setString(8, user.getdLicense().getLicenseNumber());

        str.executeUpdate();
    }

    public static void insertOnlyEmailAndPwdWithPriorConnection(User user, Connection conn) throws SQLException {

        String sql = "INSERT INTO users (email, password) " +
                "VALUES(?,?)";

        PreparedStatement str = conn.prepareStatement(sql);
        str.setString(1, user.getEmail());
        str.setString(2, user.getPassword());

        str.executeUpdate();

    }

    public static void updateFromEmailAndPwdWithPriorConnection(User user, Connection conn) throws SQLException {

        String sql = "UPDATE users SET firstName = \'" + user.getFirstName() +
                "\', lastName = \'" + user.getLastName() +
                "\', postNr = \'" + user.getPostNr() +
                "\', bankAccountNr = \'" + user.getBankAccountNr() +
                "\', tlfNr = \'" + user.getTlfNr() +
                "\', license = \'" + user.getdLicense().getLicenseNumber() +
                "\' WHERE email = \'" + user.getEmail() + "\'";

        PreparedStatement str = conn.prepareStatement(sql);

        str.executeUpdate();

    }
}