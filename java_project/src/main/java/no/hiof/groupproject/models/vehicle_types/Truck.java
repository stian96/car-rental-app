package no.hiof.groupproject.models.vehicle_types;

import no.hiof.groupproject.models.User;

public class Truck extends Vehicle {
    private int towingCapacity, storageSpace, seatingCapacity;
    private String measurement;


    public Truck(String ID, String regNumber, String manufacturer, String model, String fuelType,
                 String gearType, int modelYear, User owner, int towingCapacity,
                 int storageSpace, int seatingCapacity, String measurement) {

        super(ID, regNumber, manufacturer, model, fuelType, gearType, modelYear, owner);
        this.towingCapacity = towingCapacity;
        this.storageSpace = storageSpace;
        this.seatingCapacity = seatingCapacity;
        this.measurement = measurement;
    }

    public int getTowingCapacity() {
        return towingCapacity;
    }

    public void setTowingCapacity(int towingCapacity) {
        this.towingCapacity = towingCapacity;
    }

    public int getStorageSpace() {
        return storageSpace;
    }

    public void setStorageSpace(int storageSpace) {
        this.storageSpace = storageSpace;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }
}
