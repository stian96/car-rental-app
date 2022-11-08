package no.hiof.groupproject.tools.chat;

import java.util.ArrayList;

public class ChatRoom {
    private Message messageToSend;
    private final ArrayList<String> messageLog = new ArrayList<>();

    public void sendMessage(Message messageToSend) {
        this.messageToSend = messageToSend;

        String username = messageToSend.getUser().getFirstName();
        messageLog.add(username + ": " + messageToSend.getMessage() + " " + messageToSend.getNowTime());
    }

    public void showMessages() {
        for (String msg : messageLog)
            System.out.println(msg);
    }

    public Message getMessageToSend() {
        return messageToSend;
    }
}
