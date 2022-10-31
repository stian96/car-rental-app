package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.vehicle_types.Car;
import no.hiof.groupproject.models.vehicle_types.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/*
Returns a specific Vehicle in the database based on either the id or email of the Vehicle, both of which are unique values
 */
public class RetrieveVehicleDB {

    public static Vehicle retrieveFromId(int id) {

        String sql = "SELECT * FROM vehicles WHERE vehicles_id = " + id;

        Vehicle returnedVehicle = null;
        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            String vehicleSubclass = queryResult.getString("vehicleSubclass");
            String regNo = queryResult.getString("regNo");
            String manufacturer = queryResult.getString("manufacturer");
            String model = queryResult.getString("model");
            String engineType = queryResult.getString("engineType");
            String gearType = queryResult.getString("gearType");
            int modelYear = queryResult.getInt("modelYear");
            if (Objects.equals(vehicleSubclass, "car")) {
                int seatingCapacity = queryResult.getInt("seatingCapacity");
                int towingCapacity = queryResult.getInt("towingCapacity");
                returnedVehicle = new Car(regNo, manufacturer, model, engineType, gearType, modelYear, seatingCapacity, towingCapacity);
                returnedVehicle.setId(id);
            }
            return returnedVehicle;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return returnedVehicle;
    }

    public static Vehicle retrieveFromEmail(String regNo) {

        String sql = "SELECT * FROM vehicles WHERE regNo = " + regNo;

        Vehicle returnedVehicle = null;
        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            int idNumber = queryResult.getInt("vehicles_id");
            String vehicleSubclass = queryResult.getString("vehicleSubclass");
            String manufacturer = queryResult.getString("manufacturer");
            String model = queryResult.getString("model");
            String engineType = queryResult.getString("engineType");
            String gearType = queryResult.getString("gearType");
            int modelYear = queryResult.getInt("modelYear");
            if (Objects.equals(vehicleSubclass, "car")) {
                int seatingCapacity = queryResult.getInt("seatingCapacity");
                int towingCapacity = queryResult.getInt("towingCapacity");
                returnedVehicle = new Car(regNo, manufacturer, model, engineType, gearType, modelYear, seatingCapacity, towingCapacity);
                returnedVehicle.setId(idNumber);
            }
            return returnedVehicle;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return returnedVehicle;
    }
}