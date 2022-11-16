package no.hiof.groupproject.tools.db;

// Class to store messages in database.

import no.hiof.groupproject.tools.chat.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class InsertMessageDB {

    public static void insert(Message chat) {


        String sql = "INSERT INTO messages (user_id, user_name, melding, dato, tid) " + "VALUES(?,?,?,?,?)";

        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {
            str.setInt(1, chat.getUser().getId());
            str.setString(2, chat.getUser().getFirstName());
            str.setString(3, chat.getMessage());
            str.setString(4, chat.formatNowDate());
            str.setString(5, chat.formatNowTime());

            str.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    /*public static void insertWithPriorConnection(Message chat, Connection conn) throws SQLException {


        String sql = "INSERT INTO messages (user_id, user_name, melding, dato, tid) " + "VALUES(?,?,?,?,?)";

            PreparedStatement str = conn.prepareStatement(sql);
            str.setInt(1, chat.getUser().getId());
            str.setString(2, chat.getUser().getFirstName());
            str.setString(3, chat.getMessage());
            str.setString(4, chat.formatNowDate());
            str.setString(5, chat.formatNowTime());

            str.executeUpdate();

    }*/
}
