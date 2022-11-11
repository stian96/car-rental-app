package no.hiof.groupproject.tools.chat;

import no.hiof.groupproject.interfaces.ExistsInDb;
import no.hiof.groupproject.interfaces.Serialise;
import no.hiof.groupproject.tools.db.ConnectDB;
import no.hiof.groupproject.tools.db.InsertMessageDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChatRoom implements Serialise, ExistsInDb {
    private Message messageToSend;
    private final ArrayList<String> messageLog = new ArrayList<>();

    public void sendMessage(Message messageToSend) {
        this.messageToSend = messageToSend;

        String username = messageToSend.getUser().getFirstName();
        messageLog.add(username + ": " + messageToSend.getMessage() + " " + messageToSend.getNowTime());

        if (!existsInDb()) {
            serialise();
        }
    }

    public void showMessageLog() {
        for (String msg : messageLog)
            System.out.println(msg);
    }

    public void clearMessageLog() {
        messageLog.clear();
    }

    public Message getMessageToSend() {
        return messageToSend;
    }

    @Override
    public void serialise() {
        InsertMessageDB.insert(this.getMessageToSend());
    }

    @Override
    public boolean existsInDb() {
        String sql = "SELECT COUNT(*) AS amount FROM messages WHERE user_fk = \'" + this.messageToSend.getUser().getId()
                + "\' " + "AND melding = \'" + this.messageToSend.getMessage() + "\' AND dato = \'" +
                this.messageToSend.getNowDate() + "\'" + "AND tid = \'" + this.messageToSend.getNowTime() + "\'";

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
