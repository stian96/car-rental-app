package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.two_wheeled_vehicle.Moped;
import no.hiof.groupproject.models.two_wheeled_vehicle.Motorcycle;
import no.hiof.groupproject.models.vehicle_types.Camper;
import no.hiof.groupproject.models.vehicle_types.Car;
import no.hiof.groupproject.models.vehicle_types.Truck;
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


        String sql = "INSERT INTO vehicles (vehicleSubclass, regNo, manufacturer, model, engineType, gearType, " +
                "modelYear, seatingCapacity, bedCapacity, towingCapacity, storageSpace, dimensions, helmetProvided, " +
                "includesToilet, includesKitchen) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
                str.setInt(10, ((Car) vehicle).getTowingCapacity());
            } else if (Objects.equals(vehicle.getVehicleSubclass(), "truck")) {
                str.setInt(8, ((Truck) vehicle).getSeatingCapacity());
                str.setInt(10, ((Truck) vehicle).getTowingCapacity());
                str.setInt(11, ((Truck) vehicle).getStorageSpace());
                str.setInt(12, ((Truck) vehicle).getDimensions());
            } else if (Objects.equals(vehicle.getVehicleSubclass(), "camper")) {
                str.setInt(8, ((Camper) vehicle).getSeatingCapacity());
                str.setInt(10, ((Camper) vehicle).getTowingCapacity());
                str.setInt(12, ((Camper) vehicle).getDimensions());
                str.setInt(9, ((Camper) vehicle).getBedCapacity());
                //SQLite doesn't support boolean
                if (((Camper) vehicle).getIncludesToilet()) {
                    str.setInt(14, 1);
                } else {
                    str.setInt(14, 0);
                }
                if (((Camper) vehicle).getIncludesKitchen()) {
                    str.setInt(15, 1);
                } else {
                    str.setInt(15, 0);
                }
            } else if (Objects.equals(vehicle.getVehicleSubclass(), "moped")) {
                str.setString(13, ((Moped) vehicle).getHelmetProvided());
            }  else if (Objects.equals(vehicle.getVehicleSubclass(), "motorcycle")) {
            str.setString(13, ((Motorcycle) vehicle).getHelmetProvided());
            }

            str.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertWithPriorConnection(Vehicle vehicle, Connection conn) throws SQLException {


        String sql = "INSERT INTO vehicles (vehicleSubclass, regNo, manufacturer, model, engineType, gearType, " +
                "modelYear, seatingCapacity, bedCapacity, towingCapacity, storageSpace, dimensions, helmetProvided, " +
                "includesToilet, includesKitchen) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement str = conn.prepareStatement(sql);
        str.setString(1, vehicle.getVehicleSubclass());
        str.setString(2, vehicle.getRegNo());
        str.setString(3, vehicle.getManufacturer());
        str.setString(4, vehicle.getModel());
        str.setString(5, vehicle.getEngineType());
        str.setString(6, vehicle.getGearType());
        str.setInt(7, vehicle.getModelYear());
        if (Objects.equals(vehicle.getVehicleSubclass(), "car")) {
            str.setInt(8, ((Car) vehicle).getSeatingCapacity());
            str.setInt(10, ((Car) vehicle).getTowingCapacity());
        } else if (Objects.equals(vehicle.getVehicleSubclass(), "truck")) {
            str.setInt(8, ((Truck) vehicle).getSeatingCapacity());
            str.setInt(10, ((Truck) vehicle).getTowingCapacity());
            str.setInt(11, ((Truck) vehicle).getStorageSpace());
            str.setInt(12, ((Truck) vehicle).getDimensions());
        } else if (Objects.equals(vehicle.getVehicleSubclass(), "camper")) {
            str.setInt(8, ((Camper) vehicle).getSeatingCapacity());
            str.setInt(10, ((Camper) vehicle).getTowingCapacity());
            str.setInt(12, ((Camper) vehicle).getDimensions());
            str.setInt(9, ((Camper) vehicle).getBedCapacity());
            //SQLite doesn't support boolean
            if (((Camper) vehicle).getIncludesToilet()) {
                str.setInt(14, 1);
            } else {
                str.setInt(14, 0);
            }
            if (((Camper) vehicle).getIncludesKitchen()) {
                str.setInt(15, 1);
            } else {
                str.setInt(15, 0);
            }
        } else if (Objects.equals(vehicle.getVehicleSubclass(), "moped")) {
            str.setString(13, ((Moped) vehicle).getHelmetProvided());
        }  else if (Objects.equals(vehicle.getVehicleSubclass(), "motorcycle")) {
            str.setString(13, ((Motorcycle) vehicle).getHelmetProvided());
        }

        str.executeUpdate();

    }
}