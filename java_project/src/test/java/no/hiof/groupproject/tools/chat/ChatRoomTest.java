package no.hiof.groupproject.tools.chat;

import no.hiof.groupproject.models.License;
import no.hiof.groupproject.models.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class ChatRoomTest {

    License license = new License("98 45 123456 1", LocalDate.parse("2008-05-12"),
            "Norway");
    User user = new User("sam", "davies", "1111", "hunter2",
            "12341234123", "sam@sam.no", "12341234", license);

    ChatRoom chatRoom = new ChatRoom();

    @Test
    void VerifyThatSendMessageStoreMessagesThatDoNotExist() {
        Message testMsg = new Message(user, "Test that message is stored in database");
        assertTrue(chatRoom.sendMessage(testMsg));
    }

    @Test
    void VerifyThatGetMessageLogRetrieveDataFromDatabase() {
        ArrayList<String> testLog = new ArrayList<>();
        testLog.add("sam: Hello from sam");
        testLog.add("sam: Test from the same user");
        testLog.add("sam: Test that message is stored in database");
        testLog.add("gsdfg: This is from another user.");
        assertArrayEquals(testLog.toArray(), chatRoom.getMessageLog().toArray());
    }

    @Test
    void VerifyThatClearLogClearsTheMessageLog() {
        ArrayList<String> emptyLog = new ArrayList<>();
        chatRoom.clearMessageLog();
        assertEquals(emptyLog, chatRoom.getLog());

    }
}
