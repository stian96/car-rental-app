package no.hiof.groupproject.tools.chat;

import no.hiof.groupproject.models.License;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.tools.db.ConnectDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class ChatRoomTest {

    License license = new License("98 45 123456 1", LocalDate.parse("2008-05-12"),
            "Norway");
    User user = new User("sam", "davies", "1111", "hunter2",
            "12341234123", "sam@sam.no", "12341234", license);

    User receiver = new User("Geir", "Larsen", "1234", "cake",
            "123456789101", "geir@larsen.no","91038420", license);

    ChatRoom chatRoom = new ChatRoom();

    @BeforeEach
    void initialiseDatabasePath() {
        ConnectDB.setDb("jdbc:sqlite:sqlite/db/testable.db");
    }
    @AfterEach
    void rewindDatabasePath() {
        ConnectDB.setDb("jdbc:sqlite:sqlite/db/test.db");
    }


    @Test
    void VerifyThatSendMessageSerialiseMessages() {
        assertTrue(chatRoom.sendMessage(new Message(user, receiver,"A brand new message!")));
        chatRoom.deleteMessage("A brand new message!");
    }

    @Test
    void VerifyThatSendMessageDoNotStoreMessagesThatExist() {
        Message msg = new Message(user, receiver,"Hello from sam");
        assertFalse(chatRoom.sendMessage(msg));
    }

    @Test
    void VerifyThatGetMessageLogRetrieveDataFromDatabase() {
        ArrayList<String> testLog = new ArrayList<>();
        Message msg = new Message(user, receiver, "Hello from sam");
        chatRoom.sendMessage(msg);
        msg = new Message(user, receiver, "Test from the same user");
        chatRoom.sendMessage(msg);
        testLog.add("sam: Hello from sam");
        testLog.add("sam: Test from the same user");

        Object[] response = Arrays.copyOfRange(chatRoom.getMessageLog().toArray(), 0, 2);
        assertArrayEquals(testLog.toArray(), response);
    }

    @Test
    void VerifyThatGetLimitedMessageLogRetrieveDataFromDatabase() {
        ArrayList<String> testLog = new ArrayList<>();
        Message msg = new Message(user, receiver,"Hello from sam");
        chatRoom.sendMessage(msg);
        msg = new Message(user, receiver,"Test from the same user");
        chatRoom.sendMessage(msg);
        testLog.add("sam: Hello from sam");
        testLog.add("sam: Test from the same user");

        ArrayList<String> limitedLog = chatRoom.getLimitedMessageLog(1);
        assertEquals(1, limitedLog.size());
    }

    @Test
    void VerifyThatClearLogClearsTheMessageLog() {
        ArrayList<String> emptyLog = new ArrayList<>();
        chatRoom.clearMessageLog();
        assertEquals(emptyLog, chatRoom.getLog());

    }
}
