package no.hiof.groupproject.tools.chat;

import no.hiof.groupproject.models.License;
import no.hiof.groupproject.models.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class MessageTest {

    License license = new License("98 45 123456 1", LocalDate.parse("2008-05-12"),
            "Norway");
    User user = new User("sam", "davies", "1111", "hunter2",
            "12341234123", "sam@sam.no", "12341234", license);

    Message formatTest = new Message(user, "hello");

    @Test
    void VerifyThatFormatNowDateReturnCorrectFormat() {
        // A test to see if the format is in the following pattern: 'yyyy-MM-dd'.
        assertTrue(Pattern.matches("\\S\\S\\S\\S-\\S\\S-\\S\\S", formatTest.formatNowDate()));
    }

    @Test
    void VerifyThatFormatNowDateReturnCurrentDate() {
        LocalDateTime currentDate = LocalDateTime.now();
        assertEquals(currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), formatTest.formatNowDate());
    }

    @Test
    void VerifyThatFormatNowTimeReturnCorrectFormat() {
        // A test to see if the format is in the following pattern: 'HH:mm:ss'.
        assertTrue(Pattern.matches("\\S\\S:\\S\\S:\\S\\S", formatTest.formatNowTime()));
    }

    @Test
    void VerifyThatFormatNowTimeReturnCurrentTime() {
        LocalDateTime currentDate = LocalDateTime.now();
        assertEquals(currentDate.format(DateTimeFormatter.ofPattern("HH:mm:ss")), formatTest.formatNowTime());
    }
}
