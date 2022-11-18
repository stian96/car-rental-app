package no.hiof.groupproject.models.vehicles.four_wheeled_vehicles;

import no.hiof.groupproject.models.vehicles.Vehicle;

public class Truck extends Vehicle {
    private int towingCapacity, storageSpace, seatingCapacity, dimensions;


    public Truck(String regNumber, String manufacturer, String model, String fuelType,
                 String gearType, int modelYear, int towingCapacity,
                 int storageSpace, int seatingCapacity, int dimensions) {

        super(regNumber, manufacturer, model, fuelType, gearType, modelYear);
        this.towingCapacity = towingCapacity;
        this.storageSpace = storageSpace;
        this.seatingCapacity = seatingCapacity;
        this.dimensions = dimensions;
        super.setVehicleSubclass("truck");

        if (!existsInDb()) {
            serialise();
        }
        //the id is automatically incremented when inserted into the database
        //the autoincrement id is fetched and assigned to this instance
        this.setId(getAutoIncrementId());
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

    public int getDimensions() {
        return dimensions;
    }

    public void setDimensions(int dimensions) {
        this.dimensions = dimensions;
    }
}
