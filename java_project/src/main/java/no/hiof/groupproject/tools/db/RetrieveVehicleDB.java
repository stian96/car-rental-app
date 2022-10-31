package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.vehicle_types.Car;
import no.hiof.groupproject.models.vehicle_types.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
Returns a specific User in the database based on either the id or email of the User, both of which are unique values
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
            if (vehicleSubclass == "car") {
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

    public static User retrieveFromEmail(String email) {

        String sql = "SELECT * FROM users WHERE users_id = " + email;

        User returnedUser = null;
        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            int idNumber = queryResult.getInt("users_id");
            String firstName = queryResult.getString("firstName");
            String lastName = queryResult.getString("lastName");
            String postNr = queryResult.getString("postNr");
            String password = queryResult.getString("password");
            String bankAccountNr = queryResult.getString("bankAccountNr");
            String tlfNr = queryResult.getString("tlfNr");
            returnedUser = new User(firstName, lastName, postNr, password, bankAccountNr, email, tlfNr);
            returnedUser.setId(idNumber);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return returnedUser;
    }
}