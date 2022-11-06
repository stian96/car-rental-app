package no.hiof.groupproject.tools.chat;

import no.hiof.groupproject.models.User;

import java.util.ArrayList;

public class Message {
    private final User sender;
    private final User receiver;
    private final ArrayList<String> messageLog = new ArrayList<>();

    public Message(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public void sendMessageFromSender(String msg) {
        messageLog.add(sender.getFirstName() + ": " + msg);
    }

    public void SendResponseFromReceiver(String response) {
        messageLog.add(receiver.getFirstName() + ": " + response);
    }

    public void showMessageLog() {
        for (String msg : messageLog)
            System.out.println(msg);
    }

    public void clearLog() {
        messageLog.clear();
    }



}
