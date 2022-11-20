package no.hiof.groupproject.tools.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RetrieveMessagesDB {

    public static ArrayList<String> retrieveAllMessages(int id) {

        String sql = "SELECT * FROM messages WHERE user_id = " + id + " OR receiver_id = " + id +
                " ORDER BY dato, tid";

        ArrayList<String> returnedMessages = new ArrayList<>();

        try (Connection conn = ConnectDB.connectReadOnly();
             Statement str = conn.createStatement();
             ResultSet rs = str.executeQuery(sql)) {

            //loops through rows in the sql SELECT statement
            while (rs.next()) {
                StringBuilder storedMessages = new StringBuilder();
                String messages = rs.getString("melding");
                String userName = rs.getString("user_name");

                storedMessages.append(userName).append(": ").append(messages);
                returnedMessages.add(String.valueOf(storedMessages));
            }
            return returnedMessages;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return returnedMessages;
    }

    public static ArrayList<String> retrieveLimitedMessages(int results) {

        String sql = "SELECT * FROM messages WHERE user_id > 0 ORDER BY dato, tid LIMIT " + results;

        ArrayList<String> returnedMessages = new ArrayList<>();

        try (Connection conn = ConnectDB.connectReadOnly();
             Statement str = conn.createStatement();
             ResultSet rs = str.executeQuery(sql)) {

            //loops through rows in the sql SELECT statement
            while (rs.next()) {
                StringBuilder storedMessages = new StringBuilder();
                String messages = rs.getString("melding");
                String userName = rs.getString("user_name");

                storedMessages.append(userName).append(": ").append(messages);
                returnedMessages.add(String.valueOf(storedMessages));
            }
            return returnedMessages;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return returnedMessages;
    }
}
