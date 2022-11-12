package no.hiof.groupproject.tools.chat;

import no.hiof.groupproject.interfaces.ExistsInDb;
import no.hiof.groupproject.interfaces.Serialise;
import no.hiof.groupproject.tools.db.ConnectDB;
import no.hiof.groupproject.tools.db.InsertMessageDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Class that saves messages that a user sends to the database table 'messages'.

public class SaveToDB implements Serialise, ExistsInDb {
    private final Message saveMessage;

    public SaveToDB(Message message) {
        this.saveMessage = message;
    }

    @Override
    public void serialise() {
        InsertMessageDB.insert(this.saveMessage);
    }

    @Override
    public boolean existsInDb() {
        String sql = "SELECT COUNT(*) AS amount FROM messages " +
                "WHERE user_id = \'" + saveMessage.getUser().getId() + "\' " +
                "AND melding = \'" + saveMessage.getMessage() + "\' " +
                "AND dato = \'" + saveMessage.formatNowDate() + "\'";

        boolean ans = false;
        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            if (queryResult.getInt("amount") > 0) {
                ans = true;
            }
            return ans;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
