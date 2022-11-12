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

    public ArrayList<String> getMessageLog() {
        messageLog = RetrieveMessagesDB.retrieveAllMessages();
        return messageLog;
    }

    public void clearMessageLog() {
        messageLog.clear();
    }

}
