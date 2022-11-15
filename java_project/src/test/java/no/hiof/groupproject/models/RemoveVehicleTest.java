package no.hiof.groupproject.models;

import no.hiof.groupproject.models.payment_methods.Paypal;
import no.hiof.groupproject.models.vehicle_types.Car;
import no.hiof.groupproject.models.vehicle_types.Vehicle;
import no.hiof.groupproject.tools.db.ConnectDB;
import no.hiof.groupproject.tools.db.RetrieveBookingDB;
import no.hiof.groupproject.tools.db.RetrieveUserDB;
import no.hiof.groupproject.tools.db.RetrieveVehicleDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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