package no.hiof.groupproject.tools.chat;

import no.hiof.groupproject.models.User;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private final User user;
    private final String message;
    private final LocalDateTime time;

    public Message(User usertype, String message) {
        this.user = usertype;
        this.message = message;
        this.time = LocalDateTime.now();
    }

    public String getNowDate() {
        DateTimeFormatter nowDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return nowDate.format(time);
    }

    public String getNowTime() {
        DateTimeFormatter nowTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        return nowTime.format(time);
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public User getUser() {
        return user;
    }
}
