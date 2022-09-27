package no.hiof.groupproject.models;

public abstract class Vehicle{
    // update the necessary instance variable needed
    private String vehicle_id;
    private int year_model;
    private String manufacturer;
    private String car_model;
    private int vehicleStatus; // availability , rented or not
    private fuel_type;
   // private ArrayList<RentalRecord> records= new ArrayList<>(); --> not sure where to place this,if under person class or Vehicle class
    private gear;
    private
    public Vehicle(String vehicle_id, int year, String manufacturer, String model, int vehicleStatus, VehicleType vehicleType) {
        Vehicle_id = vehicle_id;
        Year = year;
        Manufacturer = manufacturer;
        Model = model;
        this.vehicleStatus = vehicleStatus;
        this.vehicleType = vehicleType;
    }

    public String getVehicle_id() {
        return Vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        Vehicle_id = vehicle_id;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public int getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(int vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
