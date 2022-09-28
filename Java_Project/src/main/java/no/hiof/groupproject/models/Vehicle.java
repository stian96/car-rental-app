package no.hiof.groupproject.models;

public abstract class Vehicle {
    /* update the necessary instance variable needed */
    private String vehicle_id, regNo, manufacturer, model, fuel_type, gearbox;
    private int modelYear, price;
    private Owner owner; //
    private int vehicleStatus; // availability , rented or not

    // private ArrayList<RentalRecord> records= new ArrayList<>(); --> not sure where to place this,if under person class or Vehicle class


}