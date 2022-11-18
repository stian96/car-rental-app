package no.hiof.groupproject.tools.chat;

import no.hiof.groupproject.interfaces.DeserialiseMessages;
import no.hiof.groupproject.tools.db.DeleteMessagesFromDB;
import no.hiof.groupproject.tools.db.RetrieveMessagesDB;

import java.util.ArrayList;

public class ChatRoom implements DeserialiseMessages {
    private ArrayList<String> messageLog = new ArrayList<>();

    public boolean sendMessage(Message message) {
        SaveToDB save = new SaveToDB(message);
        if (!save.existsInDb()) {
            save.serialise();
            return true;
        }
        else {
            return false;
        }
    }

    public ArrayList<String> getMessageLog() {
        messageLog = RetrieveMessagesDB.retrieveAllMessages();
        return messageLog;
    }

    public ArrayList<String> getLimitedMessageLog(int results) {
        messageLog = RetrieveMessagesDB.retrieveLimitedMessages(results);
        return messageLog;
    }

    public void deleteMessage(String writeMessage) {
        DeleteMessagesFromDB db = new DeleteMessagesFromDB();
        db.delete("messages", "melding", writeMessage);
    }

    public void clearMessageLog() {
        messageLog.clear();
    }

    public ArrayList<String> getLog() {
        return messageLog;
    }

}
