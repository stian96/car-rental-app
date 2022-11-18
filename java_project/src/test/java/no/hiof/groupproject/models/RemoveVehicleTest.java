package no.hiof.groupproject.models;

import no.hiof.groupproject.models.vehicles.four_wheeled_vehicles.Car;
import no.hiof.groupproject.models.vehicles.Vehicle;
import no.hiof.groupproject.tools.db.ConnectDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class RemoveVehicleTest {

    @BeforeEach
    void initialiseDatabasePath() {
        ConnectDB.setDb("jdbc:sqlite:sqlite/db/testable.db");
    }
    @AfterEach
    void rewindDatabasePath() {
        ConnectDB.setDb("jdbc:sqlite:sqlite/db/test.db");
    }

    @Test
    void assertsVehicleIsRemovedFromDatabase() {

        Vehicle car = new Car("87359642", "mazda", "miata", "petrol",
                "manual", 1997, 4, 700);

        String sql = "SELECT COUNT(*) AS amount FROM vehicles WHERE vehicles_id = " + car.getId();

        boolean ans = false;
        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            if (queryResult.getInt("amount") > 0) {
                ans = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        assertTrue(ans);


        car.removeVehicleAndCascade();


        ans = false;
        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            if (queryResult.getInt("amount") > 0) {
                ans = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        assertFalse(ans);
    }

}