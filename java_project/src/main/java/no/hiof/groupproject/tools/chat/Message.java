package no.hiof.groupproject.tools.chat;

import no.hiof.groupproject.interfaces.ExistsInDb;
import no.hiof.groupproject.interfaces.Serialise;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.tools.db.ConnectDB;
import no.hiof.groupproject.tools.db.InsertMessageDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message implements Serialise, ExistsInDb {
    private final User user;
    private final String message;
    private LocalDateTime time;

    public Message(User usertype, String message) {
        this.user = usertype;
        this.message = message;
        this.time = LocalDateTime.now();

        if (!existsInDb()) {
            serialise();
        }
    }

    public String getNowDate() {
        DateTimeFormatter nowDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return nowDate.format(time);
    }

    public String getNowTime() {
        DateTimeFormatter nowTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        return nowTime.format(time);
    }

    @Override
    public void serialise() {
        InsertMessageDB.insert(this);
    }

    @Override
    public boolean existsInDb() {
        String sql = "SELECT COUNT(*) AS amount FROM messages WHERE user_fk = \'" + user.getId() + "\' " +
                "AND melding = \'" + this.message + "\' AND dato = \'" + this.getNowDate() + "\'" +
                "AND tid = \'" + this.getNowTime() + "\'";

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

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }
}
