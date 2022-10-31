package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.vehicle_types.Car;
import no.hiof.groupproject.models.vehicle_types.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

/*
A class used to serialise a Vehicle into a database for permanent storage.
 */
public class InsertVehicleDB {

    public static void insert(Vehicle vehicle) {


        String sql = "INSERT INTO vehicles (vehicleSubclass, regNo, manufacturer, model, engineType, gearType, modelYear, seatingCapacity, bedCapacity, towingCapacity, storageSpace, dimensions, helmetProvided, includesToilet, includesKitchen) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {
            str.setString(1, vehicle.getVehicleSubclass());
            str.setString(2, vehicle.getRegNo());
            str.setString(3, vehicle.getManufacturer());
            str.setString(4, vehicle.getModel());
            str.setString(5, vehicle.getEngineType());
            str.setString(6, vehicle.getGearType());
            str.setInt(7, vehicle.getModelYear());
            if (Objects.equals(vehicle.getVehicleSubclass(), "car")) {
                str.setInt(8, ((Car) vehicle).getSeatingCapacity());
                str.setInt(9, ((Car) vehicle).getTowingCapacity());
            }
            str.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}