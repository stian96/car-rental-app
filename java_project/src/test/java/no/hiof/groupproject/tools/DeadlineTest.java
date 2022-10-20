package no.hiof.groupproject.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.sql.Array;

import java.time.LocalDate;

class DeadlineTest {

    LocalDate thisDay1= LocalDate.now();
    LocalDate deadline1=thisDay1.plusDays(2);

    LocalDate thisDay2= LocalDate.now();
    LocalDate deadline2=thisDay2.plusDays(0);

    LocalDate thisDay3= LocalDate.now();
    LocalDate deadline3=thisDay3.plusDays(-2);

    @Test
    public void afterDeadline(){
        Assertions.assertEquals(-2, thisDay1.compareTo(deadline1));
    }
    @Test
    public void sameDayAsDeadline(){
        Assertions.assertEquals(0, thisDay2.compareTo(deadline2));
    }

    @Test
    public void beforeDeadline(){
        Assertions.assertEquals(2, thisDay3.compareTo(deadline3));

    }
}
