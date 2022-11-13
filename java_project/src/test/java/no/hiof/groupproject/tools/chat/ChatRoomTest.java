package no.hiof.groupproject.tools.chat;

import no.hiof.groupproject.models.License;
import no.hiof.groupproject.models.User;
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

    ChatRoom chatRoom = new ChatRoom();

    // Need to write a test for serialise in database.

    @Test
    void VerifyThatSendMessageDoNotStoreMessagesThatExist() {
        Message msg = new Message(user, "Hello from sam");
        assertFalse(chatRoom.sendMessage(msg));
    }

    @Test
    void VerifyThatGetMessageLogRetrieveDataFromDatabase() {
        ArrayList<String> testLog = new ArrayList<>();
        testLog.add("sam: Hello from sam");
        testLog.add("sam: Test from the same user");

        Object[] response = Arrays.copyOfRange(chatRoom.getMessageLog().toArray(), 0, 2);
        assertArrayEquals(testLog.toArray(), response);
    }

    @Test
    void VerifyThatClearLogClearsTheMessageLog() {
        ArrayList<String> emptyLog = new ArrayList<>();
        chatRoom.clearMessageLog();
        assertEquals(emptyLog, chatRoom.getLog());

    }
}
