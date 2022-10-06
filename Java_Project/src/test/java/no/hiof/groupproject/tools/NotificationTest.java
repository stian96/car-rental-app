package no.hiof.groupproject.tools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NotificationTest {

    Notification notification = new Notification();
    ArrayList<String> messages = new ArrayList<>();
    String msg = "Hello";
    String msg2 = "Hi";
    String email1 = "ash@gmail.com";

    @Test
    void testToFillArraylistUsingAddmessages() {
        notification.addMessages("Hello");
        notification.addMessages("Hi");
        String result = notification.messages.get(1);
        assertNotEquals(msg, result);

    }

    @Test
    void checking_send_to_gui_returns_correctly() {
        notification.addMessages(msg);
        notification.addMessages(msg2);
        String ms = notification.messages.get(1);
        assertEquals(msg2, notification.sendToGUI(ms));
    }

    @Test
    void checking_email_sends(){
        String s = "Hi";
        String shawn = "Shaun";
        String e = email1;
        notification.messages.add(msg);
        assertEquals("ash@gmail.com", notification.sendToMailAddress(email1,s));

    }

    @Test
    void checking_notification_sends_the_correct_message(){
        String s = "hi";
        String shawn = "Shaun";
        String e = email1;
        notification.messages.add(msg);
        String ms = notification.messages.get(0);

        assertEquals("Hello",notification.sendNotification(shawn,notification.sendToMailAddress(e, ms)));

    }


}