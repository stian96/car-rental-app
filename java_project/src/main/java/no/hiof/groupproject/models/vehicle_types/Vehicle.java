package no.hiof.groupproject.models.vehicle_types;

import no.hiof.groupproject.interfaces.ExistsInDb;
import no.hiof.groupproject.interfaces.GetAutoIncrementId;
import no.hiof.groupproject.interfaces.Serialise;
import no.hiof.groupproject.tools.db.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Vehicle implements Serialise, GetAutoIncrementId, ExistsInDb {
    /* update the necessary instance variable needed */

    //auto-incremental id
    //private static int count = 1;
    private int id;

    private String regNo, manufacturer;
    private String model, engineType, gearType;
    private int modelYear;
    private String vehicleSubclass;

    public Vehicle(String regNumber, String manufacturer, String model, String engineType,
                   String gearType, int modelYear) {

        //this.id = count;
        //increments the id by 1
        //count++;

        this.regNo = regNumber;
        this.manufacturer = manufacturer;
        this.model = model;
        this.engineType = engineType;
        this.gearType = gearType;
        this.modelYear = modelYear;
    }

    //serialises the Vehicle class and inserts the values into the database
    @Override
    public void serialise() {
        InsertVehicleDB.insert(this);
    }

    //returns a copy of the current instance of the Vehicle class based on this.id
    //NOT CURRENTLY IN USE, but the logic behind the code may be useful later on
    public Vehicle deserialise() {
        return RetrieveVehicleDB.retrieveFromId(this.id);
    }

    //used in conjunction with an autoincremented vehicles_id value in the database
    @Override
    public int getAutoIncrementId() {
        String sql = "SELECT * FROM vehicles WHERE regNo = " + this.regNo;

        int i = 0;
        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            i = queryResult.getInt("vehicles_id");
            return i;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i;
    }

    //used to check if the regNo is stored in the database already. Returns true if the regNo is present
    @Override
    public boolean existsInDb() {
        String sql = "SELECT COUNT(*) AS amount FROM vehicles WHERE regNo = " + this.regNo;

        boolean ans = false;
        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();
            if (queryResult.getInt("amount") > 0) {
                ans = true;
            }
            return ans;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void removeVehicleAndCascade() {
        RemoveVehicleDB.remove(this.getId());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getGearType() {
        return gearType;
    }

    public void setGearType(String gearType) {
        this.gearType = gearType;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public String getVehicleSubclass() {
        return vehicleSubclass;
    }

    public void setVehicleSubclass(String vehicleSubclass) {
        this.vehicleSubclass = vehicleSubclass;
    }
}
