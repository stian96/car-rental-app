package no.hiof.groupproject.models.geocode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/*  Parameters we can use to do search in nominatim.openstreetmap.org. Now it´s set to 'city'.
    q=<query>
    street=<housenumber> <streetname>
    city=<city>
    county=<county>
    state=<state>
    country=<country>
    postalcode=<postalcode>
*/

public class Location {
    // Url to the openstreetmap API. Just need to append a "city name" to the end of the url.
    private final StringBuilder api = new StringBuilder("https://nominatim.openstreetmap.org/search?format=json&city=");
    private final StringBuilder inline = new StringBuilder();

    // Method that takes a city name as a parameter.
    public void citySearch(String cityName) throws IOException {
        URL url = new URL(api.append(cityName).toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        checkResponse(connection, url);
    }

    // Method that checks if the connection is good.
    private void checkResponse(HttpURLConnection connection, URL url) throws IOException {
        int response = connection.getResponseCode();
        if (response == 200) {
            getDataFromApi(url);
        }
        else {
            throw new RuntimeException("HttpResponseCode: " + response);
        }
    }

    // Method that uses Scanner class to fetch data from "url.openstream()" and append it to the StringBuilder.
    private void getDataFromApi(URL url) {
        try (Scanner scanner = new Scanner(url.openStream())) {
            while (scanner.hasNext()) {
                inline.append(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method that convert Json to a JsonNode Object.
    private JsonNode MapToJsonNodeObject() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(inline.toString());
    }

    // Simple method that returns the JsonNode object as a String.
    public String printLocationInfo() throws JsonProcessingException {
        JsonNode node = MapToJsonNodeObject();
        return node.get(1).get("display_name").asText();
    }
}
