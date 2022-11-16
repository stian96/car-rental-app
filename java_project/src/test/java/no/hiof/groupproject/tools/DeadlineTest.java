package no.hiof.groupproject.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.sql.Array;

import java.time.LocalDate;
import java.time.LocalDateTime;

class DeadlineTest {

    @Test
    public void beforeDeadline(){
        Deadline deadlineTest1 = new Deadline(LocalDate.now(), LocalDate.now().plusDays(2));
        Assertions.assertTrue(deadlineTest1.compareDates());
    }
    @Test
    public void sameDayAsDeadline(){
        Deadline deadlineTest2 = new Deadline(LocalDate.now(), LocalDate.now());
        System.out.println(deadlineTest2.getToday());
        System.out.println(deadlineTest2.getDeadline());
        Assertions.assertTrue(deadlineTest2.compareDates());
    }

    @Test
    public void afterDeadline(){
        Deadline deadlineTest3 = new Deadline(LocalDate.now(), LocalDate.now().minusDays(2));
        System.out.println(deadlineTest3.getToday());
        System.out.println(deadlineTest3.getDeadline());
        Assertions.assertFalse(deadlineTest3.compareDates());
    }
}
