package no.hiof.groupproject.tools;

import no.hiof.groupproject.models.geocode.Location;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {

    Location location = mock(Location.class);

    @Test
    public void VerifyThatCitySearchIsCalled() {
        try {
            doNothing().when(location).citySearch(isA(String.class));
            location.citySearch("");
            verify(location, times(1)).citySearch("");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void VerifyThatPrintLocationInfoReturnString() throws IOException {
        Location returnString = new Location();
        returnString.citySearch("Moss");
        assertEquals("Moss, Viken, 1531, Norge", returnString.printLocationInfo());

    }
}
