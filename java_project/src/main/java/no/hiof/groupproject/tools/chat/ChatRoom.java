package no.hiof.groupproject.tools.chat;

import java.util.ArrayList;

public class ChatRoom {
    private final ArrayList<String> messageLog = new ArrayList<>();

    public void sendMessage(Message message) {
        String username = message.getUser().getFirstName();
        messageLog.add(username + ": " + message.getMessage() + " " + message.getNowTime());

        SaveToDB save = new SaveToDB(message);
        if (!save.existsInDb()) {
            save.serialise();
        }
    }

    public void showMessageLog() {
        for (String msg : messageLog)
            System.out.println(msg);
    }

    public void clearMessageLog() {
        messageLog.clear();
    }

}
