package no.hiof.groupproject.models;

public abstract class Vehicle {
    /* update the necessary instance variable needed */
    private String vehicle_id, regNo, manufacturer;
    private String model, engineType, gearType;
    private int modelYear, price;
    private User owner;

    public Vehicle(String ID, String regNumber, String manufacturer, String model, String engineType,
                   String gearType, int modelYear, int price, User owner) {

        this.vehicle_id = ID;
        this.regNo = regNumber;
        this.manufacturer = manufacturer;
        this.model = model;
        this.engineType = engineType;
        this.gearType = gearType;
        this.modelYear = modelYear;
        this.price = price;
        this.owner = owner;
    }

    public String getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

}
