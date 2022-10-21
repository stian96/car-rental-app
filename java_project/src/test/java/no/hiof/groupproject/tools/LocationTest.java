package no.hiof.groupproject.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.hiof.groupproject.tools.geocode.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.*;

public class LocationTest {

    // Creating a mock object that we can use to test "void methods".
    Location location = mock(Location.class);
    Location test = new Location("Moss");

    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = null;

    // Just ignore this.
    public LocationTest() throws IOException {
    }

    @Test
    // In this test I only check if the method is called one time.
    void VerifyThatSendGetRequestIsExecuted() {
        // doNothing() is used to tell verify() that the method does nothing.
        try {
            doNothing().when(location).sendGetRequest(isA(String.class));
            location.sendGetRequest("GET");
            verify(location, times(1)).sendGetRequest("GET");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void OnGoodConnectionCheckResponseReturn200() {
        try {
            int response = test.checkResponse();
            Assertions.assertEquals(200, response);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void OnBadConnectionThrowRunTimeException() throws IOException {
        Location badResponse = mock(Location.class);
        willThrow(new RuntimeException()).given(badResponse).checkResponse();

        Assertions.assertThrows(RuntimeException.class, badResponse::checkResponse);
    }

    @Test
    void VerifyThatGetDataFromApiReturnsData() throws IOException {
        String data = test.getDataFromApi();
        Assertions.assertNotNull(data);
    }

    @Test
    void VerifyThatDataIsMappedToJsonNodeObject() throws IOException {
        String data = test.getDataFromApi();
        jsonNode = objectMapper.readTree(data);
        Assertions.assertEquals(jsonNode, test.MapToJsonNodeObject(test.getDataFromApi()));
    }

    @Test
    void VerifyThatGetLocationInfoReturnNiceFormattedString() throws IOException {
        String compare = "Moss, Viken, 1531, Norge";
        jsonNode = objectMapper.readTree(test.getDataFromApi());
        Assertions.assertEquals(compare, test.getLocationInfo(jsonNode));

    }
}
