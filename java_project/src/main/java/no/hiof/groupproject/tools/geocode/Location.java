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
        this.url = new URL("https://nominatim.openstreetmap.org/search?format=json&city=" + city + "&country=norway");
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

    //returns a string if Location is already instantiated in the format:
    //  Sarpsborg, Viken, 1707, Norge
    public String getThisLocationInfo() throws IOException {
        String dataFromApi = this.getDataFromApi();
        return this.MapToJsonNodeObject(dataFromApi).get(1).get("display_name").asText();
    }

    /*
    The following functions return the by, fylke, postnr, and land of the instantiated class
    Example of use:
        System.out.println(roa.getLocation().getBy() + " is in the county of "
                    + roa.getLocation().getFylke() + " with a postcode of "
                    + roa.getLocation().getPostNr() + " in the country "
                    + roa.getLocation().getLand());
    returns the output:
        "Sarpsborg is in the fylke Viken with a postcode of 1707 in the country Norge"
     */
    public String getBy() throws IOException {
        String str = this.getThisLocationInfo();
        str = str.replaceAll(" ", "");
        String[] splitStr = str.split(",");

        return splitStr[0];
    }

    public String getFylke() throws IOException {
        String str = this.getThisLocationInfo();
        str = str.replaceAll(" ", "");
        String[] splitStr = str.split(",");

        return splitStr[1];
    }

    public String getPostNr() throws IOException {
        String str = this.getThisLocationInfo();
        str = str.replaceAll(" ", "");
        String[] splitStr = str.split(",");

        return splitStr[2];
    }

    public String getLand() throws IOException {
        String str = this.getThisLocationInfo();
        str = str.replaceAll(" ", "");
        String[] splitStr = str.split(",");

        return splitStr[3];
    }
}
