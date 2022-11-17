package no.hiof.groupproject.tools.filters;

import no.hiof.groupproject.models.Advertisement;
import no.hiof.groupproject.models.vehicle_types.Vehicle;
import no.hiof.groupproject.tools.db.ConnectDB;
import no.hiof.groupproject.tools.db.RetrieveAdvertisementDB;
import no.hiof.groupproject.tools.db.RetrieveVehicleDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
Class designed to return an ArrayList of Vehicles, vehicle id, Advertisements, or advertisement id from
arguments supplied .
The results are automatically sorted by ratings descending
To sort by only one argument make sure that the rest of the arguments are null
To limit the amount of results, supply an Integer as the final argument
example for all cars that are automatic in halden where the owner has an average rating of at least 4:
    FilterAdvertisement.filterToArrayListVehicle("automatic", null, null, "halden", null, null, null, null, 4, null)

NOTE: it is MUCH faster to retrieve just an Integer of, for example, advertisement id, rather than an ArrayList
full of instantiated Advertisements - this is because RetrieveAdvertisementDB.retrieveFromId() isn't called
 */
public class FilterAdvertisement {

    //Statement used to filter out results
    private static String getSQLStatement(String gearType, String engineType, String manufacturer, String town,
                                          Integer dailyChargeMax, Integer chargePerTwentyKmMax, Integer yearMin,
                                          Integer seatingMin, Integer rating, Integer amountOfResults) {

        String sql = "SELECT * FROM advertisements INNER JOIN vehicles ON vehicle_fk = vehicles_id " +
                "INNER JOIN userProfiles uP on advertisements.user_fk = uP.user_fk " +
                "WHERE advertisementSubclass = \'rentoutad\'";

        if (gearType != null) {
            sql = sql + " AND LOWER(gearType) = \'" + gearType.toLowerCase() + "\'";
        }
        if (engineType != null) {
            sql = sql + " AND LOWER(engineType) = \'" + engineType.toLowerCase() + "\'";
        }
        if (manufacturer != null) {
            sql = sql + " AND LOWER(manufacturer) = \'" + manufacturer.toLowerCase() + "\'";
        }
        if (town != null) {
            sql = sql + " AND LOWER(town) = \'" + town.toLowerCase() + "\'";
        }
        if (dailyChargeMax != null) {
            sql = sql + " AND dailyCharge <= \'" + dailyChargeMax + "\'";
        }
        if (chargePerTwentyKmMax != null) {
            sql = sql + " AND chargePerTwentyKm <= \'" + chargePerTwentyKmMax + "\'";
        }
        if (yearMin != null) {
            sql = sql + " AND modelYear >= \'" + yearMin + "\'";
        }
        if (seatingMin != null) {
            sql = sql + " AND seatingCapacity >= \'" + seatingMin + "\'";
        }
        if (rating != null) {
            //there was a bug where rating can be a text value of 'null' after serialising and deserialising
            // 'null' is different to <null>
            sql = sql + " AND averageRating IS NOT NULL AND averageRating != 'null' " +
                    "AND averageRating >= \'" + rating + "\'";
        }

        sql = sql + " ORDER BY averageRating DESC";

        if (amountOfResults != null) {
            sql = sql + " LIMIT " + amountOfResults;
        }
        return sql;
    }

    //eg: FilterAdvertisement.filterToArrayListVehicle("automatic", null, null, "halden", null, null, null, null, 4, 5)
    public static ArrayList<Vehicle> filterToArrayListVehicle(String gearType, String engineType, String manufacturer,
                                                              String town, Integer dailyChargeMax,
                                                              Integer chargePerTwentyKmMax,
                                                              Integer yearMin, Integer seatingMin, Integer rating,
                                                              Integer amountOfResults) {

        String sql = getSQLStatement(gearType, engineType, manufacturer, town, dailyChargeMax, chargePerTwentyKmMax,
                yearMin, seatingMin, rating, amountOfResults);

        ArrayList<Vehicle> vehicles = new ArrayList<>();


        try (Connection conn = ConnectDB.connectReadOnly();
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

    public static ArrayList<Integer> filterToArrayListVehicleId(String gearType, String engineType, String manufacturer,
                                                                String town, Integer dailyChargeMax,
                                                                Integer chargePerTwentyKmMax,
                                                                Integer yearMin, Integer seatingMin, Integer rating,
                                                                Integer amountOfResults) {

        String sql = getSQLStatement(gearType, engineType, manufacturer, town, dailyChargeMax, chargePerTwentyKmMax,
                yearMin, seatingMin, rating, amountOfResults);

        ArrayList<Integer> vehicles = new ArrayList<>();


        try (Connection conn = ConnectDB.connectReadOnly();
             Statement str = conn.createStatement();
             ResultSet rs = str.executeQuery(sql)) {

            //loops through rows in the sql SELECT statement
            while (rs.next()) {
                int id = rs.getInt("vehicle_fk");
                vehicles.add(id);
            }
            return vehicles;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return vehicles;
    }

    public static ArrayList<Advertisement> filterToArrayListAdvertisement(String gearType, String engineType,
                                                                          String manufacturer,String town,
                                                                          Integer dailyChargeMax,
                                                                          Integer chargePerTwentyKmMax,
                                                                          Integer yearMin, Integer seatingMin,
                                                                          Integer rating,
                                                                          Integer amountOfResults) {

        String sql = getSQLStatement(gearType, engineType, manufacturer, town, dailyChargeMax, chargePerTwentyKmMax,
                yearMin, seatingMin, rating, amountOfResults);

        ArrayList<Advertisement> advertisements = new ArrayList<>();


        try (Connection conn = ConnectDB.connectReadOnly();
             Statement str = conn.createStatement();
             ResultSet rs = str.executeQuery(sql)) {

            //loops through rows in the sql SELECT statement
            while (rs.next()) {
                int id = rs.getInt("advertisements_id");
                Advertisement advertisement = RetrieveAdvertisementDB.retrieveFromId(id);
                advertisements.add(advertisement);
            }
            return advertisements;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return advertisements;
    }

    public static ArrayList<Integer> filterToArrayListAdvertisementId(String gearType, String engineType,
                                                                      String manufacturer,String town,
                                                                      Integer dailyChargeMax,
                                                                      Integer chargePerTwentyKmMax,
                                                                      Integer yearMin, Integer seatingMin,
                                                                      Integer rating,
                                                                      Integer amountOfResults) {

        String sql = getSQLStatement(gearType, engineType, manufacturer, town, dailyChargeMax, chargePerTwentyKmMax,
                yearMin, seatingMin, rating, amountOfResults);

        ArrayList<Integer> advertisements = new ArrayList<>();


        try (Connection conn = ConnectDB.connectReadOnly();
             Statement str = conn.createStatement();
             ResultSet rs = str.executeQuery(sql)) {

            //loops through rows in the sql SELECT statement
            while (rs.next()) {
                int id = rs.getInt("advertisements_id");
                advertisements.add(id);
            }
            return advertisements;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return advertisements;
    }

}
