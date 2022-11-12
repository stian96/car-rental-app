package no.hiof.groupproject.tools.chat;

import no.hiof.groupproject.interfaces.DeserialiseMessages;
import no.hiof.groupproject.tools.db.RetrieveMessagesDB;

import java.util.ArrayList;

public class ChatRoom implements DeserialiseMessages {
    private ArrayList<String> messageLog = new ArrayList<>();

    public void sendMessage(Message message) {
        SaveToDB save = new SaveToDB(message);
        if (!save.existsInDb()) {
            save.serialise();
        }
    }

    private ArrayList<String> getMessageLogFromDB() {
        return RetrieveMessagesDB.retrieveAllMessages();
    }

    public void showMessageLog() {
        messageLog = getMessageLogFromDB();
        for (String msg : messageLog)
            System.out.println(msg);
    }

    public void clearMessageLog() {
        messageLog.clear();
    }

}
