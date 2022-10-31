package no.hiof.groupproject.models.vehicle_types;

public class Car extends Vehicle {
    private int seatingCapacity;
    private int towingCapacity;

    public Car(String regNumber, String manufacturer, String model,
               String fuelType, String gearType, int modelYear,
               int seatingCapacity, int towingCapacity) {

        super(regNumber, manufacturer, model, fuelType, gearType, modelYear);
        this.seatingCapacity = seatingCapacity;
        this.towingCapacity = towingCapacity;

        super.setVehicleSubclass("car");

        if (!existsInDb()) {
            serialise();
        }
        //the id is automatically incremented when inserted into the database
        //the autoincrement id is fetched and assigned to this instance
        this.setId(getAutoIncrementId());

    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public int getTowingCapacity() {
        return towingCapacity;
    }

    public void setTowingCapacity(int towingCapacity) {
        this.towingCapacity = towingCapacity;
    }
}
