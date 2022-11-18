package no.hiof.groupproject.tools.chat;

import no.hiof.groupproject.models.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private final User user;

    private final User receiver;
    private final String message;
    private final LocalDateTime time;

    public Message(User usertype, User receiver, String message) {
        this.user = usertype;
        this.receiver = receiver;
        this.message = message;
        this.time = LocalDateTime.now();
    }

    public String formatNowDate() {
        DateTimeFormatter nowDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return nowDate.format(time);
    }

    public String formatNowTime() {
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

    public User getReceiver() {
        return receiver;
    }

}
