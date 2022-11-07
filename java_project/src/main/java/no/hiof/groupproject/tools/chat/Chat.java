package no.hiof.groupproject.tools.chat;
import no.hiof.groupproject.models.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Chat {
    private final User sender;
    private final User receiver;
    private String message;
    private final LocalDateTime today;
    private final ArrayList<String> messageLog = new ArrayList<>();
    private final HashMap<String, String> log = new HashMap<>();

    public Chat(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.today = LocalDateTime.now();
    }

    public void createMessage(String msg) {
        this.message = msg;
    }

    public void sendFromSender() {
        log.put(sender.getFirstName() + ": " + message, getNowTime());
    }

    public void sendFromReceiver() {
        log.put(receiver.getFirstName() + ": " + message, getNowTime());
    }

    public void showMessageLog() {
        for (Map.Entry<String, String> entry : log.entrySet())
            System.out.println(entry.getKey() + " :" + entry.getValue());
    }

    private int findStartIndex(int num) {
        return messageLog.size() - num;
    }

    private String getNowDate() {
        DateTimeFormatter nowDate = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return nowDate.format(today);
    }

    private String getNowTime() {
        DateTimeFormatter nowTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        return nowTime.format(today);

    }

    public void clearLog() {
        messageLog.clear();
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getMessage() {
        return message;
    }
}
