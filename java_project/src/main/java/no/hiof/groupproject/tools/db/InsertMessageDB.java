package no.hiof.groupproject.tools.db;

// Class to store messages in database.

import no.hiof.groupproject.tools.chat.Chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertMessageDB {

    public static void insert(Chat chat) {

        String sql = "INSERT INTO messages (user_fk, melding, dato, tid) " + "VALUES(?,?,?,?)";

        try (Connection conn = ConnectDB.connect()) {
            PreparedStatement str = conn.prepareStatement(sql);
            str.setInt(1, chat.getSender().getId());
            str.setString(2, chat.getMessage());


        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }


    }
}
