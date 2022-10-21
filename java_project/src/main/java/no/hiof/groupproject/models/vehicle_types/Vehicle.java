package no.hiof.groupproject.models.vehicle_types;

import no.hiof.groupproject.models.User;

public abstract class Vehicle {
    /* update the necessary instance variable needed */

    //auto-incremental id
    private static int count = 1;
    private int id;

    private String regNo, manufacturer;
    private String model, engineType, gearType;
    private int modelYear;

    public Vehicle(String regNumber, String manufacturer, String model, String engineType,
                   String gearType, int modelYear) {

        this.id = count;
        //increments the id by 1
        count++;

        this.regNo = regNumber;
        this.manufacturer = manufacturer;
        this.model = model;
        this.engineType = engineType;
        this.gearType = gearType;
        this.modelYear = modelYear;
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

    public void setGearbox(String gearType) {
        this.gearType = gearType;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

}
