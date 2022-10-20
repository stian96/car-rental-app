package no.hiof.groupproject.tools.geocode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 *  Class that can be used to get the following information about a City:
 *
 *  [City name, county, postal code and country].
 *
 *  We can include other search criteria by changing the last parameter in the Url
 *  with one of the following:
 *
 *  [street, country, state, postal code].
 **/

public class Location {
    private final URL url;
    private final HttpURLConnection connection;
    private final StringBuilder result = new StringBuilder();

    /*
     *  Constructor that open up a connection to the API by creating a URL object
     *  and then use that object to call the openConnection() method which returns a URLConnection instance.
     */
    public Location(String city) throws IOException {
        this.url = new URL("https://nominatim.openstreetmap.org/search?format=json&city=" + city);
        this.connection = (HttpURLConnection) url.openConnection();
    }

    /* Method that sets the request method for the Url, for example (GET, POST etc.). */
    public void sendGetRequest(String method) throws IOException {
        connection.setRequestMethod(method);
    }

    /*
     *  Method that both checks and returns status for connection.
     *  If connection is successful it returns 200.
     *  If it´s not successful, it returns an RunTimeException.
     */
    public Integer checkResponse() throws IOException {
        int status = connection.getResponseCode();
        if (status == 200) {
            return status;
        }
        else {
            throw new RuntimeException("HttpStatus: " + status);
        }
    }

    /*
    *  The method opens a connection to the Url and returns an InputStream
    *  for reading form the connection. Then the input is appended to the
    *  StringBuilder variable 'result'.
    *  In the end, the result is returned as a String
    */
    public String getDataFromApi() throws IOException {
        Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                result.append(scanner.nextLine());
            }
            return result.toString();
    }

    /*
     * This method takes the 'result' string from 'getDataFromApi' and then
     * converts the string into a JsonNode Object.
     */
    public JsonNode MapToJsonNodeObject(String result) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(result);
    }

    /*
     * A simple method that takes a JsonNode Object as a parameter and
     * returns the [City name, county, postal code and country].
     */
    public String getLocationInfo(JsonNode object) {
        return object.get(1).get("display_name").asText();
    }
}
