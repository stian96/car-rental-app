package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.Booking;
import no.hiof.groupproject.models.two_wheeled_vehicle.Moped;
import no.hiof.groupproject.models.two_wheeled_vehicle.Motorcycle;
import no.hiof.groupproject.models.vehicle_types.Camper;
import no.hiof.groupproject.models.vehicle_types.Car;
import no.hiof.groupproject.models.vehicle_types.Truck;
import no.hiof.groupproject.models.vehicle_types.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

/*
Returns an ArrayList of all the Vehicles with RentOutAds associated with them
Two options: retrieve ArrayList<Integer> of all vehicle ids
             retrieve ArrayList<Vehicle> of all Vehicle objects
 */
public class RetrieveVehiclesDB {

    public static ArrayList<Integer> retrieveAllVehiclesId() {

        String sql = "SELECT * FROM advertisements WHERE advertisementSubclass = \'rentoutad\'";

        ArrayList<Integer> allVehicles = new ArrayList<>();

        try (Connection conn = ConnectDB.connect();
             Statement str = conn.createStatement();
             ResultSet rs = str.executeQuery(sql)) {

            //loops through rows in the sql SELECT statement
            while (rs.next()) {

                allVehicles.add(rs.getInt("vehicle_fk"));
            }
            return allVehicles;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allVehicles;
    }

    public static ArrayList<Vehicle> retrieveAllVehiclesObject() {

        String sql = "SELECT * FROM advertisements WHERE advertisementSubclass = \'rentoutad\'";

        ArrayList<Vehicle> allVehicles = new ArrayList<>();

        try (Connection conn = ConnectDB.connect();
             Statement str = conn.createStatement();
             ResultSet rs = str.executeQuery(sql)) {

            //loops through rows in the sql SELECT statement
            while (rs.next()) {
                int id = rs.getInt("vehicle_fk");
                Vehicle vehicle = RetrieveVehicleDB.retrieveFromId(id);
                allVehicles.add(vehicle);
            }
            return allVehicles;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allVehicles;
    }

}