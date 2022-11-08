package no.hiof.groupproject.tools.filters;

import no.hiof.groupproject.models.vehicle_types.Vehicle;
import no.hiof.groupproject.tools.db.ConnectDB;
import no.hiof.groupproject.tools.db.RetrieveVehicleDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

/*
Class designed to return ArrayList<Vehicle> from a supplied ArrayList<Vehicle> and a parameter
 */
public class FilterAdvertisement {


    //eg: FilterVehicle.filterEngineType(RetrieveVehiclesDB.retrieveAllVehiclesObject(), "petrol")
    public static ArrayList<Vehicle> filter(String gearType, String engineType, String manufacturer, String town,
                                            Integer dailyChargeMax, Integer chargePerTwentyKmMax, Integer rating) {

        String sql = "SELECT * FROM advertisements INNER JOIN vehicles ON vehicle_fk = vehicles_id " +
                "INNER JOIN userProfiles uP on advertisements.user_fk = uP.user_fk " +
                "WHERE advertisementSubclass = \'rentoutad\'";

        if (gearType != null) {
            sql = sql + " AND LOWER(gearType) = \'" + gearType.toLowerCase() + "\'";
        }
        if (engineType != null) {
            sql = sql + " AND LOWER(engineType) = \'" + engineType.toLowerCase() + "\'";
        }
        if (town != null) {
            sql = sql + " AND LOWER(manufacturer) = \'" + manufacturer.toLowerCase() + "\'";
        }
        if (town != null) {
            sql = sql + " AND LOWER(town) = \'" + town.toLowerCase() + "\'";
        }
        if (dailyChargeMax != null) {
            sql = sql + " AND dailyCharge < \'" + dailyChargeMax + "\'";
        }
        if (chargePerTwentyKmMax != null) {
            sql = sql + " AND chargePerTwentyKm < \'" + chargePerTwentyKmMax + "\'";
        }
        if (rating != null) {
            sql = sql + " AND averageRating > \'" + rating + "\'";
        }

        ArrayList<Vehicle> vehicles = new ArrayList<>();


        try (Connection conn = ConnectDB.connect();
             Statement str = conn.createStatement();
             ResultSet rs = str.executeQuery(sql)) {

            //loops through rows in the sql SELECT statement
            while (rs.next()) {
                int id = rs.getInt("vehicle_fk");
                Vehicle vehicle = RetrieveVehicleDB.retrieveFromId(id);
                vehicles.add(vehicle);
            }
            return vehicles;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return vehicles;
    }

}
