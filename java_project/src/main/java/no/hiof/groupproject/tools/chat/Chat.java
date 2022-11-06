package no.hiof.groupproject.tools.chat;
import no.hiof.groupproject.models.User;
import java.util.ArrayList;

public class Chat {
    private User sender;
    private final User receiver;
    private final ArrayList<String> messageLog = new ArrayList<>();

    public Chat(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public void sendMessageFromSender(String msg) {
        messageLog.add(sender.getFirstName() + ": " + msg);
    }

    public void sendResponseFromReceiver(String response) {
        messageLog.add(receiver.getFirstName() + ": " + response);
    }

    public void showMessageLog() {
        for (String msg : messageLog)
            System.out.println(msg);
    }

    public void showSpecifiedNumberOfMessages(int num) {
        for (int i = findStartIndex(num); i < messageLog.size(); i++) {
            System.out.println(messageLog.get(i));
        }
    }

    private int findStartIndex(int num) {
        return messageLog.size() - num;
    }

    public void clearLog() {
        messageLog.clear();
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
}
